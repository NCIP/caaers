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
      insert('organizations', [ id: 102998, nci_institute_code: "GA148", name: "PharmData, Incorporate", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA148",GROUP_DESC:"GA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4013 ,protection_group_id: -3013, protection_element_id:-3013], primaryKey: false);
      insert('organizations', [ id: 102999, nci_institute_code: "GA149", name: "Harbin Clinic", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA149",GROUP_DESC:"GA149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4014 ,protection_group_id: -3014, protection_element_id:-3014], primaryKey: false);
      insert('organizations', [ id: 103000, nci_institute_code: "GA150", name: "Columbus Surgical Associates PC", city: "Columbus", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA150",GROUP_DESC:"GA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4015 ,protection_group_id: -3015, protection_element_id:-3015], primaryKey: false);
      insert('organizations', [ id: 103001, nci_institute_code: "GA151", name: "Albany Surgical, P.C.", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA151",GROUP_DESC:"GA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4016 ,protection_group_id: -3016, protection_element_id:-3016], primaryKey: false);
      insert('organizations', [ id: 103002, nci_institute_code: "GA153", name: "The Emory Clinic Inc", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA153",GROUP_DESC:"GA153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4017 ,protection_group_id: -3017, protection_element_id:-3017], primaryKey: false);
      insert('organizations', [ id: 103003, nci_institute_code: "GA154", name: "AFLAC Cancer Center & Blood Disorders Service - Meridian", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA154",GROUP_DESC:"GA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4018 ,protection_group_id: -3018, protection_element_id:-3018], primaryKey: false);
      insert('organizations', [ id: 103004, nci_institute_code: "GA155", name: "Gwinnett County Radiation Therapy Center - Lawrenceville", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA155",GROUP_DESC:"GA155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4019 ,protection_group_id: -3019, protection_element_id:-3019], primaryKey: false);
      insert('organizations', [ id: 103005, nci_institute_code: "GA156", name: "Gwinnett County Radiation Therapy Center - Snellville", city: "Snellville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA156",GROUP_DESC:"GA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4020 ,protection_group_id: -3020, protection_element_id:-3020], primaryKey: false);
      insert('organizations', [ id: 103006, nci_institute_code: "GA157", name: "Cancer Center of Gwinnett", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA157",GROUP_DESC:"GA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4021 ,protection_group_id: -3021, protection_element_id:-3021], primaryKey: false);
      insert('organizations', [ id: 103007, nci_institute_code: "GA158", name: "Institute For Radiation Therapy", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA158",GROUP_DESC:"GA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4022 ,protection_group_id: -3022, protection_element_id:-3022], primaryKey: false);
      insert('organizations', [ id: 103008, nci_institute_code: "GA159", name: "Resurgens Orthopaedics", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA159",GROUP_DESC:"GA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4023 ,protection_group_id: -3023, protection_element_id:-3023], primaryKey: false);
      insert('organizations', [ id: 103009, nci_institute_code: "GA160", name: "Griffin Regional Radiation Therapy Center", city: "Griffin", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA160",GROUP_DESC:"GA160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4024 ,protection_group_id: -3024, protection_element_id:-3024], primaryKey: false);
      insert('organizations', [ id: 103010, nci_institute_code: "GA161", name: "Georgia Colon & Rectal Surgical Associates PC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA161",GROUP_DESC:"GA161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4025 ,protection_group_id: -3025, protection_element_id:-3025], primaryKey: false);
      insert('organizations', [ id: 103011, nci_institute_code: "GA162", name: "Vascular Disease Institute & Obesity Solutions", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA162",GROUP_DESC:"GA162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4026 ,protection_group_id: -3026, protection_element_id:-3026], primaryKey: false);
      insert('organizations', [ id: 103012, nci_institute_code: "GA163", name: "Urology Associates PA", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA163",GROUP_DESC:"GA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4027 ,protection_group_id: -3027, protection_element_id:-3027], primaryKey: false);
      insert('organizations', [ id: 103013, nci_institute_code: "GA164", name: "Breast Care Specialists LLC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA164",GROUP_DESC:"GA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4028 ,protection_group_id: -3028, protection_element_id:-3028], primaryKey: false);
      insert('organizations', [ id: 103014, nci_institute_code: "GA165", name: "Atlanta Cancer Care", city: "Roswell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA165",GROUP_DESC:"GA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4029 ,protection_group_id: -3029, protection_element_id:-3029], primaryKey: false);
      insert('organizations', [ id: 103015, nci_institute_code: "GA166", name: "Piedmont Urology PC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA166",GROUP_DESC:"GA166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4030 ,protection_group_id: -3030, protection_element_id:-3030], primaryKey: false);
      insert('organizations', [ id: 103016, nci_institute_code: "GA167", name: "Piedmont Gynecologic Oncology", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA167",GROUP_DESC:"GA167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4031 ,protection_group_id: -3031, protection_element_id:-3031], primaryKey: false);
      insert('organizations', [ id: 103017, nci_institute_code: "GA168", name: "Northlake Cancer Treatment Center", city: "Tucker", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA168",GROUP_DESC:"GA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4032 ,protection_group_id: -3032, protection_element_id:-3032], primaryKey: false);
      insert('organizations', [ id: 103018, nci_institute_code: "GA169", name: "Nabors Hader & Sanders MD", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA169",GROUP_DESC:"GA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4033 ,protection_group_id: -3033, protection_element_id:-3033], primaryKey: false);
      insert('organizations', [ id: 103019, nci_institute_code: "GA170", name: "AFLAC Cancer Center & Blood Disorders Service", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA170",GROUP_DESC:"GA170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4034 ,protection_group_id: -3034, protection_element_id:-3034], primaryKey: false);
      insert('organizations', [ id: 103020, nci_institute_code: "GA171", name: "Suburban Hematology Oncology Associates PA", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA171",GROUP_DESC:"GA171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4035 ,protection_group_id: -3035, protection_element_id:-3035], primaryKey: false);
      insert('organizations', [ id: 103021, nci_institute_code: "GA172", name: "Georgia Cancer Specialists-Decatur", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA172",GROUP_DESC:"GA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4036 ,protection_group_id: -3036, protection_element_id:-3036], primaryKey: false);
      insert('organizations', [ id: 103022, nci_institute_code: "GA173", name: "Georgia Cancer Specialists-Stockbridge", city: "Stockbridge", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA173",GROUP_DESC:"GA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4037 ,protection_group_id: -3037, protection_element_id:-3037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 103023, nci_institute_code: "GA174", name: "Dekalb Surgical Associates", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA174",GROUP_DESC:"GA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4038 ,protection_group_id: -3038, protection_element_id:-3038], primaryKey: false);
      insert('organizations', [ id: 103024, nci_institute_code: "GA175", name: "Surgical Associates", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA175",GROUP_DESC:"GA175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4039 ,protection_group_id: -3039, protection_element_id:-3039], primaryKey: false);
      insert('organizations', [ id: 103025, nci_institute_code: "GA176", name: "Atlanta Cancer Care-Decatur", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA176",GROUP_DESC:"GA176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4040 ,protection_group_id: -3040, protection_element_id:-3040], primaryKey: false);
      insert('organizations', [ id: 103026, nci_institute_code: "GA177", name: "Newnan Radiation Center", city: "Newnan", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA177",GROUP_DESC:"GA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4041 ,protection_group_id: -3041, protection_element_id:-3041], primaryKey: false);
      insert('organizations', [ id: 103027, nci_institute_code: "GA178", name: "Georgia Thoracic and Cardiovascular Surgical Associates", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA178",GROUP_DESC:"GA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4042 ,protection_group_id: -3042, protection_element_id:-3042], primaryKey: false);
      insert('organizations', [ id: 103028, nci_institute_code: "GA179", name: "Southeastern Gynecologic Oncology LLC", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA179",GROUP_DESC:"GA179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4043 ,protection_group_id: -3043, protection_element_id:-3043], primaryKey: false);
      insert('organizations', [ id: 103029, nci_institute_code: "GA180", name: "Southeast Gyncology and Oncology", city: "Tucker", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA180",GROUP_DESC:"GA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4044 ,protection_group_id: -3044, protection_element_id:-3044], primaryKey: false);
      insert('organizations', [ id: 103030, nci_institute_code: "GA181", name: "Georgia Breast Care", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA181",GROUP_DESC:"GA181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4045 ,protection_group_id: -3045, protection_element_id:-3045], primaryKey: false);
      insert('organizations', [ id: 103031, nci_institute_code: "GA182", name: "Doctors Hospital Radiation Oncology", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA182",GROUP_DESC:"GA182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4046 ,protection_group_id: -3046, protection_element_id:-3046], primaryKey: false);
      insert('organizations', [ id: 103032, nci_institute_code: "GA183", name: "Harbin Clinic - Clinical Research Office", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA183",GROUP_DESC:"GA183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4047 ,protection_group_id: -3047, protection_element_id:-3047], primaryKey: false);
      insert('organizations', [ id: 103033, nci_institute_code: "GA184", name: "Georgia Radiation Therapy Center", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA184",GROUP_DESC:"GA184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4048 ,protection_group_id: -3048, protection_element_id:-3048], primaryKey: false);
      insert('organizations', [ id: 103034, nci_institute_code: "GA185", name: "Radiation Oncology Associates PC", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA185",GROUP_DESC:"GA185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4049 ,protection_group_id: -3049, protection_element_id:-3049], primaryKey: false);
      insert('organizations', [ id: 103035, nci_institute_code: "GA186", name: "Peachtree Cardiovascular and Thoracic Surgeons PA - Gainesville", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA186",GROUP_DESC:"GA186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4050 ,protection_group_id: -3050, protection_element_id:-3050], primaryKey: false);
      insert('organizations', [ id: 103036, nci_institute_code: "GA187", name: "Summit Cancer Care", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA187",GROUP_DESC:"GA187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4051 ,protection_group_id: -3051, protection_element_id:-3051], primaryKey: false);
      insert('organizations', [ id: 103037, nci_institute_code: "GA188", name: "Peachtree Hematology and Oncology Consultants PC", city: "Newman", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA188",GROUP_DESC:"GA188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4052 ,protection_group_id: -3052, protection_element_id:-3052], primaryKey: false);
      insert('organizations', [ id: 103038, nci_institute_code: "GA189", name: "Savannah Colon & Rectal Surgery Inc", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA189",GROUP_DESC:"GA189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4053 ,protection_group_id: -3053, protection_element_id:-3053], primaryKey: false);
      insert('organizations', [ id: 103039, nci_institute_code: "GA190", name: "Medical Oncology Associates", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA190",GROUP_DESC:"GA190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4054 ,protection_group_id: -3054, protection_element_id:-3054], primaryKey: false);
      insert('organizations', [ id: 103040, nci_institute_code: "GA191", name: "Nancy N and JC Lewis Cancer and Research Pavilion at Saint Joseph's Candler", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA191",GROUP_DESC:"GA191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4055 ,protection_group_id: -3055, protection_element_id:-3055], primaryKey: false);
      insert('organizations', [ id: 103041, nci_institute_code: "GA192", name: "Northwest Georgia Oncology Center PC", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA192",GROUP_DESC:"GA192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4056 ,protection_group_id: -3056, protection_element_id:-3056], primaryKey: false);
      insert('organizations', [ id: 103042, nci_institute_code: "GA193", name: "Cardiovascular Surgical Associates", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA193",GROUP_DESC:"GA193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4057 ,protection_group_id: -3057, protection_element_id:-3057], primaryKey: false);
      insert('organizations', [ id: 103043, nci_institute_code: "GA194", name: "Low Country Cancer Care Associates PC", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA194",GROUP_DESC:"GA194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4058 ,protection_group_id: -3058, protection_element_id:-3058], primaryKey: false);
      insert('organizations', [ id: 103044, nci_institute_code: "GA195", name: "North Georgia Radiation Therapy", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA195",GROUP_DESC:"GA195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4059 ,protection_group_id: -3059, protection_element_id:-3059], primaryKey: false);
      insert('organizations', [ id: 103045, nci_institute_code: "GA196", name: "Georgia Center for Oncology Research and Education", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA196",GROUP_DESC:"GA196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4060 ,protection_group_id: -3060, protection_element_id:-3060], primaryKey: false);
      insert('organizations', [ id: 103046, nci_institute_code: "GA197", name: "Harbin Clinic Radiation Oncology", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA197",GROUP_DESC:"GA197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4061 ,protection_group_id: -3061, protection_element_id:-3061], primaryKey: false);
      insert('organizations', [ id: 103047, nci_institute_code: "GA198", name: "Harbin Clinic Medical Oncology", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA198",GROUP_DESC:"GA198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4062 ,protection_group_id: -3062, protection_element_id:-3062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 103048, nci_institute_code: "GA199", name: "Newton County Radiation Therapy Center", city: "Covington", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA199",GROUP_DESC:"GA199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4063 ,protection_group_id: -3063, protection_element_id:-3063], primaryKey: false);
      insert('organizations', [ id: 103049, nci_institute_code: "GA200", name: "Chris Malone MD PC", city: "Athens", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA200",GROUP_DESC:"GA200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4064 ,protection_group_id: -3064, protection_element_id:-3064], primaryKey: false);
      insert('organizations', [ id: 103050, nci_institute_code: "GA201", name: "Georgia Gastroenterology Group PC", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA201",GROUP_DESC:"GA201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4065 ,protection_group_id: -3065, protection_element_id:-3065], primaryKey: false);
      insert('organizations', [ id: 103051, nci_institute_code: "GA202", name: "Surgical Associates of Albany PC", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA202",GROUP_DESC:"GA202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4066 ,protection_group_id: -3066, protection_element_id:-3066], primaryKey: false);
      insert('organizations', [ id: 103052, nci_institute_code: "GA203", name: "Toccoa Cancer Center", city: "Toccoa", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA203",GROUP_DESC:"GA203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4067 ,protection_group_id: -3067, protection_element_id:-3067], primaryKey: false);
      insert('organizations', [ id: 103053, nci_institute_code: "GA204", name: "Georgia Cancer Specialists- Cumming", city: "Cumming", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA204",GROUP_DESC:"GA204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4068 ,protection_group_id: -3068, protection_element_id:-3068], primaryKey: false);
      insert('organizations', [ id: 103054, nci_institute_code: "GA205", name: "Northwest Georgia Oncology Centers PC - Douglasville", city: "Douglasville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA205",GROUP_DESC:"GA205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4069 ,protection_group_id: -3069, protection_element_id:-3069], primaryKey: false);
      insert('organizations', [ id: 103055, nci_institute_code: "GA206", name: "Georgia Cancer Specialists PC - Lithonia", city: "Lithonia", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA206",GROUP_DESC:"GA206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4070 ,protection_group_id: -3070, protection_element_id:-3070], primaryKey: false);
      insert('organizations', [ id: 103056, nci_institute_code: "GA207", name: "Georgia Cancer Specialists - Tucker", city: "Tucker", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA207",GROUP_DESC:"GA207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4071 ,protection_group_id: -3071, protection_element_id:-3071], primaryKey: false);
      insert('organizations', [ id: 103057, nci_institute_code: "GA208", name: "Southeastern Gynecologic Oncology LLC-Gainsville", city: "Gainsville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA208",GROUP_DESC:"GA208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4072 ,protection_group_id: -3072, protection_element_id:-3072], primaryKey: false);
      insert('organizations', [ id: 103058, nci_institute_code: "GA209", name: "Northwest Georgia Oncology Centers PC", city: "Canton", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA209",GROUP_DESC:"GA209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4073 ,protection_group_id: -3073, protection_element_id:-3073], primaryKey: false);
      insert('organizations', [ id: 103059, nci_institute_code: "HGRC", name: "Human Genome Research Center", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HGRC",GROUP_DESC:"HGRC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HGRC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HGRC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HGRC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4074 ,protection_group_id: -3074, protection_element_id:-3074], primaryKey: false);
      insert('organizations', [ id: 103060, nci_institute_code: "HI001", name: "Kalaheo Clinic", city: "Kalaheo", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI001",GROUP_DESC:"HI001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4075 ,protection_group_id: -3075, protection_element_id:-3075], primaryKey: false);
      insert('organizations', [ id: 103061, nci_institute_code: "HI002", name: "Kauai Veterans Memorial Hospital", city: "Waimea, Kauai", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI002",GROUP_DESC:"HI002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4076 ,protection_group_id: -3076, protection_element_id:-3076], primaryKey: false);
      insert('organizations', [ id: 103062, nci_institute_code: "HI003", name: "Waimea Clinic, Inc.", city: "Waimea", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI003",GROUP_DESC:"HI003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4077 ,protection_group_id: -3077, protection_element_id:-3077], primaryKey: false);
      insert('organizations', [ id: 103063, nci_institute_code: "HI004", name: "University of Hawaii", city: "Honolula", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI004",GROUP_DESC:"HI004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4078 ,protection_group_id: -3078, protection_element_id:-3078], primaryKey: false);
      insert('organizations', [ id: 103064, nci_institute_code: "HI005", name: "Queen's Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI005",GROUP_DESC:"HI005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4079 ,protection_group_id: -3079, protection_element_id:-3079], primaryKey: false);
      insert('organizations', [ id: 103065, nci_institute_code: "HI006", name: "Straub Clinic and Hospital", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI006",GROUP_DESC:"HI006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4080 ,protection_group_id: -3080, protection_element_id:-3080], primaryKey: false);
      insert('organizations', [ id: 103066, nci_institute_code: "HI007", name: "Kaiser Permanente Moanalua Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI007",GROUP_DESC:"HI007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4081 ,protection_group_id: -3081, protection_element_id:-3081], primaryKey: false);
      insert('organizations', [ id: 103067, nci_institute_code: "HI008", name: "Saint Francis Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI008",GROUP_DESC:"HI008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4082 ,protection_group_id: -3082, protection_element_id:-3082], primaryKey: false);
      insert('organizations', [ id: 103068, nci_institute_code: "HI009", name: "Kuakini Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI009",GROUP_DESC:"HI009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4083 ,protection_group_id: -3083, protection_element_id:-3083], primaryKey: false);
      insert('organizations', [ id: 103069, nci_institute_code: "HI010", name: "Tripler Army Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI010",GROUP_DESC:"HI010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4084 ,protection_group_id: -3084, protection_element_id:-3084], primaryKey: false);
      insert('organizations', [ id: 103070, nci_institute_code: "HI011", name: "Castle Medical Center", city: "Kailua", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI011",GROUP_DESC:"HI011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4085 ,protection_group_id: -3085, protection_element_id:-3085], primaryKey: false);
      insert('organizations', [ id: 103071, nci_institute_code: "HI012", name: "Kapiolani Medical Center", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI012",GROUP_DESC:"HI012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4086 ,protection_group_id: -3086, protection_element_id:-3086], primaryKey: false);
      insert('organizations', [ id: 103072, nci_institute_code: "HI013", name: "Hawaii Minority Based CCOP", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI013",GROUP_DESC:"HI013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4087 ,protection_group_id: -3087, protection_element_id:-3087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 103073, nci_institute_code: "HI014", name: "Kauai Medical Clinic", city: "Lihue", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI014",GROUP_DESC:"HI014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4088 ,protection_group_id: -3088, protection_element_id:-3088], primaryKey: false);
      insert('organizations', [ id: 103074, nci_institute_code: "HI015", name: "Hawaii Medical Association", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI015",GROUP_DESC:"HI015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4089 ,protection_group_id: -3089, protection_element_id:-3089], primaryKey: false);
      insert('organizations', [ id: 103075, nci_institute_code: "HI016", name: "University of Hawaii at Manoa", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI016",GROUP_DESC:"HI016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4090 ,protection_group_id: -3090, protection_element_id:-3090], primaryKey: false);
      insert('organizations', [ id: 103076, nci_institute_code: "HI017", name: "Kapiolani Medical Center at Pali Momi", city: "Aiea", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI017",GROUP_DESC:"HI017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4091 ,protection_group_id: -3091, protection_element_id:-3091], primaryKey: false);
      insert('organizations', [ id: 103077, nci_institute_code: "HI018", name: "Hilo Medical Center", city: "Hilo", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI018",GROUP_DESC:"HI018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4092 ,protection_group_id: -3092, protection_element_id:-3092], primaryKey: false);
      insert('organizations', [ id: 103078, nci_institute_code: "HI019", name: "Cancer Center of Hawaii/Hawaii AIDS Clinical Research Program", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI019",GROUP_DESC:"HI019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4093 ,protection_group_id: -3093, protection_element_id:-3093], primaryKey: false);
      insert('organizations', [ id: 103079, nci_institute_code: "HI020", name: "Hawaii Pacific Oncology Center", city: "Hilo", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI020",GROUP_DESC:"HI020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4094 ,protection_group_id: -3094, protection_element_id:-3094], primaryKey: false);
      insert('organizations', [ id: 103080, nci_institute_code: "HI021", name: "Wahiawa Hospital", city: "Wahiawa", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI021",GROUP_DESC:"HI021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4095 ,protection_group_id: -3095, protection_element_id:-3095], primaryKey: false);
      insert('organizations', [ id: 103081, nci_institute_code: "HI022", name: "Oncare Hawaii, Inc. (POB II)", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI022",GROUP_DESC:"HI022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4096 ,protection_group_id: -3096, protection_element_id:-3096], primaryKey: false);
      insert('organizations', [ id: 103082, nci_institute_code: "HI023", name: "Oncare Hawaii, Inc. (Kuakini)", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI023",GROUP_DESC:"HI023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4097 ,protection_group_id: -3097, protection_element_id:-3097], primaryKey: false);
      insert('organizations', [ id: 103083, nci_institute_code: "HI024", name: "Pacific Cancer Institute of Maui", city: "Wailuku, Maui", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI024",GROUP_DESC:"HI024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4098 ,protection_group_id: -3098, protection_element_id:-3098], primaryKey: false);
      insert('organizations', [ id: 103084, nci_institute_code: "HI025", name: "Maui Memorial Medical Center", city: "Wailuku", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI025",GROUP_DESC:"HI025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4099 ,protection_group_id: -3099, protection_element_id:-3099], primaryKey: false);
      insert('organizations', [ id: 103085, nci_institute_code: "HI026", name: "Oncare Hawaii, Inc. Mililani Clinic", city: "Mililani", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI026",GROUP_DESC:"HI026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4100 ,protection_group_id: -3100, protection_element_id:-3100], primaryKey: false);
      insert('organizations', [ id: 103086, nci_institute_code: "HI027", name: "Oncare Hawaii, Inc. - Kapiolani Medical Center at Pali Momi", city: "Aiea", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI027",GROUP_DESC:"HI027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4101 ,protection_group_id: -3101, protection_element_id:-3101], primaryKey: false);
      insert('organizations', [ id: 103087, nci_institute_code: "HI028", name: "Saint Francis West", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI028",GROUP_DESC:"HI028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4102 ,protection_group_id: -3102, protection_element_id:-3102], primaryKey: false);
      insert('organizations', [ id: 103088, nci_institute_code: "HI029", name: "Honolulu Medical Group", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI029",GROUP_DESC:"HI029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4103 ,protection_group_id: -3103, protection_element_id:-3103], primaryKey: false);
      insert('organizations', [ id: 103089, nci_institute_code: "HI030", name: "Leeward Radiation Oncology Center", city: "Ewa Beach", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI030",GROUP_DESC:"HI030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4104 ,protection_group_id: -3104, protection_element_id:-3104], primaryKey: false);
      insert('organizations', [ id: 103090, nci_institute_code: "HI031", name: "Hematology Oncology Associates Inc", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI031",GROUP_DESC:"HI031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4105 ,protection_group_id: -3105, protection_element_id:-3105], primaryKey: false);
      insert('organizations', [ id: 103091, nci_institute_code: "HI032", name: "Kona Community Hospital", city: "Kealakekua", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI032",GROUP_DESC:"HI032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4106 ,protection_group_id: -3106, protection_element_id:-3106], primaryKey: false);
      insert('organizations', [ id: 103092, nci_institute_code: "HI033", name: "Maui Medical Group - Wailuku", city: "Wailuku", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI033",GROUP_DESC:"HI033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4107 ,protection_group_id: -3107, protection_element_id:-3107], primaryKey: false);
      insert('organizations', [ id: 103093, nci_institute_code: "HIVNCI", name: "National Cancer Institute HIV/AIDS Malignancy Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HIVNCI",GROUP_DESC:"HIVNCI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HIVNCI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HIVNCI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HIVNCI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4108 ,protection_group_id: -3108, protection_element_id:-3108], primaryKey: false);
      insert('organizations', [ id: 103094, nci_institute_code: "HNCP", name: "Head and Neck Contracts Program", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HNCP",GROUP_DESC:"HNCP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HNCP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HNCP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HNCP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4109 ,protection_group_id: -3109, protection_element_id:-3109], primaryKey: false);
      insert('organizations', [ id: 103095, nci_institute_code: "HTSG", name: "Hepatic Tumor Saintudy Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HTSG",GROUP_DESC:"HTSG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HTSG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HTSG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HTSG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4110 ,protection_group_id: -3110, protection_element_id:-3110], primaryKey: false);
      insert('organizations', [ id: 103096, nci_institute_code: "IA001", name: "United States  Department of Agriculture", city: "Ames", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA001",GROUP_DESC:"IA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4111 ,protection_group_id: -3111, protection_element_id:-3111], primaryKey: false);
      insert('organizations', [ id: 103097, nci_institute_code: "IA002", name: "Iowa State University of Science and Technology", city: "Ames", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA002",GROUP_DESC:"IA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4112 ,protection_group_id: -3112, protection_element_id:-3112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 103098, nci_institute_code: "IA003", name: "McFarland Clinic", city: "Ames", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA003",GROUP_DESC:"IA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4113 ,protection_group_id: -3113, protection_element_id:-3113], primaryKey: false);
      insert('organizations', [ id: 103099, nci_institute_code: "IA004", name: "Iowa Methodist Medical Center", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA004",GROUP_DESC:"IA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4114 ,protection_group_id: -3114, protection_element_id:-3114], primaryKey: false);
      insert('organizations', [ id: 103100, nci_institute_code: "IA005", name: "Raymond Blank Children's Hospital", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA005",GROUP_DESC:"IA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4115 ,protection_group_id: -3115, protection_element_id:-3115], primaryKey: false);
      insert('organizations', [ id: 103101, nci_institute_code: "IA006", name: "VA Central Iowa Healthcare System", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA006",GROUP_DESC:"IA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4116 ,protection_group_id: -3116, protection_element_id:-3116], primaryKey: false);
      insert('organizations', [ id: 103102, nci_institute_code: "IA007", name: "University Osteopathic Medical Health Science", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA007",GROUP_DESC:"IA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4117 ,protection_group_id: -3117, protection_element_id:-3117], primaryKey: false);
      insert('organizations', [ id: 103103, nci_institute_code: "IA008", name: "Mercy Medical Center - Des Moines", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA008",GROUP_DESC:"IA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4118 ,protection_group_id: -3118, protection_element_id:-3118], primaryKey: false);
      insert('organizations', [ id: 103104, nci_institute_code: "IA009", name: "Mercy Capitol", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA009",GROUP_DESC:"IA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4119 ,protection_group_id: -3119, protection_element_id:-3119], primaryKey: false);
      insert('organizations', [ id: 103105, nci_institute_code: "IA011", name: "Sartori Hospital", city: "Cedar Falls", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA011",GROUP_DESC:"IA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4120 ,protection_group_id: -3120, protection_element_id:-3120], primaryKey: false);
      insert('organizations', [ id: 103106, nci_institute_code: "IA012", name: "Covenant Medical Center", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA012",GROUP_DESC:"IA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4121 ,protection_group_id: -3121, protection_element_id:-3121], primaryKey: false);
      insert('organizations', [ id: 103107, nci_institute_code: "IA013", name: "Saint Francis Hospital", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA013",GROUP_DESC:"IA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4122 ,protection_group_id: -3122, protection_element_id:-3122], primaryKey: false);
      insert('organizations', [ id: 103108, nci_institute_code: "IA014", name: "Mercy Medical Center-Sioux City", city: "Sioux City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA014",GROUP_DESC:"IA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4123 ,protection_group_id: -3123, protection_element_id:-3123], primaryKey: false);
      insert('organizations', [ id: 103109, nci_institute_code: "IA015", name: "Saint Luke's Regional Medical Center", city: "Sioux City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA015",GROUP_DESC:"IA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4124 ,protection_group_id: -3124, protection_element_id:-3124], primaryKey: false);
      insert('organizations', [ id: 103110, nci_institute_code: "IA017", name: "Central Iowa Oncology Hematology Association PC", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA017",GROUP_DESC:"IA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4125 ,protection_group_id: -3125, protection_element_id:-3125], primaryKey: false);
      insert('organizations', [ id: 103111, nci_institute_code: "IA018", name: "University of Iowa Hospitals & Clinics", city: "Iowa City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA018",GROUP_DESC:"IA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4126 ,protection_group_id: -3126, protection_element_id:-3126], primaryKey: false);
      insert('organizations', [ id: 103112, nci_institute_code: "IA019", name: "Ottumwa Regional Health Center", city: "Ottumwa", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA019",GROUP_DESC:"IA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4127 ,protection_group_id: -3127, protection_element_id:-3127], primaryKey: false);
      insert('organizations', [ id: 103113, nci_institute_code: "IA020", name: "Mercy Hospital", city: "Cedar Rapids", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA020",GROUP_DESC:"IA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4128 ,protection_group_id: -3128, protection_element_id:-3128], primaryKey: false);
      insert('organizations', [ id: 103114, nci_institute_code: "IA021", name: "VA Medical Center - University of Iowa", city: "Iowa City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA021",GROUP_DESC:"IA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4129 ,protection_group_id: -3129, protection_element_id:-3129], primaryKey: false);
      insert('organizations', [ id: 103115, nci_institute_code: "IA022", name: "Saint Lukes Hospital", city: "Bettenforf", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA022",GROUP_DESC:"IA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4130 ,protection_group_id: -3130, protection_element_id:-3130], primaryKey: false);
      insert('organizations', [ id: 103116, nci_institute_code: "IA023", name: "Burlington Medical Center", city: "Burlington", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA023",GROUP_DESC:"IA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4131 ,protection_group_id: -3131, protection_element_id:-3131], primaryKey: false);
      insert('organizations', [ id: 103117, nci_institute_code: "IA024", name: "Oncology Associates", city: "Cedar Rapids", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA024",GROUP_DESC:"IA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4132 ,protection_group_id: -3132, protection_element_id:-3132], primaryKey: false);
      insert('organizations', [ id: 103118, nci_institute_code: "IA025", name: "Saint Luke's Cancer Center", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA025",GROUP_DESC:"IA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4133 ,protection_group_id: -3133, protection_element_id:-3133], primaryKey: false);
      insert('organizations', [ id: 103119, nci_institute_code: "IA027", name: "Mercy Medical Center - North Iowa", city: "Mason City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA027",GROUP_DESC:"IA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4134 ,protection_group_id: -3134, protection_element_id:-3134], primaryKey: false);
      insert('organizations', [ id: 103120, nci_institute_code: "IA028", name: "Genesis Medical Center - West Campus", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA028",GROUP_DESC:"IA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4135 ,protection_group_id: -3135, protection_element_id:-3135], primaryKey: false);
      insert('organizations', [ id: 103121, nci_institute_code: "IA029", name: "Medical Associates Clinic, Professional Corporation", city: "Dubuque", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA029",GROUP_DESC:"IA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4136 ,protection_group_id: -3136, protection_element_id:-3136], primaryKey: false);
      insert('organizations', [ id: 103122, nci_institute_code: "IA030", name: "Iowa Lutheran Hospital", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA030",GROUP_DESC:"IA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4137 ,protection_group_id: -3137, protection_element_id:-3137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 103123, nci_institute_code: "IA031", name: "Burgess Memorial Hospital", city: "Onawa", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA031",GROUP_DESC:"IA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4138 ,protection_group_id: -3138, protection_element_id:-3138], primaryKey: false);
      insert('organizations', [ id: 103124, nci_institute_code: "IA032", name: "Community Memorial Hospital", city: "Missouri Valley", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA032",GROUP_DESC:"IA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4139 ,protection_group_id: -3139, protection_element_id:-3139], primaryKey: false);
      insert('organizations', [ id: 103125, nci_institute_code: "IA033", name: "Saint Luke's Hospital", city: "Cedar Rapids", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA033",GROUP_DESC:"IA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4140 ,protection_group_id: -3140, protection_element_id:-3140], primaryKey: false);
      insert('organizations', [ id: 103126, nci_institute_code: "IA034", name: "Siouxland Hematology - Oncology Associates", city: "Sioux City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA034",GROUP_DESC:"IA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4141 ,protection_group_id: -3141, protection_element_id:-3141], primaryKey: false);
      insert('organizations', [ id: 103127, nci_institute_code: "IA035", name: "Genesis Medical Center - East Campus", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA035",GROUP_DESC:"IA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4142 ,protection_group_id: -3142, protection_element_id:-3142], primaryKey: false);
      insert('organizations', [ id: 103128, nci_institute_code: "IA036", name: "Cedar Rapids Oncology Association", city: "Cedar Rapids", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA036",GROUP_DESC:"IA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4143 ,protection_group_id: -3143, protection_element_id:-3143], primaryKey: false);
      insert('organizations', [ id: 103129, nci_institute_code: "IA038", name: "Iowa Oncology Research Association CCOP", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA038",GROUP_DESC:"IA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4144 ,protection_group_id: -3144, protection_element_id:-3144], primaryKey: false);
      insert('organizations', [ id: 103130, nci_institute_code: "IA039", name: "Iowa Oncology Research Association Creighton Cancer Center", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA039",GROUP_DESC:"IA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4145 ,protection_group_id: -3145, protection_element_id:-3145], primaryKey: false);
      insert('organizations', [ id: 103131, nci_institute_code: "IA040", name: "Cancer Treatment Center", city: "Iowa City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA040",GROUP_DESC:"IA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4146 ,protection_group_id: -3146, protection_element_id:-3146], primaryKey: false);
      insert('organizations', [ id: 103132, nci_institute_code: "IA041", name: "John Stoddard Cancer Center", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA041",GROUP_DESC:"IA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4147 ,protection_group_id: -3147, protection_element_id:-3147], primaryKey: false);
      insert('organizations', [ id: 103133, nci_institute_code: "IA042", name: "Cedar Valley Medical Specialists", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA042",GROUP_DESC:"IA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4148 ,protection_group_id: -3148, protection_element_id:-3148], primaryKey: false);
      insert('organizations', [ id: 103134, nci_institute_code: "IA043", name: "Medcl Onc/Palliative Care, P.C.", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA043",GROUP_DESC:"IA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4149 ,protection_group_id: -3149, protection_element_id:-3149], primaryKey: false);
      insert('organizations', [ id: 103135, nci_institute_code: "IA044", name: "Allen Memorial Hospital", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA044",GROUP_DESC:"IA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4150 ,protection_group_id: -3150, protection_element_id:-3150], primaryKey: false);
      insert('organizations', [ id: 103136, nci_institute_code: "IA045", name: "Finley Hospital", city: "Dubuque", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA045",GROUP_DESC:"IA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4151 ,protection_group_id: -3151, protection_element_id:-3151], primaryKey: false);
      insert('organizations', [ id: 103137, nci_institute_code: "IA046", name: "Southwest Iowa Surgical Assoc, P.C.", city: "Council Bluffs", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA046",GROUP_DESC:"IA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4152 ,protection_group_id: -3152, protection_element_id:-3152], primaryKey: false);
      insert('organizations', [ id: 103138, nci_institute_code: "IA047", name: "Heartland Oncology and Hematology LLP", city: "Council Bluffs", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA047",GROUP_DESC:"IA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4153 ,protection_group_id: -3153, protection_element_id:-3153], primaryKey: false);
      insert('organizations', [ id: 103139, nci_institute_code: "IA049", name: "Samaritan Health System", city: "Clinton", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA049",GROUP_DESC:"IA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4154 ,protection_group_id: -3154, protection_element_id:-3154], primaryKey: false);
      insert('organizations', [ id: 103140, nci_institute_code: "IA051", name: "Saint Anthony Regional Hospital", city: "Carroll", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA051",GROUP_DESC:"IA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4155 ,protection_group_id: -3155, protection_element_id:-3155], primaryKey: false);
      insert('organizations', [ id: 103141, nci_institute_code: "IA052", name: "Alegent Health Mercy Hospital", city: "Council Bluffs", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA052",GROUP_DESC:"IA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4156 ,protection_group_id: -3156, protection_element_id:-3156], primaryKey: false);
      insert('organizations', [ id: 103142, nci_institute_code: "IA053", name: "Shenandoah Memorial Hospital", city: "Shenandoah", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA053",GROUP_DESC:"IA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4157 ,protection_group_id: -3157, protection_element_id:-3157], primaryKey: false);
      insert('organizations', [ id: 103143, nci_institute_code: "IA054", name: "Urological Associates, P.C.", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA054",GROUP_DESC:"IA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4158 ,protection_group_id: -3158, protection_element_id:-3158], primaryKey: false);
      insert('organizations', [ id: 103144, nci_institute_code: "IA055", name: "Jennie Edmundson Memorial Hospital", city: "Council Bluffs", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA055",GROUP_DESC:"IA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4159 ,protection_group_id: -3159, protection_element_id:-3159], primaryKey: false);
      insert('organizations', [ id: 103145, nci_institute_code: "IA056", name: "Therapeutic Radiation Association., P.C.", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA056",GROUP_DESC:"IA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4160 ,protection_group_id: -3160, protection_element_id:-3160], primaryKey: false);
      insert('organizations', [ id: 103146, nci_institute_code: "IA057", name: "Hematology Oncology Associates/Quad Cities", city: "Bettendorf", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA057",GROUP_DESC:"IA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4161 ,protection_group_id: -3161, protection_element_id:-3161], primaryKey: false);
      insert('organizations', [ id: 103147, nci_institute_code: "IA058", name: "Dubuque Internal Medicine, P.C", city: "Dubuque", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA058",GROUP_DESC:"IA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4162 ,protection_group_id: -3162, protection_element_id:-3162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 103148, nci_institute_code: "IA059", name: "Center for Advanced Drug Development", city: "Iowa City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA059",GROUP_DESC:"IA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4163 ,protection_group_id: -3163, protection_element_id:-3163], primaryKey: false);
      insert('organizations', [ id: 103149, nci_institute_code: "IA060", name: "Great River Oncology", city: "West Burlington", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA060",GROUP_DESC:"IA060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4164 ,protection_group_id: -3164, protection_element_id:-3164], primaryKey: false);
      insert('organizations', [ id: 103150, nci_institute_code: "IA061", name: "Hematology and Medical Oncology Consultants", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA061",GROUP_DESC:"IA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4165 ,protection_group_id: -3165, protection_element_id:-3165], primaryKey: false);
      insert('organizations', [ id: 103151, nci_institute_code: "IA062", name: "Hematology/Medical Oncology Consultants", city: "Bettendorf", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA062",GROUP_DESC:"IA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4166 ,protection_group_id: -3166, protection_element_id:-3166], primaryKey: false);
      insert('organizations', [ id: 103152, nci_institute_code: "IA064", name: "Mercy Clinics Surgical Affiliates", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA064",GROUP_DESC:"IA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4167 ,protection_group_id: -3167, protection_element_id:-3167], primaryKey: false);
      insert('organizations', [ id: 103153, nci_institute_code: "IA065", name: "Iowa Heart Center, P.C.", city: "Fort Dodge", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA065",GROUP_DESC:"IA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4168 ,protection_group_id: -3168, protection_element_id:-3168], primaryKey: false);
      insert('organizations', [ id: 103154, nci_institute_code: "IA066", name: "Iowa Blood and Cancer Care, PLC", city: "Cedar Rapids", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA066",GROUP_DESC:"IA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4169 ,protection_group_id: -3169, protection_element_id:-3169], primaryKey: false);
      insert('organizations', [ id: 103155, nci_institute_code: "IA067", name: "Mary Greeley Medical Center", city: "Ames", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA067",GROUP_DESC:"IA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4170 ,protection_group_id: -3170, protection_element_id:-3170], primaryKey: false);
      insert('organizations', [ id: 103156, nci_institute_code: "IA068", name: "Covenant Clinic", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA068",GROUP_DESC:"IA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4171 ,protection_group_id: -3171, protection_element_id:-3171], primaryKey: false);
      insert('organizations', [ id: 103157, nci_institute_code: "IA069", name: "Covenant Clinic Cancer Center", city: "Waterloo", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA069",GROUP_DESC:"IA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4172 ,protection_group_id: -3172, protection_element_id:-3172], primaryKey: false);
      insert('organizations', [ id: 103158, nci_institute_code: "IA070", name: "Broadlawns Medical Center", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA070",GROUP_DESC:"IA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4173 ,protection_group_id: -3173, protection_element_id:-3173], primaryKey: false);
      insert('organizations', [ id: 103159, nci_institute_code: "IA071", name: "Medical Oncology & Hematology Associates-West Des Moines", city: "West Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA071",GROUP_DESC:"IA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4174 ,protection_group_id: -3174, protection_element_id:-3174], primaryKey: false);
      insert('organizations', [ id: 103160, nci_institute_code: "IA072", name: "Medical Oncology & Hematology Associates-Des Moines", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA072",GROUP_DESC:"IA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4175 ,protection_group_id: -3175, protection_element_id:-3175], primaryKey: false);
      insert('organizations', [ id: 103161, nci_institute_code: "IA073", name: "Gregory D Naden MD PC", city: "Sioux City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA073",GROUP_DESC:"IA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4176 ,protection_group_id: -3176, protection_element_id:-3176], primaryKey: false);
      insert('organizations', [ id: 103162, nci_institute_code: "IA074", name: "Medical Oncology and Hematology Associates", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA074",GROUP_DESC:"IA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4177 ,protection_group_id: -3177, protection_element_id:-3177], primaryKey: false);
      insert('organizations', [ id: 103163, nci_institute_code: "IA075", name: "Mercy Medical Center", city: "Clinton", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA075",GROUP_DESC:"IA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4178 ,protection_group_id: -3178, protection_element_id:-3178], primaryKey: false);
      insert('organizations', [ id: 103164, nci_institute_code: "IA076", name: "Iowa Surgery Center, PC", city: "Windsor Heights", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA076",GROUP_DESC:"IA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4179 ,protection_group_id: -3179, protection_element_id:-3179], primaryKey: false);
      insert('organizations', [ id: 103165, nci_institute_code: "IA077", name: "The Iowa Clinic, PC/ Urology", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA077",GROUP_DESC:"IA077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4180 ,protection_group_id: -3180, protection_element_id:-3180], primaryKey: false);
      insert('organizations', [ id: 103166, nci_institute_code: "IA078", name: "Constantinou, Costas L MD (UIA Investigator)", city: "Bettendorf", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA078",GROUP_DESC:"IA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4181 ,protection_group_id: -3181, protection_element_id:-3181], primaryKey: false);
      insert('organizations', [ id: 103167, nci_institute_code: "IA079", name: "Childrens Health Center", city: "Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA079",GROUP_DESC:"IA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4182 ,protection_group_id: -3182, protection_element_id:-3182], primaryKey: false);
      insert('organizations', [ id: 103168, nci_institute_code: "IA080", name: "Radiation Therapy Center of the Quad Cities", city: "Bettendorf", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA080",GROUP_DESC:"IA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4183 ,protection_group_id: -3183, protection_element_id:-3183], primaryKey: false);
      insert('organizations', [ id: 103169, nci_institute_code: "IA081", name: "Morning Star Internal Medicine", city: "Creston", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA081",GROUP_DESC:"IA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4184 ,protection_group_id: -3184, protection_element_id:-3184], primaryKey: false);
      insert('organizations', [ id: 103170, nci_institute_code: "IA082", name: "Hematology and Oncology Care", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA082",GROUP_DESC:"IA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4185 ,protection_group_id: -3185, protection_element_id:-3185], primaryKey: false);
      insert('organizations', [ id: 103171, nci_institute_code: "IA083", name: "Medical Oncology Hematology of Ottumwa", city: "Ottumwa", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA083",GROUP_DESC:"IA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4186 ,protection_group_id: -3186, protection_element_id:-3186], primaryKey: false);
      insert('organizations', [ id: 103172, nci_institute_code: "IA084", name: "Mason City Clinic", city: "Mason City", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA084",GROUP_DESC:"IA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4187 ,protection_group_id: -3187, protection_element_id:-3187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 103173, nci_institute_code: "IA085", name: "Abben Cancer Center", city: "Spencer", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA085",GROUP_DESC:"IA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4188 ,protection_group_id: -3188, protection_element_id:-3188], primaryKey: false);
      insert('organizations', [ id: 103174, nci_institute_code: "IAML", name: "Acute Myelocytic Leukemia Intergroup", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IAML",GROUP_DESC:"IAML group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IAML",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IAML",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IAML", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4189 ,protection_group_id: -3189, protection_element_id:-3189], primaryKey: false);
      insert('organizations', [ id: 103175, nci_institute_code: "ID001", name: "Portneuf Medical Center", city: "Pocatello", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID001",GROUP_DESC:"ID001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4190 ,protection_group_id: -3190, protection_element_id:-3190], primaryKey: false);
      insert('organizations', [ id: 103176, nci_institute_code: "ID003", name: "Saint Luke's Mountain States Tumor Institute", city: "Boise", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID003",GROUP_DESC:"ID003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4191 ,protection_group_id: -3191, protection_element_id:-3191], primaryKey: false);
      insert('organizations', [ id: 103177, nci_institute_code: "ID004", name: "Saint Luke's Regional Medical Center", city: "Boise", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID004",GROUP_DESC:"ID004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4192 ,protection_group_id: -3192, protection_element_id:-3192], primaryKey: false);
      insert('organizations', [ id: 103178, nci_institute_code: "ID005", name: "Kootenai Medical Center", city: "Coeur D'Alene", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID005",GROUP_DESC:"ID005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4193 ,protection_group_id: -3193, protection_element_id:-3193], primaryKey: false);
      insert('organizations', [ id: 103179, nci_institute_code: "ID006", name: "Saint Joseph Regional Medical Center", city: "Lewiston", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID006",GROUP_DESC:"ID006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4194 ,protection_group_id: -3194, protection_element_id:-3194], primaryKey: false);
      insert('organizations', [ id: 103180, nci_institute_code: "ID007", name: "East Idaho Regional Medical Center", city: "idaho Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID007",GROUP_DESC:"ID007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4195 ,protection_group_id: -3195, protection_element_id:-3195], primaryKey: false);
      insert('organizations', [ id: 103181, nci_institute_code: "ID008", name: "Valley County Hospital", city: "Cascade", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID008",GROUP_DESC:"ID008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4196 ,protection_group_id: -3196, protection_element_id:-3196], primaryKey: false);
      insert('organizations', [ id: 103182, nci_institute_code: "ID009", name: "Mercy Medical Center", city: "Nampa", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID009",GROUP_DESC:"ID009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4197 ,protection_group_id: -3197, protection_element_id:-3197], primaryKey: false);
      insert('organizations', [ id: 103183, nci_institute_code: "ID010", name: "Magic Valley Regional Medical Center", city: "Twin Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID010",GROUP_DESC:"ID010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4198 ,protection_group_id: -3198, protection_element_id:-3198], primaryKey: false);
      insert('organizations', [ id: 103184, nci_institute_code: "ID011", name: "Saint Alphonsus Regional Medical Center", city: "Boise", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID011",GROUP_DESC:"ID011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4199 ,protection_group_id: -3199, protection_element_id:-3199], primaryKey: false);
      insert('organizations', [ id: 103185, nci_institute_code: "ID012", name: "Private Practice/Hematology/Medical Oncology", city: "Boise", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID012",GROUP_DESC:"ID012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4200 ,protection_group_id: -3200, protection_element_id:-3200], primaryKey: false);
      insert('organizations', [ id: 103186, nci_institute_code: "ID013", name: "Kootenai Cancer Center", city: "Coeur D'Alene", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID013",GROUP_DESC:"ID013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4201 ,protection_group_id: -3201, protection_element_id:-3201], primaryKey: false);
      insert('organizations', [ id: 103187, nci_institute_code: "ID014", name: "Pocatello Regional Hospital", city: "Pocatello", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID014",GROUP_DESC:"ID014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4202 ,protection_group_id: -3202, protection_element_id:-3202], primaryKey: false);
      insert('organizations', [ id: 103188, nci_institute_code: "ID015", name: "Hematology Oncology Associates of Eastern Idaho", city: "Idaho Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID015",GROUP_DESC:"ID015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4203 ,protection_group_id: -3203, protection_element_id:-3203], primaryKey: false);
      insert('organizations', [ id: 103189, nci_institute_code: "ID016", name: "Teton Medical Specialty Center", city: "Idaho Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID016",GROUP_DESC:"ID016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4204 ,protection_group_id: -3204, protection_element_id:-3204], primaryKey: false);
      insert('organizations', [ id: 103190, nci_institute_code: "ID017", name: "St. Luke's Regional Medical Center", city: "Boise", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID017",GROUP_DESC:"ID017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4205 ,protection_group_id: -3205, protection_element_id:-3205], primaryKey: false);
      insert('organizations', [ id: 103191, nci_institute_code: "ID018", name: "Oncology - Hematology Specialists, P.A.", city: "Lewiston", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID018",GROUP_DESC:"ID018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4206 ,protection_group_id: -3206, protection_element_id:-3206], primaryKey: false);
      insert('organizations', [ id: 103192, nci_institute_code: "ID019", name: "Portneuf Medical Oncology", city: "Pocatello", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID019",GROUP_DESC:"ID019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4207 ,protection_group_id: -3207, protection_element_id:-3207], primaryKey: false);
      insert('organizations', [ id: 103193, nci_institute_code: "ID020", name: "Saint Luke's Mountain States Tumor Institute - Nampa", city: "Nampa", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID020",GROUP_DESC:"ID020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4208 ,protection_group_id: -3208, protection_element_id:-3208], primaryKey: false);
      insert('organizations', [ id: 103194, nci_institute_code: "ID021", name: "Saint Luke's Mountain States Tumor Institute - Meridian", city: "Meridian", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID021",GROUP_DESC:"ID021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4209 ,protection_group_id: -3209, protection_element_id:-3209], primaryKey: false);
      insert('organizations', [ id: 103195, nci_institute_code: "ID022", name: "Saint Luke's Mountain States Tumor Institute - Fruitland", city: "Fruitland", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID022",GROUP_DESC:"ID022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4210 ,protection_group_id: -3210, protection_element_id:-3210], primaryKey: false);
      insert('organizations', [ id: 103196, nci_institute_code: "ID023", name: "Mountain States Tumor Institute - Twin Falls", city: "Twin Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID023",GROUP_DESC:"ID023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4211 ,protection_group_id: -3211, protection_element_id:-3211], primaryKey: false);
      insert('organizations', [ id: 103197, nci_institute_code: "ID024", name: "Snake River Oncology of Eastern Idaho PLLC", city: "Idaho Falls", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID024",GROUP_DESC:"ID024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4212 ,protection_group_id: -3212, protection_element_id:-3212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 103198, nci_institute_code: "IDD", name: "Institute for Drug Development", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IDD",GROUP_DESC:"IDD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IDD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IDD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IDD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4213 ,protection_group_id: -3213, protection_element_id:-3213], primaryKey: false);
      insert('organizations', [ id: 103199, nci_institute_code: "IL001", name: "Northwest Community Hospital", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL001",GROUP_DESC:"IL001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4214 ,protection_group_id: -3214, protection_element_id:-3214], primaryKey: false);
      insert('organizations', [ id: 103200, nci_institute_code: "IL002", name: "Advocate Good Shepherd Hospital", city: "Barrington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL002",GROUP_DESC:"IL002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4215 ,protection_group_id: -3215, protection_element_id:-3215], primaryKey: false);
      insert('organizations', [ id: 103201, nci_institute_code: "IL003", name: "Lutheran General Hospital", city: "Des Plains", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL003",GROUP_DESC:"IL003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4216 ,protection_group_id: -3216, protection_element_id:-3216], primaryKey: false);
      insert('organizations', [ id: 103202, nci_institute_code: "IL004", name: "Highland Park Hospital", city: "Northbrook", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL004",GROUP_DESC:"IL004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4217 ,protection_group_id: -3217, protection_element_id:-3217], primaryKey: false);
      insert('organizations', [ id: 103203, nci_institute_code: "IL005", name: "Lake Forest Hospital", city: "Lake Forest", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL005",GROUP_DESC:"IL005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4218 ,protection_group_id: -3218, protection_element_id:-3218], primaryKey: false);
      insert('organizations', [ id: 103204, nci_institute_code: "IL006", name: "Condell Memorial Hospital", city: "Libertyville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL006",GROUP_DESC:"IL006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4219 ,protection_group_id: -3219, protection_element_id:-3219], primaryKey: false);
      insert('organizations', [ id: 103205, nci_institute_code: "IL007", name: "Veterans Administration Medical Center", city: "North Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL007",GROUP_DESC:"IL007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4220 ,protection_group_id: -3220, protection_element_id:-3220], primaryKey: false);
      insert('organizations', [ id: 103206, nci_institute_code: "IL008", name: "University of Chicago", city: "North Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL008",GROUP_DESC:"IL008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4221 ,protection_group_id: -3221, protection_element_id:-3221], primaryKey: false);
      insert('organizations', [ id: 103207, nci_institute_code: "IL009", name: "Chicago Medical School", city: "North Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL009",GROUP_DESC:"IL009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4222 ,protection_group_id: -3222, protection_element_id:-3222], primaryKey: false);
      insert('organizations', [ id: 103208, nci_institute_code: "IL010", name: "Advocate Lutheran General Hospital", city: "Park Ridge", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL010",GROUP_DESC:"IL010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4223 ,protection_group_id: -3223, protection_element_id:-3223], primaryKey: false);
      insert('organizations', [ id: 103209, nci_institute_code: "IL011", name: "Naval Hospital", city: "Great Lakes", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL011",GROUP_DESC:"IL011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4224 ,protection_group_id: -3224, protection_element_id:-3224], primaryKey: false);
      insert('organizations', [ id: 103210, nci_institute_code: "IL012", name: "American International Hospital", city: "Zion", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL012",GROUP_DESC:"IL012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4225 ,protection_group_id: -3225, protection_element_id:-3225], primaryKey: false);
      insert('organizations', [ id: 103211, nci_institute_code: "IL014", name: "Saint Joseph Hospital", city: "Elgin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL014",GROUP_DESC:"IL014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4226 ,protection_group_id: -3226, protection_element_id:-3226], primaryKey: false);
      insert('organizations', [ id: 103212, nci_institute_code: "IL015", name: "Sherman Hospital", city: "Elgin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL015",GROUP_DESC:"IL015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4227 ,protection_group_id: -3227, protection_element_id:-3227], primaryKey: false);
      insert('organizations', [ id: 103213, nci_institute_code: "IL016", name: "Hines Veterans Administration Hospital", city: "Hines", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL016",GROUP_DESC:"IL016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4228 ,protection_group_id: -3228, protection_element_id:-3228], primaryKey: false);
      insert('organizations', [ id: 103214, nci_institute_code: "IL017", name: "Loyola University Medical Center", city: "Maywood", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL017",GROUP_DESC:"IL017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4229 ,protection_group_id: -3229, protection_element_id:-3229], primaryKey: false);
      insert('organizations', [ id: 103215, nci_institute_code: "IL018", name: "Evanston Northwestern Healthcare", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL018",GROUP_DESC:"IL018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4230 ,protection_group_id: -3230, protection_element_id:-3230], primaryKey: false);
      insert('organizations', [ id: 103216, nci_institute_code: "IL019", name: "Saint Francis Hospital", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL019",GROUP_DESC:"IL019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4231 ,protection_group_id: -3231, protection_element_id:-3231], primaryKey: false);
      insert('organizations', [ id: 103217, nci_institute_code: "IL020", name: "West Suburban Hospital", city: "Oak Hill", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL020",GROUP_DESC:"IL020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4232 ,protection_group_id: -3232, protection_element_id:-3232], primaryKey: false);
      insert('organizations', [ id: 103218, nci_institute_code: "IL021", name: "Oak Park Hospital", city: "Oak Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL021",GROUP_DESC:"IL021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4233 ,protection_group_id: -3233, protection_element_id:-3233], primaryKey: false);
      insert('organizations', [ id: 103219, nci_institute_code: "IL022", name: "Mac Neal Hospital", city: "Berwyn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL022",GROUP_DESC:"IL022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4234 ,protection_group_id: -3234, protection_element_id:-3234], primaryKey: false);
      insert('organizations', [ id: 103220, nci_institute_code: "IL023", name: "Resurrection Mc@St Francis Hospital", city: "Blue Island", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL023",GROUP_DESC:"IL023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4235 ,protection_group_id: -3235, protection_element_id:-3235], primaryKey: false);
      insert('organizations', [ id: 103221, nci_institute_code: "IL024", name: "Provena United Samaritans Medical Center", city: "Danville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL024",GROUP_DESC:"IL024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4236 ,protection_group_id: -3236, protection_element_id:-3236], primaryKey: false);
      insert('organizations', [ id: 103222, nci_institute_code: "IL025", name: "Family Medical Group", city: "Joliet", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL025",GROUP_DESC:"IL025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4237 ,protection_group_id: -3237, protection_element_id:-3237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 103223, nci_institute_code: "IL026", name: "Advocate Christ Medical Center", city: "Oak Lawn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL026",GROUP_DESC:"IL026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4238 ,protection_group_id: -3238, protection_element_id:-3238], primaryKey: false);
      insert('organizations', [ id: 103224, nci_institute_code: "IL027", name: "Olympia Fields Osteopathic Medical Center", city: "Olympia Fields", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL027",GROUP_DESC:"IL027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4239 ,protection_group_id: -3239, protection_element_id:-3239], primaryKey: false);
      insert('organizations', [ id: 103225, nci_institute_code: "IL029", name: "Palos Community Hospital", city: "Palos Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL029",GROUP_DESC:"IL029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4240 ,protection_group_id: -3240, protection_element_id:-3240], primaryKey: false);
      insert('organizations', [ id: 103226, nci_institute_code: "IL031", name: "Rush - Copley Medical Center", city: "Aurora", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL031",GROUP_DESC:"IL031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4241 ,protection_group_id: -3241, protection_element_id:-3241], primaryKey: false);
      insert('organizations', [ id: 103227, nci_institute_code: "IL032", name: "Hinsdale Hematology Oncology Associates, Incorporated", city: "Hinsdale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL032",GROUP_DESC:"IL032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4242 ,protection_group_id: -3242, protection_element_id:-3242], primaryKey: false);
      insert('organizations', [ id: 103228, nci_institute_code: "IL033", name: "Dammer and Cartwright", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL033",GROUP_DESC:"IL033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4243 ,protection_group_id: -3243, protection_element_id:-3243], primaryKey: false);
      insert('organizations', [ id: 103229, nci_institute_code: "IL034", name: "Mount Sinai Hospital Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL034",GROUP_DESC:"IL034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4244 ,protection_group_id: -3244, protection_element_id:-3244], primaryKey: false);
      insert('organizations', [ id: 103230, nci_institute_code: "IL036", name: "Northwestern University", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL036",GROUP_DESC:"IL036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4245 ,protection_group_id: -3245, protection_element_id:-3245], primaryKey: false);
      insert('organizations', [ id: 103231, nci_institute_code: "IL037", name: "Hematology and Oncology Associates", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL037",GROUP_DESC:"IL037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4246 ,protection_group_id: -3246, protection_element_id:-3246], primaryKey: false);
      insert('organizations', [ id: 103232, nci_institute_code: "IL038", name: "Prentice Womens Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL038",GROUP_DESC:"IL038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4247 ,protection_group_id: -3247, protection_element_id:-3247], primaryKey: false);
      insert('organizations', [ id: 103233, nci_institute_code: "IL039", name: "Jesse Brown Veterans Affairs Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL039",GROUP_DESC:"IL039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4248 ,protection_group_id: -3248, protection_element_id:-3248], primaryKey: false);
      insert('organizations', [ id: 103234, nci_institute_code: "IL040", name: "University of Illinois", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL040",GROUP_DESC:"IL040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4249 ,protection_group_id: -3249, protection_element_id:-3249], primaryKey: false);
      insert('organizations', [ id: 103235, nci_institute_code: "IL041", name: "Northern Illinois Cancer Treatment Center", city: "Dixon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL041",GROUP_DESC:"IL041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4250 ,protection_group_id: -3250, protection_element_id:-3250], primaryKey: false);
      insert('organizations', [ id: 103236, nci_institute_code: "IL042", name: "John H Stroger Jr Hospital of Cook County", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL042",GROUP_DESC:"IL042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4251 ,protection_group_id: -3251, protection_element_id:-3251], primaryKey: false);
      insert('organizations', [ id: 103237, nci_institute_code: "IL043", name: "Rush University Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL043",GROUP_DESC:"IL043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4252 ,protection_group_id: -3252, protection_element_id:-3252], primaryKey: false);
      insert('organizations', [ id: 103238, nci_institute_code: "IL044", name: "Thorek Hospital and Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL044",GROUP_DESC:"IL044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4253 ,protection_group_id: -3253, protection_element_id:-3253], primaryKey: false);
      insert('organizations', [ id: 103239, nci_institute_code: "IL045", name: "Childrens Memorial Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL045",GROUP_DESC:"IL045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4254 ,protection_group_id: -3254, protection_element_id:-3254], primaryKey: false);
      insert('organizations', [ id: 103240, nci_institute_code: "IL046", name: "Grant Hospital of Chicago", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL046",GROUP_DESC:"IL046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4255 ,protection_group_id: -3255, protection_element_id:-3255], primaryKey: false);
      insert('organizations', [ id: 103241, nci_institute_code: "IL047", name: "Columbus Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL047",GROUP_DESC:"IL047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4256 ,protection_group_id: -3256, protection_element_id:-3256], primaryKey: false);
      insert('organizations', [ id: 103242, nci_institute_code: "IL048", name: "Chicago Coll Osteo Med/Midwest Univ", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL048",GROUP_DESC:"IL048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4257 ,protection_group_id: -3257, protection_element_id:-3257], primaryKey: false);
      insert('organizations', [ id: 103243, nci_institute_code: "IL049", name: "Billings Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL049",GROUP_DESC:"IL049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4258 ,protection_group_id: -3258, protection_element_id:-3258], primaryKey: false);
      insert('organizations', [ id: 103244, nci_institute_code: "IL051", name: "Michael Reese Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL051",GROUP_DESC:"IL051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4259 ,protection_group_id: -3259, protection_element_id:-3259], primaryKey: false);
      insert('organizations', [ id: 103245, nci_institute_code: "IL052", name: "Mercy Hospital and Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL052",GROUP_DESC:"IL052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4260 ,protection_group_id: -3260, protection_element_id:-3260], primaryKey: false);
      insert('organizations', [ id: 103246, nci_institute_code: "IL053", name: "Saint Mary's of Nazareth", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL053",GROUP_DESC:"IL053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4261 ,protection_group_id: -3261, protection_element_id:-3261], primaryKey: false);
      insert('organizations', [ id: 103247, nci_institute_code: "IL054", name: "Swedish Covenant Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL054",GROUP_DESC:"IL054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4262 ,protection_group_id: -3262, protection_element_id:-3262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 103248, nci_institute_code: "IL055", name: "University of Health Sciences Medical Center", city: "North Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL055",GROUP_DESC:"IL055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4263 ,protection_group_id: -3263, protection_element_id:-3263], primaryKey: false);
      insert('organizations', [ id: 103249, nci_institute_code: "IL056", name: "Resurrection Healthcare", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL056",GROUP_DESC:"IL056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4264 ,protection_group_id: -3264, protection_element_id:-3264], primaryKey: false);
      insert('organizations', [ id: 103250, nci_institute_code: "IL057", name: "University of Chicago", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL057",GROUP_DESC:"IL057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4265 ,protection_group_id: -3265, protection_element_id:-3265], primaryKey: false);
      insert('organizations', [ id: 103251, nci_institute_code: "IL058", name: "Wyler Child Hosp Univ Chicago", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL058",GROUP_DESC:"IL058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4266 ,protection_group_id: -3266, protection_element_id:-3266], primaryKey: false);
      insert('organizations', [ id: 103252, nci_institute_code: "IL059", name: "Chicago Lying-In Hospital", city: "Taipei", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL059",GROUP_DESC:"IL059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4267 ,protection_group_id: -3267, protection_element_id:-3267], primaryKey: false);
      insert('organizations', [ id: 103253, nci_institute_code: "IL060", name: "Weiss Memorial Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL060",GROUP_DESC:"IL060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4268 ,protection_group_id: -3268, protection_element_id:-3268], primaryKey: false);
      insert('organizations', [ id: 103254, nci_institute_code: "IL061", name: "Ravenswood Hospital Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL061",GROUP_DESC:"IL061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4269 ,protection_group_id: -3269, protection_element_id:-3269], primaryKey: false);
      insert('organizations', [ id: 103255, nci_institute_code: "IL062", name: "Little Company of Mary Hospital", city: "Evergreen Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL062",GROUP_DESC:"IL062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4270 ,protection_group_id: -3270, protection_element_id:-3270], primaryKey: false);
      insert('organizations', [ id: 103256, nci_institute_code: "IL063", name: "Loretto Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL063",GROUP_DESC:"IL063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4271 ,protection_group_id: -3271, protection_element_id:-3271], primaryKey: false);
      insert('organizations', [ id: 103257, nci_institute_code: "IL064", name: "Illinois Masonic Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL064",GROUP_DESC:"IL064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4272 ,protection_group_id: -3272, protection_element_id:-3272], primaryKey: false);
      insert('organizations', [ id: 103258, nci_institute_code: "IL065", name: "Saint Joseph Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL065",GROUP_DESC:"IL065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4273 ,protection_group_id: -3273, protection_element_id:-3273], primaryKey: false);
      insert('organizations', [ id: 103259, nci_institute_code: "IL066", name: "Edgewater Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL066",GROUP_DESC:"IL066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4274 ,protection_group_id: -3274, protection_element_id:-3274], primaryKey: false);
      insert('organizations', [ id: 103260, nci_institute_code: "IL067", name: "Chicago Medical School", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL067",GROUP_DESC:"IL067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4275 ,protection_group_id: -3275, protection_element_id:-3275], primaryKey: false);
      insert('organizations', [ id: 103261, nci_institute_code: "IL068", name: "Abraham Lincoln School of Medicine", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL068",GROUP_DESC:"IL068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4276 ,protection_group_id: -3276, protection_element_id:-3276], primaryKey: false);
      insert('organizations', [ id: 103262, nci_institute_code: "IL069", name: "Saint Mary's Hospital", city: "Kankakee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL069",GROUP_DESC:"IL069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4277 ,protection_group_id: -3277, protection_element_id:-3277], primaryKey: false);
      insert('organizations', [ id: 103263, nci_institute_code: "IL070", name: "Riverside Medical Center", city: "Kankakee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL070",GROUP_DESC:"IL070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4278 ,protection_group_id: -3278, protection_element_id:-3278], primaryKey: false);
      insert('organizations', [ id: 103264, nci_institute_code: "IL071", name: "Katherine Shaw Bethea Hospital", city: "Dixon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL071",GROUP_DESC:"IL071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4279 ,protection_group_id: -3279, protection_element_id:-3279], primaryKey: false);
      insert('organizations', [ id: 103265, nci_institute_code: "IL072", name: "Swedish American Hospital", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL072",GROUP_DESC:"IL072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4280 ,protection_group_id: -3280, protection_element_id:-3280], primaryKey: false);
      insert('organizations', [ id: 103266, nci_institute_code: "IL073", name: "Saint Anthony Medical Center", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL073",GROUP_DESC:"IL073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4281 ,protection_group_id: -3281, protection_element_id:-3281], primaryKey: false);
      insert('organizations', [ id: 103267, nci_institute_code: "IL074", name: "Rockford Clinic", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL074",GROUP_DESC:"IL074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4282 ,protection_group_id: -3282, protection_element_id:-3282], primaryKey: false);
      insert('organizations', [ id: 103268, nci_institute_code: "IL075", name: "Gottlieb Memorial Hospital", city: "Melrose Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL075",GROUP_DESC:"IL075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4283 ,protection_group_id: -3283, protection_element_id:-3283], primaryKey: false);
      insert('organizations', [ id: 103269, nci_institute_code: "IL076", name: "Trinity Medical Center", city: "Rock Island", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL076",GROUP_DESC:"IL076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4284 ,protection_group_id: -3284, protection_element_id:-3284], primaryKey: false);
      insert('organizations', [ id: 103270, nci_institute_code: "IL078", name: "Saint Mary's Hospital", city: "Streator", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL078",GROUP_DESC:"IL078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4285 ,protection_group_id: -3285, protection_element_id:-3285], primaryKey: false);
      insert('organizations', [ id: 103271, nci_institute_code: "IL079", name: "Graham Hospital Association", city: "Canton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL079",GROUP_DESC:"IL079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4286 ,protection_group_id: -3286, protection_element_id:-3286], primaryKey: false);
      insert('organizations', [ id: 103272, nci_institute_code: "IL080", name: "Midwest Urological Group", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL080",GROUP_DESC:"IL080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4287 ,protection_group_id: -3287, protection_element_id:-3287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 103273, nci_institute_code: "IL081", name: "Methodist Medical Center of Illinois", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL081",GROUP_DESC:"IL081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4288 ,protection_group_id: -3288, protection_element_id:-3288], primaryKey: false);
      insert('organizations', [ id: 103274, nci_institute_code: "IL082", name: "Saint Francis Medical Center", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL082",GROUP_DESC:"IL082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4289 ,protection_group_id: -3289, protection_element_id:-3289], primaryKey: false);
      insert('organizations', [ id: 103275, nci_institute_code: "IL083", name: "Bromenn Lifecare Center", city: "Bloomington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL083",GROUP_DESC:"IL083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4290 ,protection_group_id: -3290, protection_element_id:-3290], primaryKey: false);
      insert('organizations', [ id: 103276, nci_institute_code: "IL084", name: "Saint Joseph Medical Center", city: "Bloomington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL084",GROUP_DESC:"IL084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4291 ,protection_group_id: -3291, protection_element_id:-3291], primaryKey: false);
      insert('organizations', [ id: 103277, nci_institute_code: "IL085", name: "Bromenn Regional Medical Center", city: "Normal", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL085",GROUP_DESC:"IL085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4292 ,protection_group_id: -3292, protection_element_id:-3292], primaryKey: false);
      insert('organizations', [ id: 103278, nci_institute_code: "IL086", name: "Carle Clinic Association", city: "Urbana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL086",GROUP_DESC:"IL086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4293 ,protection_group_id: -3293, protection_element_id:-3293], primaryKey: false);
      insert('organizations', [ id: 103279, nci_institute_code: "IL087", name: "Good Samaritan Hospital", city: "Downers Grove", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL087",GROUP_DESC:"IL087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4294 ,protection_group_id: -3294, protection_element_id:-3294], primaryKey: false);
      insert('organizations', [ id: 103280, nci_institute_code: "IL088", name: "Veterans Administration Hospital", city: "Danville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL088",GROUP_DESC:"IL088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4295 ,protection_group_id: -3295, protection_element_id:-3295], primaryKey: false);
      insert('organizations', [ id: 103281, nci_institute_code: "IL089", name: "Sarah Bush Lincoln Health Center", city: "Mattoon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL089",GROUP_DESC:"IL089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4296 ,protection_group_id: -3296, protection_element_id:-3296], primaryKey: false);
      insert('organizations', [ id: 103282, nci_institute_code: "IL090", name: "Gateway Regional Medical Center", city: "Granite City", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL090",GROUP_DESC:"IL090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4297 ,protection_group_id: -3297, protection_element_id:-3297], primaryKey: false);
      insert('organizations', [ id: 103283, nci_institute_code: "IL091", name: "Saint Francis Hospital", city: "Litchfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL091",GROUP_DESC:"IL091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4298 ,protection_group_id: -3298, protection_element_id:-3298], primaryKey: false);
      insert('organizations', [ id: 103284, nci_institute_code: "IL092", name: "USAF Medical Center", city: "Scott Air Force Base", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL092",GROUP_DESC:"IL092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4299 ,protection_group_id: -3299, protection_element_id:-3299], primaryKey: false);
      insert('organizations', [ id: 103285, nci_institute_code: "IL093", name: "Saint Joseph's Hospital", city: "Breese", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL093",GROUP_DESC:"IL093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4300 ,protection_group_id: -3300, protection_element_id:-3300], primaryKey: false);
      insert('organizations', [ id: 103286, nci_institute_code: "IL094", name: "Decatur Memorial Hospital", city: "Decatur", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL094",GROUP_DESC:"IL094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4301 ,protection_group_id: -3301, protection_element_id:-3301], primaryKey: false);
      insert('organizations', [ id: 103287, nci_institute_code: "IL095", name: "Saint John's Hospital", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL095",GROUP_DESC:"IL095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4302 ,protection_group_id: -3302, protection_element_id:-3302], primaryKey: false);
      insert('organizations', [ id: 103288, nci_institute_code: "IL096", name: "Southern Illinois University", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL096",GROUP_DESC:"IL096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4303 ,protection_group_id: -3303, protection_element_id:-3303], primaryKey: false);
      insert('organizations', [ id: 103289, nci_institute_code: "IL097", name: "Memorial Medical Center", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL097",GROUP_DESC:"IL097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4304 ,protection_group_id: -3304, protection_element_id:-3304], primaryKey: false);
      insert('organizations', [ id: 103290, nci_institute_code: "IL098", name: "Knapp Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL098",GROUP_DESC:"IL098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4305 ,protection_group_id: -3305, protection_element_id:-3305], primaryKey: false);
      insert('organizations', [ id: 103291, nci_institute_code: "IL099", name: "Carbondale Clinic", city: "Carbondale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL099",GROUP_DESC:"IL099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4306 ,protection_group_id: -3306, protection_element_id:-3306], primaryKey: false);
      insert('organizations', [ id: 103292, nci_institute_code: "IL100", name: "Illinois Cancer Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL100",GROUP_DESC:"IL100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4307 ,protection_group_id: -3307, protection_element_id:-3307], primaryKey: false);
      insert('organizations', [ id: 103293, nci_institute_code: "IL101", name: "Oncology-Hematology Associates of Central Illinois", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL101",GROUP_DESC:"IL101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4308 ,protection_group_id: -3308, protection_element_id:-3308], primaryKey: false);
      insert('organizations', [ id: 103294, nci_institute_code: "IL102", name: "United Medical Center", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL102",GROUP_DESC:"IL102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4309 ,protection_group_id: -3309, protection_element_id:-3309], primaryKey: false);
      insert('organizations', [ id: 103295, nci_institute_code: "IL103", name: "Central Illinois Hematology Oncology Center", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL103",GROUP_DESC:"IL103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4310 ,protection_group_id: -3310, protection_element_id:-3310], primaryKey: false);
      insert('organizations', [ id: 103296, nci_institute_code: "IL104", name: "Edward Hospital", city: "Naperville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL104",GROUP_DESC:"IL104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4311 ,protection_group_id: -3311, protection_element_id:-3311], primaryKey: false);
      insert('organizations', [ id: 103297, nci_institute_code: "IL105", name: "Ingalls Memorial Hospital", city: "Harvey", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL105",GROUP_DESC:"IL105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4312 ,protection_group_id: -3312, protection_element_id:-3312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 103298, nci_institute_code: "IL106", name: "Advocate South Suburban Hospital", city: "Hazel Crest", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL106",GROUP_DESC:"IL106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4313 ,protection_group_id: -3313, protection_element_id:-3313], primaryKey: false);
      insert('organizations', [ id: 103299, nci_institute_code: "IL109", name: "Sarah Culbertson Memorial Hospital", city: "Rushville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL109",GROUP_DESC:"IL109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4314 ,protection_group_id: -3314, protection_element_id:-3314], primaryKey: false);
      insert('organizations', [ id: 103300, nci_institute_code: "IL110", name: "Rockford Memorial Hospital", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL110",GROUP_DESC:"IL110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4315 ,protection_group_id: -3315, protection_element_id:-3315], primaryKey: false);
      insert('organizations', [ id: 103301, nci_institute_code: "IL111", name: "Jackson Park Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL111",GROUP_DESC:"IL111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4316 ,protection_group_id: -3316, protection_element_id:-3316], primaryKey: false);
      insert('organizations', [ id: 103302, nci_institute_code: "IL112", name: "Saint James Hospital and Medical Center", city: "Chicago Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL112",GROUP_DESC:"IL112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4317 ,protection_group_id: -3317, protection_element_id:-3317], primaryKey: false);
      insert('organizations', [ id: 103303, nci_institute_code: "IL113", name: "Glenbrook Hospital", city: "Glenview", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL113",GROUP_DESC:"IL113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4318 ,protection_group_id: -3318, protection_element_id:-3318], primaryKey: false);
      insert('organizations', [ id: 103304, nci_institute_code: "IL114", name: "Silver Cross Hospital", city: "Joliet", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL114",GROUP_DESC:"IL114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4319 ,protection_group_id: -3319, protection_element_id:-3319], primaryKey: false);
      insert('organizations', [ id: 103305, nci_institute_code: "IL115", name: "Saint Joseph Medical Center", city: "Joliet", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL115",GROUP_DESC:"IL115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4320 ,protection_group_id: -3320, protection_element_id:-3320], primaryKey: false);
      insert('organizations', [ id: 103306, nci_institute_code: "IL116", name: "Oncology Hematology Association of Northern Illinois Inc", city: "Gurnee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL116",GROUP_DESC:"IL116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4321 ,protection_group_id: -3321, protection_element_id:-3321], primaryKey: false);
      insert('organizations', [ id: 103307, nci_institute_code: "IL117", name: "Wellgroup Health Partners", city: "Chicago Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL117",GROUP_DESC:"IL117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4322 ,protection_group_id: -3322, protection_element_id:-3322], primaryKey: false);
      insert('organizations', [ id: 103308, nci_institute_code: "IL118", name: "University of Illinois Rockford", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL118",GROUP_DESC:"IL118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4323 ,protection_group_id: -3323, protection_element_id:-3323], primaryKey: false);
      insert('organizations', [ id: 103309, nci_institute_code: "IL119", name: "Memorial & Saint Elizabeth's Health Care Services, LLP", city: "Swansea", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL119",GROUP_DESC:"IL119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4324 ,protection_group_id: -3324, protection_element_id:-3324], primaryKey: false);
      insert('organizations', [ id: 103310, nci_institute_code: "IL120", name: "Midwestern Regional Medical Center", city: "Zion", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL120",GROUP_DESC:"IL120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4325 ,protection_group_id: -3325, protection_element_id:-3325], primaryKey: false);
      insert('organizations', [ id: 103311, nci_institute_code: "IL121", name: "Galesburg Cottage Hospital", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL121",GROUP_DESC:"IL121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4326 ,protection_group_id: -3326, protection_element_id:-3326], primaryKey: false);
      insert('organizations', [ id: 103312, nci_institute_code: "IL122", name: "Glen Ellyn Clinic", city: "Glen Ellyn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL122",GROUP_DESC:"IL122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4327 ,protection_group_id: -3327, protection_element_id:-3327], primaryKey: false);
      insert('organizations', [ id: 103313, nci_institute_code: "IL123", name: "Quincy Medical Group (Clinic)", city: "Quincy", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL123",GROUP_DESC:"IL123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4328 ,protection_group_id: -3328, protection_element_id:-3328], primaryKey: false);
      insert('organizations', [ id: 103314, nci_institute_code: "IL124", name: "Alexian Brothers Medical Center", city: "Elk Grove Village", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL124",GROUP_DESC:"IL124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4329 ,protection_group_id: -3329, protection_element_id:-3329], primaryKey: false);
      insert('organizations', [ id: 103315, nci_institute_code: "IL125", name: "Northwest Medical Specialists P.C", city: "Park Ridge", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL125",GROUP_DESC:"IL125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4330 ,protection_group_id: -3330, protection_element_id:-3330], primaryKey: false);
      insert('organizations', [ id: 103316, nci_institute_code: "IL126", name: "Glen Morton Medical Center", city: "Morton Grove", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL126",GROUP_DESC:"IL126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4331 ,protection_group_id: -3331, protection_element_id:-3331], primaryKey: false);
      insert('organizations', [ id: 103317, nci_institute_code: "IL127", name: "Saint Mary's Hospital", city: "Centralia", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL127",GROUP_DESC:"IL127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4332 ,protection_group_id: -3332, protection_element_id:-3332], primaryKey: false);
      insert('organizations', [ id: 103318, nci_institute_code: "IL129", name: "Hopedale Medical Complex - Hospital", city: "Hopedale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL129",GROUP_DESC:"IL129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4333 ,protection_group_id: -3333, protection_element_id:-3333], primaryKey: false);
      insert('organizations', [ id: 103319, nci_institute_code: "IL130", name: "Illinois Valley Hospital", city: "Peru", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL130",GROUP_DESC:"IL130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4334 ,protection_group_id: -3334, protection_element_id:-3334], primaryKey: false);
      insert('organizations', [ id: 103320, nci_institute_code: "IL131", name: "Mcdonough District Hospital", city: "Macomb", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL131",GROUP_DESC:"IL131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4335 ,protection_group_id: -3335, protection_element_id:-3335], primaryKey: false);
      insert('organizations', [ id: 103321, nci_institute_code: "IL132", name: "Mason District Hospital", city: "Havana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL132",GROUP_DESC:"IL132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4336 ,protection_group_id: -3336, protection_element_id:-3336], primaryKey: false);
      insert('organizations', [ id: 103322, nci_institute_code: "IL133", name: "Memorial Hospital", city: "Carthage", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL133",GROUP_DESC:"IL133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4337 ,protection_group_id: -3337, protection_element_id:-3337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 103323, nci_institute_code: "IL134", name: "Mendota Community Hospital", city: "Mendota", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL134",GROUP_DESC:"IL134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4338 ,protection_group_id: -3338, protection_element_id:-3338], primaryKey: false);
      insert('organizations', [ id: 103324, nci_institute_code: "IL135", name: "Pekin Hospital", city: "Pekin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL135",GROUP_DESC:"IL135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4339 ,protection_group_id: -3339, protection_element_id:-3339], primaryKey: false);
      insert('organizations', [ id: 103325, nci_institute_code: "IL136", name: "Perry Memorial Hospital", city: "Princeton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL136",GROUP_DESC:"IL136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4340 ,protection_group_id: -3340, protection_element_id:-3340], primaryKey: false);
      insert('organizations', [ id: 103326, nci_institute_code: "IL137", name: "Proctor Hospital", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL137",GROUP_DESC:"IL137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4341 ,protection_group_id: -3341, protection_element_id:-3341], primaryKey: false);
      insert('organizations', [ id: 103327, nci_institute_code: "IL138", name: "Saint Margaret's Hospital", city: "Spring Valley", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL138",GROUP_DESC:"IL138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4342 ,protection_group_id: -3342, protection_element_id:-3342], primaryKey: false);
      insert('organizations', [ id: 103328, nci_institute_code: "IL139", name: "Eureka Hospital", city: "Eureka", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL139",GROUP_DESC:"IL139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4343 ,protection_group_id: -3343, protection_element_id:-3343], primaryKey: false);
      insert('organizations', [ id: 103329, nci_institute_code: "IL140", name: "Intercommunity Cancer Center", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL140",GROUP_DESC:"IL140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4344 ,protection_group_id: -3344, protection_element_id:-3344], primaryKey: false);
      insert('organizations', [ id: 103330, nci_institute_code: "IL141", name: "Community Hospital of Ottawa", city: "Ottawa", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL141",GROUP_DESC:"IL141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4345 ,protection_group_id: -3345, protection_element_id:-3345], primaryKey: false);
      insert('organizations', [ id: 103331, nci_institute_code: "IL142", name: "Kewanee Hospital", city: "Kewanee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL142",GROUP_DESC:"IL142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4346 ,protection_group_id: -3346, protection_element_id:-3346], primaryKey: false);
      insert('organizations', [ id: 103332, nci_institute_code: "IL143", name: "Trinity Medical Center", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL143",GROUP_DESC:"IL143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4347 ,protection_group_id: -3347, protection_element_id:-3347], primaryKey: false);
      insert('organizations', [ id: 103333, nci_institute_code: "IL144", name: "Medical Arts Associates", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL144",GROUP_DESC:"IL144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4348 ,protection_group_id: -3348, protection_element_id:-3348], primaryKey: false);
      insert('organizations', [ id: 103334, nci_institute_code: "IL145", name: "Cgh Medical Center", city: "Sterling", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL145",GROUP_DESC:"IL145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4349 ,protection_group_id: -3349, protection_element_id:-3349], primaryKey: false);
      insert('organizations', [ id: 103335, nci_institute_code: "IL146", name: "Saint Jude Midwest Affiliate", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL146",GROUP_DESC:"IL146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4350 ,protection_group_id: -3350, protection_element_id:-3350], primaryKey: false);
      insert('organizations', [ id: 103336, nci_institute_code: "IL147", name: "Central Illinois CCOP", city: "Decatur", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL147",GROUP_DESC:"IL147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4351 ,protection_group_id: -3351, protection_element_id:-3351], primaryKey: false);
      insert('organizations', [ id: 103337, nci_institute_code: "IL148", name: "Good Samaritan Regional Health Center", city: "Mt. Vernon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL148",GROUP_DESC:"IL148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4352 ,protection_group_id: -3352, protection_element_id:-3352], primaryKey: false);
      insert('organizations', [ id: 103338, nci_institute_code: "IL149", name: "Belleville Memorial Hospital", city: "Belleville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL149",GROUP_DESC:"IL149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4353 ,protection_group_id: -3353, protection_element_id:-3353], primaryKey: false);
      insert('organizations', [ id: 103339, nci_institute_code: "IL150", name: "Saint Anthony's Health", city: "Alton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL150",GROUP_DESC:"IL150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4354 ,protection_group_id: -3354, protection_element_id:-3354], primaryKey: false);
      insert('organizations', [ id: 103340, nci_institute_code: "IL151", name: "Saint Elizabeth's Hospital", city: "Belleville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL151",GROUP_DESC:"IL151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4355 ,protection_group_id: -3355, protection_element_id:-3355], primaryKey: false);
      insert('organizations', [ id: 103341, nci_institute_code: "IL154", name: "North Shore Medical Center", city: "Skokie", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL154",GROUP_DESC:"IL154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4356 ,protection_group_id: -3356, protection_element_id:-3356], primaryKey: false);
      insert('organizations', [ id: 103342, nci_institute_code: "IL155", name: "Midwest Radiation Therapy Consultants Limited", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL155",GROUP_DESC:"IL155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4357 ,protection_group_id: -3357, protection_element_id:-3357], primaryKey: false);
      insert('organizations', [ id: 103343, nci_institute_code: "IL156", name: "Affiliated Urology Specialist", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL156",GROUP_DESC:"IL156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4358 ,protection_group_id: -3358, protection_element_id:-3358], primaryKey: false);
      insert('organizations', [ id: 103344, nci_institute_code: "IL157", name: "Memorial Hospital", city: "Carbondale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL157",GROUP_DESC:"IL157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4359 ,protection_group_id: -3359, protection_element_id:-3359], primaryKey: false);
      insert('organizations', [ id: 103345, nci_institute_code: "IL159", name: "Holy Family Medical Center", city: "Des Plaines", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL159",GROUP_DESC:"IL159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4360 ,protection_group_id: -3360, protection_element_id:-3360], primaryKey: false);
      insert('organizations', [ id: 103346, nci_institute_code: "IL160", name: "West Lake Community Hospital", city: "Melrose Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL160",GROUP_DESC:"IL160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4361 ,protection_group_id: -3361, protection_element_id:-3361], primaryKey: false);
      insert('organizations', [ id: 103347, nci_institute_code: "IL161", name: "Hematology Oncology Associates", city: "Maywood", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL161",GROUP_DESC:"IL161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4362 ,protection_group_id: -3362, protection_element_id:-3362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 103348, nci_institute_code: "IL162", name: "Valley Cancer Center", city: "Spring Valley", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL162",GROUP_DESC:"IL162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4363 ,protection_group_id: -3363, protection_element_id:-3363], primaryKey: false);
      insert('organizations', [ id: 103349, nci_institute_code: "IL163", name: "North Suburban Medical Consultants", city: "Park Ridge", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL163",GROUP_DESC:"IL163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4364 ,protection_group_id: -3364, protection_element_id:-3364], primaryKey: false);
      insert('organizations', [ id: 103350, nci_institute_code: "IL164", name: "Hematology Oncology Consultants Limited", city: "Naperville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL164",GROUP_DESC:"IL164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4365 ,protection_group_id: -3365, protection_element_id:-3365], primaryKey: false);
      insert('organizations', [ id: 103351, nci_institute_code: "IL165", name: "Humana Health Care Plan", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL165",GROUP_DESC:"IL165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4366 ,protection_group_id: -3366, protection_element_id:-3366], primaryKey: false);
      insert('organizations', [ id: 103352, nci_institute_code: "IL166", name: "Kellogg Cancer Center - Evanston Hospital", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL166",GROUP_DESC:"IL166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4367 ,protection_group_id: -3367, protection_element_id:-3367], primaryKey: false);
      insert('organizations', [ id: 103353, nci_institute_code: "IL167", name: "Illinois Oncology Research Association CCOP", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL167",GROUP_DESC:"IL167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4368 ,protection_group_id: -3368, protection_element_id:-3368], primaryKey: false);
      insert('organizations', [ id: 103354, nci_institute_code: "IL168", name: "Carle Cancer Center CCOP", city: "Urbana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL168",GROUP_DESC:"IL168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4369 ,protection_group_id: -3369, protection_element_id:-3369], primaryKey: false);
      insert('organizations', [ id: 103355, nci_institute_code: "IL170", name: "Progressive Care", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL170",GROUP_DESC:"IL170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4370 ,protection_group_id: -3370, protection_element_id:-3370], primaryKey: false);
      insert('organizations', [ id: 103356, nci_institute_code: "IL171", name: "La Grange Memorial Hospital", city: "La Grange", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL171",GROUP_DESC:"IL171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4371 ,protection_group_id: -3371, protection_element_id:-3371], primaryKey: false);
      insert('organizations', [ id: 103357, nci_institute_code: "IL172", name: "Joliet Oncology-Hematology Associates Limited", city: "Joliet", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL172",GROUP_DESC:"IL172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4372 ,protection_group_id: -3372, protection_element_id:-3372], primaryKey: false);
      insert('organizations', [ id: 103358, nci_institute_code: "IL173", name: "Hematology - Oncology Associates", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL173",GROUP_DESC:"IL173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4373 ,protection_group_id: -3373, protection_element_id:-3373], primaryKey: false);
      insert('organizations', [ id: 103359, nci_institute_code: "IL175", name: "Oncology Joint Practic Associates- Elmhurst Memorial Oncology Services", city: "Winfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL175",GROUP_DESC:"IL175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4374 ,protection_group_id: -3374, protection_element_id:-3374], primaryKey: false);
      insert('organizations', [ id: 103360, nci_institute_code: "IL176", name: "Cancer Care and Hematology Specialists of Chicagoland PC", city: "Niles", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL176",GROUP_DESC:"IL176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4375 ,protection_group_id: -3375, protection_element_id:-3375], primaryKey: false);
      insert('organizations', [ id: 103361, nci_institute_code: "IL177", name: "Cancer Care and Hematology Specialists of Chicagoland PC", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL177",GROUP_DESC:"IL177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4376 ,protection_group_id: -3376, protection_element_id:-3376], primaryKey: false);
      insert('organizations', [ id: 103362, nci_institute_code: "IL178", name: "Metro-Suburban Medical Specialist., S.C.", city: "Park Ridge", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL178",GROUP_DESC:"IL178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4377 ,protection_group_id: -3377, protection_element_id:-3377], primaryKey: false);
      insert('organizations', [ id: 103363, nci_institute_code: "IL179", name: "Infusion Technologies", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL179",GROUP_DESC:"IL179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4378 ,protection_group_id: -3378, protection_element_id:-3378], primaryKey: false);
      insert('organizations', [ id: 103364, nci_institute_code: "IL181", name: "Mid-Illinois Hematology/Oncology Association", city: "Normal", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL181",GROUP_DESC:"IL181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4379 ,protection_group_id: -3379, protection_element_id:-3379], primaryKey: false);
      insert('organizations', [ id: 103365, nci_institute_code: "IL185", name: "Cancer Care Specialists Central Illinois", city: "Decatur", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL185",GROUP_DESC:"IL185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4380 ,protection_group_id: -3380, protection_element_id:-3380], primaryKey: false);
      insert('organizations', [ id: 103366, nci_institute_code: "IL186", name: "Saint Anthony Memorial Hospital", city: "Effingham", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL186",GROUP_DESC:"IL186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4381 ,protection_group_id: -3381, protection_element_id:-3381], primaryKey: false);
      insert('organizations', [ id: 103367, nci_institute_code: "IL188", name: "Galesburg Clinic", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL188",GROUP_DESC:"IL188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4382 ,protection_group_id: -3382, protection_element_id:-3382], primaryKey: false);
      insert('organizations', [ id: 103368, nci_institute_code: "IL189", name: "La Grange Treament Pavilion", city: "La Grange", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL189",GROUP_DESC:"IL189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4383 ,protection_group_id: -3383, protection_element_id:-3383], primaryKey: false);
      insert('organizations', [ id: 103369, nci_institute_code: "IL191", name: "Sterling/Rock Falls Clinic", city: "Sterling", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL191",GROUP_DESC:"IL191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4384 ,protection_group_id: -3384, protection_element_id:-3384], primaryKey: false);
      insert('organizations', [ id: 103370, nci_institute_code: "IL193", name: "Saint James Hospital", city: "Pontiac", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL193",GROUP_DESC:"IL193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4385 ,protection_group_id: -3385, protection_element_id:-3385], primaryKey: false);
      insert('organizations', [ id: 103371, nci_institute_code: "IL194", name: "Springfield Clinic", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL194",GROUP_DESC:"IL194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4386 ,protection_group_id: -3386, protection_element_id:-3386], primaryKey: false);
      insert('organizations', [ id: 103372, nci_institute_code: "IL195", name: "The Professional Building At Mercy", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL195",GROUP_DESC:"IL195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4387 ,protection_group_id: -3387, protection_element_id:-3387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 103373, nci_institute_code: "IL196", name: "Freeport Memorial Hospital", city: "Freeport", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL196",GROUP_DESC:"IL196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4388 ,protection_group_id: -3388, protection_element_id:-3388], primaryKey: false);
      insert('organizations', [ id: 103374, nci_institute_code: "IL197", name: "Illinois Oncology Limited", city: "Glen Carbon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL197",GROUP_DESC:"IL197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4389 ,protection_group_id: -3389, protection_element_id:-3389], primaryKey: false);
      insert('organizations', [ id: 103375, nci_institute_code: "IL198", name: "Saint Mary's Medical", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL198",GROUP_DESC:"IL198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4390 ,protection_group_id: -3390, protection_element_id:-3390], primaryKey: false);
      insert('organizations', [ id: 103376, nci_institute_code: "IL199", name: "Saint Elizabeth Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL199",GROUP_DESC:"IL199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4391 ,protection_group_id: -3391, protection_element_id:-3391], primaryKey: false);
      insert('organizations', [ id: 103377, nci_institute_code: "IL200", name: "Illinois Oncology Limited", city: "Granite City", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL200",GROUP_DESC:"IL200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4392 ,protection_group_id: -3392, protection_element_id:-3392], primaryKey: false);
      insert('organizations', [ id: 103378, nci_institute_code: "IL201", name: "Metro-Suburban Mdcl Spec., S.C.", city: "Des Plaines", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL201",GROUP_DESC:"IL201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4393 ,protection_group_id: -3393, protection_element_id:-3393], primaryKey: false);
      insert('organizations', [ id: 103379, nci_institute_code: "IL202", name: "Hope Children's Hospital", city: "Oaklawn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL202",GROUP_DESC:"IL202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4394 ,protection_group_id: -3394, protection_element_id:-3394], primaryKey: false);
      insert('organizations', [ id: 103380, nci_institute_code: "IL203", name: "L.C. Ferguson Cancer Center", city: "Freeport", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL203",GROUP_DESC:"IL203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4395 ,protection_group_id: -3395, protection_element_id:-3395], primaryKey: false);
      insert('organizations', [ id: 103381, nci_institute_code: "IL204", name: "General Surgery Associates, L.L.C.", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL204",GROUP_DESC:"IL204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4396 ,protection_group_id: -3396, protection_element_id:-3396], primaryKey: false);
      insert('organizations', [ id: 103382, nci_institute_code: "IL205", name: "West Suburban Cancer Center", city: "River Forest", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL205",GROUP_DESC:"IL205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4397 ,protection_group_id: -3397, protection_element_id:-3397], primaryKey: false);
      insert('organizations', [ id: 103383, nci_institute_code: "IL206", name: "Elmhurst Memorial Hospital", city: "Elmhurst", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL206",GROUP_DESC:"IL206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4398 ,protection_group_id: -3398, protection_element_id:-3398], primaryKey: false);
      insert('organizations', [ id: 103384, nci_institute_code: "IL207", name: "Internal Medicine, Hematology Oncology", city: "Geneva", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL207",GROUP_DESC:"IL207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4399 ,protection_group_id: -3399, protection_element_id:-3399], primaryKey: false);
      insert('organizations', [ id: 103385, nci_institute_code: "IL208", name: "Crossroads Cancer Center", city: "Effingham", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL208",GROUP_DESC:"IL208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4400 ,protection_group_id: -3400, protection_element_id:-3400], primaryKey: false);
      insert('organizations', [ id: 103386, nci_institute_code: "IL209", name: "Central Dupage Hospital", city: "Winfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL209",GROUP_DESC:"IL209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4401 ,protection_group_id: -3401, protection_element_id:-3401], primaryKey: false);
      insert('organizations', [ id: 103387, nci_institute_code: "IL210", name: "Wright State University School of Medicine", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL210",GROUP_DESC:"IL210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4402 ,protection_group_id: -3402, protection_element_id:-3402], primaryKey: false);
      insert('organizations', [ id: 103388, nci_institute_code: "IL211", name: "Horizon Health Care", city: "Tinley Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL211",GROUP_DESC:"IL211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4403 ,protection_group_id: -3403, protection_element_id:-3403], primaryKey: false);
      insert('organizations', [ id: 103389, nci_institute_code: "IL212", name: "Amer Pharmaceutical Partners", city: "Melrose Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL212",GROUP_DESC:"IL212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4404 ,protection_group_id: -3404, protection_element_id:-3404], primaryKey: false);
      insert('organizations', [ id: 103390, nci_institute_code: "IL214", name: "Central Illinois Breast Center", city: "Champaign", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL214",GROUP_DESC:"IL214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4405 ,protection_group_id: -3405, protection_element_id:-3405], primaryKey: false);
      insert('organizations', [ id: 103391, nci_institute_code: "IL216", name: "Midwest Cancer Research Group, Incorporated", city: "Skokie", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL216",GROUP_DESC:"IL216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4406 ,protection_group_id: -3406, protection_element_id:-3406], primaryKey: false);
      insert('organizations', [ id: 103392, nci_institute_code: "IL217", name: "Mercy Harvard Medical Center", city: "Harvard", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL217",GROUP_DESC:"IL217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4407 ,protection_group_id: -3407, protection_element_id:-3407], primaryKey: false);
      insert('organizations', [ id: 103393, nci_institute_code: "IL218", name: "Intermed Oncology Associates", city: "Homewood", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL218",GROUP_DESC:"IL218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4408 ,protection_group_id: -3408, protection_element_id:-3408], primaryKey: false);
      insert('organizations', [ id: 103394, nci_institute_code: "IL219", name: "Edward Kaplan and Associates", city: "Skokie", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL219",GROUP_DESC:"IL219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4409 ,protection_group_id: -3409, protection_element_id:-3409], primaryKey: false);
      insert('organizations', [ id: 103395, nci_institute_code: "IL221", name: "Our Lady of Resurrection", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL221",GROUP_DESC:"IL221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4410 ,protection_group_id: -3410, protection_element_id:-3410], primaryKey: false);
      insert('organizations', [ id: 103396, nci_institute_code: "IL222", name: "Morris Hospital", city: "Morris", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL222",GROUP_DESC:"IL222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4411 ,protection_group_id: -3411, protection_element_id:-3411], primaryKey: false);
      insert('organizations', [ id: 103397, nci_institute_code: "IL223", name: "Deerpath Medical Associates", city: "Lake Forest", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL223",GROUP_DESC:"IL223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4412 ,protection_group_id: -3412, protection_element_id:-3412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 103398, nci_institute_code: "IL224", name: "North Shore Hematology Oncology", city: "Libertyville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL224",GROUP_DESC:"IL224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4413 ,protection_group_id: -3413, protection_element_id:-3413], primaryKey: false);
      insert('organizations', [ id: 103399, nci_institute_code: "IL225", name: "Hematology Oncology Associates -Highland Park", city: "Highland Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL225",GROUP_DESC:"IL225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4414 ,protection_group_id: -3414, protection_element_id:-3414], primaryKey: false);
      insert('organizations', [ id: 103400, nci_institute_code: "IL226", name: "Comprehensive Bleeding Disorders Center", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL226",GROUP_DESC:"IL226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4415 ,protection_group_id: -3415, protection_element_id:-3415], primaryKey: false);
      insert('organizations', [ id: 103401, nci_institute_code: "IL227", name: "University of Illinois College of Medicine - Peoria", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL227",GROUP_DESC:"IL227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4416 ,protection_group_id: -3416, protection_element_id:-3416], primaryKey: false);
      insert('organizations', [ id: 103402, nci_institute_code: "IL228", name: "Northwestern Memorial Hospital", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL228",GROUP_DESC:"IL228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4417 ,protection_group_id: -3417, protection_element_id:-3417], primaryKey: false);
      insert('organizations', [ id: 103403, nci_institute_code: "IL229", name: "Monroe Medical Associates", city: "Harvey", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL229",GROUP_DESC:"IL229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4418 ,protection_group_id: -3418, protection_element_id:-3418], primaryKey: false);
      insert('organizations', [ id: 103404, nci_institute_code: "IL230", name: "OSF Saint Mary Medical Center", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL230",GROUP_DESC:"IL230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4419 ,protection_group_id: -3419, protection_element_id:-3419], primaryKey: false);
      insert('organizations', [ id: 103405, nci_institute_code: "IL231", name: "Greater Rockford Hematology and Oncology Center", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL231",GROUP_DESC:"IL231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4420 ,protection_group_id: -3420, protection_element_id:-3420], primaryKey: false);
      insert('organizations', [ id: 103406, nci_institute_code: "IL232", name: "Community Cancer Center", city: "Normal", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL232",GROUP_DESC:"IL232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4421 ,protection_group_id: -3421, protection_element_id:-3421], primaryKey: false);
      insert('organizations', [ id: 103407, nci_institute_code: "IL233", name: "Provena Covenant Medical Center", city: "Urbana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL233",GROUP_DESC:"IL233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4422 ,protection_group_id: -3422, protection_element_id:-3422], primaryKey: false);
      insert('organizations', [ id: 103408, nci_institute_code: "IL234", name: "Bloomington Cancer Center", city: "Bloomington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL234",GROUP_DESC:"IL234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4423 ,protection_group_id: -3423, protection_element_id:-3423], primaryKey: false);
      insert('organizations', [ id: 103409, nci_institute_code: "IL235", name: "Carle Clinic Foundation Hospital", city: "Urbana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL235",GROUP_DESC:"IL235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4424 ,protection_group_id: -3424, protection_element_id:-3424], primaryKey: false);
      insert('organizations', [ id: 103410, nci_institute_code: "IL236", name: "Central Illinois Neuro Science Foundation", city: "Bloomington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL236",GROUP_DESC:"IL236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4425 ,protection_group_id: -3425, protection_element_id:-3425], primaryKey: false);
      insert('organizations', [ id: 103411, nci_institute_code: "IL237", name: "Marion Veterans Administration Medical Center", city: "Marion", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL237",GROUP_DESC:"IL237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4426 ,protection_group_id: -3426, protection_element_id:-3426], primaryKey: false);
      insert('organizations', [ id: 103412, nci_institute_code: "IL239", name: "Hematology Oncology Associates- Melrose Park", city: "Melrose Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL239",GROUP_DESC:"IL239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4427 ,protection_group_id: -3427, protection_element_id:-3427], primaryKey: false);
      insert('organizations', [ id: 103413, nci_institute_code: "IL240", name: "Edward Cancer Center", city: "Naperville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL240",GROUP_DESC:"IL240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4428 ,protection_group_id: -3428, protection_element_id:-3428], primaryKey: false);
      insert('organizations', [ id: 103414, nci_institute_code: "IL241", name: "Dreyer Medical Clinic", city: "Aurora", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL241",GROUP_DESC:"IL241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4429 ,protection_group_id: -3429, protection_element_id:-3429], primaryKey: false);
      insert('organizations', [ id: 103415, nci_institute_code: "IL242", name: "Hematology Oncology Associates - Oak Park", city: "Oak Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL242",GROUP_DESC:"IL242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4430 ,protection_group_id: -3430, protection_element_id:-3430], primaryKey: false);
      insert('organizations', [ id: 103416, nci_institute_code: "IL244", name: "Hematology/Oncology Associates of Illinois - Skokie", city: "Skokie", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL244",GROUP_DESC:"IL244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4431 ,protection_group_id: -3431, protection_element_id:-3431], primaryKey: false);
      insert('organizations', [ id: 103417, nci_institute_code: "IL245", name: "Pekin Cancer Treatment Center", city: "Pekin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL245",GROUP_DESC:"IL245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4432 ,protection_group_id: -3432, protection_element_id:-3432], primaryKey: false);
      insert('organizations', [ id: 103418, nci_institute_code: "IL246", name: "Alexian Brothers Regional Cancer Care Center", city: "Elk Grove Village", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL246",GROUP_DESC:"IL246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4433 ,protection_group_id: -3433, protection_element_id:-3433], primaryKey: false);
      insert('organizations', [ id: 103419, nci_institute_code: "IL247", name: "LaGrange Oncology Associates", city: "Geneva", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL247",GROUP_DESC:"IL247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4434 ,protection_group_id: -3434, protection_element_id:-3434], primaryKey: false);
      insert('organizations', [ id: 103420, nci_institute_code: "IL248", name: "Carol Jo Vecchie Women and Children's Center", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL248",GROUP_DESC:"IL248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4435 ,protection_group_id: -3435, protection_element_id:-3435], primaryKey: false);
      insert('organizations', [ id: 103421, nci_institute_code: "IL249", name: "Advanced Care & Treatment Medical Group, S.C.", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL249",GROUP_DESC:"IL249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4436 ,protection_group_id: -3436, protection_element_id:-3436], primaryKey: false);
      insert('organizations', [ id: 103422, nci_institute_code: "IL250", name: "Chicago Prostate Cancer Center", city: "Westmont", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL250",GROUP_DESC:"IL250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4437 ,protection_group_id: -3437, protection_element_id:-3437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 103423, nci_institute_code: "IL251", name: "Feinberg School of Medicine", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL251",GROUP_DESC:"IL251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4438 ,protection_group_id: -3438, protection_element_id:-3438], primaryKey: false);
      insert('organizations', [ id: 103424, nci_institute_code: "IL252", name: "Harris Pharmacy", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL252",GROUP_DESC:"IL252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4439 ,protection_group_id: -3439, protection_element_id:-3439], primaryKey: false);
      insert('organizations', [ id: 103425, nci_institute_code: "IL253", name: "Illinois Oncology Limited", city: "Belleville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL253",GROUP_DESC:"IL253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4440 ,protection_group_id: -3440, protection_element_id:-3440], primaryKey: false);
      insert('organizations', [ id: 103426, nci_institute_code: "IL254", name: "Southern Illinois Surgical Consultants", city: "Swansea", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL254",GROUP_DESC:"IL254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4441 ,protection_group_id: -3441, protection_element_id:-3441], primaryKey: false);
      insert('organizations', [ id: 103427, nci_institute_code: "IL255", name: "Lincoln Surgical Group", city: "Belleville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL255",GROUP_DESC:"IL255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4442 ,protection_group_id: -3442, protection_element_id:-3442], primaryKey: false);
      insert('organizations', [ id: 103428, nci_institute_code: "IL256", name: "Alexian Brothers Medical Center", city: "Bloomingdale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL256",GROUP_DESC:"IL256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4443 ,protection_group_id: -3443, protection_element_id:-3443], primaryKey: false);
      insert('organizations', [ id: 103429, nci_institute_code: "IL257", name: "Illinois Cardiac Surgery Association", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL257",GROUP_DESC:"IL257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4444 ,protection_group_id: -3444, protection_element_id:-3444], primaryKey: false);
      insert('organizations', [ id: 103430, nci_institute_code: "IL258", name: "Delnor Community Hospital", city: "Geneva", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL258",GROUP_DESC:"IL258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4445 ,protection_group_id: -3445, protection_element_id:-3445], primaryKey: false);
      insert('organizations', [ id: 103431, nci_institute_code: "IL259", name: "Illini Hospital", city: "Silvis", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL259",GROUP_DESC:"IL259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4446 ,protection_group_id: -3446, protection_element_id:-3446], primaryKey: false);
      insert('organizations', [ id: 103432, nci_institute_code: "IL260", name: "University of Illinois College of Medicine - Chicago", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL260",GROUP_DESC:"IL260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4447 ,protection_group_id: -3447, protection_element_id:-3447], primaryKey: false);
      insert('organizations', [ id: 103433, nci_institute_code: "IL261", name: "Midwest Center for Hematology/Oncology", city: "Joliet", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL261",GROUP_DESC:"IL261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4448 ,protection_group_id: -3448, protection_element_id:-3448], primaryKey: false);
      insert('organizations', [ id: 103434, nci_institute_code: "IL262", name: "AmericasDoctor, Inc.", city: "Gurnee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL262",GROUP_DESC:"IL262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4449 ,protection_group_id: -3449, protection_element_id:-3449], primaryKey: false);
      insert('organizations', [ id: 103435, nci_institute_code: "IL263", name: "Hematology Oncology Associates of Illinois - Elmhurst", city: "Elmhurst", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL263",GROUP_DESC:"IL263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4450 ,protection_group_id: -3450, protection_element_id:-3450], primaryKey: false);
      insert('organizations', [ id: 103436, nci_institute_code: "IL264", name: "Cancer Care and Hematology Specialists of Chicagoland PC", city: "Winfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL264",GROUP_DESC:"IL264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4451 ,protection_group_id: -3451, protection_element_id:-3451], primaryKey: false);
      insert('organizations', [ id: 103437, nci_institute_code: "IL266", name: "North Shore Oncology & Hematology Associates", city: "Barrington", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL266",GROUP_DESC:"IL266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4452 ,protection_group_id: -3452, protection_element_id:-3452], primaryKey: false);
      insert('organizations', [ id: 103438, nci_institute_code: "IL267", name: "Block Medical Center", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL267",GROUP_DESC:"IL267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4453 ,protection_group_id: -3453, protection_element_id:-3453], primaryKey: false);
      insert('organizations', [ id: 103439, nci_institute_code: "IL268", name: "Chicago Gynecologic Oncology, S.C.", city: "Palatine", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL268",GROUP_DESC:"IL268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4454 ,protection_group_id: -3454, protection_element_id:-3454], primaryKey: false);
      insert('organizations', [ id: 103440, nci_institute_code: "IL269", name: "Edward Center for Radiation Therapy", city: "Lisle", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL269",GROUP_DESC:"IL269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4455 ,protection_group_id: -3455, protection_element_id:-3455], primaryKey: false);
      insert('organizations', [ id: 103441, nci_institute_code: "IL270", name: "St. Johns Pavilion", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL270",GROUP_DESC:"IL270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4456 ,protection_group_id: -3456, protection_element_id:-3456], primaryKey: false);
      insert('organizations', [ id: 103442, nci_institute_code: "IL271", name: "Peoria Surgical Group", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL271",GROUP_DESC:"IL271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4457 ,protection_group_id: -3457, protection_element_id:-3457], primaryKey: false);
      insert('organizations', [ id: 103443, nci_institute_code: "IL272", name: "Peoria Gynecologic/Oncology", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL272",GROUP_DESC:"IL272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4458 ,protection_group_id: -3458, protection_element_id:-3458], primaryKey: false);
      insert('organizations', [ id: 103444, nci_institute_code: "IL273", name: "Medical & Surgical Specialists", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL273",GROUP_DESC:"IL273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4459 ,protection_group_id: -3459, protection_element_id:-3459], primaryKey: false);
      insert('organizations', [ id: 103445, nci_institute_code: "IL274", name: "Saint James Hospital and Health Centers-Olympia Fields", city: "Olympia Fields", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL274",GROUP_DESC:"IL274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4460 ,protection_group_id: -3460, protection_element_id:-3460], primaryKey: false);
      insert('organizations', [ id: 103446, nci_institute_code: "IL275", name: "Heart Care Midwest Surgeons", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL275",GROUP_DESC:"IL275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4461 ,protection_group_id: -3461, protection_element_id:-3461], primaryKey: false);
      insert('organizations', [ id: 103447, nci_institute_code: "IL276", name: "The Rockford Surgical Service", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL276",GROUP_DESC:"IL276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4462 ,protection_group_id: -3462, protection_element_id:-3462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 103448, nci_institute_code: "IL277", name: "Oncology Hematology of Central Illinois - Ottawa", city: "Ottawa", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL277",GROUP_DESC:"IL277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4463 ,protection_group_id: -3463, protection_element_id:-3463], primaryKey: false);
      insert('organizations', [ id: 103449, nci_institute_code: "IL278", name: "Saint James Hospital and Health Centers Comprehensive Cancer Institute", city: "Olympia Fields", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL278",GROUP_DESC:"IL278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4464 ,protection_group_id: -3464, protection_element_id:-3464], primaryKey: false);
      insert('organizations', [ id: 103450, nci_institute_code: "IL279", name: "North Shore Oncology and Hematology Associates- McHenry", city: "McHenry", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL279",GROUP_DESC:"IL279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4465 ,protection_group_id: -3465, protection_element_id:-3465], primaryKey: false);
      insert('organizations', [ id: 103451, nci_institute_code: "IL280", name: "Cancer Institute at Alexian Brothers", city: "Elk Grove Village", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL280",GROUP_DESC:"IL280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4466 ,protection_group_id: -3466, protection_element_id:-3466], primaryKey: false);
      insert('organizations', [ id: 103452, nci_institute_code: "IL281", name: "Surgical Practice, Ltd.", city: "Aurora", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL281",GROUP_DESC:"IL281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4467 ,protection_group_id: -3467, protection_element_id:-3467], primaryKey: false);
      insert('organizations', [ id: 103453, nci_institute_code: "IL282", name: "Raymond G. Scott Cancer Center", city: "Geneva", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL282",GROUP_DESC:"IL282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4468 ,protection_group_id: -3468, protection_element_id:-3468], primaryKey: false);
      insert('organizations', [ id: 103454, nci_institute_code: "IL283", name: "Saint Vincent Memorial Hospital", city: "Taylorville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL283",GROUP_DESC:"IL283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4469 ,protection_group_id: -3469, protection_element_id:-3469], primaryKey: false);
      insert('organizations', [ id: 103455, nci_institute_code: "IL284", name: "Fairfield Memorial Hospital", city: "Fairfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL284",GROUP_DESC:"IL284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4470 ,protection_group_id: -3470, protection_element_id:-3470], primaryKey: false);
      insert('organizations', [ id: 103456, nci_institute_code: "IL285", name: "Pana Coomunity Hospital", city: "Pana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL285",GROUP_DESC:"IL285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4471 ,protection_group_id: -3471, protection_element_id:-3471], primaryKey: false);
      insert('organizations', [ id: 103457, nci_institute_code: "IL286", name: "Shelby Memorial Hospital", city: "Shelbyville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL286",GROUP_DESC:"IL286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4472 ,protection_group_id: -3472, protection_element_id:-3472], primaryKey: false);
      insert('organizations', [ id: 103458, nci_institute_code: "IL287", name: "Fox Valley Hematology and Oncology, Ltd. - Elgin", city: "Elgin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL287",GROUP_DESC:"IL287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4473 ,protection_group_id: -3473, protection_element_id:-3473], primaryKey: false);
      insert('organizations', [ id: 103459, nci_institute_code: "IL288", name: "Fox Valley Hematology and Oncology, Ltd. - McHenry", city: "McHenry", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL288",GROUP_DESC:"IL288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4474 ,protection_group_id: -3474, protection_element_id:-3474], primaryKey: false);
      insert('organizations', [ id: 103460, nci_institute_code: "IL289", name: "Fox Valley Hematology and Oncology, Ltd. - Woodstock", city: "Woodstock", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL289",GROUP_DESC:"IL289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4475 ,protection_group_id: -3475, protection_element_id:-3475], primaryKey: false);
      insert('organizations', [ id: 103461, nci_institute_code: "IL290", name: "Clay County Hospital", city: "Flora", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL290",GROUP_DESC:"IL290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4476 ,protection_group_id: -3476, protection_element_id:-3476], primaryKey: false);
      insert('organizations', [ id: 103462, nci_institute_code: "IL291", name: "Passavant Area Hospital", city: "Jacksonville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL291",GROUP_DESC:"IL291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4477 ,protection_group_id: -3477, protection_element_id:-3477], primaryKey: false);
      insert('organizations', [ id: 103463, nci_institute_code: "IL292", name: "Abraham Lincoln Memorial Hospital", city: "Lincoln", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL292",GROUP_DESC:"IL292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4478 ,protection_group_id: -3478, protection_element_id:-3478], primaryKey: false);
      insert('organizations', [ id: 103464, nci_institute_code: "IL293", name: "Southern Illinois University School of Medicine - Department of Surgery", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL293",GROUP_DESC:"IL293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4479 ,protection_group_id: -3479, protection_element_id:-3479], primaryKey: false);
      insert('organizations', [ id: 103465, nci_institute_code: "IL294", name: "Southern Illinois University School of Medicine - Obstetrics and Oncology", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL294",GROUP_DESC:"IL294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4480 ,protection_group_id: -3480, protection_element_id:-3480], primaryKey: false);
      insert('organizations', [ id: 103466, nci_institute_code: "IL295", name: "Southern Illinois University School of Medicine - Breast Center", city: "Springfield", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL295",GROUP_DESC:"IL295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4481 ,protection_group_id: -3481, protection_element_id:-3481], primaryKey: false);
      insert('organizations', [ id: 103467, nci_institute_code: "IL296", name: "Provident Hospital of Cook County", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL296",GROUP_DESC:"IL296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4482 ,protection_group_id: -3482, protection_element_id:-3482], primaryKey: false);
      insert('organizations', [ id: 103468, nci_institute_code: "IL297", name: "Dr. John Warner Hospital", city: "Clinton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL297",GROUP_DESC:"IL297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4483 ,protection_group_id: -3483, protection_element_id:-3483], primaryKey: false);
      insert('organizations', [ id: 103469, nci_institute_code: "IL298", name: "University Surgeons", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL298",GROUP_DESC:"IL298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4484 ,protection_group_id: -3484, protection_element_id:-3484], primaryKey: false);
      insert('organizations', [ id: 103470, nci_institute_code: "IL299", name: "Glen Morton Oncology", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL299",GROUP_DESC:"IL299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4485 ,protection_group_id: -3485, protection_element_id:-3485], primaryKey: false);
      insert('organizations', [ id: 103471, nci_institute_code: "IL300", name: "Center for Advanced Breast Care", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL300",GROUP_DESC:"IL300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4486 ,protection_group_id: -3486, protection_element_id:-3486], primaryKey: false);
      insert('organizations', [ id: 103472, nci_institute_code: "IL301", name: "Surgical Specialists of Decatur", city: "Decatur", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL301",GROUP_DESC:"IL301 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL301",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL301",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL301", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4487 ,protection_group_id: -3487, protection_element_id:-3487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 103473, nci_institute_code: "IL302", name: "ENTA Allergy, Head & Neck Institute", city: "Decatur", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL302",GROUP_DESC:"IL302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4488 ,protection_group_id: -3488, protection_element_id:-3488], primaryKey: false);
      insert('organizations', [ id: 103474, nci_institute_code: "IL303", name: "Oncology Specialists, S.C.", city: "Park Ridge", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL303",GROUP_DESC:"IL303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4489 ,protection_group_id: -3489, protection_element_id:-3489], primaryKey: false);
      insert('organizations', [ id: 103475, nci_institute_code: "IL304", name: "Southern Illinois Hematology/Oncology", city: "Centralia", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL304",GROUP_DESC:"IL304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4490 ,protection_group_id: -3490, protection_element_id:-3490], primaryKey: false);
      insert('organizations', [ id: 103476, nci_institute_code: "IL305", name: "Gynecologic Oncology", city: "Hinsdale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL305",GROUP_DESC:"IL305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4491 ,protection_group_id: -3491, protection_element_id:-3491], primaryKey: false);
      insert('organizations', [ id: 103477, nci_institute_code: "IL306", name: "Northwest Suburban Surgical Specialists", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL306",GROUP_DESC:"IL306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4492 ,protection_group_id: -3492, protection_element_id:-3492], primaryKey: false);
      insert('organizations', [ id: 103478, nci_institute_code: "IL307", name: "NIU Institute for Neutron Therapy at Fermilab", city: "Batavia", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL307",GROUP_DESC:"IL307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4493 ,protection_group_id: -3493, protection_element_id:-3493], primaryKey: false);
      insert('organizations', [ id: 103479, nci_institute_code: "IL308", name: "La Grange Oncology Associates, SC", city: "La Grange", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL308",GROUP_DESC:"IL308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4494 ,protection_group_id: -3494, protection_element_id:-3494], primaryKey: false);
      insert('organizations', [ id: 103480, nci_institute_code: "IL309", name: "SwedishAmerican Regional Cancer Center", city: "Rockford", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL309",GROUP_DESC:"IL309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4495 ,protection_group_id: -3495, protection_element_id:-3495], primaryKey: false);
      insert('organizations', [ id: 103481, nci_institute_code: "IL310", name: "Garneau, Stewart C MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL310",GROUP_DESC:"IL310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4496 ,protection_group_id: -3496, protection_element_id:-3496], primaryKey: false);
      insert('organizations', [ id: 103482, nci_institute_code: "IL311", name: "Porubcin, Michael MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL311",GROUP_DESC:"IL311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4497 ,protection_group_id: -3497, protection_element_id:-3497], primaryKey: false);
      insert('organizations', [ id: 103483, nci_institute_code: "IL312", name: "Suh, Jason Jung-Gon MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL312",GROUP_DESC:"IL312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4498 ,protection_group_id: -3498, protection_element_id:-3498], primaryKey: false);
      insert('organizations', [ id: 103484, nci_institute_code: "IL313", name: "Stoffel, Thomas J  MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL313",GROUP_DESC:"IL313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4499 ,protection_group_id: -3499, protection_element_id:-3499], primaryKey: false);
      insert('organizations', [ id: 103485, nci_institute_code: "IL314", name: "Vigliotti, Antonio, P.G. M.D. (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL314",GROUP_DESC:"IL314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4500 ,protection_group_id: -3500, protection_element_id:-3500], primaryKey: false);
      insert('organizations', [ id: 103486, nci_institute_code: "IL315", name: "Sharis, Christine M MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL315",GROUP_DESC:"IL315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4501 ,protection_group_id: -3501, protection_element_id:-3501], primaryKey: false);
      insert('organizations', [ id: 103487, nci_institute_code: "IL316", name: "Anderson, Richard, C. M.D. (UIA Investigator)", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL316",GROUP_DESC:"IL316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4502 ,protection_group_id: -3502, protection_element_id:-3502], primaryKey: false);
      insert('organizations', [ id: 103488, nci_institute_code: "IL317", name: "Rashid, Thomas, M.D. (UIA Investigator)", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL317",GROUP_DESC:"IL317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4503 ,protection_group_id: -3503, protection_element_id:-3503], primaryKey: false);
      insert('organizations', [ id: 103489, nci_institute_code: "IL318", name: "Provena Saint Mary's Cancer Center", city: "Bourbonnais", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL318",GROUP_DESC:"IL318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4504 ,protection_group_id: -3504, protection_element_id:-3504], primaryKey: false);
      insert('organizations', [ id: 103490, nci_institute_code: "IL319", name: "OSF Saint Francis Medical Center Radiation Oncology Service at the Central Illinois Comprehensive CC", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL319",GROUP_DESC:"IL319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4505 ,protection_group_id: -3505, protection_element_id:-3505], primaryKey: false);
      insert('organizations', [ id: 103491, nci_institute_code: "IL320", name: "Associates in Medical Oncology, SC", city: "Oak Lawn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL320",GROUP_DESC:"IL320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4506 ,protection_group_id: -3506, protection_element_id:-3506], primaryKey: false);
      insert('organizations', [ id: 103492, nci_institute_code: "IL321", name: "Mueller, Dale K MD (UIA Investigator)", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL321",GROUP_DESC:"IL321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4507 ,protection_group_id: -3507, protection_element_id:-3507], primaryKey: false);
      insert('organizations', [ id: 103493, nci_institute_code: "IL322", name: "Hematology Oncology Associates of Illinois", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL322",GROUP_DESC:"IL322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4508 ,protection_group_id: -3508, protection_element_id:-3508], primaryKey: false);
      insert('organizations', [ id: 103494, nci_institute_code: "IL323", name: "Creticos Cancer Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL323",GROUP_DESC:"IL323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4509 ,protection_group_id: -3509, protection_element_id:-3509], primaryKey: false);
      insert('organizations', [ id: 103495, nci_institute_code: "IL324", name: "Associated Midwest Surgeons Ltd", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL324",GROUP_DESC:"IL324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4510 ,protection_group_id: -3510, protection_element_id:-3510], primaryKey: false);
      insert('organizations', [ id: 103496, nci_institute_code: "IL325", name: "Kankakee Cancer Care", city: "Kankakee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL325",GROUP_DESC:"IL325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4511 ,protection_group_id: -3511, protection_element_id:-3511], primaryKey: false);
      insert('organizations', [ id: 103497, nci_institute_code: "IL326", name: "Flossmoor Cancer Care", city: "Flossmoor", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL326",GROUP_DESC:"IL326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4512 ,protection_group_id: -3512, protection_element_id:-3512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 103498, nci_institute_code: "IL327", name: "Hematology Oncology Consultants Inc", city: "Alton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL327",GROUP_DESC:"IL327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4513 ,protection_group_id: -3513, protection_element_id:-3513], primaryKey: false);
      insert('organizations', [ id: 103499, nci_institute_code: "IL328", name: "Resurrection West Suburban Healthcare", city: "Oak Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL328",GROUP_DESC:"IL328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4514 ,protection_group_id: -3514, protection_element_id:-3514], primaryKey: false);
      insert('organizations', [ id: 103500, nci_institute_code: "IL329", name: "Rush-Riverside Cancer Institute", city: "Bourbonnais", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL329",GROUP_DESC:"IL329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4515 ,protection_group_id: -3515, protection_element_id:-3515], primaryKey: false);
      insert('organizations', [ id: 103501, nci_institute_code: "IL330", name: "Resurrection Medical Center", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL330",GROUP_DESC:"IL330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4516 ,protection_group_id: -3516, protection_element_id:-3516], primaryKey: false);
      insert('organizations', [ id: 103502, nci_institute_code: "IL331", name: "Advanced Surgical Associates", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL331",GROUP_DESC:"IL331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4517 ,protection_group_id: -3517, protection_element_id:-3517], primaryKey: false);
      insert('organizations', [ id: 103503, nci_institute_code: "IL332", name: "Clintell Inc", city: "Skokie", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL332",GROUP_DESC:"IL332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4518 ,protection_group_id: -3518, protection_element_id:-3518], primaryKey: false);
      insert('organizations', [ id: 103504, nci_institute_code: "IL333", name: "Oncology and Hematology Care Center of the Quad Cities", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL333",GROUP_DESC:"IL333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4519 ,protection_group_id: -3519, protection_element_id:-3519], primaryKey: false);
      insert('organizations', [ id: 103505, nci_institute_code: "IL334", name: "Saint Clare's Hosptial", city: "Alton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL334",GROUP_DESC:"IL334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4520 ,protection_group_id: -3520, protection_element_id:-3520], primaryKey: false);
      insert('organizations', [ id: 103506, nci_institute_code: "IL335", name: "Kellogg Cancer Care Pharmacy", city: "Highland Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL335",GROUP_DESC:"IL335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4521 ,protection_group_id: -3521, protection_element_id:-3521], primaryKey: false);
      insert('organizations', [ id: 103507, nci_institute_code: "IL336", name: "Cancer Care Specialists Ltd", city: "Tinley Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL336",GROUP_DESC:"IL336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4522 ,protection_group_id: -3522, protection_element_id:-3522], primaryKey: false);
      insert('organizations', [ id: 103508, nci_institute_code: "IL337", name: "Surgical Consultants Limited", city: "Palos Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL337",GROUP_DESC:"IL337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4523 ,protection_group_id: -3523, protection_element_id:-3523], primaryKey: false);
      insert('organizations', [ id: 103509, nci_institute_code: "IL338", name: "Iwanetz Medical Clinic", city: "Dolton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL338",GROUP_DESC:"IL338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4524 ,protection_group_id: -3524, protection_element_id:-3524], primaryKey: false);
      insert('organizations', [ id: 103510, nci_institute_code: "IL339", name: "Suburban Surgical Care Specialists SC - Arlington Heights", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL339",GROUP_DESC:"IL339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4525 ,protection_group_id: -3525, protection_element_id:-3525], primaryKey: false);
      insert('organizations', [ id: 103511, nci_institute_code: "IL340", name: "Center for Comprehensive Cancer Care", city: "Mount Vernon", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL340",GROUP_DESC:"IL340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4526 ,protection_group_id: -3526, protection_element_id:-3526], primaryKey: false);
      insert('organizations', [ id: 103512, nci_institute_code: "IL341", name: "Midwest Center for Cancer and Blood", city: "Sandwich", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL341",GROUP_DESC:"IL341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4527 ,protection_group_id: -3527, protection_element_id:-3527], primaryKey: false);
      insert('organizations', [ id: 103513, nci_institute_code: "IL342", name: "Ira J Piel MD FACP SC", city: "Gurnee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL342",GROUP_DESC:"IL342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4528 ,protection_group_id: -3528, protection_element_id:-3528], primaryKey: false);
      insert('organizations', [ id: 103514, nci_institute_code: "IL343", name: "Ann Kinnealey MD PC", city: "Evanston", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL343",GROUP_DESC:"IL343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4529 ,protection_group_id: -3529, protection_element_id:-3529], primaryKey: false);
      insert('organizations', [ id: 103515, nci_institute_code: "IL344", name: "Oncology Hematology Associates of Central Illinois PC", city: "Kewanee", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL344",GROUP_DESC:"IL344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4530 ,protection_group_id: -3530, protection_element_id:-3530], primaryKey: false);
      insert('organizations', [ id: 103516, nci_institute_code: "IL345", name: "Elmhurst Memorial Oncology Services LLC", city: "Lombard", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL345",GROUP_DESC:"IL345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4531 ,protection_group_id: -3531, protection_element_id:-3531], primaryKey: false);
      insert('organizations', [ id: 103517, nci_institute_code: "IL346", name: "Saint Alexius Medical Center", city: "Hoffman Estates", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL346",GROUP_DESC:"IL346 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL346",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL346",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL346", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4532 ,protection_group_id: -3532, protection_element_id:-3532], primaryKey: false);
      insert('organizations', [ id: 103518, nci_institute_code: "IL347", name: "Northwest Neurosurgery Institute LLC", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL347",GROUP_DESC:"IL347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4533 ,protection_group_id: -3533, protection_element_id:-3533], primaryKey: false);
      insert('organizations', [ id: 103519, nci_institute_code: "IN001", name: "Dow Chemical Company", city: "Zionsville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN001",GROUP_DESC:"IN001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4534 ,protection_group_id: -3534, protection_element_id:-3534], primaryKey: false);
      insert('organizations', [ id: 103520, nci_institute_code: "IN002", name: "Saint Francis Hospital and Health Centers", city: "Beech Grove", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN002",GROUP_DESC:"IN002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4535 ,protection_group_id: -3535, protection_element_id:-3535], primaryKey: false);
      insert('organizations', [ id: 103521, nci_institute_code: "IN003", name: "Methodist Hospital", city: "Merrillville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN003",GROUP_DESC:"IN003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4536 ,protection_group_id: -3536, protection_element_id:-3536], primaryKey: false);
      insert('organizations', [ id: 103522, nci_institute_code: "IN004", name: "Richard L. Roudebush Veterans Affairs Medical Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN004",GROUP_DESC:"IN004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4537 ,protection_group_id: -3537, protection_element_id:-3537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 103523, nci_institute_code: "IN005", name: "Clarian Health", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN005",GROUP_DESC:"IN005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4538 ,protection_group_id: -3538, protection_element_id:-3538], primaryKey: false);
      insert('organizations', [ id: 103524, nci_institute_code: "IN006", name: "Community Regional Cancer Care-North", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN006",GROUP_DESC:"IN006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4539 ,protection_group_id: -3539, protection_element_id:-3539], primaryKey: false);
      insert('organizations', [ id: 103525, nci_institute_code: "IN007", name: "Indiana University Medical Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN007",GROUP_DESC:"IN007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4540 ,protection_group_id: -3540, protection_element_id:-3540], primaryKey: false);
      insert('organizations', [ id: 103526, nci_institute_code: "IN009", name: "Riley Hospital for Children", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN009",GROUP_DESC:"IN009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4541 ,protection_group_id: -3541, protection_element_id:-3541], primaryKey: false);
      insert('organizations', [ id: 103527, nci_institute_code: "IN010", name: "Saint Vincent Hospital and Health Services", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN010",GROUP_DESC:"IN010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4542 ,protection_group_id: -3542, protection_element_id:-3542], primaryKey: false);
      insert('organizations', [ id: 103528, nci_institute_code: "IN011", name: "Saint Anthony Medical Center", city: "Crown Point", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN011",GROUP_DESC:"IN011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4543 ,protection_group_id: -3543, protection_element_id:-3543], primaryKey: false);
      insert('organizations', [ id: 103529, nci_institute_code: "IN012", name: "Saint Catherine Hospital", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN012",GROUP_DESC:"IN012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4544 ,protection_group_id: -3544, protection_element_id:-3544], primaryKey: false);
      insert('organizations', [ id: 103530, nci_institute_code: "IN013", name: "Porter Memorial Hospital", city: "Valparaiso", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN013",GROUP_DESC:"IN013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4545 ,protection_group_id: -3545, protection_element_id:-3545], primaryKey: false);
      insert('organizations', [ id: 103531, nci_institute_code: "IN014", name: "Gary Methodist Hospital", city: "Gary", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN014",GROUP_DESC:"IN014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4546 ,protection_group_id: -3546, protection_element_id:-3546], primaryKey: false);
      insert('organizations', [ id: 103532, nci_institute_code: "IN015", name: "Saint Joseph's Medical Center", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN015",GROUP_DESC:"IN015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4547 ,protection_group_id: -3547, protection_element_id:-3547], primaryKey: false);
      insert('organizations', [ id: 103533, nci_institute_code: "IN016", name: "Pediatric Oncology Hematology of Indiana", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN016",GROUP_DESC:"IN016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4548 ,protection_group_id: -3548, protection_element_id:-3548], primaryKey: false);
      insert('organizations', [ id: 103534, nci_institute_code: "IN017", name: "Parkview Memorial Hospital", city: "Fort Wayne", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN017",GROUP_DESC:"IN017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4549 ,protection_group_id: -3549, protection_element_id:-3549], primaryKey: false);
      insert('organizations', [ id: 103535, nci_institute_code: "IN018", name: "Lutheran Hospital of Indiana", city: "Fort Wayne", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN018",GROUP_DESC:"IN018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4550 ,protection_group_id: -3550, protection_element_id:-3550], primaryKey: false);
      insert('organizations', [ id: 103536, nci_institute_code: "IN019", name: "Saint Joseph's Memorial Hospital", city: "Kokomo", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN019",GROUP_DESC:"IN019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4551 ,protection_group_id: -3551, protection_element_id:-3551], primaryKey: false);
      insert('organizations', [ id: 103537, nci_institute_code: "IN022", name: "Ball Memorial Hospital", city: "Muncie", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN022",GROUP_DESC:"IN022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4552 ,protection_group_id: -3552, protection_element_id:-3552], primaryKey: false);
      insert('organizations', [ id: 103538, nci_institute_code: "IN024", name: "Mead Johnson and Company", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN024",GROUP_DESC:"IN024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4553 ,protection_group_id: -3553, protection_element_id:-3553], primaryKey: false);
      insert('organizations', [ id: 103539, nci_institute_code: "IN025", name: "Saint Mary's Medical Center", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN025",GROUP_DESC:"IN025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4554 ,protection_group_id: -3554, protection_element_id:-3554], primaryKey: false);
      insert('organizations', [ id: 103540, nci_institute_code: "IN026", name: "Union Hospital", city: "Terre Haute", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN026",GROUP_DESC:"IN026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4555 ,protection_group_id: -3555, protection_element_id:-3555], primaryKey: false);
      insert('organizations', [ id: 103541, nci_institute_code: "IN027", name: "Saint Elizabeth Hospital", city: "Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN027",GROUP_DESC:"IN027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4556 ,protection_group_id: -3556, protection_element_id:-3556], primaryKey: false);
      insert('organizations', [ id: 103542, nci_institute_code: "IN028", name: "Purdue University", city: "West Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN028",GROUP_DESC:"IN028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4557 ,protection_group_id: -3557, protection_element_id:-3557], primaryKey: false);
      insert('organizations', [ id: 103543, nci_institute_code: "IN029", name: "Methodist Hospital.", city: "Beach Grove", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN029",GROUP_DESC:"IN029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4558 ,protection_group_id: -3558, protection_element_id:-3558], primaryKey: false);
      insert('organizations', [ id: 103544, nci_institute_code: "IN030", name: "Elkhart General Hospital", city: "Elkhart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN030",GROUP_DESC:"IN030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4559 ,protection_group_id: -3559, protection_element_id:-3559], primaryKey: false);
      insert('organizations', [ id: 103545, nci_institute_code: "IN032", name: "University Heights Cancer Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN032",GROUP_DESC:"IN032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4560 ,protection_group_id: -3560, protection_element_id:-3560], primaryKey: false);
      insert('organizations', [ id: 103546, nci_institute_code: "IN033", name: "Hoosier Oncology Group", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN033",GROUP_DESC:"IN033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4561 ,protection_group_id: -3561, protection_element_id:-3561], primaryKey: false);
      insert('organizations', [ id: 103547, nci_institute_code: "IN034", name: "Reid Hospital and Health Care Services", city: "Richmond", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN034",GROUP_DESC:"IN034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4562 ,protection_group_id: -3562, protection_element_id:-3562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 103548, nci_institute_code: "IN035", name: "Laporte Hospital", city: "La Porte", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN035",GROUP_DESC:"IN035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4563 ,protection_group_id: -3563, protection_element_id:-3563], primaryKey: false);
      insert('organizations', [ id: 103549, nci_institute_code: "IN036", name: "Memorial Hospital of South Bend", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN036",GROUP_DESC:"IN036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4564 ,protection_group_id: -3564, protection_element_id:-3564], primaryKey: false);
      insert('organizations', [ id: 103550, nci_institute_code: "IN037", name: "Lafayette Home Hospital", city: "Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN037",GROUP_DESC:"IN037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4565 ,protection_group_id: -3565, protection_element_id:-3565], primaryKey: false);
      insert('organizations', [ id: 103551, nci_institute_code: "IN038", name: "Wishard Hospital", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN038",GROUP_DESC:"IN038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4566 ,protection_group_id: -3566, protection_element_id:-3566], primaryKey: false);
      insert('organizations', [ id: 103552, nci_institute_code: "IN039", name: "Columbus Regional Hospital", city: "Columbus", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN039",GROUP_DESC:"IN039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4567 ,protection_group_id: -3567, protection_element_id:-3567], primaryKey: false);
      insert('organizations', [ id: 103553, nci_institute_code: "IN040", name: "Indianapolis Breast Clinic", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN040",GROUP_DESC:"IN040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4568 ,protection_group_id: -3568, protection_element_id:-3568], primaryKey: false);
      insert('organizations', [ id: 103554, nci_institute_code: "IN041", name: "Daviess County Hospital", city: "Washington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN041",GROUP_DESC:"IN041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4569 ,protection_group_id: -3569, protection_element_id:-3569], primaryKey: false);
      insert('organizations', [ id: 103555, nci_institute_code: "IN042", name: "Deaconess Hospital", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN042",GROUP_DESC:"IN042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4570 ,protection_group_id: -3570, protection_element_id:-3570], primaryKey: false);
      insert('organizations', [ id: 103556, nci_institute_code: "IN043", name: "Arnett Clinic", city: "Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN043",GROUP_DESC:"IN043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4571 ,protection_group_id: -3571, protection_element_id:-3571], primaryKey: false);
      insert('organizations', [ id: 103557, nci_institute_code: "IN044", name: "The Community Hospital", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN044",GROUP_DESC:"IN044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4572 ,protection_group_id: -3572, protection_element_id:-3572], primaryKey: false);
      insert('organizations', [ id: 103558, nci_institute_code: "IN046", name: "Clarian Health - Methodist Hospital", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN046",GROUP_DESC:"IN046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4573 ,protection_group_id: -3573, protection_element_id:-3573], primaryKey: false);
      insert('organizations', [ id: 103559, nci_institute_code: "IN047", name: "Cancer Health Association, P.C.", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN047",GROUP_DESC:"IN047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4574 ,protection_group_id: -3574, protection_element_id:-3574], primaryKey: false);
      insert('organizations', [ id: 103560, nci_institute_code: "IN048", name: "Southern In Cancer Center", city: "Jeffersonville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN048",GROUP_DESC:"IN048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4575 ,protection_group_id: -3575, protection_element_id:-3575], primaryKey: false);
      insert('organizations', [ id: 103561, nci_institute_code: "IN049", name: "Therapy Associates Inc-Evansville Cancer Center", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN049",GROUP_DESC:"IN049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4576 ,protection_group_id: -3576, protection_element_id:-3576], primaryKey: false);
      insert('organizations', [ id: 103562, nci_institute_code: "IN050", name: "Medical Consultants", city: "Muncie", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN050",GROUP_DESC:"IN050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4577 ,protection_group_id: -3577, protection_element_id:-3577], primaryKey: false);
      insert('organizations', [ id: 103563, nci_institute_code: "IN051", name: "Michiana Hematology Oncology P.C.", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN051",GROUP_DESC:"IN051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4578 ,protection_group_id: -3578, protection_element_id:-3578], primaryKey: false);
      insert('organizations', [ id: 103564, nci_institute_code: "IN052", name: "Cancer Health Associate", city: "Merrillville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN052",GROUP_DESC:"IN052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4579 ,protection_group_id: -3579, protection_element_id:-3579], primaryKey: false);
      insert('organizations', [ id: 103565, nci_institute_code: "IN053", name: "American Health Network Inc", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN053",GROUP_DESC:"IN053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4580 ,protection_group_id: -3580, protection_element_id:-3580], primaryKey: false);
      insert('organizations', [ id: 103566, nci_institute_code: "IN054", name: "Michiana Hematology-Oncology, Professional Corporation", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN054",GROUP_DESC:"IN054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4581 ,protection_group_id: -3581, protection_element_id:-3581], primaryKey: false);
      insert('organizations', [ id: 103567, nci_institute_code: "IN055", name: "Caylor-Nickel Hospital", city: "Bluffon", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN055",GROUP_DESC:"IN055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4582 ,protection_group_id: -3582, protection_element_id:-3582], primaryKey: false);
      insert('organizations', [ id: 103568, nci_institute_code: "IN056", name: "Wabash County Hospital", city: "Wabash", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN056",GROUP_DESC:"IN056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4583 ,protection_group_id: -3583, protection_element_id:-3583], primaryKey: false);
      insert('organizations', [ id: 103569, nci_institute_code: "IN057", name: "Memorial Hospital of Jackson County", city: "Seymour", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN057",GROUP_DESC:"IN057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4584 ,protection_group_id: -3584, protection_element_id:-3584], primaryKey: false);
      insert('organizations', [ id: 103570, nci_institute_code: "IN058", name: "Putnam County Hospital", city: "Greencastle", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN058",GROUP_DESC:"IN058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4585 ,protection_group_id: -3585, protection_element_id:-3585], primaryKey: false);
      insert('organizations', [ id: 103571, nci_institute_code: "IN059", name: "Tipton County Memorial Hospital", city: "Tipton", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN059",GROUP_DESC:"IN059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4586 ,protection_group_id: -3586, protection_element_id:-3586], primaryKey: false);
      insert('organizations', [ id: 103572, nci_institute_code: "IN060", name: "White County Memorial Hospital", city: "Monticello", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN060",GROUP_DESC:"IN060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4587 ,protection_group_id: -3587, protection_element_id:-3587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 103573, nci_institute_code: "IN061", name: "King's Daughters Hospital", city: "Madison", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN061",GROUP_DESC:"IN061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4588 ,protection_group_id: -3588, protection_element_id:-3588], primaryKey: false);
      insert('organizations', [ id: 103574, nci_institute_code: "IN062", name: "Howard Regional Healthcare System", city: "Kokomo", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN062",GROUP_DESC:"IN062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4589 ,protection_group_id: -3589, protection_element_id:-3589], primaryKey: false);
      insert('organizations', [ id: 103575, nci_institute_code: "IN063", name: "Good Samaritan Hospital", city: "Vincennes", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN063",GROUP_DESC:"IN063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4590 ,protection_group_id: -3590, protection_element_id:-3590], primaryKey: false);
      insert('organizations', [ id: 103576, nci_institute_code: "IN064", name: "Saint John's Health System Cancer Center", city: "Anderson", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN064",GROUP_DESC:"IN064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4591 ,protection_group_id: -3591, protection_element_id:-3591], primaryKey: false);
      insert('organizations', [ id: 103577, nci_institute_code: "IN065", name: "Oncology/Hematology Department", city: "Porter", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN065",GROUP_DESC:"IN065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4592 ,protection_group_id: -3592, protection_element_id:-3592], primaryKey: false);
      insert('organizations', [ id: 103578, nci_institute_code: "IN066", name: "Cancer Care Center", city: "New Albany", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN066",GROUP_DESC:"IN066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4593 ,protection_group_id: -3593, protection_element_id:-3593], primaryKey: false);
      insert('organizations', [ id: 103579, nci_institute_code: "IN071", name: "Cancer Care Center of Southern Indiana", city: "Bloomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN071",GROUP_DESC:"IN071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4594 ,protection_group_id: -3594, protection_element_id:-3594], primaryKey: false);
      insert('organizations', [ id: 103580, nci_institute_code: "IN072", name: "Cancer Care Specialists, P.C.", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN072",GROUP_DESC:"IN072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4595 ,protection_group_id: -3595, protection_element_id:-3595], primaryKey: false);
      insert('organizations', [ id: 103581, nci_institute_code: "IN073", name: "Arnett Cancer Care", city: "Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN073",GROUP_DESC:"IN073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4596 ,protection_group_id: -3596, protection_element_id:-3596], primaryKey: false);
      insert('organizations', [ id: 103582, nci_institute_code: "IN074", name: "Fort Wayne Medical Oncology and Hematology Inc - State Boulevard", city: "Fort Wayne", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN074",GROUP_DESC:"IN074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4597 ,protection_group_id: -3597, protection_element_id:-3597], primaryKey: false);
      insert('organizations', [ id: 103583, nci_institute_code: "IN075", name: "Bedford Regional Medical Center", city: "Bedford", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN075",GROUP_DESC:"IN075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4598 ,protection_group_id: -3598, protection_element_id:-3598], primaryKey: false);
      insert('organizations', [ id: 103584, nci_institute_code: "IN076", name: "Oncology Hematology Associates of South West Indiana - Saint Mary's Cancer Center", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN076",GROUP_DESC:"IN076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4599 ,protection_group_id: -3599, protection_element_id:-3599], primaryKey: false);
      insert('organizations', [ id: 103585, nci_institute_code: "IN077", name: "Indiana Community Cancer Care", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN077",GROUP_DESC:"IN077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4600 ,protection_group_id: -3600, protection_element_id:-3600], primaryKey: false);
      insert('organizations', [ id: 103586, nci_institute_code: "IN078", name: "Riverview Hospital", city: "Noblesville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN078",GROUP_DESC:"IN078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4601 ,protection_group_id: -3601, protection_element_id:-3601], primaryKey: false);
      insert('organizations', [ id: 103587, nci_institute_code: "IN079", name: "Child Life Center", city: "Merrillville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN079",GROUP_DESC:"IN079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4602 ,protection_group_id: -3602, protection_element_id:-3602], primaryKey: false);
      insert('organizations', [ id: 103588, nci_institute_code: "IN080", name: "Saint Joseph Regional Medical Center - Mishawaka", city: "Plymouth", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN080",GROUP_DESC:"IN080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4603 ,protection_group_id: -3603, protection_element_id:-3603], primaryKey: false);
      insert('organizations', [ id: 103589, nci_institute_code: "IN081", name: "Elkhart Clinic", city: "Elkhart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN081",GROUP_DESC:"IN081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4604 ,protection_group_id: -3604, protection_element_id:-3604], primaryKey: false);
      insert('organizations', [ id: 103590, nci_institute_code: "IN082", name: "Saint Joseph's Reg. Med. Ctr -Plymouth Campus", city: "Plymouth", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN082",GROUP_DESC:"IN082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4605 ,protection_group_id: -3605, protection_element_id:-3605], primaryKey: false);
      insert('organizations', [ id: 103591, nci_institute_code: "IN083", name: "Center for Cancer Care at Goshen Health System", city: "Goshen", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN083",GROUP_DESC:"IN083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4606 ,protection_group_id: -3606, protection_element_id:-3606], primaryKey: false);
      insert('organizations', [ id: 103592, nci_institute_code: "IN085", name: "Bloomington Hospital", city: "Bloomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN085",GROUP_DESC:"IN085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4607 ,protection_group_id: -3607, protection_element_id:-3607], primaryKey: false);
      insert('organizations', [ id: 103593, nci_institute_code: "IN086", name: "Indiana Blood\\Marrow Transplantation, LLC", city: "Beech Grove", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN086",GROUP_DESC:"IN086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4608 ,protection_group_id: -3608, protection_element_id:-3608], primaryKey: false);
      insert('organizations', [ id: 103594, nci_institute_code: "IN087", name: "St. Francis Hospital & Health Centers", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN087",GROUP_DESC:"IN087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4609 ,protection_group_id: -3609, protection_element_id:-3609], primaryKey: false);
      insert('organizations', [ id: 103595, nci_institute_code: "IN088", name: "American Health Network of Indiana LLC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN088",GROUP_DESC:"IN088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4610 ,protection_group_id: -3610, protection_element_id:-3610], primaryKey: false);
      insert('organizations', [ id: 103596, nci_institute_code: "IN089", name: "Assoc Physicians and Surgeons Clinic", city: "Terre Haute", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN089",GROUP_DESC:"IN089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4611 ,protection_group_id: -3611, protection_element_id:-3611], primaryKey: false);
      insert('organizations', [ id: 103597, nci_institute_code: "IN090", name: "Northern Indiana Cancer Research Consortium", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN090",GROUP_DESC:"IN090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4612 ,protection_group_id: -3612, protection_element_id:-3612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 103598, nci_institute_code: "IN091", name: "Oncology Hematology Associates, Inc", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN091",GROUP_DESC:"IN091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4613 ,protection_group_id: -3613, protection_element_id:-3613], primaryKey: false);
      insert('organizations', [ id: 103599, nci_institute_code: "IN092", name: "Oncology and Hematology Associates Inc", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN092",GROUP_DESC:"IN092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4614 ,protection_group_id: -3614, protection_element_id:-3614], primaryKey: false);
      insert('organizations', [ id: 103600, nci_institute_code: "IN093", name: "Northern Indiana Oncology Associates", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN093",GROUP_DESC:"IN093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4615 ,protection_group_id: -3615, protection_element_id:-3615], primaryKey: false);
      insert('organizations', [ id: 103601, nci_institute_code: "IN094", name: "Urology Care Center", city: "Bloomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN094",GROUP_DESC:"IN094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4616 ,protection_group_id: -3616, protection_element_id:-3616], primaryKey: false);
      insert('organizations', [ id: 103602, nci_institute_code: "IN095", name: "Internal Medicine Associates, Inc.", city: "Blomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN095",GROUP_DESC:"IN095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4617 ,protection_group_id: -3617, protection_element_id:-3617], primaryKey: false);
      insert('organizations', [ id: 103603, nci_institute_code: "IN096", name: "Northern Indiana Neurological Ins't", city: "Merrillville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN096",GROUP_DESC:"IN096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4618 ,protection_group_id: -3618, protection_element_id:-3618], primaryKey: false);
      insert('organizations', [ id: 103604, nci_institute_code: "IN097", name: "Marion Cancer Care Center", city: "Marion", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN097",GROUP_DESC:"IN097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4619 ,protection_group_id: -3619, protection_element_id:-3619], primaryKey: false);
      insert('organizations', [ id: 103605, nci_institute_code: "IN098", name: "Central Indiana Cancer Centers - Indianapolis", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN098",GROUP_DESC:"IN098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4620 ,protection_group_id: -3620, protection_element_id:-3620], primaryKey: false);
      insert('organizations', [ id: 103606, nci_institute_code: "IN099", name: "Hematology/Oncology Associates", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN099",GROUP_DESC:"IN099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4621 ,protection_group_id: -3621, protection_element_id:-3621], primaryKey: false);
      insert('organizations', [ id: 103607, nci_institute_code: "IN100", name: "Gerald P. Murphy Cancer Foundation", city: "West Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN100",GROUP_DESC:"IN100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4622 ,protection_group_id: -3622, protection_element_id:-3622], primaryKey: false);
      insert('organizations', [ id: 103608, nci_institute_code: "IN101", name: "Saint Anthony Memorial Health Center", city: "Michigan City", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN101",GROUP_DESC:"IN101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4623 ,protection_group_id: -3623, protection_element_id:-3623], primaryKey: false);
      insert('organizations', [ id: 103609, nci_institute_code: "IN102", name: "Medical Center of Vincennes", city: "Vincennes", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN102",GROUP_DESC:"IN102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4624 ,protection_group_id: -3624, protection_element_id:-3624], primaryKey: false);
      insert('organizations', [ id: 103610, nci_institute_code: "IN103", name: "Evansville Surgical Associates", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN103",GROUP_DESC:"IN103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4625 ,protection_group_id: -3625, protection_element_id:-3625], primaryKey: false);
      insert('organizations', [ id: 103611, nci_institute_code: "IN104", name: "Summit Urology", city: "Bloomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN104",GROUP_DESC:"IN104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4626 ,protection_group_id: -3626, protection_element_id:-3626], primaryKey: false);
      insert('organizations', [ id: 103612, nci_institute_code: "IN105", name: "Central Indiana Cancer Center - Fishers", city: "Fishers", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN105",GROUP_DESC:"IN105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4627 ,protection_group_id: -3627, protection_element_id:-3627], primaryKey: false);
      insert('organizations', [ id: 103613, nci_institute_code: "IN106", name: "Kosciusko Community Hospital", city: "Warsaw", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN106",GROUP_DESC:"IN106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4628 ,protection_group_id: -3628, protection_element_id:-3628], primaryKey: false);
      insert('organizations', [ id: 103614, nci_institute_code: "IN107", name: "Hammond Clinic", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN107",GROUP_DESC:"IN107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4629 ,protection_group_id: -3629, protection_element_id:-3629], primaryKey: false);
      insert('organizations', [ id: 103615, nci_institute_code: "IN108", name: "Michiana Hematology - Oncology, P.C.", city: "Elkhart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN108",GROUP_DESC:"IN108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4630 ,protection_group_id: -3630, protection_element_id:-3630], primaryKey: false);
      insert('organizations', [ id: 103616, nci_institute_code: "IN109", name: "St. Joseph's Radiation Oncology", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN109",GROUP_DESC:"IN109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4631 ,protection_group_id: -3631, protection_element_id:-3631], primaryKey: false);
      insert('organizations', [ id: 103617, nci_institute_code: "IN110", name: "Oncology & Hematology Associates of South West Indiana - Chancellor Cancer Center", city: "Newburgh", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN110",GROUP_DESC:"IN110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4632 ,protection_group_id: -3632, protection_element_id:-3632], primaryKey: false);
      insert('organizations', [ id: 103618, nci_institute_code: "IN111", name: "Cardiac Surgery Associates", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN111",GROUP_DESC:"IN111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4633 ,protection_group_id: -3633, protection_element_id:-3633], primaryKey: false);
      insert('organizations', [ id: 103619, nci_institute_code: "IN112", name: "Premier Oncology Hematology Associates", city: "Merrillville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN112",GROUP_DESC:"IN112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4634 ,protection_group_id: -3634, protection_element_id:-3634], primaryKey: false);
      insert('organizations', [ id: 103620, nci_institute_code: "IN113", name: "Michiana Cancer Center", city: "Michigan City", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN113",GROUP_DESC:"IN113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4635 ,protection_group_id: -3635, protection_element_id:-3635], primaryKey: false);
      insert('organizations', [ id: 103621, nci_institute_code: "IN114", name: "Cancer Care Group PC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN114",GROUP_DESC:"IN114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4636 ,protection_group_id: -3636, protection_element_id:-3636], primaryKey: false);
      insert('organizations', [ id: 103622, nci_institute_code: "IN115", name: "Indiana Gynecologic Oncology", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN115",GROUP_DESC:"IN115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4637 ,protection_group_id: -3637, protection_element_id:-3637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 103623, nci_institute_code: "IN116", name: "General and Vascular Surgery PC-South Bend", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN116",GROUP_DESC:"IN116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4638 ,protection_group_id: -3638, protection_element_id:-3638], primaryKey: false);
      insert('organizations', [ id: 103624, nci_institute_code: "IN118", name: "Urology Associates of South Bend", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN118",GROUP_DESC:"IN118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4639 ,protection_group_id: -3639, protection_element_id:-3639], primaryKey: false);
      insert('organizations', [ id: 103625, nci_institute_code: "IN119", name: "Northwest Oncology, P.C.", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN119",GROUP_DESC:"IN119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4640 ,protection_group_id: -3640, protection_element_id:-3640], primaryKey: false);
      insert('organizations', [ id: 103626, nci_institute_code: "IN120", name: "Saint Margaret Mercy Healthcare Centers- North Campus", city: "Hammond", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN120",GROUP_DESC:"IN120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4641 ,protection_group_id: -3641, protection_element_id:-3641], primaryKey: false);
      insert('organizations', [ id: 103627, nci_institute_code: "IN121", name: "The South Bend Clinic and Surgicenter-Granger", city: "Granger", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN121",GROUP_DESC:"IN121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4642 ,protection_group_id: -3642, protection_element_id:-3642], primaryKey: false);
      insert('organizations', [ id: 103628, nci_institute_code: "IN122", name: "Radiation Oncology Associates, P.C.", city: "Fort Wayne", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN122",GROUP_DESC:"IN122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4643 ,protection_group_id: -3643, protection_element_id:-3643], primaryKey: false);
      insert('organizations', [ id: 103629, nci_institute_code: "IN123", name: "Purdue University Calumet", city: "Hammond", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN123",GROUP_DESC:"IN123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4644 ,protection_group_id: -3644, protection_element_id:-3644], primaryKey: false);
      insert('organizations', [ id: 103630, nci_institute_code: "IN124", name: "Horizon Oncology Center", city: "Lafayette", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN124",GROUP_DESC:"IN124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4645 ,protection_group_id: -3645, protection_element_id:-3645], primaryKey: false);
      insert('organizations', [ id: 103631, nci_institute_code: "IN125", name: "Madison County Cancer Center", city: "Anderson", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN125",GROUP_DESC:"IN125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4646 ,protection_group_id: -3646, protection_element_id:-3646], primaryKey: false);
      insert('organizations', [ id: 103632, nci_institute_code: "IN126", name: "Wagner and Associates", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN126",GROUP_DESC:"IN126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4647 ,protection_group_id: -3647, protection_element_id:-3647], primaryKey: false);
      insert('organizations', [ id: 103633, nci_institute_code: "IN127", name: "Center for Women's Health", city: "Greenwood", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN127",GROUP_DESC:"IN127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4648 ,protection_group_id: -3648, protection_element_id:-3648], primaryKey: false);
      insert('organizations', [ id: 103634, nci_institute_code: "IN128", name: "Community Hospital North", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN128",GROUP_DESC:"IN128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4649 ,protection_group_id: -3649, protection_element_id:-3649], primaryKey: false);
      insert('organizations', [ id: 103635, nci_institute_code: "IN129", name: "Hematology Oncology Associates -- LaPorte County", city: "LaPorte", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN129",GROUP_DESC:"IN129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4650 ,protection_group_id: -3650, protection_element_id:-3650], primaryKey: false);
      insert('organizations', [ id: 103636, nci_institute_code: "IN130", name: "Indiana University Cancer Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN130",GROUP_DESC:"IN130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4651 ,protection_group_id: -3651, protection_element_id:-3651], primaryKey: false);
      insert('organizations', [ id: 103637, nci_institute_code: "IN131", name: "Monroe Medical Associates", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN131",GROUP_DESC:"IN131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4652 ,protection_group_id: -3652, protection_element_id:-3652], primaryKey: false);
      insert('organizations', [ id: 103638, nci_institute_code: "IN132", name: "Allied Hematology Oncology Associates LLC", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN132",GROUP_DESC:"IN132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4653 ,protection_group_id: -3653, protection_element_id:-3653], primaryKey: false);
      insert('organizations', [ id: 103639, nci_institute_code: "IN133", name: "Hematology and Oncology of Indiana PC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN133",GROUP_DESC:"IN133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4654 ,protection_group_id: -3654, protection_element_id:-3654], primaryKey: false);
      insert('organizations', [ id: 103640, nci_institute_code: "IN134", name: "Central Indiana Cancer Centers - Greenfield", city: "Greenfield", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN134",GROUP_DESC:"IN134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4655 ,protection_group_id: -3655, protection_element_id:-3655], primaryKey: false);
      insert('organizations', [ id: 103641, nci_institute_code: "IN135", name: "Clarion North Medical Center", city: "Carmel", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN135",GROUP_DESC:"IN135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4656 ,protection_group_id: -3656, protection_element_id:-3656], primaryKey: false);
      insert('organizations', [ id: 103642, nci_institute_code: "IN136", name: "Bluffton Regional Medical Center", city: "Bluffton", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN136",GROUP_DESC:"IN136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4657 ,protection_group_id: -3657, protection_element_id:-3657], primaryKey: false);
      insert('organizations', [ id: 103643, nci_institute_code: "IN137", name: "Indiana Hemophilia and Thrombosis Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN137",GROUP_DESC:"IN137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4658 ,protection_group_id: -3658, protection_element_id:-3658], primaryKey: false);
      insert('organizations', [ id: 103644, nci_institute_code: "IN138", name: "Saint Mary Medical Center", city: "Hobart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN138",GROUP_DESC:"IN138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4659 ,protection_group_id: -3659, protection_element_id:-3659], primaryKey: false);
      insert('organizations', [ id: 103645, nci_institute_code: "IN139", name: "Monroe Medical Associates - Hobart", city: "Hobart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN139",GROUP_DESC:"IN139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4660 ,protection_group_id: -3660, protection_element_id:-3660], primaryKey: false);
      insert('organizations', [ id: 103646, nci_institute_code: "IN140", name: "Clarian West Medical Center", city: "Avon", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN140",GROUP_DESC:"IN140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4661 ,protection_group_id: -3661, protection_element_id:-3661], primaryKey: false);
      insert('organizations', [ id: 103647, nci_institute_code: "IN141", name: "Indiana Women's Oncology PC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN141",GROUP_DESC:"IN141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4662 ,protection_group_id: -3662, protection_element_id:-3662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 103648, nci_institute_code: "IN142", name: "Gynecologic Oncology of Indiana", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN142",GROUP_DESC:"IN142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4663 ,protection_group_id: -3663, protection_element_id:-3663], primaryKey: false);
      insert('organizations', [ id: 103649, nci_institute_code: "IN143", name: "Indiana University - Purdue University Indianapolis", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN143",GROUP_DESC:"IN143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4664 ,protection_group_id: -3664, protection_element_id:-3664], primaryKey: false);
      insert('organizations', [ id: 103650, nci_institute_code: "IN144", name: "Navarre Medical Group", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN144",GROUP_DESC:"IN144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4665 ,protection_group_id: -3665, protection_element_id:-3665], primaryKey: false);
      insert('organizations', [ id: 103651, nci_institute_code: "IN145", name: "Indiana Radiation Oncology LLC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN145",GROUP_DESC:"IN145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4666 ,protection_group_id: -3666, protection_element_id:-3666], primaryKey: false);
      insert('organizations', [ id: 103652, nci_institute_code: "IN146", name: "Breast Care Center of Indiana", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN146",GROUP_DESC:"IN146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4667 ,protection_group_id: -3667, protection_element_id:-3667], primaryKey: false);
      insert('organizations', [ id: 103653, nci_institute_code: "IN147", name: "Corvasc MD's PC", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN147",GROUP_DESC:"IN147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4668 ,protection_group_id: -3668, protection_element_id:-3668], primaryKey: false);
      insert('organizations', [ id: 103654, nci_institute_code: "IN148", name: "Kendrick Regional Center for Colon and Rectal Care", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN148",GROUP_DESC:"IN148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4669 ,protection_group_id: -3669, protection_element_id:-3669], primaryKey: false);
      insert('organizations', [ id: 103655, nci_institute_code: "IN149", name: "The Center for Wholism", city: "Bloomington", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN149",GROUP_DESC:"IN149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4670 ,protection_group_id: -3670, protection_element_id:-3670], primaryKey: false);
      insert('organizations', [ id: 103656, nci_institute_code: "IN150", name: "Northwest Indiana Oncology PC", city: "Michigan City", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN150",GROUP_DESC:"IN150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4671 ,protection_group_id: -3671, protection_element_id:-3671], primaryKey: false);
      insert('organizations', [ id: 103657, nci_institute_code: "IN151", name: "Saint Vincent Oncology Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN151",GROUP_DESC:"IN151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4672 ,protection_group_id: -3672, protection_element_id:-3672], primaryKey: false);
      insert('organizations', [ id: 103658, nci_institute_code: "IN152", name: "General and Vascular Surgery PC-Elkhart", city: "Elkhart", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN152",GROUP_DESC:"IN152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4673 ,protection_group_id: -3673, protection_element_id:-3673], primaryKey: false);
      insert('organizations', [ id: 103659, nci_institute_code: "IN153", name: "Quality Oncology Care", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN153",GROUP_DESC:"IN153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4674 ,protection_group_id: -3674, protection_element_id:-3674], primaryKey: false);
      insert('organizations', [ id: 103660, nci_institute_code: "IN154", name: "Fort Wayne Medical Oncology and Hematology Inc - Jefferson Boulevard", city: "Fort Wayne", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN154",GROUP_DESC:"IN154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4675 ,protection_group_id: -3675, protection_element_id:-3675], primaryKey: false);
      insert('organizations', [ id: 103661, nci_institute_code: "IN155", name: "Community Regional Cancer Care-East Medical Oncology", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN155",GROUP_DESC:"IN155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4676 ,protection_group_id: -3676, protection_element_id:-3676], primaryKey: false);
      insert('organizations', [ id: 103662, nci_institute_code: "IN156", name: "Community Regional Cancer Care-East Radiation Oncology", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN156",GROUP_DESC:"IN156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4677 ,protection_group_id: -3677, protection_element_id:-3677], primaryKey: false);
      insert('organizations', [ id: 103663, nci_institute_code: "IN157", name: "South Bend Clinic", city: "South Bend", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN157",GROUP_DESC:"IN157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4678 ,protection_group_id: -3678, protection_element_id:-3678], primaryKey: false);
      insert('organizations', [ id: 103664, nci_institute_code: "IRS", name: "Intergroup Rhabdomyosarcoma Study", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IRS",GROUP_DESC:"IRS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IRS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IRS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IRS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4679 ,protection_group_id: -3679, protection_element_id:-3679], primaryKey: false);
      insert('organizations', [ id: 103665, nci_institute_code: "KS002", name: "Wichita Clinic, PA", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS002",GROUP_DESC:"KS002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4680 ,protection_group_id: -3680, protection_element_id:-3680], primaryKey: false);
      insert('organizations', [ id: 103666, nci_institute_code: "KS003", name: "Lawrence Memorial Hospital", city: "Lawrence", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS003",GROUP_DESC:"KS003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4681 ,protection_group_id: -3681, protection_element_id:-3681], primaryKey: false);
      insert('organizations', [ id: 103667, nci_institute_code: "KS004", name: "University of Kansas Medical Center", city: "Kansas City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS004",GROUP_DESC:"KS004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4682 ,protection_group_id: -3682, protection_element_id:-3682], primaryKey: false);
      insert('organizations', [ id: 103668, nci_institute_code: "KS005", name: "Irwin Army Community Hospital", city: "Fort Riley", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS005",GROUP_DESC:"KS005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4683 ,protection_group_id: -3683, protection_element_id:-3683], primaryKey: false);
      insert('organizations', [ id: 103669, nci_institute_code: "KS006", name: "Memorial Hospital", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS006",GROUP_DESC:"KS006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4684 ,protection_group_id: -3684, protection_element_id:-3684], primaryKey: false);
      insert('organizations', [ id: 103670, nci_institute_code: "KS007", name: "Topeka VA Hospital", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS007",GROUP_DESC:"KS007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4685 ,protection_group_id: -3685, protection_element_id:-3685], primaryKey: false);
      insert('organizations', [ id: 103671, nci_institute_code: "KS008", name: "Coffey County Hospital", city: "Burlington", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS008",GROUP_DESC:"KS008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4686 ,protection_group_id: -3686, protection_element_id:-3686], primaryKey: false);
      insert('organizations', [ id: 103672, nci_institute_code: "KS009", name: "Saint Francis Hospital/Witchita Incorporated", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS009",GROUP_DESC:"KS009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4687 ,protection_group_id: -3687, protection_element_id:-3687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 103673, nci_institute_code: "KS010", name: "Wesley Medical Center", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS010",GROUP_DESC:"KS010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4688 ,protection_group_id: -3688, protection_element_id:-3688], primaryKey: false);
      insert('organizations', [ id: 103674, nci_institute_code: "KS011", name: "Saint Francis Regional Medical Center", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS011",GROUP_DESC:"KS011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4689 ,protection_group_id: -3689, protection_element_id:-3689], primaryKey: false);
      insert('organizations', [ id: 103675, nci_institute_code: "KS016", name: "University of Kansas - Wichita", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS016",GROUP_DESC:"KS016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4690 ,protection_group_id: -3690, protection_element_id:-3690], primaryKey: false);
      insert('organizations', [ id: 103676, nci_institute_code: "KS017", name: "Wichita CCOP", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS017",GROUP_DESC:"KS017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4691 ,protection_group_id: -3691, protection_element_id:-3691], primaryKey: false);
      insert('organizations', [ id: 103677, nci_institute_code: "KS018", name: "Capital Region Radiation Oncology Center", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS018",GROUP_DESC:"KS018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4692 ,protection_group_id: -3692, protection_element_id:-3692], primaryKey: false);
      insert('organizations', [ id: 103678, nci_institute_code: "KS019", name: "Saint Joseph Medical Center", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS019",GROUP_DESC:"KS019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4693 ,protection_group_id: -3693, protection_element_id:-3693], primaryKey: false);
      insert('organizations', [ id: 103679, nci_institute_code: "KS020", name: "Olathe Medical Center", city: "Olathe", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS020",GROUP_DESC:"KS020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4694 ,protection_group_id: -3694, protection_element_id:-3694], primaryKey: false);
      insert('organizations', [ id: 103680, nci_institute_code: "KS021", name: "Harry Hynes Memorial Hospice", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS021",GROUP_DESC:"KS021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4695 ,protection_group_id: -3695, protection_element_id:-3695], primaryKey: false);
      insert('organizations', [ id: 103681, nci_institute_code: "KS022", name: "Cotton-O'Neil Clinic, P.A.", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS022",GROUP_DESC:"KS022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4696 ,protection_group_id: -3696, protection_element_id:-3696], primaryKey: false);
      insert('organizations', [ id: 103682, nci_institute_code: "KS023", name: "Mount Carmel Regional Cancer Center", city: "Pittsburg", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS023",GROUP_DESC:"KS023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4697 ,protection_group_id: -3697, protection_element_id:-3697], primaryKey: false);
      insert('organizations', [ id: 103683, nci_institute_code: "KS024", name: "Wichita State University", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS024",GROUP_DESC:"KS024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4698 ,protection_group_id: -3698, protection_element_id:-3698], primaryKey: false);
      insert('organizations', [ id: 103684, nci_institute_code: "KS025", name: "Phillips County Hospital", city: "Phillipsburg", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS025",GROUP_DESC:"KS025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4699 ,protection_group_id: -3699, protection_element_id:-3699], primaryKey: false);
      insert('organizations', [ id: 103685, nci_institute_code: "KS026", name: "Hutchinson Hospital", city: "Hutchinson", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS026",GROUP_DESC:"KS026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4700 ,protection_group_id: -3700, protection_element_id:-3700], primaryKey: false);
      insert('organizations', [ id: 103686, nci_institute_code: "KS027", name: "Stormont-Vail Regional Health Center", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS027",GROUP_DESC:"KS027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4701 ,protection_group_id: -3701, protection_element_id:-3701], primaryKey: false);
      insert('organizations', [ id: 103687, nci_institute_code: "KS028", name: "Georgetown Medical Building", city: "Shawnee Mission", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS028",GROUP_DESC:"KS028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4702 ,protection_group_id: -3702, protection_element_id:-3702], primaryKey: false);
      insert('organizations', [ id: 103688, nci_institute_code: "KS029", name: "Salina Regional Health Center.", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS029",GROUP_DESC:"KS029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4703 ,protection_group_id: -3703, protection_element_id:-3703], primaryKey: false);
      insert('organizations', [ id: 103689, nci_institute_code: "KS030", name: "Bethany Medical Center", city: "Kansas City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS030",GROUP_DESC:"KS030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4704 ,protection_group_id: -3704, protection_element_id:-3704], primaryKey: false);
      insert('organizations', [ id: 103690, nci_institute_code: "KS031", name: "Saint John Hospital", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS031",GROUP_DESC:"KS031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4705 ,protection_group_id: -3705, protection_element_id:-3705], primaryKey: false);
      insert('organizations', [ id: 103691, nci_institute_code: "KS032", name: "Salina Breast Cancer Prevention and Treatment Subcenter", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS032",GROUP_DESC:"KS032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4706 ,protection_group_id: -3706, protection_element_id:-3706], primaryKey: false);
      insert('organizations', [ id: 103692, nci_institute_code: "KS033", name: "Hutchinson Clinic", city: "Hutchinson", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS033",GROUP_DESC:"KS033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4707 ,protection_group_id: -3707, protection_element_id:-3707], primaryKey: false);
      insert('organizations', [ id: 103693, nci_institute_code: "KS034", name: "Cancer Center of Kansas - Main Office", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS034",GROUP_DESC:"KS034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4708 ,protection_group_id: -3708, protection_element_id:-3708], primaryKey: false);
      insert('organizations', [ id: 103694, nci_institute_code: "KS035", name: "Hays Medical Center", city: "Hays", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS035",GROUP_DESC:"KS035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4709 ,protection_group_id: -3709, protection_element_id:-3709], primaryKey: false);
      insert('organizations', [ id: 103695, nci_institute_code: "KS036", name: "Cancer Center of Kansas - Dodge City", city: "Dodge City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS036",GROUP_DESC:"KS036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4710 ,protection_group_id: -3710, protection_element_id:-3710], primaryKey: false);
      insert('organizations', [ id: 103696, nci_institute_code: "KS037", name: "Menorah Medical Center", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS037",GROUP_DESC:"KS037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4711 ,protection_group_id: -3711, protection_element_id:-3711], primaryKey: false);
      insert('organizations', [ id: 103697, nci_institute_code: "KS038", name: "Oncology/Hematology Associates of Kansas City", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS038",GROUP_DESC:"KS038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4712 ,protection_group_id: -3712, protection_element_id:-3712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 103698, nci_institute_code: "KS039", name: "Radiation Oncology Practice Corporation Southwest", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS039",GROUP_DESC:"KS039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4713 ,protection_group_id: -3713, protection_element_id:-3713], primaryKey: false);
      insert('organizations', [ id: 103699, nci_institute_code: "KS040", name: "Overland Park Regional Medical Center", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS040",GROUP_DESC:"KS040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4714 ,protection_group_id: -3714, protection_element_id:-3714], primaryKey: false);
      insert('organizations', [ id: 103700, nci_institute_code: "KS041", name: "Providence Medical Center", city: "Kansas City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS041",GROUP_DESC:"KS041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4715 ,protection_group_id: -3715, protection_element_id:-3715], primaryKey: false);
      insert('organizations', [ id: 103701, nci_institute_code: "KS042", name: "Radiation Oncology Center of Olathe", city: "Olathe", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS042",GROUP_DESC:"KS042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4716 ,protection_group_id: -3716, protection_element_id:-3716], primaryKey: false);
      insert('organizations', [ id: 103702, nci_institute_code: "KS043", name: "Shawnee Mission Medical Center", city: "Shawnee Mission", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS043",GROUP_DESC:"KS043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4717 ,protection_group_id: -3717, protection_element_id:-3717], primaryKey: false);
      insert('organizations', [ id: 103703, nci_institute_code: "KS044", name: "Memorial Hospital of Arkansas City", city: "Arkansas City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS044",GROUP_DESC:"KS044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4718 ,protection_group_id: -3718, protection_element_id:-3718], primaryKey: false);
      insert('organizations', [ id: 103704, nci_institute_code: "KS045", name: "Neosha Memorial Regional", city: "Chanute", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS045",GROUP_DESC:"KS045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4719 ,protection_group_id: -3719, protection_element_id:-3719], primaryKey: false);
      insert('organizations', [ id: 103705, nci_institute_code: "KS046", name: "Veteran's Administration Medical Center", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS046",GROUP_DESC:"KS046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4720 ,protection_group_id: -3720, protection_element_id:-3720], primaryKey: false);
      insert('organizations', [ id: 103706, nci_institute_code: "KS047", name: "Western Plains Regional Hospital", city: "Dodge City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS047",GROUP_DESC:"KS047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4721 ,protection_group_id: -3721, protection_element_id:-3721], primaryKey: false);
      insert('organizations', [ id: 103707, nci_institute_code: "KS048", name: "William Newton Memorial Hospital", city: "Winfield", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS048",GROUP_DESC:"KS048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4722 ,protection_group_id: -3722, protection_element_id:-3722], primaryKey: false);
      insert('organizations', [ id: 103708, nci_institute_code: "KS049", name: "Lawrence Internal Medicine, PA", city: "Lawrence", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS049",GROUP_DESC:"KS049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4723 ,protection_group_id: -3723, protection_element_id:-3723], primaryKey: false);
      insert('organizations', [ id: 103709, nci_institute_code: "KS050", name: "Cancer Center of Kansas - Winfield", city: "Winfield", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS050",GROUP_DESC:"KS050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4724 ,protection_group_id: -3724, protection_element_id:-3724], primaryKey: false);
      insert('organizations', [ id: 103710, nci_institute_code: "KS051", name: "Cancer Center of Kansas - Chanute", city: "Chanute", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS051",GROUP_DESC:"KS051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4725 ,protection_group_id: -3725, protection_element_id:-3725], primaryKey: false);
      insert('organizations', [ id: 103711, nci_institute_code: "KS052", name: "Cancer Center of Kansas - Newton", city: "Newton", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS052",GROUP_DESC:"KS052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4726 ,protection_group_id: -3726, protection_element_id:-3726], primaryKey: false);
      insert('organizations', [ id: 103712, nci_institute_code: "KS053", name: "Cancer Center of Kansas - Wellington", city: "Wellington", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS053",GROUP_DESC:"KS053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4727 ,protection_group_id: -3727, protection_element_id:-3727], primaryKey: false);
      insert('organizations', [ id: 103713, nci_institute_code: "KS054", name: "Cancer Center of Kansas - Salina", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS054",GROUP_DESC:"KS054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4728 ,protection_group_id: -3728, protection_element_id:-3728], primaryKey: false);
      insert('organizations', [ id: 103714, nci_institute_code: "KS055", name: "Via Christi Regional Medical Center", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS055",GROUP_DESC:"KS055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4729 ,protection_group_id: -3729, protection_element_id:-3729], primaryKey: false);
      insert('organizations', [ id: 103715, nci_institute_code: "KS056", name: "Associates In Womens Health", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS056",GROUP_DESC:"KS056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4730 ,protection_group_id: -3730, protection_element_id:-3730], primaryKey: false);
      insert('organizations', [ id: 103716, nci_institute_code: "KS057", name: "Central Care Cancer Center", city: "Newton", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS057",GROUP_DESC:"KS057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4731 ,protection_group_id: -3731, protection_element_id:-3731], primaryKey: false);
      insert('organizations', [ id: 103717, nci_institute_code: "KS058", name: "The University of Kansas", city: "Lawrence", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS058",GROUP_DESC:"KS058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4732 ,protection_group_id: -3732, protection_element_id:-3732], primaryKey: false);
      insert('organizations', [ id: 103718, nci_institute_code: "KS059", name: "Saint Francis Hospital and Medical Center - Topeka", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS059",GROUP_DESC:"KS059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4733 ,protection_group_id: -3733, protection_element_id:-3733], primaryKey: false);
      insert('organizations', [ id: 103719, nci_institute_code: "KS060", name: "Veteran Administration Eastern Kansas Healthcare", city: "Leavenworth", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS060",GROUP_DESC:"KS060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4734 ,protection_group_id: -3734, protection_element_id:-3734], primaryKey: false);
      insert('organizations', [ id: 103720, nci_institute_code: "KS061", name: "Kansas City Cancer Centers", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS061",GROUP_DESC:"KS061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4735 ,protection_group_id: -3735, protection_element_id:-3735], primaryKey: false);
      insert('organizations', [ id: 103721, nci_institute_code: "KS062", name: "Bio-Communications Research Institute", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS062",GROUP_DESC:"KS062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4736 ,protection_group_id: -3736, protection_element_id:-3736], primaryKey: false);
      insert('organizations', [ id: 103722, nci_institute_code: "KS063", name: "Cancer Center of Kansas - Pratt", city: "Pratt", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS063",GROUP_DESC:"KS063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4737 ,protection_group_id: -3737, protection_element_id:-3737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 103723, nci_institute_code: "KS064", name: "Southwest Medical Center", city: "Liberal", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS064",GROUP_DESC:"KS064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4738 ,protection_group_id: -3738, protection_element_id:-3738], primaryKey: false);
      insert('organizations', [ id: 103724, nci_institute_code: "KS065", name: "Halstead Hertzler Cancer Center of Kansas", city: "Halstead", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS065",GROUP_DESC:"KS065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4739 ,protection_group_id: -3739, protection_element_id:-3739], primaryKey: false);
      insert('organizations', [ id: 103725, nci_institute_code: "KS066", name: "Cancer Center of Kansas-Kingman", city: "Kingman", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS066",GROUP_DESC:"KS066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4740 ,protection_group_id: -3740, protection_element_id:-3740], primaryKey: false);
      insert('organizations', [ id: 103726, nci_institute_code: "KS067", name: "Cancer Center of Kansas-Medical Arts Tower", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS067",GROUP_DESC:"KS067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4741 ,protection_group_id: -3741, protection_element_id:-3741], primaryKey: false);
      insert('organizations', [ id: 103727, nci_institute_code: "KS068", name: "Kansas Pediatric Hematology & Oncology", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS068",GROUP_DESC:"KS068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4742 ,protection_group_id: -3742, protection_element_id:-3742], primaryKey: false);
      insert('organizations', [ id: 103728, nci_institute_code: "KS069", name: "Cancer Center of Kansas - McPherson", city: "McPherson", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS069",GROUP_DESC:"KS069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4743 ,protection_group_id: -3743, protection_element_id:-3743], primaryKey: false);
      insert('organizations', [ id: 103729, nci_institute_code: "KS070", name: "Kansas Surgical Consultants, LLP", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS070",GROUP_DESC:"KS070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4744 ,protection_group_id: -3744, protection_element_id:-3744], primaryKey: false);
      insert('organizations', [ id: 103730, nci_institute_code: "KS071", name: "Wichita Urology Group", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS071",GROUP_DESC:"KS071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4745 ,protection_group_id: -3745, protection_element_id:-3745], primaryKey: false);
      insert('organizations', [ id: 103731, nci_institute_code: "KS072", name: "Midwest Surgical", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS072",GROUP_DESC:"KS072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4746 ,protection_group_id: -3746, protection_element_id:-3746], primaryKey: false);
      insert('organizations', [ id: 103732, nci_institute_code: "KS073", name: "Cancer Center of Kansas - Parsons", city: "Parsons", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS073",GROUP_DESC:"KS073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4747 ,protection_group_id: -3747, protection_element_id:-3747], primaryKey: false);
      insert('organizations', [ id: 103733, nci_institute_code: "KS074", name: "Cancer Center of Kansas - Ottawa", city: "Ottawa", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS074",GROUP_DESC:"KS074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4748 ,protection_group_id: -3748, protection_element_id:-3748], primaryKey: false);
      insert('organizations', [ id: 103734, nci_institute_code: "KS075", name: "Cancer Center of Kansas - El Dorado", city: "El Dorado", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS075",GROUP_DESC:"KS075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4749 ,protection_group_id: -3749, protection_element_id:-3749], primaryKey: false);
      insert('organizations', [ id: 103735, nci_institute_code: "KS076", name: "Susan B. Allen Memorial Hospital", city: "El Dorado", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS076",GROUP_DESC:"KS076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4750 ,protection_group_id: -3750, protection_element_id:-3750], primaryKey: false);
      insert('organizations', [ id: 103736, nci_institute_code: "KS077", name: "Kansas City Cancer Centers - Lenexa", city: "Lenexa", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS077",GROUP_DESC:"KS077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4751 ,protection_group_id: -3751, protection_element_id:-3751], primaryKey: false);
      insert('organizations', [ id: 103737, nci_institute_code: "KS078", name: "Lawrence Cancer Center", city: "Lawrence", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS078",GROUP_DESC:"KS078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4752 ,protection_group_id: -3752, protection_element_id:-3752], primaryKey: false);
      insert('organizations', [ id: 103738, nci_institute_code: "KS079", name: "South Kansas City Surgical Center", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS079",GROUP_DESC:"KS079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4753 ,protection_group_id: -3753, protection_element_id:-3753], primaryKey: false);
      insert('organizations', [ id: 103739, nci_institute_code: "KS080", name: "Mid-American Sarcoma Institute", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS080",GROUP_DESC:"KS080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4754 ,protection_group_id: -3754, protection_element_id:-3754], primaryKey: false);
      insert('organizations', [ id: 103740, nci_institute_code: "KS081", name: "Heartland Research Associates, LLC", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS081",GROUP_DESC:"KS081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4755 ,protection_group_id: -3755, protection_element_id:-3755], primaryKey: false);
      insert('organizations', [ id: 103741, nci_institute_code: "KS082", name: "Mowery Cancer Center", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS082",GROUP_DESC:"KS082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4756 ,protection_group_id: -3756, protection_element_id:-3756], primaryKey: false);
      insert('organizations', [ id: 103742, nci_institute_code: "KS083", name: "Cancer Center of Kansas - Wichita", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS083",GROUP_DESC:"KS083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4757 ,protection_group_id: -3757, protection_element_id:-3757], primaryKey: false);
      insert('organizations', [ id: 103743, nci_institute_code: "KS084", name: "Mowery Clinic LLC", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS084",GROUP_DESC:"KS084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4758 ,protection_group_id: -3758, protection_element_id:-3758], primaryKey: false);
      insert('organizations', [ id: 103744, nci_institute_code: "KS085", name: "Wichita Surgical Specialists PA", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS085",GROUP_DESC:"KS085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4759 ,protection_group_id: -3759, protection_element_id:-3759], primaryKey: false);
      insert('organizations', [ id: 103745, nci_institute_code: "KS086", name: "Tammy Walker Cancer Center", city: "Salina", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS086",GROUP_DESC:"KS086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4760 ,protection_group_id: -3760, protection_element_id:-3760], primaryKey: false);
      insert('organizations', [ id: 103746, nci_institute_code: "KS087", name: "Cotton-O'Neil Cancer Center", city: "Topeka", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS087",GROUP_DESC:"KS087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4761 ,protection_group_id: -3761, protection_element_id:-3761], primaryKey: false);
      insert('organizations', [ id: 103747, nci_institute_code: "KS088", name: "University of Kansas Hospital - Westwood Cancer Center", city: "Westwood", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS088",GROUP_DESC:"KS088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4762 ,protection_group_id: -3762, protection_element_id:-3762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 103748, nci_institute_code: "KY001", name: "Ireland Army Hospital", city: "Fort Knox", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY001",GROUP_DESC:"KY001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4763 ,protection_group_id: -3763, protection_element_id:-3763], primaryKey: false);
      insert('organizations', [ id: 103749, nci_institute_code: "KY002", name: "University of Louisville", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY002",GROUP_DESC:"KY002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4764 ,protection_group_id: -3764, protection_element_id:-3764], primaryKey: false);
      insert('organizations', [ id: 103750, nci_institute_code: "KY003", name: "Jewish Hospital", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY003",GROUP_DESC:"KY003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4765 ,protection_group_id: -3765, protection_element_id:-3765], primaryKey: false);
      insert('organizations', [ id: 103751, nci_institute_code: "KY004", name: "Childrens Hospital of Louisville", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY004",GROUP_DESC:"KY004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4766 ,protection_group_id: -3766, protection_element_id:-3766], primaryKey: false);
      insert('organizations', [ id: 103752, nci_institute_code: "KY005", name: "Louisville VA Medical Center", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY005",GROUP_DESC:"KY005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4767 ,protection_group_id: -3767, protection_element_id:-3767], primaryKey: false);
      insert('organizations', [ id: 103753, nci_institute_code: "KY006", name: "Louisville General Hospital", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY006",GROUP_DESC:"KY006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4768 ,protection_group_id: -3768, protection_element_id:-3768], primaryKey: false);
      insert('organizations', [ id: 103754, nci_institute_code: "KY009", name: "Saint Joseph Hospital", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY009",GROUP_DESC:"KY009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4769 ,protection_group_id: -3769, protection_element_id:-3769], primaryKey: false);
      insert('organizations', [ id: 103755, nci_institute_code: "KY010", name: "University of Kentucky", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY010",GROUP_DESC:"KY010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4770 ,protection_group_id: -3770, protection_element_id:-3770], primaryKey: false);
      insert('organizations', [ id: 103756, nci_institute_code: "KY011", name: "Veteran's Administration Medical Center - Lexington", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY011",GROUP_DESC:"KY011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4771 ,protection_group_id: -3771, protection_element_id:-3771], primaryKey: false);
      insert('organizations', [ id: 103757, nci_institute_code: "KY012", name: "Good Samaritan Hospital", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY012",GROUP_DESC:"KY012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4772 ,protection_group_id: -3772, protection_element_id:-3772], primaryKey: false);
      insert('organizations', [ id: 103758, nci_institute_code: "KY015", name: "Saint Elizabeth Medical Center South", city: "Edgewood", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY015",GROUP_DESC:"KY015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4773 ,protection_group_id: -3773, protection_element_id:-3773], primaryKey: false);
      insert('organizations', [ id: 103759, nci_institute_code: "KY016", name: "Saint Luke Hospital", city: "Fort Thomas", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY016",GROUP_DESC:"KY016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4774 ,protection_group_id: -3774, protection_element_id:-3774], primaryKey: false);
      insert('organizations', [ id: 103760, nci_institute_code: "KY017", name: "King's Daughter's Medical Center", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY017",GROUP_DESC:"KY017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4775 ,protection_group_id: -3775, protection_element_id:-3775], primaryKey: false);
      insert('organizations', [ id: 103761, nci_institute_code: "KY018", name: "Lourdes Hospital", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY018",GROUP_DESC:"KY018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4776 ,protection_group_id: -3776, protection_element_id:-3776], primaryKey: false);
      insert('organizations', [ id: 103762, nci_institute_code: "KY019", name: "Medical Center", city: "Bowling Green", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY019",GROUP_DESC:"KY019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4777 ,protection_group_id: -3777, protection_element_id:-3777], primaryKey: false);
      insert('organizations', [ id: 103763, nci_institute_code: "KY020", name: "Blanchfield Army Hospital", city: "Fort Campbell", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY020",GROUP_DESC:"KY020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4778 ,protection_group_id: -3778, protection_element_id:-3778], primaryKey: false);
      insert('organizations', [ id: 103764, nci_institute_code: "KY021", name: "Taylor Regional Hospital", city: "Campbellsville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY021",GROUP_DESC:"KY021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4779 ,protection_group_id: -3779, protection_element_id:-3779], primaryKey: false);
      insert('organizations', [ id: 103765, nci_institute_code: "KY022", name: "Alliant Medical Group", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY022",GROUP_DESC:"KY022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4780 ,protection_group_id: -3780, protection_element_id:-3780], primaryKey: false);
      insert('organizations', [ id: 103766, nci_institute_code: "KY023", name: "Markey Cancer Center", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY023",GROUP_DESC:"KY023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4781 ,protection_group_id: -3781, protection_element_id:-3781], primaryKey: false);
      insert('organizations', [ id: 103767, nci_institute_code: "KY024", name: "Baptist Hospital East", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY024",GROUP_DESC:"KY024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4782 ,protection_group_id: -3782, protection_element_id:-3782], primaryKey: false);
      insert('organizations', [ id: 103768, nci_institute_code: "KY025", name: "Merle Mahr Cancer Center", city: "Madisonville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY025",GROUP_DESC:"KY025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4783 ,protection_group_id: -3783, protection_element_id:-3783], primaryKey: false);
      insert('organizations', [ id: 103769, nci_institute_code: "KY026", name: "Central Baptist Hospital", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY026",GROUP_DESC:"KY026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4784 ,protection_group_id: -3784, protection_element_id:-3784], primaryKey: false);
      insert('organizations', [ id: 103770, nci_institute_code: "KY027", name: "Kosair Children's Hospital", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY027",GROUP_DESC:"KY027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4785 ,protection_group_id: -3785, protection_element_id:-3785], primaryKey: false);
      insert('organizations', [ id: 103771, nci_institute_code: "KY028", name: "Greenview Hospital (Hca)", city: "Bowling Green", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY028",GROUP_DESC:"KY028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4786 ,protection_group_id: -3786, protection_element_id:-3786], primaryKey: false);
      insert('organizations', [ id: 103772, nci_institute_code: "KY029", name: "Kentuckiana Medical", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY029",GROUP_DESC:"KY029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4787 ,protection_group_id: -3787, protection_element_id:-3787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 103773, nci_institute_code: "KY030", name: "John D Cronin Cancer Center at Lexington Clinic", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY030",GROUP_DESC:"KY030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4788 ,protection_group_id: -3788, protection_element_id:-3788], primaryKey: false);
      insert('organizations', [ id: 103774, nci_institute_code: "KY031", name: "Our Lady Bellefonte Hospital", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY031",GROUP_DESC:"KY031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4789 ,protection_group_id: -3789, protection_element_id:-3789], primaryKey: false);
      insert('organizations', [ id: 103775, nci_institute_code: "KY033", name: "Highland Regional Medical Center", city: "Prestonsburg", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY033",GROUP_DESC:"KY033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4790 ,protection_group_id: -3790, protection_element_id:-3790], primaryKey: false);
      insert('organizations', [ id: 103776, nci_institute_code: "KY034", name: "Specialty Medical Center", city: "Elizabethtown", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY034",GROUP_DESC:"KY034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4791 ,protection_group_id: -3791, protection_element_id:-3791], primaryKey: false);
      insert('organizations', [ id: 103777, nci_institute_code: "KY035", name: "University Surgical Association", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY035",GROUP_DESC:"KY035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4792 ,protection_group_id: -3792, protection_element_id:-3792], primaryKey: false);
      insert('organizations', [ id: 103778, nci_institute_code: "KY036", name: "Bellfonte Cancer Center", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY036",GROUP_DESC:"KY036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4793 ,protection_group_id: -3793, protection_element_id:-3793], primaryKey: false);
      insert('organizations', [ id: 103779, nci_institute_code: "KY037", name: "Graves-Gilbert Clinic", city: "Bowling Green", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY037",GROUP_DESC:"KY037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4794 ,protection_group_id: -3794, protection_element_id:-3794], primaryKey: false);
      insert('organizations', [ id: 103780, nci_institute_code: "KY038", name: "Lexington Clinic", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY038",GROUP_DESC:"KY038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4795 ,protection_group_id: -3795, protection_element_id:-3795], primaryKey: false);
      insert('organizations', [ id: 103781, nci_institute_code: "KY039", name: "Ephraim McDowell Regional Medical Center", city: "Danville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY039",GROUP_DESC:"KY039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4796 ,protection_group_id: -3796, protection_element_id:-3796], primaryKey: false);
      insert('organizations', [ id: 103782, nci_institute_code: "KY040", name: "Norton Suburban Hospital", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY040",GROUP_DESC:"KY040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4797 ,protection_group_id: -3797, protection_element_id:-3797], primaryKey: false);
      insert('organizations', [ id: 103783, nci_institute_code: "KY041", name: "Bluegrass Hematology/Oncology, PSC", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY041",GROUP_DESC:"KY041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4798 ,protection_group_id: -3798, protection_element_id:-3798], primaryKey: false);
      insert('organizations', [ id: 103784, nci_institute_code: "KY042", name: "Oncology Hematology Care, Incorporated", city: "Crestview Hills", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY042",GROUP_DESC:"KY042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4799 ,protection_group_id: -3799, protection_element_id:-3799], primaryKey: false);
      insert('organizations', [ id: 103785, nci_institute_code: "KY043", name: "Western Baptist Hospital", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY043",GROUP_DESC:"KY043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4800 ,protection_group_id: -3800, protection_element_id:-3800], primaryKey: false);
      insert('organizations', [ id: 103786, nci_institute_code: "KY044", name: "Springs Medcial Center", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY044",GROUP_DESC:"KY044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4801 ,protection_group_id: -3801, protection_element_id:-3801], primaryKey: false);
      insert('organizations', [ id: 103787, nci_institute_code: "KY045", name: "Caritas Regional Cancer Center", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY045",GROUP_DESC:"KY045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4802 ,protection_group_id: -3802, protection_element_id:-3802], primaryKey: false);
      insert('organizations', [ id: 103788, nci_institute_code: "KY046", name: "Murray-Calloway County Hospital", city: "Murray", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY046",GROUP_DESC:"KY046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4803 ,protection_group_id: -3803, protection_element_id:-3803], primaryKey: false);
      insert('organizations', [ id: 103789, nci_institute_code: "KY047", name: "Pikeville Medical Center", city: "Pikeville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY047",GROUP_DESC:"KY047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4804 ,protection_group_id: -3804, protection_element_id:-3804], primaryKey: false);
      insert('organizations', [ id: 103790, nci_institute_code: "KY049", name: "Norton Health Care Pavilion - Downtown", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY049",GROUP_DESC:"KY049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4805 ,protection_group_id: -3805, protection_element_id:-3805], primaryKey: false);
      insert('organizations', [ id: 103791, nci_institute_code: "KY051", name: "Owensboro Mercy Medical Center", city: "Owensboro", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY051",GROUP_DESC:"KY051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4806 ,protection_group_id: -3806, protection_element_id:-3806], primaryKey: false);
      insert('organizations', [ id: 103792, nci_institute_code: "KY052", name: "Caritas Health System", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY052",GROUP_DESC:"KY052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4807 ,protection_group_id: -3807, protection_element_id:-3807], primaryKey: false);
      insert('organizations', [ id: 103793, nci_institute_code: "KY053", name: "Kentuckiana Cancer Institute, PLLC", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY053",GROUP_DESC:"KY053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4808 ,protection_group_id: -3808, protection_element_id:-3808], primaryKey: false);
      insert('organizations', [ id: 103794, nci_institute_code: "KY054", name: "Norton Hospital and Healthcare", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY054",GROUP_DESC:"KY054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4809 ,protection_group_id: -3809, protection_element_id:-3809], primaryKey: false);
      insert('organizations', [ id: 103795, nci_institute_code: "KY055", name: "Drs.Carrol, Sheth, Raghavan", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY055",GROUP_DESC:"KY055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4810 ,protection_group_id: -3810, protection_element_id:-3810], primaryKey: false);
      insert('organizations', [ id: 103796, nci_institute_code: "KY056", name: "Tri-State Hematology/Oncology", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY056",GROUP_DESC:"KY056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4811 ,protection_group_id: -3811, protection_element_id:-3811], primaryKey: false);
      insert('organizations', [ id: 103797, nci_institute_code: "KY057", name: "Lake Cumberland Regional Hospital", city: "Somerset", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY057",GROUP_DESC:"KY057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4812 ,protection_group_id: -3812, protection_element_id:-3812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 103798, nci_institute_code: "KY058", name: "Saint Claire Cancer Treatment Center", city: "Morehead", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY058",GROUP_DESC:"KY058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4813 ,protection_group_id: -3813, protection_element_id:-3813], primaryKey: false);
      insert('organizations', [ id: 103799, nci_institute_code: "KY059", name: "Kentucky Clinic Berea", city: "Berea", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY059",GROUP_DESC:"KY059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4814 ,protection_group_id: -3814, protection_element_id:-3814], primaryKey: false);
      insert('organizations', [ id: 103800, nci_institute_code: "KY060", name: "Graves Gilbert Clinic", city: "Glasgow", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY060",GROUP_DESC:"KY060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4815 ,protection_group_id: -3815, protection_element_id:-3815], primaryKey: false);
      insert('organizations', [ id: 103801, nci_institute_code: "KY061", name: "Purchase Cancer Group", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY061",GROUP_DESC:"KY061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4816 ,protection_group_id: -3816, protection_element_id:-3816], primaryKey: false);
      insert('organizations', [ id: 103802, nci_institute_code: "KY062", name: "Oncology Associates of  West Kentucky", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY062",GROUP_DESC:"KY062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4817 ,protection_group_id: -3817, protection_element_id:-3817], primaryKey: false);
      insert('organizations', [ id: 103803, nci_institute_code: "KY063", name: "Urology Center of Northeastern Kentucky", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY063",GROUP_DESC:"KY063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4818 ,protection_group_id: -3818, protection_element_id:-3818], primaryKey: false);
      insert('organizations', [ id: 103804, nci_institute_code: "KY064", name: "Commonwealth Cancer Center of Danville", city: "Danville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY064",GROUP_DESC:"KY064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4819 ,protection_group_id: -3819, protection_element_id:-3819], primaryKey: false);
      insert('organizations', [ id: 103805, nci_institute_code: "KY065", name: "West Kentucky Hematology/Oncology Group", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY065",GROUP_DESC:"KY065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4820 ,protection_group_id: -3820, protection_element_id:-3820], primaryKey: false);
      insert('organizations', [ id: 103806, nci_institute_code: "KY066", name: "Thoracic Vascular Associates", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY066",GROUP_DESC:"KY066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4821 ,protection_group_id: -3821, protection_element_id:-3821], primaryKey: false);
      insert('organizations', [ id: 103807, nci_institute_code: "KY067", name: "Consultants in Blood Disorders and Cancer", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY067",GROUP_DESC:"KY067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4822 ,protection_group_id: -3822, protection_element_id:-3822], primaryKey: false);
      insert('organizations', [ id: 103808, nci_institute_code: "KY068", name: "Ohio Valley Surgical Specialists", city: "Owensboro", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY068",GROUP_DESC:"KY068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4823 ,protection_group_id: -3823, protection_element_id:-3823], primaryKey: false);
      insert('organizations', [ id: 103809, nci_institute_code: "KY069", name: "University Pediatric Surgery Associates", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY069",GROUP_DESC:"KY069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4824 ,protection_group_id: -3824, protection_element_id:-3824], primaryKey: false);
      insert('organizations', [ id: 103810, nci_institute_code: "KY070", name: "Owensboro Cancer Center", city: "Owensboro", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY070",GROUP_DESC:"KY070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4825 ,protection_group_id: -3825, protection_element_id:-3825], primaryKey: false);
      insert('organizations', [ id: 103811, nci_institute_code: "KY071", name: "Common Wealth Cancer Center", city: "Corbin", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY071",GROUP_DESC:"KY071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4826 ,protection_group_id: -3826, protection_element_id:-3826], primaryKey: false);
      insert('organizations', [ id: 103812, nci_institute_code: "KY072", name: "Baptist Regional Medical Center", city: "Corbin", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY072",GROUP_DESC:"KY072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4827 ,protection_group_id: -3827, protection_element_id:-3827], primaryKey: false);
      insert('organizations', [ id: 103813, nci_institute_code: "KY073", name: "Caritas Health Services - Medical Plaza #1", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY073",GROUP_DESC:"KY073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4828 ,protection_group_id: -3828, protection_element_id:-3828], primaryKey: false);
      insert('organizations', [ id: 103814, nci_institute_code: "KY074", name: "James Graham Brown Cancer Center", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY074",GROUP_DESC:"KY074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4829 ,protection_group_id: -3829, protection_element_id:-3829], primaryKey: false);
      insert('organizations', [ id: 103815, nci_institute_code: "KY075", name: "Commonwealth Urology PSC", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY075",GROUP_DESC:"KY075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4830 ,protection_group_id: -3830, protection_element_id:-3830], primaryKey: false);
      insert('organizations', [ id: 103816, nci_institute_code: "KY076", name: "Tri-State Cancer and Blood Specialist", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY076",GROUP_DESC:"KY076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4831 ,protection_group_id: -3831, protection_element_id:-3831], primaryKey: false);
      insert('organizations', [ id: 103817, nci_institute_code: "KY077", name: "University Cardiothoracic Surgical Associates", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY077",GROUP_DESC:"KY077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4832 ,protection_group_id: -3832, protection_element_id:-3832], primaryKey: false);
      insert('organizations', [ id: 103818, nci_institute_code: "KY078", name: "Taylor Regional Surgical Associates", city: "Campbellsville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY078",GROUP_DESC:"KY078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4833 ,protection_group_id: -3833, protection_element_id:-3833], primaryKey: false);
      insert('organizations', [ id: 103819, nci_institute_code: "KY079", name: "United Radiation Oncology", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY079",GROUP_DESC:"KY079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4834 ,protection_group_id: -3834, protection_element_id:-3834], primaryKey: false);
      insert('organizations', [ id: 103820, nci_institute_code: "KY080", name: "Northeastern Kentucky Surgeons PSC", city: "Ashland", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY080",GROUP_DESC:"KY080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4835 ,protection_group_id: -3835, protection_element_id:-3835], primaryKey: false);
      insert('organizations', [ id: 103821, nci_institute_code: "KY081", name: "Murray Oncology Associates", city: "Murray", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY081",GROUP_DESC:"KY081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4836 ,protection_group_id: -3836, protection_element_id:-3836], primaryKey: false);
      insert('organizations', [ id: 103822, nci_institute_code: "KY082", name: "Saint Joseph Healthcare Research Center", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY082",GROUP_DESC:"KY082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4837 ,protection_group_id: -3837, protection_element_id:-3837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 103823, nci_institute_code: "KY083", name: "Lexington Oncology Associates PSC", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY083",GROUP_DESC:"KY083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4838 ,protection_group_id: -3838, protection_element_id:-3838], primaryKey: false);
      insert('organizations', [ id: 103824, nci_institute_code: "KY084", name: "Carroll Sheath and Raghavan MD", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY084",GROUP_DESC:"KY084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4839 ,protection_group_id: -3839, protection_element_id:-3839], primaryKey: false);
      insert('organizations', [ id: 103825, nci_institute_code: "LA001", name: "Tulane University Hospital and Clinic", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA001",GROUP_DESC:"LA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4840 ,protection_group_id: -3840, protection_element_id:-3840], primaryKey: false);
      insert('organizations', [ id: 103826, nci_institute_code: "LA002", name: "Louisiana State University Health Science Center", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA002",GROUP_DESC:"LA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4841 ,protection_group_id: -3841, protection_element_id:-3841], primaryKey: false);
      insert('organizations', [ id: 103827, nci_institute_code: "LA003", name: "Inter Com Cancer Center S Baptist Hospital", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA003",GROUP_DESC:"LA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4842 ,protection_group_id: -3842, protection_element_id:-3842], primaryKey: false);
      insert('organizations', [ id: 103828, nci_institute_code: "LA004", name: "Children's Hospital", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA004",GROUP_DESC:"LA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4843 ,protection_group_id: -3843, protection_element_id:-3843], primaryKey: false);
      insert('organizations', [ id: 103829, nci_institute_code: "LA005", name: "US Phs Hospital", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA005",GROUP_DESC:"LA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4844 ,protection_group_id: -3844, protection_element_id:-3844], primaryKey: false);
      insert('organizations', [ id: 103830, nci_institute_code: "LA006", name: "Medical Arts Center", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA006",GROUP_DESC:"LA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4845 ,protection_group_id: -3845, protection_element_id:-3845], primaryKey: false);
      insert('organizations', [ id: 103831, nci_institute_code: "LA007", name: "Ochsner Clinic Foundation", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA007",GROUP_DESC:"LA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4846 ,protection_group_id: -3846, protection_element_id:-3846], primaryKey: false);
      insert('organizations', [ id: 103832, nci_institute_code: "LA008", name: "Veteran's Administration Medical Center - New Orleans", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA008",GROUP_DESC:"LA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4847 ,protection_group_id: -3847, protection_element_id:-3847], primaryKey: false);
      insert('organizations', [ id: 103833, nci_institute_code: "LA009", name: "South Louisiana Medical Center", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA009",GROUP_DESC:"LA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4848 ,protection_group_id: -3848, protection_element_id:-3848], primaryKey: false);
      insert('organizations', [ id: 103834, nci_institute_code: "LA010", name: "Lafayette Charity Hospital", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA010",GROUP_DESC:"LA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4849 ,protection_group_id: -3849, protection_element_id:-3849], primaryKey: false);
      insert('organizations', [ id: 103835, nci_institute_code: "LA011", name: "Louisiana State University.", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA011",GROUP_DESC:"LA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4850 ,protection_group_id: -3850, protection_element_id:-3850], primaryKey: false);
      insert('organizations', [ id: 103836, nci_institute_code: "LA013", name: "Baton Rouge Clinic", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA013",GROUP_DESC:"LA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4851 ,protection_group_id: -3851, protection_element_id:-3851], primaryKey: false);
      insert('organizations', [ id: 103837, nci_institute_code: "LA014", name: "Christus Schumpert Highland Hospital", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA014",GROUP_DESC:"LA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4852 ,protection_group_id: -3852, protection_element_id:-3852], primaryKey: false);
      insert('organizations', [ id: 103838, nci_institute_code: "LA016", name: "Christus Schumpert Saint Mary's Place", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA016",GROUP_DESC:"LA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4853 ,protection_group_id: -3853, protection_element_id:-3853], primaryKey: false);
      insert('organizations', [ id: 103839, nci_institute_code: "LA017", name: "Louisiana State University", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA017",GROUP_DESC:"LA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4854 ,protection_group_id: -3854, protection_element_id:-3854], primaryKey: false);
      insert('organizations', [ id: 103840, nci_institute_code: "LA018", name: "Veteran's Administration Medical Center", city: "Shrevport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA018",GROUP_DESC:"LA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4855 ,protection_group_id: -3855, protection_element_id:-3855], primaryKey: false);
      insert('organizations', [ id: 103841, nci_institute_code: "LA019", name: "Saint Francis Medical Center", city: "Monroe", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA019",GROUP_DESC:"LA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4856 ,protection_group_id: -3856, protection_element_id:-3856], primaryKey: false);
      insert('organizations', [ id: 103842, nci_institute_code: "LA021", name: "Veteran's Administration Medical Center", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA021",GROUP_DESC:"LA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4857 ,protection_group_id: -3857, protection_element_id:-3857], primaryKey: false);
      insert('organizations', [ id: 103843, nci_institute_code: "LA022", name: "Jefferson Oncology Clinic", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA022",GROUP_DESC:"LA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4858 ,protection_group_id: -3858, protection_element_id:-3858], primaryKey: false);
      insert('organizations', [ id: 103844, nci_institute_code: "LA024", name: "Ochsner Clinic Foundation-Baton Rouge", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA024",GROUP_DESC:"LA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4859 ,protection_group_id: -3859, protection_element_id:-3859], primaryKey: false);
      insert('organizations', [ id: 103845, nci_institute_code: "LA026", name: "Woman's Hospital", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA026",GROUP_DESC:"LA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4860 ,protection_group_id: -3860, protection_element_id:-3860], primaryKey: false);
      insert('organizations', [ id: 103846, nci_institute_code: "LA027", name: "Doctors Hospital of Jefferson", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA027",GROUP_DESC:"LA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4861 ,protection_group_id: -3861, protection_element_id:-3861], primaryKey: false);
      insert('organizations', [ id: 103847, nci_institute_code: "LA028", name: "Terrebonne General Hospital", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA028",GROUP_DESC:"LA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4862 ,protection_group_id: -3862, protection_element_id:-3862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 103848, nci_institute_code: "LA029", name: "Medical Center of Louisiana", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA029",GROUP_DESC:"LA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4863 ,protection_group_id: -3863, protection_element_id:-3863], primaryKey: false);
      insert('organizations', [ id: 103849, nci_institute_code: "LA030", name: "Baton Rouge General Medical Center", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA030",GROUP_DESC:"LA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4864 ,protection_group_id: -3864, protection_element_id:-3864], primaryKey: false);
      insert('organizations', [ id: 103850, nci_institute_code: "LA031", name: "Thibodaux Regional Medical Center", city: "Thibodaux", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA031",GROUP_DESC:"LA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4865 ,protection_group_id: -3865, protection_element_id:-3865], primaryKey: false);
      insert('organizations', [ id: 103851, nci_institute_code: "LA032", name: "Memorial Medical Center", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA032",GROUP_DESC:"LA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4866 ,protection_group_id: -3866, protection_element_id:-3866], primaryKey: false);
      insert('organizations', [ id: 103852, nci_institute_code: "LA033", name: "Methodist Cancer Center", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA033",GROUP_DESC:"LA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4867 ,protection_group_id: -3867, protection_element_id:-3867], primaryKey: false);
      insert('organizations', [ id: 103853, nci_institute_code: "LA034", name: "Willis Knighton Medical Center", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA034",GROUP_DESC:"LA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4868 ,protection_group_id: -3868, protection_element_id:-3868], primaryKey: false);
      insert('organizations', [ id: 103854, nci_institute_code: "LA035", name: "Mary Bird Perkins Cancer Center", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA035",GROUP_DESC:"LA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4869 ,protection_group_id: -3869, protection_element_id:-3869], primaryKey: false);
      insert('organizations', [ id: 103855, nci_institute_code: "LA036", name: "Mercy Baptist Medical Center", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA036",GROUP_DESC:"LA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4870 ,protection_group_id: -3870, protection_element_id:-3870], primaryKey: false);
      insert('organizations', [ id: 103856, nci_institute_code: "LA037", name: "Our Lady of Lourdes Regional Medical Center", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA037",GROUP_DESC:"LA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4871 ,protection_group_id: -3871, protection_element_id:-3871], primaryKey: false);
      insert('organizations', [ id: 103857, nci_institute_code: "LA038", name: "Inter-Community Cancer Center", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA038",GROUP_DESC:"LA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4872 ,protection_group_id: -3872, protection_element_id:-3872], primaryKey: false);
      insert('organizations', [ id: 103858, nci_institute_code: "LA039", name: "Freedman Clinic", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA039",GROUP_DESC:"LA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4873 ,protection_group_id: -3873, protection_element_id:-3873], primaryKey: false);
      insert('organizations', [ id: 103859, nci_institute_code: "LA040", name: "Ochsner Clinic CCOP", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA040",GROUP_DESC:"LA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4874 ,protection_group_id: -3874, protection_element_id:-3874], primaryKey: false);
      insert('organizations', [ id: 103860, nci_institute_code: "LA041", name: "Earl K. Long Hospital", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA041",GROUP_DESC:"LA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4875 ,protection_group_id: -3875, protection_element_id:-3875], primaryKey: false);
      insert('organizations', [ id: 103861, nci_institute_code: "LA042", name: "Our Lady of The Lake", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA042",GROUP_DESC:"LA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4876 ,protection_group_id: -3876, protection_element_id:-3876], primaryKey: false);
      insert('organizations', [ id: 103862, nci_institute_code: "LA043", name: "Overton Brooks Veteran's Administration Medical Center", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA043",GROUP_DESC:"LA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4877 ,protection_group_id: -3877, protection_element_id:-3877], primaryKey: false);
      insert('organizations', [ id: 103863, nci_institute_code: "LA044", name: "Slidell Memorial Hospital", city: "Slidell", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA044",GROUP_DESC:"LA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4878 ,protection_group_id: -3878, protection_element_id:-3878], primaryKey: false);
      insert('organizations', [ id: 103864, nci_institute_code: "LA045", name: "East Jefferson General", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA045",GROUP_DESC:"LA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4879 ,protection_group_id: -3879, protection_element_id:-3879], primaryKey: false);
      insert('organizations', [ id: 103865, nci_institute_code: "LA046", name: "Medical Center of Baton Rouge", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA046",GROUP_DESC:"LA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4880 ,protection_group_id: -3880, protection_element_id:-3880], primaryKey: false);
      insert('organizations', [ id: 103866, nci_institute_code: "LA047", name: "Louisiana State University Medical Center CCOP", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA047",GROUP_DESC:"LA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4881 ,protection_group_id: -3881, protection_element_id:-3881], primaryKey: false);
      insert('organizations', [ id: 103867, nci_institute_code: "LA050", name: "Romagosa Radiation/ Oncology Center", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA050",GROUP_DESC:"LA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4882 ,protection_group_id: -3882, protection_element_id:-3882], primaryKey: false);
      insert('organizations', [ id: 103868, nci_institute_code: "LA051", name: "Medical Oncology Hematology Clinic", city: "West Monroe", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA051",GROUP_DESC:"LA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4883 ,protection_group_id: -3883, protection_element_id:-3883], primaryKey: false);
      insert('organizations', [ id: 103869, nci_institute_code: "LA052", name: "Urology Clinic", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA052",GROUP_DESC:"LA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4884 ,protection_group_id: -3884, protection_element_id:-3884], primaryKey: false);
      insert('organizations', [ id: 103870, nci_institute_code: "LA053", name: "Internal Medicine Clinic/Houma", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA053",GROUP_DESC:"LA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4885 ,protection_group_id: -3885, protection_element_id:-3885], primaryKey: false);
      insert('organizations', [ id: 103871, nci_institute_code: "LA054", name: "Romagosa Radiation Oncology Center", city: "New Iberia", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA054",GROUP_DESC:"LA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4886 ,protection_group_id: -3886, protection_element_id:-3886], primaryKey: false);
      insert('organizations', [ id: 103872, nci_institute_code: "LA055", name: "Northeast Louisiana Cancer Institute", city: "West Monroe", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA055",GROUP_DESC:"LA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4887 ,protection_group_id: -3887, protection_element_id:-3887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 103873, nci_institute_code: "LA057", name: "Oncology Center of The South, Incorporated.,", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA057",GROUP_DESC:"LA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4888 ,protection_group_id: -3888, protection_element_id:-3888], primaryKey: false);
      insert('organizations', [ id: 103874, nci_institute_code: "LA058", name: "University Hospital", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA058",GROUP_DESC:"LA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4889 ,protection_group_id: -3889, protection_element_id:-3889], primaryKey: false);
      insert('organizations', [ id: 103875, nci_institute_code: "LA059", name: "Pennington Biomedical Research Center", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA059",GROUP_DESC:"LA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4890 ,protection_group_id: -3890, protection_element_id:-3890], primaryKey: false);
      insert('organizations', [ id: 103876, nci_institute_code: "LA060", name: "Louisiana Oncology Associates Pmc", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA060",GROUP_DESC:"LA060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4891 ,protection_group_id: -3891, protection_element_id:-3891], primaryKey: false);
      insert('organizations', [ id: 103877, nci_institute_code: "LA061", name: "Pendleton Memorial Methodist Hospital", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA061",GROUP_DESC:"LA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4892 ,protection_group_id: -3892, protection_element_id:-3892], primaryKey: false);
      insert('organizations', [ id: 103878, nci_institute_code: "LA062", name: "Hematology and Oncology Clinic.", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA062",GROUP_DESC:"LA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4893 ,protection_group_id: -3893, protection_element_id:-3893], primaryKey: false);
      insert('organizations', [ id: 103879, nci_institute_code: "LA063", name: "Rapides Regional Medical Center", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA063",GROUP_DESC:"LA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4894 ,protection_group_id: -3894, protection_element_id:-3894], primaryKey: false);
      insert('organizations', [ id: 103880, nci_institute_code: "LA064", name: "Cancer Care Center of Terrebonne Center/alton Ochsner", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA064",GROUP_DESC:"LA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4895 ,protection_group_id: -3895, protection_element_id:-3895], primaryKey: false);
      insert('organizations', [ id: 103881, nci_institute_code: "LA065", name: "Hematology & Oncology Services, LLC", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA065",GROUP_DESC:"LA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4896 ,protection_group_id: -3896, protection_element_id:-3896], primaryKey: false);
      insert('organizations', [ id: 103882, nci_institute_code: "LA066", name: "Touro Infirmary", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA066",GROUP_DESC:"LA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4897 ,protection_group_id: -3897, protection_element_id:-3897], primaryKey: false);
      insert('organizations', [ id: 103883, nci_institute_code: "LA067", name: "Christus Saint Frances Cabrini Hospital", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA067",GROUP_DESC:"LA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4898 ,protection_group_id: -3898, protection_element_id:-3898], primaryKey: false);
      insert('organizations', [ id: 103884, nci_institute_code: "LA068", name: "Louisiana State University Sciences Center- Monroe", city: "Monroe", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA068",GROUP_DESC:"LA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4899 ,protection_group_id: -3899, protection_element_id:-3899], primaryKey: false);
      insert('organizations', [ id: 103885, nci_institute_code: "LA069", name: "Medical Center of Southwest Louisiana", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA069",GROUP_DESC:"LA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4900 ,protection_group_id: -3900, protection_element_id:-3900], primaryKey: false);
      insert('organizations', [ id: 103886, nci_institute_code: "LA070", name: "MBCCOP, Baton Rouge Gen Reg Cancer Ctr", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA070",GROUP_DESC:"LA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4901 ,protection_group_id: -3901, protection_element_id:-3901], primaryKey: false);
      insert('organizations', [ id: 103887, nci_institute_code: "LA072", name: "Hematology and Oncology Specialists LLC OncRx", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA072",GROUP_DESC:"LA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4902 ,protection_group_id: -3902, protection_element_id:-3902], primaryKey: false);
      insert('organizations', [ id: 103888, nci_institute_code: "LA073", name: "Hematology/Oncology Specialists, LLC", city: "Marrero", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA073",GROUP_DESC:"LA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4903 ,protection_group_id: -3903, protection_element_id:-3903], primaryKey: false);
      insert('organizations', [ id: 103889, nci_institute_code: "LA074", name: "Medical Oncology, L.L.C", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA074",GROUP_DESC:"LA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4904 ,protection_group_id: -3904, protection_element_id:-3904], primaryKey: false);
      insert('organizations', [ id: 103890, nci_institute_code: "LA075", name: "Frank P. Savoy Cancer Center", city: "Mamou", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA075",GROUP_DESC:"LA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4905 ,protection_group_id: -3905, protection_element_id:-3905], primaryKey: false);
      insert('organizations', [ id: 103891, nci_institute_code: "LA076", name: "Ocean Springs Hospital", city: "Ocean Springs", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA076",GROUP_DESC:"LA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4906 ,protection_group_id: -3906, protection_element_id:-3906], primaryKey: false);
      insert('organizations', [ id: 103892, nci_institute_code: "LA078", name: "Cardiovascular Institute of the South - Lafayette", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA078",GROUP_DESC:"LA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4907 ,protection_group_id: -3907, protection_element_id:-3907], primaryKey: false);
      insert('organizations', [ id: 103893, nci_institute_code: "LA079", name: "Lake Charles Medical and Surgical Clinic", city: "Lake Charles", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA079",GROUP_DESC:"LA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4908 ,protection_group_id: -3908, protection_element_id:-3908], primaryKey: false);
      insert('organizations', [ id: 103894, nci_institute_code: "LA080", name: "Willis-Knighton Cancer Center", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA080",GROUP_DESC:"LA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4909 ,protection_group_id: -3909, protection_element_id:-3909], primaryKey: false);
      insert('organizations', [ id: 103895, nci_institute_code: "LA081", name: "Saint Jude Affiliate/Baton Rouge", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA081",GROUP_DESC:"LA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4910 ,protection_group_id: -3910, protection_element_id:-3910], primaryKey: false);
      insert('organizations', [ id: 103896, nci_institute_code: "LA082", name: "Ochsner North Shore Medical Center", city: "Covington", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA082",GROUP_DESC:"LA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4911 ,protection_group_id: -3911, protection_element_id:-3911], primaryKey: false);
      insert('organizations', [ id: 103897, nci_institute_code: "LA083", name: "The Urology Center of Southwest Louisiana", city: "Lake Charles", state: "Louisiana", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA083",GROUP_DESC:"LA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4912 ,protection_group_id: -3912, protection_element_id:-3912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 103898, nci_institute_code: "LA084", name: "Hematology Oncology Life Center", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA084",GROUP_DESC:"LA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4913 ,protection_group_id: -3913, protection_element_id:-3913], primaryKey: false);
      insert('organizations', [ id: 103899, nci_institute_code: "LA085", name: "Acadiana Medical Oncology, L.L.C.", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA085",GROUP_DESC:"LA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4914 ,protection_group_id: -3914, protection_element_id:-3914], primaryKey: false);
      insert('organizations', [ id: 103900, nci_institute_code: "LA086", name: "Northlake Hematology/Oncology Associates Inc.", city: "Hammond", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA086",GROUP_DESC:"LA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4915 ,protection_group_id: -3915, protection_element_id:-3915], primaryKey: false);
      insert('organizations', [ id: 103901, nci_institute_code: "LA087", name: "Hematology and Oncology Life Center", city: "Alexandria", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA087",GROUP_DESC:"LA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4916 ,protection_group_id: -3916, protection_element_id:-3916], primaryKey: false);
      insert('organizations', [ id: 103902, nci_institute_code: "LA088", name: "Surgical Specialty Group", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA088",GROUP_DESC:"LA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4917 ,protection_group_id: -3917, protection_element_id:-3917], primaryKey: false);
      insert('organizations', [ id: 103903, nci_institute_code: "LA089", name: "DeSoto Regional Health System", city: "Mansfield", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA089",GROUP_DESC:"LA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4918 ,protection_group_id: -3918, protection_element_id:-3918], primaryKey: false);
      insert('organizations', [ id: 103904, nci_institute_code: "LA090", name: "Highland Clinic", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA090",GROUP_DESC:"LA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4919 ,protection_group_id: -3919, protection_element_id:-3919], primaryKey: false);
      insert('organizations', [ id: 103905, nci_institute_code: "LA091", name: "New Orleans Cancer Institute", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA091",GROUP_DESC:"LA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4920 ,protection_group_id: -3920, protection_element_id:-3920], primaryKey: false);
      insert('organizations', [ id: 103906, nci_institute_code: "LA092", name: "OncoLogics Inc", city: "Lafayette", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA092",GROUP_DESC:"LA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4921 ,protection_group_id: -3921, protection_element_id:-3921], primaryKey: false);
      insert('organizations', [ id: 103907, nci_institute_code: "LA093", name: "Cancer Care Specialists", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA093",GROUP_DESC:"LA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4922 ,protection_group_id: -3922, protection_element_id:-3922], primaryKey: false);
      insert('organizations', [ id: 103908, nci_institute_code: "LA094", name: "Tulane - Lakeside Hospital", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA094",GROUP_DESC:"LA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4923 ,protection_group_id: -3923, protection_element_id:-3923], primaryKey: false);
      insert('organizations', [ id: 103909, nci_institute_code: "LA095", name: "Bayou Oncology Specialist", city: "Thibodaux", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA095",GROUP_DESC:"LA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4924 ,protection_group_id: -3924, protection_element_id:-3924], primaryKey: false);
      insert('organizations', [ id: 103910, nci_institute_code: "LA096", name: "Lakeview Regional Medical Center", city: "Covington", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA096",GROUP_DESC:"LA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4925 ,protection_group_id: -3925, protection_element_id:-3925], primaryKey: false);
      insert('organizations', [ id: 103911, nci_institute_code: "LA097", name: "Lincoln General Hospital", city: "Ruston", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA097",GROUP_DESC:"LA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4926 ,protection_group_id: -3926, protection_element_id:-3926], primaryKey: false);
      insert('organizations', [ id: 103912, nci_institute_code: "LA098", name: "Ochsner For Children Ambulatory Care Center", city: "New Orleans", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA098",GROUP_DESC:"LA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4927 ,protection_group_id: -3927, protection_element_id:-3927], primaryKey: false);
      insert('organizations', [ id: 103913, nci_institute_code: "LA099", name: "Hematology and Oncology", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA099",GROUP_DESC:"LA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4928 ,protection_group_id: -3928, protection_element_id:-3928], primaryKey: false);
      insert('organizations', [ id: 103914, nci_institute_code: "LA100", name: "Hematology & Oncology Specialists LLC", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA100",GROUP_DESC:"LA100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4929 ,protection_group_id: -3929, protection_element_id:-3929], primaryKey: false);
      insert('organizations', [ id: 103915, nci_institute_code: "LA101", name: "Lousiana Hematology / Oncology Associates LLC", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA101",GROUP_DESC:"LA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4930 ,protection_group_id: -3930, protection_element_id:-3930], primaryKey: false);
      insert('organizations', [ id: 103916, nci_institute_code: "LA102", name: "Drs Gurtler Brinz and Russo APMC-Metairie Oncologists", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA102",GROUP_DESC:"LA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4931 ,protection_group_id: -3931, protection_element_id:-3931], primaryKey: false);
      insert('organizations', [ id: 103917, nci_institute_code: "LA103", name: "Cardiovascular Institute of the South", city: "Houma", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA103",GROUP_DESC:"LA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4932 ,protection_group_id: -3932, protection_element_id:-3932], primaryKey: false);
      insert('organizations', [ id: 103918, nci_institute_code: "LA104", name: "Louisiana State University Healthcare Network", city: "Baton Rouge", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA104",GROUP_DESC:"LA104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4933 ,protection_group_id: -3933, protection_element_id:-3933], primaryKey: false);
      insert('organizations', [ id: 103919, nci_institute_code: "LA105", name: "Robert Veith MD LLC", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA105",GROUP_DESC:"LA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4934 ,protection_group_id: -3934, protection_element_id:-3934], primaryKey: false);
      insert('organizations', [ id: 103920, nci_institute_code: "LA106", name: "Lallie Kemp Regional Medical Center", city: "Independence", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA106",GROUP_DESC:"LA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4935 ,protection_group_id: -3935, protection_element_id:-3935], primaryKey: false);
      insert('organizations', [ id: 103921, nci_institute_code: "LA107", name: "Regional Urology Oncology and Radiation Treatment Center", city: "Shreveport", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA107",GROUP_DESC:"LA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4936 ,protection_group_id: -3936, protection_element_id:-3936], primaryKey: false);
      insert('organizations', [ id: 103922, nci_institute_code: "LCPDCE", name: "National Cancer Institute Laboratory for Chemoprevention", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LCPDCE",GROUP_DESC:"LCPDCE group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LCPDCE",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LCPDCE",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LCPDCE", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4937 ,protection_group_id: -3937, protection_element_id:-3937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 103923, nci_institute_code: "LMC", name: "Laboratory of Medicinal Chemistry, DBS", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LMC",GROUP_DESC:"LMC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LMC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LMC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LMC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4938 ,protection_group_id: -3938, protection_element_id:-3938], primaryKey: false);
      insert('organizations', [ id: 103924, nci_institute_code: "MA001", name: "Holyoke Hospital, Incorporated", city: "Holyoke", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA001",GROUP_DESC:"MA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4939 ,protection_group_id: -3939, protection_element_id:-3939], primaryKey: false);
      insert('organizations', [ id: 103925, nci_institute_code: "MA002", name: "Wesson Memorial Hospital", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA002",GROUP_DESC:"MA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4940 ,protection_group_id: -3940, protection_element_id:-3940], primaryKey: false);
      insert('organizations', [ id: 103926, nci_institute_code: "MA003", name: "Saint Luke's Hospital Springfield", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA003",GROUP_DESC:"MA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4941 ,protection_group_id: -3941, protection_element_id:-3941], primaryKey: false);
      insert('organizations', [ id: 103927, nci_institute_code: "MA004", name: "Baystate Medical Center", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA004",GROUP_DESC:"MA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4942 ,protection_group_id: -3942, protection_element_id:-3942], primaryKey: false);
      insert('organizations', [ id: 103928, nci_institute_code: "MA005", name: "Berkshire Medical Center", city: "Pittsfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA005",GROUP_DESC:"MA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4943 ,protection_group_id: -3943, protection_element_id:-3943], primaryKey: false);
      insert('organizations', [ id: 103929, nci_institute_code: "MA006", name: "Cutler Army Hospital", city: "Fort Devens", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA006",GROUP_DESC:"MA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4944 ,protection_group_id: -3944, protection_element_id:-3944], primaryKey: false);
      insert('organizations', [ id: 103930, nci_institute_code: "MA007", name: "HealthAlliance Hospital - Leominster", city: "Leominster", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA007",GROUP_DESC:"MA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4945 ,protection_group_id: -3945, protection_element_id:-3945], primaryKey: false);
      insert('organizations', [ id: 103931, nci_institute_code: "MA008", name: "Saint Vincent Hospital", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA008",GROUP_DESC:"MA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4946 ,protection_group_id: -3946, protection_element_id:-3946], primaryKey: false);
      insert('organizations', [ id: 103932, nci_institute_code: "MA009", name: "UMass Memorial Medical Center", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA009",GROUP_DESC:"MA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4947 ,protection_group_id: -3947, protection_element_id:-3947], primaryKey: false);
      insert('organizations', [ id: 103933, nci_institute_code: "MA010", name: "Worcester Hahnemann Hospital", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA010",GROUP_DESC:"MA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4948 ,protection_group_id: -3948, protection_element_id:-3948], primaryKey: false);
      insert('organizations', [ id: 103934, nci_institute_code: "MA011", name: "University of Massachusetts Medical School", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA011",GROUP_DESC:"MA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4949 ,protection_group_id: -3949, protection_element_id:-3949], primaryKey: false);
      insert('organizations', [ id: 103935, nci_institute_code: "MA012", name: "Mason Research Institute", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA012",GROUP_DESC:"MA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4950 ,protection_group_id: -3950, protection_element_id:-3950], primaryKey: false);
      insert('organizations', [ id: 103936, nci_institute_code: "MA013", name: "Worcester City Hospital", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA013",GROUP_DESC:"MA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4951 ,protection_group_id: -3951, protection_element_id:-3951], primaryKey: false);
      insert('organizations', [ id: 103937, nci_institute_code: "MA014", name: "Framingham Union Hospital", city: "Framingham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA014",GROUP_DESC:"MA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4952 ,protection_group_id: -3952, protection_element_id:-3952], primaryKey: false);
      insert('organizations', [ id: 103938, nci_institute_code: "MA015", name: "Concord Medical Group", city: "Concord", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA015",GROUP_DESC:"MA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4953 ,protection_group_id: -3953, protection_element_id:-3953], primaryKey: false);
      insert('organizations', [ id: 103939, nci_institute_code: "MA016", name: "Marlborough Hospital", city: "Marlborough", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA016",GROUP_DESC:"MA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4954 ,protection_group_id: -3954, protection_element_id:-3954], primaryKey: false);
      insert('organizations', [ id: 103940, nci_institute_code: "MA017", name: "Lahey Clinic Medical Center", city: "Burlington", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA017",GROUP_DESC:"MA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4955 ,protection_group_id: -3955, protection_element_id:-3955], primaryKey: false);
      insert('organizations', [ id: 103941, nci_institute_code: "MA019", name: "Saints Memorial Medical Center", city: "Lowell", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA019",GROUP_DESC:"MA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4956 ,protection_group_id: -3956, protection_element_id:-3956], primaryKey: false);
      insert('organizations', [ id: 103942, nci_institute_code: "MA021", name: "Union Hospital", city: "Lynn", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA021",GROUP_DESC:"MA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4957 ,protection_group_id: -3957, protection_element_id:-3957], primaryKey: false);
      insert('organizations', [ id: 103943, nci_institute_code: "MA023", name: "Beverly Hospital", city: "Beverly", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA023",GROUP_DESC:"MA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4958 ,protection_group_id: -3958, protection_element_id:-3958], primaryKey: false);
      insert('organizations', [ id: 103944, nci_institute_code: "MA025", name: "Addison Gilbert Hospital", city: "Gloucester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA025",GROUP_DESC:"MA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4959 ,protection_group_id: -3959, protection_element_id:-3959], primaryKey: false);
      insert('organizations', [ id: 103945, nci_institute_code: "MA026", name: "Cape Ann Medical Center", city: "Gloucester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA026",GROUP_DESC:"MA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4960 ,protection_group_id: -3960, protection_element_id:-3960], primaryKey: false);
      insert('organizations', [ id: 103946, nci_institute_code: "MA027", name: "Cable Hospital", city: "Ipswich", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA027",GROUP_DESC:"MA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4961 ,protection_group_id: -3961, protection_element_id:-3961], primaryKey: false);
      insert('organizations', [ id: 103947, nci_institute_code: "MA028", name: "Salem Hospital", city: "Salem", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA028",GROUP_DESC:"MA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4962 ,protection_group_id: -3962, protection_element_id:-3962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 103948, nci_institute_code: "MA029", name: "Dedham Medical Associates", city: "Dedham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA029",GROUP_DESC:"MA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4963 ,protection_group_id: -3963, protection_element_id:-3963], primaryKey: false);
      insert('organizations', [ id: 103949, nci_institute_code: "MA030", name: "Southwood Community Hospital", city: "Norfolk", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA030",GROUP_DESC:"MA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4964 ,protection_group_id: -3964, protection_element_id:-3964], primaryKey: false);
      insert('organizations', [ id: 103950, nci_institute_code: "MA031", name: "South Shore Medical Clinic", city: "Norwell", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA031",GROUP_DESC:"MA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4965 ,protection_group_id: -3965, protection_element_id:-3965], primaryKey: false);
      insert('organizations', [ id: 103951, nci_institute_code: "MA032", name: "Pondsville Hospital", city: "Walpole", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA032",GROUP_DESC:"MA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4966 ,protection_group_id: -3966, protection_element_id:-3966], primaryKey: false);
      insert('organizations', [ id: 103952, nci_institute_code: "MA033", name: "Tufts-New England Medical Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA033",GROUP_DESC:"MA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4967 ,protection_group_id: -3967, protection_element_id:-3967], primaryKey: false);
      insert('organizations', [ id: 103953, nci_institute_code: "MA034", name: "Massachusetts General Hospital Cancer Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA034",GROUP_DESC:"MA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4968 ,protection_group_id: -3968, protection_element_id:-3968], primaryKey: false);
      insert('organizations', [ id: 103954, nci_institute_code: "MA035", name: "Eye Research Inst Retina Ford", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA035",GROUP_DESC:"MA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4969 ,protection_group_id: -3969, protection_element_id:-3969], primaryKey: false);
      insert('organizations', [ id: 103955, nci_institute_code: "MA036", name: "Dana-Farber Cancer Center Institute", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA036",GROUP_DESC:"MA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4970 ,protection_group_id: -3970, protection_element_id:-3970], primaryKey: false);
      insert('organizations', [ id: 103956, nci_institute_code: "MA037", name: "Brigham and Women's Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA037",GROUP_DESC:"MA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4971 ,protection_group_id: -3971, protection_element_id:-3971], primaryKey: false);
      insert('organizations', [ id: 103957, nci_institute_code: "MA038", name: "Beth Israel Deaconess Medical Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA038",GROUP_DESC:"MA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4972 ,protection_group_id: -3972, protection_element_id:-3972], primaryKey: false);
      insert('organizations', [ id: 103958, nci_institute_code: "MA039", name: "Harvard Medical School", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA039",GROUP_DESC:"MA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4973 ,protection_group_id: -3973, protection_element_id:-3973], primaryKey: false);
      insert('organizations', [ id: 103959, nci_institute_code: "MA040", name: "Angell Memorial Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA040",GROUP_DESC:"MA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4974 ,protection_group_id: -3974, protection_element_id:-3974], primaryKey: false);
      insert('organizations', [ id: 103960, nci_institute_code: "MA042", name: "Boston Children's Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA042",GROUP_DESC:"MA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4975 ,protection_group_id: -3975, protection_element_id:-3975], primaryKey: false);
      insert('organizations', [ id: 103961, nci_institute_code: "MA043", name: "Boston Medical Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA043",GROUP_DESC:"MA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4976 ,protection_group_id: -3976, protection_element_id:-3976], primaryKey: false);
      insert('organizations', [ id: 103962, nci_institute_code: "MA044", name: "Boston City Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA044",GROUP_DESC:"MA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4977 ,protection_group_id: -3977, protection_element_id:-3977], primaryKey: false);
      insert('organizations', [ id: 103963, nci_institute_code: "MA045", name: "Lemuel Shattuck Hospital", city: "Jamaica Plain", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA045",GROUP_DESC:"MA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4978 ,protection_group_id: -3978, protection_element_id:-3978], primaryKey: false);
      insert('organizations', [ id: 103964, nci_institute_code: "MA046", name: "Faulkner Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA046",GROUP_DESC:"MA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4979 ,protection_group_id: -3979, protection_element_id:-3979], primaryKey: false);
      insert('organizations', [ id: 103965, nci_institute_code: "MA047", name: "Boston Veteran's Affairs Medical Center", city: "Jamaica Plain", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA047",GROUP_DESC:"MA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4980 ,protection_group_id: -3980, protection_element_id:-3980], primaryKey: false);
      insert('organizations', [ id: 103966, nci_institute_code: "MA048", name: "Boston Usphs Hospital", city: "Brighton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA048",GROUP_DESC:"MA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4981 ,protection_group_id: -3981, protection_element_id:-3981], primaryKey: false);
      insert('organizations', [ id: 103967, nci_institute_code: "MA049", name: "Caritas Saint Elizabeth's Medical Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA049",GROUP_DESC:"MA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4982 ,protection_group_id: -3982, protection_element_id:-3982], primaryKey: false);
      insert('organizations', [ id: 103968, nci_institute_code: "MA050", name: "Cambridge Hospital", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA050",GROUP_DESC:"MA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4983 ,protection_group_id: -3983, protection_element_id:-3983], primaryKey: false);
      insert('organizations', [ id: 103969, nci_institute_code: "MA051", name: "Arthur D. Little, Incorporated", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA051",GROUP_DESC:"MA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4984 ,protection_group_id: -3984, protection_element_id:-3984], primaryKey: false);
      insert('organizations', [ id: 103970, nci_institute_code: "MA052", name: "Biogen Research Corporation", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA052",GROUP_DESC:"MA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4985 ,protection_group_id: -3985, protection_element_id:-3985], primaryKey: false);
      insert('organizations', [ id: 103971, nci_institute_code: "MA053", name: "Boston Hospital for Women", city: "Brookline", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA053",GROUP_DESC:"MA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4986 ,protection_group_id: -3986, protection_element_id:-3986], primaryKey: false);
      insert('organizations', [ id: 103972, nci_institute_code: "MA054", name: "Malden Hospital", city: "Malden", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA054",GROUP_DESC:"MA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4987 ,protection_group_id: -3987, protection_element_id:-3987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 103973, nci_institute_code: "MA055", name: "Lawrence Memorial", city: "Medford", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA055",GROUP_DESC:"MA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4988 ,protection_group_id: -3988, protection_element_id:-3988], primaryKey: false);
      insert('organizations', [ id: 103974, nci_institute_code: "MA056", name: "Newton-Wellesley Hospital", city: "Newton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA056",GROUP_DESC:"MA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4989 ,protection_group_id: -3989, protection_element_id:-3989], primaryKey: false);
      insert('organizations', [ id: 103975, nci_institute_code: "MA057", name: "Quincy Hospital", city: "Quincy", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA057",GROUP_DESC:"MA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4990 ,protection_group_id: -3990, protection_element_id:-3990], primaryKey: false);
      insert('organizations', [ id: 103976, nci_institute_code: "MA058", name: "Medical Center at Symmes", city: "Arlington", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA058",GROUP_DESC:"MA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4991 ,protection_group_id: -3991, protection_element_id:-3991], primaryKey: false);
      insert('organizations', [ id: 103977, nci_institute_code: "MA059", name: "Melrose-Wakefield Hospital", city: "Melrose", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA059",GROUP_DESC:"MA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4992 ,protection_group_id: -3992, protection_element_id:-3992], primaryKey: false);
      insert('organizations', [ id: 103978, nci_institute_code: "MA060", name: "Glover Memorial Hospital", city: "Needham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA060",GROUP_DESC:"MA060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4993 ,protection_group_id: -3993, protection_element_id:-3993], primaryKey: false);
      insert('organizations', [ id: 103979, nci_institute_code: "MA062", name: "Morton Hospital and Medical Center", city: "Taunton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA062",GROUP_DESC:"MA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4994 ,protection_group_id: -3994, protection_element_id:-3994], primaryKey: false);
      insert('organizations', [ id: 103980, nci_institute_code: "MA064", name: "Mount Auburn Hospital", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA064",GROUP_DESC:"MA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4995 ,protection_group_id: -3995, protection_element_id:-3995], primaryKey: false);
      insert('organizations', [ id: 103981, nci_institute_code: "MA065", name: "Deaconess Waltham Hospital", city: "Waltham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA065",GROUP_DESC:"MA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4996 ,protection_group_id: -3996, protection_element_id:-3996], primaryKey: false);
      insert('organizations', [ id: 103982, nci_institute_code: "MA066", name: "Jordan Hospital", city: "Plymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA066",GROUP_DESC:"MA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4997 ,protection_group_id: -3997, protection_element_id:-3997], primaryKey: false);
      insert('organizations', [ id: 103983, nci_institute_code: "MA067", name: "Cardinal Cushing General Hospital", city: "Brockton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA067",GROUP_DESC:"MA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4998 ,protection_group_id: -3998, protection_element_id:-3998], primaryKey: false);
      insert('organizations', [ id: 103984, nci_institute_code: "MA068", name: "Brockton Hospital", city: "Brockton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA068",GROUP_DESC:"MA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4999 ,protection_group_id: -3999, protection_element_id:-3999], primaryKey: false);
      insert('organizations', [ id: 103985, nci_institute_code: "MA071", name: "Charlton Memorial", city: "Fall River", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA071",GROUP_DESC:"MA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5000 ,protection_group_id: -4000, protection_element_id:-4000], primaryKey: false);
      insert('organizations', [ id: 103986, nci_institute_code: "MA072", name: "Saint Anne's Hospital", city: "Fall River", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA072",GROUP_DESC:"MA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5001 ,protection_group_id: -4001, protection_element_id:-4001], primaryKey: false);
      insert('organizations', [ id: 103987, nci_institute_code: "MA073", name: "Saint Luke's Hospital", city: "New Bedford", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA073",GROUP_DESC:"MA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5002 ,protection_group_id: -4002, protection_element_id:-4002], primaryKey: false);
      insert('organizations', [ id: 103988, nci_institute_code: "MA074", name: "New England Baptist Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA074",GROUP_DESC:"MA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5003 ,protection_group_id: -4003, protection_element_id:-4003], primaryKey: false);
      insert('organizations', [ id: 103989, nci_institute_code: "MA075", name: "Harvard Vanguard Medical Associates Inc", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA075",GROUP_DESC:"MA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5004 ,protection_group_id: -4004, protection_element_id:-4004], primaryKey: false);
      insert('organizations', [ id: 103990, nci_institute_code: "MA076", name: "Anna Jaques Hospital", city: "Newburyport", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA076",GROUP_DESC:"MA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5005 ,protection_group_id: -4005, protection_element_id:-4005], primaryKey: false);
      insert('organizations', [ id: 103991, nci_institute_code: "MA078", name: "Cape Cod Hospital", city: "Hyannis", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA078",GROUP_DESC:"MA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5006 ,protection_group_id: -4006, protection_element_id:-4006], primaryKey: false);
      insert('organizations', [ id: 103992, nci_institute_code: "MA080", name: "Floating Hospital for Children", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA080",GROUP_DESC:"MA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5007 ,protection_group_id: -4007, protection_element_id:-4007], primaryKey: false);
      insert('organizations', [ id: 103993, nci_institute_code: "MA081", name: "Norwood Hospital", city: "Norwood", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA081",GROUP_DESC:"MA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5008 ,protection_group_id: -4008, protection_element_id:-4008], primaryKey: false);
      insert('organizations', [ id: 103994, nci_institute_code: "MA085", name: "Sturdy Memorial Hospital", city: "Attleboro", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA085",GROUP_DESC:"MA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5009 ,protection_group_id: -4009, protection_element_id:-4009], primaryKey: false);
      insert('organizations', [ id: 103995, nci_institute_code: "MA086", name: "The Cancer Center, Incorporated", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA086",GROUP_DESC:"MA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5010 ,protection_group_id: -4010, protection_element_id:-4010], primaryKey: false);
      insert('organizations', [ id: 103996, nci_institute_code: "MA087", name: "South Shore Hospital", city: "South Weymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA087",GROUP_DESC:"MA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5011 ,protection_group_id: -4011, protection_element_id:-4011], primaryKey: false);
      insert('organizations', [ id: 103997, nci_institute_code: "MA088", name: "Medical Center of Central Massachusetts", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA088",GROUP_DESC:"MA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5012 ,protection_group_id: -4012, protection_element_id:-4012], primaryKey: false);
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
