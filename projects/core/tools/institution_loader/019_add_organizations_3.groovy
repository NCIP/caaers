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
      insert('organizations', [ id: 101998, nci_institute_code: "CA420", name: "Daniel Freeman Marina Hospital", city: "Marina Del Ray", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA420",GROUP_DESC:"CA420 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA420",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA420",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA420", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3013 ,protection_group_id: -2013, protection_element_id:-2013], primaryKey: false);
      insert('organizations', [ id: 101999, nci_institute_code: "CA421", name: "Medical Oncology Care Associates", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA421",GROUP_DESC:"CA421 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA421",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA421",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA421", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3014 ,protection_group_id: -2014, protection_element_id:-2014], primaryKey: false);
      insert('organizations', [ id: 102000, nci_institute_code: "CA422", name: "Saint Jude Heritage Medical Group", city: "Fullerton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA422",GROUP_DESC:"CA422 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA422",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA422",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA422", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3015 ,protection_group_id: -2015, protection_element_id:-2015], primaryKey: false);
      insert('organizations', [ id: 102001, nci_institute_code: "CA423", name: "Doctors Medical Center- JC Robinson Regional Cancer Center", city: "San Pablo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA423",GROUP_DESC:"CA423 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA423",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA423",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA423", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3016 ,protection_group_id: -2016, protection_element_id:-2016], primaryKey: false);
      insert('organizations', [ id: 102002, nci_institute_code: "CA425", name: "Eureka Internal Medicine", city: "Eureka", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA425",GROUP_DESC:"CA425 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA425",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA425",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA425", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3017 ,protection_group_id: -2017, protection_element_id:-2017], primaryKey: false);
      insert('organizations', [ id: 102003, nci_institute_code: "CA426", name: "Oncology Associates Mdcl Grp", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA426",GROUP_DESC:"CA426 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA426",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA426",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA426", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3018 ,protection_group_id: -2018, protection_element_id:-2018], primaryKey: false);
      insert('organizations', [ id: 102004, nci_institute_code: "CA427", name: "Veteran's Administration Medical Center of North California", city: "sacromento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA427",GROUP_DESC:"CA427 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA427",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA427",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA427", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3019 ,protection_group_id: -2019, protection_element_id:-2019], primaryKey: false);
      insert('organizations', [ id: 102005, nci_institute_code: "CA428", name: "Casaintro Street Clinic", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA428",GROUP_DESC:"CA428 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA428",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA428",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA428", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3020 ,protection_group_id: -2020, protection_element_id:-2020], primaryKey: false);
      insert('organizations', [ id: 102006, nci_institute_code: "CA429", name: "Virginia K Crosson Cancer Center", city: "Fullerton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA429",GROUP_DESC:"CA429 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA429",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA429",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA429", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3021 ,protection_group_id: -2021, protection_element_id:-2021], primaryKey: false);
      insert('organizations', [ id: 102007, nci_institute_code: "CA430", name: "Redwood Regional Medical Group - Ukiah", city: "Ukiah", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA430",GROUP_DESC:"CA430 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA430",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA430",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA430", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3022 ,protection_group_id: -2022, protection_element_id:-2022], primaryKey: false);
      insert('organizations', [ id: 102008, nci_institute_code: "CA431", name: "Tower Cancer Research Foundation", city: "Beverly Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA431",GROUP_DESC:"CA431 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA431",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA431",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA431", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3023 ,protection_group_id: -2023, protection_element_id:-2023], primaryKey: false);
      insert('organizations', [ id: 102009, nci_institute_code: "CA432", name: "Saint Rose Hospital", city: "Hayward", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA432",GROUP_DESC:"CA432 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA432",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA432",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA432", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3024 ,protection_group_id: -2024, protection_element_id:-2024], primaryKey: false);
      insert('organizations', [ id: 102010, nci_institute_code: "CA433", name: "Valley Care Health System - Livermore", city: "Livermore", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA433",GROUP_DESC:"CA433 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA433",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA433",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA433", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3025 ,protection_group_id: -2025, protection_element_id:-2025], primaryKey: false);
      insert('organizations', [ id: 102011, nci_institute_code: "CA434", name: "Riverside County Reg Med Ctr", city: "Moreno Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA434",GROUP_DESC:"CA434 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA434",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA434",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA434", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3026 ,protection_group_id: -2026, protection_element_id:-2026], primaryKey: false);
      insert('organizations', [ id: 102012, nci_institute_code: "CA435", name: "Children's Hospital Central California", city: "Madera", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA435",GROUP_DESC:"CA435 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA435",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA435",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA435", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3027 ,protection_group_id: -2027, protection_element_id:-2027], primaryKey: false);
      insert('organizations', [ id: 102013, nci_institute_code: "CA436", name: "St. Elizabeth Community Hospital", city: "Red Bluff", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA436",GROUP_DESC:"CA436 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA436",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA436",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA436", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3028 ,protection_group_id: -2028, protection_element_id:-2028], primaryKey: false);
      insert('organizations', [ id: 102014, nci_institute_code: "CA438", name: "San Diego Endocrine and Medical Clinic", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA438",GROUP_DESC:"CA438 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA438",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA438",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA438", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3029 ,protection_group_id: -2029, protection_element_id:-2029], primaryKey: false);
      insert('organizations', [ id: 102015, nci_institute_code: "CA440", name: "Twin Cities Community Hospital", city: "Templeton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA440",GROUP_DESC:"CA440 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA440",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA440",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA440", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3030 ,protection_group_id: -2030, protection_element_id:-2030], primaryKey: false);
      insert('organizations', [ id: 102016, nci_institute_code: "CA441", name: "Sievers and Candela Medical Corp", city: "Tarzana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA441",GROUP_DESC:"CA441 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA441",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA441",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA441", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3031 ,protection_group_id: -2031, protection_element_id:-2031], primaryKey: false);
      insert('organizations', [ id: 102017, nci_institute_code: "CA442", name: "Naval Hospital", city: "Twentynine Palms", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA442",GROUP_DESC:"CA442 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA442",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA442",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA442", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3032 ,protection_group_id: -2032, protection_element_id:-2032], primaryKey: false);
      insert('organizations', [ id: 102018, nci_institute_code: "CA443", name: "Weed Army Community Hospital", city: "Fort Irwin", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA443",GROUP_DESC:"CA443 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA443",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA443",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA443", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3033 ,protection_group_id: -2033, protection_element_id:-2033], primaryKey: false);
      insert('organizations', [ id: 102019, nci_institute_code: "CA445", name: "Naval Ambulatory Care Center", city: "Port Hueneme", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA445",GROUP_DESC:"CA445 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA445",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA445",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA445", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3034 ,protection_group_id: -2034, protection_element_id:-2034], primaryKey: false);
      insert('organizations', [ id: 102020, nci_institute_code: "CA446", name: "Good Samaritan Hospital Regional Cancer Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA446",GROUP_DESC:"CA446 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA446",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA446",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA446", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3035 ,protection_group_id: -2035, protection_element_id:-2035], primaryKey: false);
      insert('organizations', [ id: 102021, nci_institute_code: "CA447", name: "Ukia Valley Medical Center", city: "Ukiah", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA447",GROUP_DESC:"CA447 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA447",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA447",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA447", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3036 ,protection_group_id: -2036, protection_element_id:-2036], primaryKey: false);
      insert('organizations', [ id: 102022, nci_institute_code: "CA449", name: "Glendale Adventist Medical Center", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA449",GROUP_DESC:"CA449 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA449",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA449",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA449", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3037 ,protection_group_id: -2037, protection_element_id:-2037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 102023, nci_institute_code: "CA451", name: "Vandenberg Air Force Base", city: "Vandenberg,Afb", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA451",GROUP_DESC:"CA451 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA451",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA451",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA451", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3038 ,protection_group_id: -2038, protection_element_id:-2038], primaryKey: false);
      insert('organizations', [ id: 102024, nci_institute_code: "CA452", name: "Los Angeles Air Force Base", city: "El Segundo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA452",GROUP_DESC:"CA452 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA452",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA452",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA452", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3039 ,protection_group_id: -2039, protection_element_id:-2039], primaryKey: false);
      insert('organizations', [ id: 102025, nci_institute_code: "CA453", name: "Edwards Air Force Base", city: "Edwards Afb", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA453",GROUP_DESC:"CA453 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA453",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA453",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA453", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3040 ,protection_group_id: -2040, protection_element_id:-2040], primaryKey: false);
      insert('organizations', [ id: 102026, nci_institute_code: "CA454", name: "Kaiser Permanente Medical Group - Baldwin Park", city: "Baldwin Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA454",GROUP_DESC:"CA454 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA454",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA454",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA454", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3041 ,protection_group_id: -2041, protection_element_id:-2041], primaryKey: false);
      insert('organizations', [ id: 102027, nci_institute_code: "CA455", name: "California Cancer Medical Center", city: "West Covina", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA455",GROUP_DESC:"CA455 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA455",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA455",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA455", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3042 ,protection_group_id: -2042, protection_element_id:-2042], primaryKey: false);
      insert('organizations', [ id: 102028, nci_institute_code: "CA456", name: "Investigative Clinical Research", city: "Chino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA456",GROUP_DESC:"CA456 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA456",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA456",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA456", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3043 ,protection_group_id: -2043, protection_element_id:-2043], primaryKey: false);
      insert('organizations', [ id: 102029, nci_institute_code: "CA457", name: "Hematology Oncology Consultants", city: "Hemlet", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA457",GROUP_DESC:"CA457 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA457",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA457",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA457", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3044 ,protection_group_id: -2044, protection_element_id:-2044], primaryKey: false);
      insert('organizations', [ id: 102030, nci_institute_code: "CA458", name: "Intervalley Surgical Specialists", city: "Valencia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA458",GROUP_DESC:"CA458 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA458",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA458",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA458", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3045 ,protection_group_id: -2045, protection_element_id:-2045], primaryKey: false);
      insert('organizations', [ id: 102031, nci_institute_code: "CA459", name: "North Valley Breast Clinic", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA459",GROUP_DESC:"CA459 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA459",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA459",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA459", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3046 ,protection_group_id: -2046, protection_element_id:-2046], primaryKey: false);
      insert('organizations', [ id: 102032, nci_institute_code: "CA460", name: "Oncology Hematology", city: "Granada Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA460",GROUP_DESC:"CA460 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA460",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA460",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA460", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3047 ,protection_group_id: -2047, protection_element_id:-2047], primaryKey: false);
      insert('organizations', [ id: 102033, nci_institute_code: "CA461", name: "Sierra Hematology and Oncology", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA461",GROUP_DESC:"CA461 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA461",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA461",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA461", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3048 ,protection_group_id: -2048, protection_element_id:-2048], primaryKey: false);
      insert('organizations', [ id: 102034, nci_institute_code: "CA462", name: "Eddie Hu, M.D.", city: "Monterey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA462",GROUP_DESC:"CA462 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA462",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA462",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA462", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3049 ,protection_group_id: -2049, protection_element_id:-2049], primaryKey: false);
      insert('organizations', [ id: 102035, nci_institute_code: "CA463", name: "Contra Costa Regional Medical Center", city: "Martinez", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA463",GROUP_DESC:"CA463 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA463",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA463",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA463", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3050 ,protection_group_id: -2050, protection_element_id:-2050], primaryKey: false);
      insert('organizations', [ id: 102036, nci_institute_code: "CA464", name: "Chapman Medical Center", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA464",GROUP_DESC:"CA464 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA464",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA464",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA464", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3051 ,protection_group_id: -2051, protection_element_id:-2051], primaryKey: false);
      insert('organizations', [ id: 102037, nci_institute_code: "CA465", name: "Valley Radiation Oncology Center", city: "Tarzana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA465",GROUP_DESC:"CA465 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA465",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA465",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA465", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3052 ,protection_group_id: -2052, protection_element_id:-2052], primaryKey: false);
      insert('organizations', [ id: 102038, nci_institute_code: "CA466", name: "Loma Linda University Radiation Medicine", city: "Loma Linda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA466",GROUP_DESC:"CA466 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA466",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA466",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA466", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3053 ,protection_group_id: -2053, protection_element_id:-2053], primaryKey: false);
      insert('organizations', [ id: 102039, nci_institute_code: "CA467", name: "Valley Care Health System - Pleasanton", city: "Pleasanton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA467",GROUP_DESC:"CA467 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA467",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA467",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA467", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3054 ,protection_group_id: -2054, protection_element_id:-2054], primaryKey: false);
      insert('organizations', [ id: 102040, nci_institute_code: "CA468", name: "Merced Radiation Therapy, Inc.", city: "Merced", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA468",GROUP_DESC:"CA468 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA468",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA468",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA468", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3055 ,protection_group_id: -2055, protection_element_id:-2055], primaryKey: false);
      insert('organizations', [ id: 102041, nci_institute_code: "CA469", name: "The Comprehensive Cancer Centers of the Desert", city: "Palm Springs", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA469",GROUP_DESC:"CA469 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA469",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA469",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA469", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3056 ,protection_group_id: -2056, protection_element_id:-2056], primaryKey: false);
      insert('organizations', [ id: 102042, nci_institute_code: "CA470", name: "Kaiser Permanente-Stockton", city: "Stockton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA470",GROUP_DESC:"CA470 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA470",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA470",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA470", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3057 ,protection_group_id: -2057, protection_element_id:-2057], primaryKey: false);
      insert('organizations', [ id: 102043, nci_institute_code: "CA471", name: "University California Los Angeles-Pasadena", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA471",GROUP_DESC:"CA471 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA471",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA471",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA471", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3058 ,protection_group_id: -2058, protection_element_id:-2058], primaryKey: false);
      insert('organizations', [ id: 102044, nci_institute_code: "CA473", name: "Franks Prescription Shop", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA473",GROUP_DESC:"CA473 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA473",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA473",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA473", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3059 ,protection_group_id: -2059, protection_element_id:-2059], primaryKey: false);
      insert('organizations', [ id: 102045, nci_institute_code: "CA474", name: "Valley Regional Oncology Center", city: "Wildomar", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA474",GROUP_DESC:"CA474 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA474",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA474",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA474", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3060 ,protection_group_id: -2060, protection_element_id:-2060], primaryKey: false);
      insert('organizations', [ id: 102046, nci_institute_code: "CA475", name: "Orange County Regional Cancer Center", city: "Fountain Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA475",GROUP_DESC:"CA475 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA475",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA475",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA475", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3061 ,protection_group_id: -2061, protection_element_id:-2061], primaryKey: false);
      insert('organizations', [ id: 102047, nci_institute_code: "CA476", name: "Kaiser Foundation Hospital -Anaheim", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA476",GROUP_DESC:"CA476 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA476",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA476",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA476", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3062 ,protection_group_id: -2062, protection_element_id:-2062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 102048, nci_institute_code: "CA478", name: "Univ. of California San Francisco Beckman Vision Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA478",GROUP_DESC:"CA478 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA478",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA478",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA478", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3063 ,protection_group_id: -2063, protection_element_id:-2063], primaryKey: false);
      insert('organizations', [ id: 102049, nci_institute_code: "CA482", name: "Hematology and Oncology Associates", city: "San Mateo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA482",GROUP_DESC:"CA482 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA482",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA482",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA482", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3064 ,protection_group_id: -2064, protection_element_id:-2064], primaryKey: false);
      insert('organizations', [ id: 102050, nci_institute_code: "CA483", name: "Kaiser Permanente - Panorama City", city: "Panorama City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA483",GROUP_DESC:"CA483 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA483",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA483",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA483", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3065 ,protection_group_id: -2065, protection_element_id:-2065], primaryKey: false);
      insert('organizations', [ id: 102051, nci_institute_code: "CA484", name: "Burnham Institute", city: "Lajolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA484",GROUP_DESC:"CA484 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA484",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA484",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA484", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3066 ,protection_group_id: -2066, protection_element_id:-2066], primaryKey: false);
      insert('organizations', [ id: 102052, nci_institute_code: "CA485", name: "Buck Institute for Age Research", city: "Novato", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA485",GROUP_DESC:"CA485 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA485",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA485",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA485", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3067 ,protection_group_id: -2067, protection_element_id:-2067], primaryKey: false);
      insert('organizations', [ id: 102053, nci_institute_code: "CA486", name: "Cancer Care Assoc Medical Group, Inc.", city: "Redondo Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA486",GROUP_DESC:"CA486 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA486",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA486",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA486", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3068 ,protection_group_id: -2068, protection_element_id:-2068], primaryKey: false);
      insert('organizations', [ id: 102054, nci_institute_code: "CA487", name: "Redwood Regional Medical Group - Petaluma", city: "Petaluma", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA487",GROUP_DESC:"CA487 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA487",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA487",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA487", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3069 ,protection_group_id: -2069, protection_element_id:-2069], primaryKey: false);
      insert('organizations', [ id: 102055, nci_institute_code: "CA488", name: "California Hema\\Onco Medical Group", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA488",GROUP_DESC:"CA488 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA488",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA488",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA488", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3070 ,protection_group_id: -2070, protection_element_id:-2070], primaryKey: false);
      insert('organizations', [ id: 102056, nci_institute_code: "CA489", name: "Novato Community Hospital", city: "Novato", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA489",GROUP_DESC:"CA489 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA489",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA489",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA489", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3071 ,protection_group_id: -2071, protection_element_id:-2071], primaryKey: false);
      insert('organizations', [ id: 102057, nci_institute_code: "CA490", name: "Northern California Children's Hematology/Oncology Medical Group", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA490",GROUP_DESC:"CA490 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA490",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA490",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA490", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3072 ,protection_group_id: -2072, protection_element_id:-2072], primaryKey: false);
      insert('organizations', [ id: 102058, nci_institute_code: "CA491", name: "Diablo Valley Oncology and Hematology Medical Group Inc", city: "Walnut Creek", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA491",GROUP_DESC:"CA491 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA491",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA491",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA491", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3073 ,protection_group_id: -2073, protection_element_id:-2073], primaryKey: false);
      insert('organizations', [ id: 102059, nci_institute_code: "CA492", name: "Centinela Hospital Medical Center", city: "Inglewood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA492",GROUP_DESC:"CA492 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA492",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA492",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA492", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3074 ,protection_group_id: -2074, protection_element_id:-2074], primaryKey: false);
      insert('organizations', [ id: 102060, nci_institute_code: "CA493", name: "Corona Regional Medical Center", city: "Corona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA493",GROUP_DESC:"CA493 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA493",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA493",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA493", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3075 ,protection_group_id: -2075, protection_element_id:-2075], primaryKey: false);
      insert('organizations', [ id: 102061, nci_institute_code: "CA494", name: "South Orange County Medical Research Center", city: "Laguna Woods", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA494",GROUP_DESC:"CA494 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA494",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA494",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA494", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3076 ,protection_group_id: -2076, protection_element_id:-2076], primaryKey: false);
      insert('organizations', [ id: 102062, nci_institute_code: "CA495", name: "San Diego VA Health Care System", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA495",GROUP_DESC:"CA495 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA495",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA495",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA495", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3077 ,protection_group_id: -2077, protection_element_id:-2077], primaryKey: false);
      insert('organizations', [ id: 102063, nci_institute_code: "CA497", name: "San Diego Hospice Acute Care Center", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA497",GROUP_DESC:"CA497 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA497",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA497",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA497", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3078 ,protection_group_id: -2078, protection_element_id:-2078], primaryKey: false);
      insert('organizations', [ id: 102064, nci_institute_code: "CA498", name: "City of Hope Medical Group, Inc.", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA498",GROUP_DESC:"CA498 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA498",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA498",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA498", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3079 ,protection_group_id: -2079, protection_element_id:-2079], primaryKey: false);
      insert('organizations', [ id: 102065, nci_institute_code: "CA499", name: "Los Palos Medical Associates, Inc.", city: "Salinas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA499",GROUP_DESC:"CA499 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA499",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA499",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA499", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3080 ,protection_group_id: -2080, protection_element_id:-2080], primaryKey: false);
      insert('organizations', [ id: 102066, nci_institute_code: "CA500", name: "East Bay Med'cl Onc\\Hema Assoc, Inc.", city: "San Leandro", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA500",GROUP_DESC:"CA500 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA500",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA500",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA500", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3081 ,protection_group_id: -2081, protection_element_id:-2081], primaryKey: false);
      insert('organizations', [ id: 102067, nci_institute_code: "CA501", name: "Cancer and Blood Institute Medical Group at The Lucy Curci Cancer Center", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA501",GROUP_DESC:"CA501 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA501",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA501",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA501", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3082 ,protection_group_id: -2082, protection_element_id:-2082], primaryKey: false);
      insert('organizations', [ id: 102068, nci_institute_code: "CA502", name: "Mission Regional Breast Center", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA502",GROUP_DESC:"CA502 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA502",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA502",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA502", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3083 ,protection_group_id: -2083, protection_element_id:-2083], primaryKey: false);
      insert('organizations', [ id: 102069, nci_institute_code: "CA503", name: "University Medical Center", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA503",GROUP_DESC:"CA503 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA503",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA503",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA503", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3084 ,protection_group_id: -2084, protection_element_id:-2084], primaryKey: false);
      insert('organizations', [ id: 102070, nci_institute_code: "CA504", name: "Sutter Auburn Faith Hospital", city: "Auburn", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA504",GROUP_DESC:"CA504 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA504",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA504",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA504", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3085 ,protection_group_id: -2085, protection_element_id:-2085], primaryKey: false);
      insert('organizations', [ id: 102071, nci_institute_code: "CA505", name: "Sutter Amador Hospital", city: "Jackson", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA505",GROUP_DESC:"CA505 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA505",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA505",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA505", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3086 ,protection_group_id: -2086, protection_element_id:-2086], primaryKey: false);
      insert('organizations', [ id: 102072, nci_institute_code: "CA506", name: "Clovis Community Hospital", city: "Clovis", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA506",GROUP_DESC:"CA506 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA506",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA506",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA506", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3087 ,protection_group_id: -2087, protection_element_id:-2087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 102073, nci_institute_code: "CA507", name: "Sutter Roseville Medical Center", city: "Roseville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA507",GROUP_DESC:"CA507 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA507",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA507",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA507", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3088 ,protection_group_id: -2088, protection_element_id:-2088], primaryKey: false);
      insert('organizations', [ id: 102074, nci_institute_code: "CA508", name: "Huntington Medical Research Institutes", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA508",GROUP_DESC:"CA508 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA508",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA508",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA508", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3089 ,protection_group_id: -2089, protection_element_id:-2089], primaryKey: false);
      insert('organizations', [ id: 102075, nci_institute_code: "CA510", name: "Scripps Mercy Hospital", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA510",GROUP_DESC:"CA510 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA510",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA510",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA510", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3090 ,protection_group_id: -2090, protection_element_id:-2090], primaryKey: false);
      insert('organizations', [ id: 102076, nci_institute_code: "CA512", name: "Kaiser Permanente, West Los Angeles", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA512",GROUP_DESC:"CA512 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA512",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA512",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA512", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3091 ,protection_group_id: -2091, protection_element_id:-2091], primaryKey: false);
      insert('organizations', [ id: 102077, nci_institute_code: "CA514", name: "Santa Barbara Hematology Oncology Group Inc. - Solvang", city: "Solvang", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA514",GROUP_DESC:"CA514 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA514",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA514",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA514", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3092 ,protection_group_id: -2092, protection_element_id:-2092], primaryKey: false);
      insert('organizations', [ id: 102078, nci_institute_code: "CA515", name: "University of California San Diego-Chula Vista", city: "Chula Vista", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA515",GROUP_DESC:"CA515 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA515",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA515",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA515", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3093 ,protection_group_id: -2093, protection_element_id:-2093], primaryKey: false);
      insert('organizations', [ id: 102079, nci_institute_code: "CA516", name: "San Francisco Oncology Associates", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA516",GROUP_DESC:"CA516 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA516",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA516",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA516", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3094 ,protection_group_id: -2094, protection_element_id:-2094], primaryKey: false);
      insert('organizations', [ id: 102080, nci_institute_code: "CA517", name: "Saddleback Valley Surgical Medical Group", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA517",GROUP_DESC:"CA517 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA517",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA517",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA517", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3095 ,protection_group_id: -2095, protection_element_id:-2095], primaryKey: false);
      insert('organizations', [ id: 102081, nci_institute_code: "CA518", name: "Valley Medical Oncology", city: "Fremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA518",GROUP_DESC:"CA518 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA518",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA518",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA518", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3096 ,protection_group_id: -2096, protection_element_id:-2096], primaryKey: false);
      insert('organizations', [ id: 102082, nci_institute_code: "CA519", name: "Santa Barbara Hematology Oncology Group, Inc. - Lompoc", city: "Lompoc", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA519",GROUP_DESC:"CA519 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA519",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA519",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA519", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3097 ,protection_group_id: -2097, protection_element_id:-2097], primaryKey: false);
      insert('organizations', [ id: 102083, nci_institute_code: "CA520", name: "Kaiser Permanente, Division of Research", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA520",GROUP_DESC:"CA520 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA520",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA520",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA520", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3098 ,protection_group_id: -2098, protection_element_id:-2098], primaryKey: false);
      insert('organizations', [ id: 102084, nci_institute_code: "CA521", name: "Salk Institute", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA521",GROUP_DESC:"CA521 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA521",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA521",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA521", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3099 ,protection_group_id: -2099, protection_element_id:-2099], primaryKey: false);
      insert('organizations', [ id: 102085, nci_institute_code: "CA522", name: "Red Bluff Tumor Institute", city: "Red Bluff", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA522",GROUP_DESC:"CA522 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA522",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA522",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA522", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3100 ,protection_group_id: -2100, protection_element_id:-2100], primaryKey: false);
      insert('organizations', [ id: 102086, nci_institute_code: "CA523", name: "Oncotech Inc.", city: "Tustin", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA523",GROUP_DESC:"CA523 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA523",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA523",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA523", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3101 ,protection_group_id: -2101, protection_element_id:-2101], primaryKey: false);
      insert('organizations', [ id: 102087, nci_institute_code: "CA524", name: "Lionel B. Katchem Oncology", city: "Upland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA524",GROUP_DESC:"CA524 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA524",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA524",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA524", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3102 ,protection_group_id: -2102, protection_element_id:-2102], primaryKey: false);
      insert('organizations', [ id: 102088, nci_institute_code: "CA525", name: "Los Palos Hematology and Oncology", city: "Salinas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA525",GROUP_DESC:"CA525 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA525",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA525",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA525", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3103 ,protection_group_id: -2103, protection_element_id:-2103], primaryKey: false);
      insert('organizations', [ id: 102089, nci_institute_code: "CA526", name: "Sonoma Valley Hospital", city: "Sonoma", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA526",GROUP_DESC:"CA526 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA526",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA526",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA526", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3104 ,protection_group_id: -2104, protection_element_id:-2104], primaryKey: false);
      insert('organizations', [ id: 102090, nci_institute_code: "CA527", name: "Cancer Center at Perlman", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA527",GROUP_DESC:"CA527 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA527",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA527",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA527", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3105 ,protection_group_id: -2105, protection_element_id:-2105], primaryKey: false);
      insert('organizations', [ id: 102091, nci_institute_code: "CA528", name: "Pacific Imaging PET Center", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA528",GROUP_DESC:"CA528 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA528",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA528",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA528", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3106 ,protection_group_id: -2106, protection_element_id:-2106], primaryKey: false);
      insert('organizations', [ id: 102092, nci_institute_code: "CA529", name: "St. Joseph's Hospital Medical Center", city: "Stockton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA529",GROUP_DESC:"CA529 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA529",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA529",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA529", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3107 ,protection_group_id: -2107, protection_element_id:-2107], primaryKey: false);
      insert('organizations', [ id: 102093, nci_institute_code: "CA530", name: "The Breast Care Center", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA530",GROUP_DESC:"CA530 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA530",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA530",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA530", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3108 ,protection_group_id: -2108, protection_element_id:-2108], primaryKey: false);
      insert('organizations', [ id: 102094, nci_institute_code: "CA531", name: "California Hematology Oncology Medical Group", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA531",GROUP_DESC:"CA531 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA531",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA531",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA531", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3109 ,protection_group_id: -2109, protection_element_id:-2109], primaryKey: false);
      insert('organizations', [ id: 102095, nci_institute_code: "CA532", name: "Valley Medical Oncology Consultants", city: "Pleasanton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA532",GROUP_DESC:"CA532 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA532",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA532",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA532", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3110 ,protection_group_id: -2110, protection_element_id:-2110], primaryKey: false);
      insert('organizations', [ id: 102096, nci_institute_code: "CA533", name: "Women's Cancer Center", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA533",GROUP_DESC:"CA533 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA533",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA533",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA533", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3111 ,protection_group_id: -2111, protection_element_id:-2111], primaryKey: false);
      insert('organizations', [ id: 102097, nci_institute_code: "CA534", name: "UCSD Thornton Hospital", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA534",GROUP_DESC:"CA534 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA534",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA534",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA534", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3112 ,protection_group_id: -2112, protection_element_id:-2112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 102098, nci_institute_code: "CA535", name: "Alvarado Hospital Medical Center", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA535",GROUP_DESC:"CA535 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA535",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA535",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA535", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3113 ,protection_group_id: -2113, protection_element_id:-2113], primaryKey: false);
      insert('organizations', [ id: 102099, nci_institute_code: "CA537", name: "Kaiser Permanente Medical Care Program - Los Angeles Medical Center & Sunset Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA537",GROUP_DESC:"CA537 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA537",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA537",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA537", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3114 ,protection_group_id: -2114, protection_element_id:-2114], primaryKey: false);
      insert('organizations', [ id: 102100, nci_institute_code: "CA538", name: "Women's Cancer Center of Southern California at Queen of Angels-Hollywood Presbyterian Medcal Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA538",GROUP_DESC:"CA538 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA538",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA538",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA538", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3115 ,protection_group_id: -2115, protection_element_id:-2115], primaryKey: false);
      insert('organizations', [ id: 102101, nci_institute_code: "CA539", name: "North Valley Hematology Oncology Medical Group", city: "Mission Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA539",GROUP_DESC:"CA539 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA539",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA539",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA539", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3116 ,protection_group_id: -2116, protection_element_id:-2116], primaryKey: false);
      insert('organizations', [ id: 102102, nci_institute_code: "CA540", name: "Sidney Kimmel Cancer Center", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA540",GROUP_DESC:"CA540 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA540",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA540",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA540", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3117 ,protection_group_id: -2117, protection_element_id:-2117], primaryKey: false);
      insert('organizations', [ id: 102103, nci_institute_code: "CA541", name: "Washington-Stanford Radiation Oncology Center", city: "Fremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA541",GROUP_DESC:"CA541 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA541",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA541",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA541", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3118 ,protection_group_id: -2118, protection_element_id:-2118], primaryKey: false);
      insert('organizations', [ id: 102104, nci_institute_code: "CA543", name: "Chao Family Comprehensive Cancer Center", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA543",GROUP_DESC:"CA543 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA543",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA543",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA543", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3119 ,protection_group_id: -2119, protection_element_id:-2119], primaryKey: false);
      insert('organizations', [ id: 102105, nci_institute_code: "CA544", name: "Colon & Rectal Surgeons", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA544",GROUP_DESC:"CA544 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA544",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA544",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA544", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3120 ,protection_group_id: -2120, protection_element_id:-2120], primaryKey: false);
      insert('organizations', [ id: 102106, nci_institute_code: "CA545", name: "Oncology Associates of San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA545",GROUP_DESC:"CA545 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA545",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA545",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA545", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3121 ,protection_group_id: -2121, protection_element_id:-2121], primaryKey: false);
      insert('organizations', [ id: 102107, nci_institute_code: "CA546", name: "Thomas S. Lossing Inc.", city: "Lompoc", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA546",GROUP_DESC:"CA546 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA546",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA546",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA546", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3122 ,protection_group_id: -2122, protection_element_id:-2122], primaryKey: false);
      insert('organizations', [ id: 102108, nci_institute_code: "CA547", name: "Northstate Cancer Specialty", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA547",GROUP_DESC:"CA547 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA547",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA547",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA547", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3123 ,protection_group_id: -2123, protection_element_id:-2123], primaryKey: false);
      insert('organizations', [ id: 102109, nci_institute_code: "CA548", name: "East Bay Regional Cancer Center", city: "Hayward", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA548",GROUP_DESC:"CA548 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA548",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA548",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA548", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3124 ,protection_group_id: -2124, protection_element_id:-2124], primaryKey: false);
      insert('organizations', [ id: 102110, nci_institute_code: "CA549", name: "Radiation Center Medical Group", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA549",GROUP_DESC:"CA549 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA549",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA549",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA549", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3125 ,protection_group_id: -2125, protection_element_id:-2125], primaryKey: false);
      insert('organizations', [ id: 102111, nci_institute_code: "CA550", name: "Scripps Cancer Center - San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA550",GROUP_DESC:"CA550 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA550",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA550",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA550", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3126 ,protection_group_id: -2126, protection_element_id:-2126], primaryKey: false);
      insert('organizations', [ id: 102112, nci_institute_code: "CA551", name: "California Cancer Care - Greenbrae", city: "Greenbrae", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA551",GROUP_DESC:"CA551 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA551",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA551",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA551", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3127 ,protection_group_id: -2127, protection_element_id:-2127], primaryKey: false);
      insert('organizations', [ id: 102113, nci_institute_code: "CA552", name: "East Bay Medical Oncology Hematology", city: "Concord", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA552",GROUP_DESC:"CA552 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA552",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA552",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA552", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3128 ,protection_group_id: -2128, protection_element_id:-2128], primaryKey: false);
      insert('organizations', [ id: 102114, nci_institute_code: "CA553", name: "Redwood Regional Medical Group - Santa Rosa", city: "Santa Rosa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA553",GROUP_DESC:"CA553 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA553",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA553",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA553", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3129 ,protection_group_id: -2129, protection_element_id:-2129], primaryKey: false);
      insert('organizations', [ id: 102115, nci_institute_code: "CA554", name: "Arrowhead Regional Medical Center", city: "Colton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA554",GROUP_DESC:"CA554 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA554",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA554",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA554", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3130 ,protection_group_id: -2130, protection_element_id:-2130], primaryKey: false);
      insert('organizations', [ id: 102116, nci_institute_code: "CA555", name: "Oncology/Hematology Medical Associates of the Central Coast Inc.", city: "San Luis Obispo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA555",GROUP_DESC:"CA555 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA555",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA555",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA555", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3131 ,protection_group_id: -2131, protection_element_id:-2131], primaryKey: false);
      insert('organizations', [ id: 102117, nci_institute_code: "CA556", name: "Loma Linda University Cancer Institute", city: "Loma Linda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA556",GROUP_DESC:"CA556 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA556",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA556",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA556", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3132 ,protection_group_id: -2132, protection_element_id:-2132], primaryKey: false);
      insert('organizations', [ id: 102118, nci_institute_code: "CA557", name: "Sonora Regional Medical Center", city: "Sonora", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA557",GROUP_DESC:"CA557 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA557",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA557",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA557", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3133 ,protection_group_id: -2133, protection_element_id:-2133], primaryKey: false);
      insert('organizations', [ id: 102119, nci_institute_code: "CA558", name: "Breastlink Medical Group Inc.", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA558",GROUP_DESC:"CA558 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA558",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA558",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA558", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3134 ,protection_group_id: -2134, protection_element_id:-2134], primaryKey: false);
      insert('organizations', [ id: 102120, nci_institute_code: "CA559", name: "Sutter Solano Medical Center", city: "Vallejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA559",GROUP_DESC:"CA559 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA559",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA559",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA559", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3135 ,protection_group_id: -2135, protection_element_id:-2135], primaryKey: false);
      insert('organizations', [ id: 102121, nci_institute_code: "CA560", name: "Gynecologic Oncology Associates", city: "Los Gatos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA560",GROUP_DESC:"CA560 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA560",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA560",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA560", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3136 ,protection_group_id: -2136, protection_element_id:-2136], primaryKey: false);
      insert('organizations', [ id: 102122, nci_institute_code: "CA561", name: "Women's Cance Center - San Ramon", city: "San Ramon", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA561",GROUP_DESC:"CA561 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA561",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA561",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA561", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3137 ,protection_group_id: -2137, protection_element_id:-2137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 102123, nci_institute_code: "CA562", name: "Wilshire Oncology Medical Group Inc", city: "Pomona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA562",GROUP_DESC:"CA562 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA562",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA562",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA562", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3138 ,protection_group_id: -2138, protection_element_id:-2138], primaryKey: false);
      insert('organizations', [ id: 102124, nci_institute_code: "CA564", name: "Fremont - Rideout Cancer Center", city: "Marysville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA564",GROUP_DESC:"CA564 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA564",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA564",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA564", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3139 ,protection_group_id: -2139, protection_element_id:-2139], primaryKey: false);
      insert('organizations', [ id: 102125, nci_institute_code: "CA565", name: "The Angeles Clinic and Research Institute", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA565",GROUP_DESC:"CA565 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA565",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA565",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA565", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3140 ,protection_group_id: -2140, protection_element_id:-2140], primaryKey: false);
      insert('organizations', [ id: 102126, nci_institute_code: "CA566", name: "Compassionate Oncology Medical Group", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA566",GROUP_DESC:"CA566 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA566",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA566",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA566", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3141 ,protection_group_id: -2141, protection_element_id:-2141], primaryKey: false);
      insert('organizations', [ id: 102127, nci_institute_code: "CA567", name: "Mather Veteran Affairs Medical Center", city: "Mather", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA567",GROUP_DESC:"CA567 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA567",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA567",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA567", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3142 ,protection_group_id: -2142, protection_element_id:-2142], primaryKey: false);
      insert('organizations', [ id: 102128, nci_institute_code: "CA568", name: "Sierra Nevada Cancer Center", city: "Grass Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA568",GROUP_DESC:"CA568 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA568",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA568",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA568", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3143 ,protection_group_id: -2143, protection_element_id:-2143], primaryKey: false);
      insert('organizations', [ id: 102129, nci_institute_code: "CA569", name: "Grass Valley Hematology/Oncology Medical Group", city: "Grass Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA569",GROUP_DESC:"CA569 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA569",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA569",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA569", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3144 ,protection_group_id: -2144, protection_element_id:-2144], primaryKey: false);
      insert('organizations', [ id: 102130, nci_institute_code: "CA570", name: "Wilshire Oncology Medical Group, Inc-Administrative office", city: "LA Verne", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA570",GROUP_DESC:"CA570 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA570",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA570",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA570", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3145 ,protection_group_id: -2145, protection_element_id:-2145], primaryKey: false);
      insert('organizations', [ id: 102131, nci_institute_code: "CA571", name: "Saint Teresa Cancer Center", city: "Stockton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA571",GROUP_DESC:"CA571 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA571",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA571",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA571", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3146 ,protection_group_id: -2146, protection_element_id:-2146], primaryKey: false);
      insert('organizations', [ id: 102132, nci_institute_code: "CA572", name: "Petaluma Valley Hospital", city: "Petaluma", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA572",GROUP_DESC:"CA572 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA572",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA572",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA572", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3147 ,protection_group_id: -2147, protection_element_id:-2147], primaryKey: false);
      insert('organizations', [ id: 102133, nci_institute_code: "CA573", name: "Modesto Surgical Associates", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA573",GROUP_DESC:"CA573 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA573",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA573",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA573", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3148 ,protection_group_id: -2148, protection_element_id:-2148], primaryKey: false);
      insert('organizations', [ id: 102134, nci_institute_code: "CA574", name: "Breast Care Specialist", city: "Medesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA574",GROUP_DESC:"CA574 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA574",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA574",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA574", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3149 ,protection_group_id: -2149, protection_element_id:-2149], primaryKey: false);
      insert('organizations', [ id: 102135, nci_institute_code: "CA575", name: "High Desert Blood and Cancer Center", city: "Victorville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA575",GROUP_DESC:"CA575 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA575",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA575",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA575", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3150 ,protection_group_id: -2150, protection_element_id:-2150], primaryKey: false);
      insert('organizations', [ id: 102136, nci_institute_code: "CA576", name: "Valley Medical Oncology Consultants-Castro Valley", city: "Castro Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA576",GROUP_DESC:"CA576 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA576",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA576",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA576", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3151 ,protection_group_id: -2151, protection_element_id:-2151], primaryKey: false);
      insert('organizations', [ id: 102137, nci_institute_code: "CA577", name: "Kaiser Permanente - Union City Landing", city: "Union City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA577",GROUP_DESC:"CA577 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA577",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA577",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA577", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3152 ,protection_group_id: -2152, protection_element_id:-2152], primaryKey: false);
      insert('organizations', [ id: 102138, nci_institute_code: "CA578", name: "University of California Davis Cancer Center", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA578",GROUP_DESC:"CA578 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA578",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA578",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA578", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3153 ,protection_group_id: -2153, protection_element_id:-2153], primaryKey: false);
      insert('organizations', [ id: 102139, nci_institute_code: "CA579", name: "Oncology Care Medical Associates", city: "Montebello", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA579",GROUP_DESC:"CA579 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA579",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA579",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA579", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3154 ,protection_group_id: -2154, protection_element_id:-2154], primaryKey: false);
      insert('organizations', [ id: 102140, nci_institute_code: "CA580", name: "Alta Bates Comprehensive Cancer Center", city: "Berkeley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA580",GROUP_DESC:"CA580 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA580",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA580",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA580", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3155 ,protection_group_id: -2155, protection_element_id:-2155], primaryKey: false);
      insert('organizations', [ id: 102141, nci_institute_code: "CA581", name: "Orange County Thoracic & Cardiovascular Surgery", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA581",GROUP_DESC:"CA581 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA581",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA581",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA581", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3156 ,protection_group_id: -2156, protection_element_id:-2156], primaryKey: false);
      insert('organizations', [ id: 102142, nci_institute_code: "CA582", name: "Pulmonary Consultants & Primary Care Physicians Medical Group, Inc.", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA582",GROUP_DESC:"CA582 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA582",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA582",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA582", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3157 ,protection_group_id: -2157, protection_element_id:-2157], primaryKey: false);
      insert('organizations', [ id: 102143, nci_institute_code: "CA583", name: "Salinas Radiation Oncology Center", city: "Salinas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA583",GROUP_DESC:"CA583 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA583",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA583",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA583", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3158 ,protection_group_id: -2158, protection_element_id:-2158], primaryKey: false);
      insert('organizations', [ id: 102144, nci_institute_code: "CA585", name: "Women's Cancer Center at El Camino Hosptial", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA585",GROUP_DESC:"CA585 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA585",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA585",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA585", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3159 ,protection_group_id: -2159, protection_element_id:-2159], primaryKey: false);
      insert('organizations', [ id: 102145, nci_institute_code: "CA586", name: "Sequoia Oncology Medical Associates", city: "Visalia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA586",GROUP_DESC:"CA586 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA586",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA586",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA586", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3160 ,protection_group_id: -2160, protection_element_id:-2160], primaryKey: false);
      insert('organizations', [ id: 102146, nci_institute_code: "CA587", name: "Ray, Margaret, Savage. M.D. (UIA Investigator)", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA587",GROUP_DESC:"CA587 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA587",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA587",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA587", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3161 ,protection_group_id: -2161, protection_element_id:-2161], primaryKey: false);
      insert('organizations', [ id: 102147, nci_institute_code: "CA588", name: "Rosenblum, Alan, M.D. (UIA Investigator)", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA588",GROUP_DESC:"CA588 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA588",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA588",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA588", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3162 ,protection_group_id: -2162, protection_element_id:-2162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 102148, nci_institute_code: "CA589", name: "Beckman Research Institute - City of Hope", city: "Duarte", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA589",GROUP_DESC:"CA589 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA589",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA589",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA589", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3163 ,protection_group_id: -2163, protection_element_id:-2163], primaryKey: false);
      insert('organizations', [ id: 102149, nci_institute_code: "CA590", name: "Claremont Colleges", city: "Claremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA590",GROUP_DESC:"CA590 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA590",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA590",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA590", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3164 ,protection_group_id: -2164, protection_element_id:-2164], primaryKey: false);
      insert('organizations', [ id: 102150, nci_institute_code: "CA591", name: "Intercenter Cancer Research Group", city: "Palm Springs", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA591",GROUP_DESC:"CA591 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA591",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA591",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA591", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3165 ,protection_group_id: -2165, protection_element_id:-2165], primaryKey: false);
      insert('organizations', [ id: 102151, nci_institute_code: "CA592", name: "University of California Berkeley", city: "Berkeley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA592",GROUP_DESC:"CA592 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA592",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA592",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA592", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3166 ,protection_group_id: -2166, protection_element_id:-2166], primaryKey: false);
      insert('organizations', [ id: 102152, nci_institute_code: "CA593", name: "Cancer Center Radiation Oncology", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA593",GROUP_DESC:"CA593 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA593",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA593",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA593", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3167 ,protection_group_id: -2167, protection_element_id:-2167], primaryKey: false);
      insert('organizations', [ id: 102153, nci_institute_code: "CA594", name: "RAND Corporation", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA594",GROUP_DESC:"CA594 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA594",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA594",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA594", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3168 ,protection_group_id: -2168, protection_element_id:-2168], primaryKey: false);
      insert('organizations', [ id: 102154, nci_institute_code: "CA595", name: "Hematology - Oncology Medical Group of Fresno Inc", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA595",GROUP_DESC:"CA595 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA595",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA595",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA595", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3169 ,protection_group_id: -2169, protection_element_id:-2169], primaryKey: false);
      insert('organizations', [ id: 102155, nci_institute_code: "CA596", name: "Fairmont Pharmacy", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA596",GROUP_DESC:"CA596 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA596",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA596",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA596", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3170 ,protection_group_id: -2170, protection_element_id:-2170], primaryKey: false);
      insert('organizations', [ id: 102156, nci_institute_code: "CA597", name: "Comprehensive Surgical Specialists", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA597",GROUP_DESC:"CA597 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA597",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA597",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA597", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3171 ,protection_group_id: -2171, protection_element_id:-2171], primaryKey: false);
      insert('organizations', [ id: 102157, nci_institute_code: "CA598", name: "Kaiser Permanente-Rancho Cordova Cancer Center", city: "Rancho Cardova", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA598",GROUP_DESC:"CA598 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA598",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA598",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA598", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3172 ,protection_group_id: -2172, protection_element_id:-2172], primaryKey: false);
      insert('organizations', [ id: 102158, nci_institute_code: "CA599", name: "Fresno Cancer Center", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA599",GROUP_DESC:"CA599 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA599",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA599",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA599", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3173 ,protection_group_id: -2173, protection_element_id:-2173], primaryKey: false);
      insert('organizations', [ id: 102159, nci_institute_code: "CA600", name: "Stanford Cancer Center", city: "Stanford", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA600",GROUP_DESC:"CA600 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA600",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA600",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA600", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3174 ,protection_group_id: -2174, protection_element_id:-2174], primaryKey: false);
      insert('organizations', [ id: 102160, nci_institute_code: "CA601", name: "Alameda Radiation Oncology", city: "Hayward", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA601",GROUP_DESC:"CA601 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA601",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA601",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA601", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3175 ,protection_group_id: -2175, protection_element_id:-2175], primaryKey: false);
      insert('organizations', [ id: 102161, nci_institute_code: "CA602", name: "Rohnert Park Cancer Center", city: "Rohnert Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA602",GROUP_DESC:"CA602 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA602",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA602",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA602", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3176 ,protection_group_id: -2176, protection_element_id:-2176], primaryKey: false);
      insert('organizations', [ id: 102162, nci_institute_code: "CA603", name: "Redding Cancer Treatment Center", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA603",GROUP_DESC:"CA603 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA603",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA603",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA603", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3177 ,protection_group_id: -2177, protection_element_id:-2177], primaryKey: false);
      insert('organizations', [ id: 102163, nci_institute_code: "CA604", name: "Sequoia Regional Cancer Center", city: "Visalia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA604",GROUP_DESC:"CA604 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA604",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA604",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA604", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3178 ,protection_group_id: -2178, protection_element_id:-2178], primaryKey: false);
      insert('organizations', [ id: 102164, nci_institute_code: "CA605", name: "University of California at San Francisco - Comprehensive Cancer Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA605",GROUP_DESC:"CA605 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA605",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA605",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA605", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3179 ,protection_group_id: -2179, protection_element_id:-2179], primaryKey: false);
      insert('organizations', [ id: 102165, nci_institute_code: "CA606", name: "California Oncology of the Central Valley", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA606",GROUP_DESC:"CA606 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA606",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA606",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA606", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3180 ,protection_group_id: -2180, protection_element_id:-2180], primaryKey: false);
      insert('organizations', [ id: 102166, nci_institute_code: "CA607", name: "CCS Associates", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA607",GROUP_DESC:"CA607 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA607",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA607",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA607", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3181 ,protection_group_id: -2181, protection_element_id:-2181], primaryKey: false);
      insert('organizations', [ id: 102167, nci_institute_code: "CA608", name: "East Bay Radiation Oncology Center", city: "Castro Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA608",GROUP_DESC:"CA608 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA608",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA608",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA608", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3182 ,protection_group_id: -2182, protection_element_id:-2182], primaryKey: false);
      insert('organizations', [ id: 102168, nci_institute_code: "CA609", name: "Sequoia Regional Cancer Center", city: "Hanford", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA609",GROUP_DESC:"CA609 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA609",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA609",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA609", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3183 ,protection_group_id: -2183, protection_element_id:-2183], primaryKey: false);
      insert('organizations', [ id: 102169, nci_institute_code: "CA610", name: "Radiological Associates of Sacramento Medical Group, Inc.", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA610",GROUP_DESC:"CA610 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA610",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA610",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA610", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3184 ,protection_group_id: -2184, protection_element_id:-2184], primaryKey: false);
      insert('organizations', [ id: 102170, nci_institute_code: "CA611", name: "Bay Area Colon and Rectal Surgeons", city: "Concord", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA611",GROUP_DESC:"CA611 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA611",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA611",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA611", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3185 ,protection_group_id: -2185, protection_element_id:-2185], primaryKey: false);
      insert('organizations', [ id: 102171, nci_institute_code: "CA612", name: "Santa Monica Hematology & Oncology Consultants", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA612",GROUP_DESC:"CA612 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA612",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA612",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA612", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3186 ,protection_group_id: -2186, protection_element_id:-2186], primaryKey: false);
      insert('organizations', [ id: 102172, nci_institute_code: "CA613", name: "Mission Viejo Radiation Oncology Center", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA613",GROUP_DESC:"CA613 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA613",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA613",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA613", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3187 ,protection_group_id: -2187, protection_element_id:-2187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 102173, nci_institute_code: "CA614", name: "American Institute of Mathematics", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA614",GROUP_DESC:"CA614 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA614",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA614",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA614", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3188 ,protection_group_id: -2188, protection_element_id:-2188], primaryKey: false);
      insert('organizations', [ id: 102174, nci_institute_code: "CA615", name: "Compassionate Cancer Care Medical Group Inc - Fountain Valley", city: "Fountain Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA615",GROUP_DESC:"CA615 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA615",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA615",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA615", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3189 ,protection_group_id: -2189, protection_element_id:-2189], primaryKey: false);
      insert('organizations', [ id: 102175, nci_institute_code: "CA616", name: "Compassionate Cancer Care Medical Group Inc-Carona", city: "Carona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA616",GROUP_DESC:"CA616 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA616",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA616",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA616", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3190 ,protection_group_id: -2190, protection_element_id:-2190], primaryKey: false);
      insert('organizations', [ id: 102176, nci_institute_code: "CA617", name: "San Diego Oncology Medical Clinic", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA617",GROUP_DESC:"CA617 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA617",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA617",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA617", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3191 ,protection_group_id: -2191, protection_element_id:-2191], primaryKey: false);
      insert('organizations', [ id: 102177, nci_institute_code: "CA618", name: "Oncology Hematology Consultants Inc", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA618",GROUP_DESC:"CA618 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA618",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA618",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA618", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3192 ,protection_group_id: -2192, protection_element_id:-2192], primaryKey: false);
      insert('organizations', [ id: 102178, nci_institute_code: "CA619", name: "Burrill & Company", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA619",GROUP_DESC:"CA619 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA619",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA619",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA619", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3193 ,protection_group_id: -2193, protection_element_id:-2193], primaryKey: false);
      insert('organizations', [ id: 102179, nci_institute_code: "CA621", name: "Cancer Care Associates", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA621",GROUP_DESC:"CA621 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA621",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA621",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA621", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3194 ,protection_group_id: -2194, protection_element_id:-2194], primaryKey: false);
      insert('organizations', [ id: 102180, nci_institute_code: "CA622", name: "Los Angeles Hematology/Oncology Medical Group", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA622",GROUP_DESC:"CA622 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA622",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA622",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA622", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3195 ,protection_group_id: -2195, protection_element_id:-2195], primaryKey: false);
      insert('organizations', [ id: 102181, nci_institute_code: "CA623", name: "Valley Associated Urology Medical Group Inc", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA623",GROUP_DESC:"CA623 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA623",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA623",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA623", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3196 ,protection_group_id: -2196, protection_element_id:-2196], primaryKey: false);
      insert('organizations', [ id: 102182, nci_institute_code: "CA624", name: "Oncology and Hematology of Imperial Valley", city: "El Centro", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA624",GROUP_DESC:"CA624 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA624",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA624",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA624", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3197 ,protection_group_id: -2197, protection_element_id:-2197], primaryKey: false);
      insert('organizations', [ id: 102183, nci_institute_code: "CA625", name: "Pacific Oncology and Hematology Associates", city: "Encinitas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA625",GROUP_DESC:"CA625 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA625",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA625",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA625", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3198 ,protection_group_id: -2198, protection_element_id:-2198], primaryKey: false);
      insert('organizations', [ id: 102184, nci_institute_code: "CA626", name: "Pacific Gynecologic Specialist", city: "Burbank", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA626",GROUP_DESC:"CA626 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA626",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA626",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA626", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3199 ,protection_group_id: -2199, protection_element_id:-2199], primaryKey: false);
      insert('organizations', [ id: 102185, nci_institute_code: "CA627", name: "Capitol Surgical Associates Inc", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA627",GROUP_DESC:"CA627 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA627",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA627",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA627", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3200 ,protection_group_id: -2200, protection_element_id:-2200], primaryKey: false);
      insert('organizations', [ id: 102186, nci_institute_code: "CA628", name: "Sutter Midtown Pharmacy", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA628",GROUP_DESC:"CA628 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA628",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA628",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA628", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3201 ,protection_group_id: -2201, protection_element_id:-2201], primaryKey: false);
      insert('organizations', [ id: 102187, nci_institute_code: "CA629", name: "Affiliates in Imaging A Medical Group Inc", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA629",GROUP_DESC:"CA629 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA629",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA629",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA629", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3202 ,protection_group_id: -2202, protection_element_id:-2202], primaryKey: false);
      insert('organizations', [ id: 102188, nci_institute_code: "CA630", name: "Peninsula Urology Center", city: "Atherton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA630",GROUP_DESC:"CA630 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA630",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA630",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA630", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3203 ,protection_group_id: -2203, protection_element_id:-2203], primaryKey: false);
      insert('organizations', [ id: 102189, nci_institute_code: "CA631", name: "Advanced Research Management Services Inc", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA631",GROUP_DESC:"CA631 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA631",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA631",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA631", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3204 ,protection_group_id: -2204, protection_element_id:-2204], primaryKey: false);
      insert('organizations', [ id: 102190, nci_institute_code: "CA632", name: "Valley Radiotherapy Associates Medical Group", city: "El Segundo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA632",GROUP_DESC:"CA632 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA632",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA632",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA632", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3205 ,protection_group_id: -2205, protection_element_id:-2205], primaryKey: false);
      insert('organizations', [ id: 102191, nci_institute_code: "CA633", name: "Gynecologic Oncology Associates", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA633",GROUP_DESC:"CA633 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA633",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA633",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA633", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3206 ,protection_group_id: -2206, protection_element_id:-2206], primaryKey: false);
      insert('organizations', [ id: 102192, nci_institute_code: "CA634", name: "Good Samaritan Hospital Breast Care Center", city: "Los Gatos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA634",GROUP_DESC:"CA634 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA634",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA634",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA634", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3207 ,protection_group_id: -2207, protection_element_id:-2207], primaryKey: false);
      insert('organizations', [ id: 102193, nci_institute_code: "CA635", name: "Sharp Rees Stealy Medical Group", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA635",GROUP_DESC:"CA635 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA635",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA635",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA635", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3208 ,protection_group_id: -2208, protection_element_id:-2208], primaryKey: false);
      insert('organizations', [ id: 102194, nci_institute_code: "CA636", name: "Solano Radiation Oncology Center", city: "Vacaville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA636",GROUP_DESC:"CA636 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA636",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA636",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA636", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3209 ,protection_group_id: -2209, protection_element_id:-2209], primaryKey: false);
      insert('organizations', [ id: 102195, nci_institute_code: "CA637", name: "Advanced Urology of the Desert", city: "Palm Desert", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA637",GROUP_DESC:"CA637 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA637",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA637",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA637", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3210 ,protection_group_id: -2210, protection_element_id:-2210], primaryKey: false);
      insert('organizations', [ id: 102196, nci_institute_code: "CA638", name: "Midpeninsula Surgical Associates Group Inc", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA638",GROUP_DESC:"CA638 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA638",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA638",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA638", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3211 ,protection_group_id: -2211, protection_element_id:-2211], primaryKey: false);
      insert('organizations', [ id: 102197, nci_institute_code: "CA639", name: "Sarcoma Oncology Center", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA639",GROUP_DESC:"CA639 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA639",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA639",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA639", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3212 ,protection_group_id: -2212, protection_element_id:-2212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 102198, nci_institute_code: "CA640", name: "Premiere Oncology", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA640",GROUP_DESC:"CA640 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA640",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA640",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA640", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3213 ,protection_group_id: -2213, protection_element_id:-2213], primaryKey: false);
      insert('organizations', [ id: 102199, nci_institute_code: "CA641", name: "California Endocurietherapy Cancer Center", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA641",GROUP_DESC:"CA641 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA641",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA641",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA641", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3214 ,protection_group_id: -2214, protection_element_id:-2214], primaryKey: false);
      insert('organizations', [ id: 102200, nci_institute_code: "CA642", name: "The Cancer Care Institute", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA642",GROUP_DESC:"CA642 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA642",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA642",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA642", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3215 ,protection_group_id: -2215, protection_element_id:-2215], primaryKey: false);
      insert('organizations', [ id: 102201, nci_institute_code: "CA643", name: "Kaiser Permanente Research and Evaluation", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA643",GROUP_DESC:"CA643 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA643",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA643",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA643", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3216 ,protection_group_id: -2216, protection_element_id:-2216], primaryKey: false);
      insert('organizations', [ id: 102202, nci_institute_code: "CA644", name: "Sierra Foothills Surgical Specialists", city: "Auburn", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA644",GROUP_DESC:"CA644 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA644",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA644",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA644", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3217 ,protection_group_id: -2217, protection_element_id:-2217], primaryKey: false);
      insert('organizations', [ id: 102203, nci_institute_code: "CA645", name: "Desert Hematology Oncology Medical Group", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA645",GROUP_DESC:"CA645 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA645",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA645",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA645", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3218 ,protection_group_id: -2218, protection_element_id:-2218], primaryKey: false);
      insert('organizations', [ id: 102204, nci_institute_code: "CA646", name: "Irwin, Lowell Eugene, MD (Office)", city: "Monterey Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA646",GROUP_DESC:"CA646 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA646",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA646",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA646", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3219 ,protection_group_id: -2219, protection_element_id:-2219], primaryKey: false);
      insert('organizations', [ id: 102205, nci_institute_code: "CA647", name: "Temple Community Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA647",GROUP_DESC:"CA647 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA647",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA647",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA647", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3220 ,protection_group_id: -2220, protection_element_id:-2220], primaryKey: false);
      insert('organizations', [ id: 102206, nci_institute_code: "CA648", name: "Newport Cancer Care", city: "Newport Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA648",GROUP_DESC:"CA648 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA648",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA648",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA648", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3221 ,protection_group_id: -2221, protection_element_id:-2221], primaryKey: false);
      insert('organizations', [ id: 102207, nci_institute_code: "CA649", name: "The Angeles Clinic and Research Institute - West Los Angeles Office", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA649",GROUP_DESC:"CA649 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA649",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA649",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA649", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3222 ,protection_group_id: -2222, protection_element_id:-2222], primaryKey: false);
      insert('organizations', [ id: 102208, nci_institute_code: "CA650", name: "Advanced Breast Care Specialists of Orange County", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA650",GROUP_DESC:"CA650 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA650",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA650",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA650", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3223 ,protection_group_id: -2223, protection_element_id:-2223], primaryKey: false);
      insert('organizations', [ id: 102209, nci_institute_code: "CA651", name: "Brian J LeBerthon MD Inc", city: "West Covina", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA651",GROUP_DESC:"CA651 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA651",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA651",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA651", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3224 ,protection_group_id: -2224, protection_element_id:-2224], primaryKey: false);
      insert('organizations', [ id: 102210, nci_institute_code: "CA652", name: "UCLA Healthcare - Santa Monica Surgery", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA652",GROUP_DESC:"CA652 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA652",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA652",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA652", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3225 ,protection_group_id: -2225, protection_element_id:-2225], primaryKey: false);
      insert('organizations', [ id: 102211, nci_institute_code: "CA653", name: "Kaiser Permanente Radiation Oncology Cancer Treatment Center", city: "Santa Clara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA653",GROUP_DESC:"CA653 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA653",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA653",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA653", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3226 ,protection_group_id: -2226, protection_element_id:-2226], primaryKey: false);
      insert('organizations', [ id: 102212, nci_institute_code: "CA654", name: "Edward R Alexson MD Inc", city: "Santa Ana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA654",GROUP_DESC:"CA654 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA654",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA654",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA654", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3227 ,protection_group_id: -2227, protection_element_id:-2227], primaryKey: false);
      insert('organizations', [ id: 102213, nci_institute_code: "CA655", name: "Pasadena Urological Medical Group", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA655",GROUP_DESC:"CA655 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA655",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA655",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA655", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3228 ,protection_group_id: -2228, protection_element_id:-2228], primaryKey: false);
      insert('organizations', [ id: 102214, nci_institute_code: "CA656", name: "South Sacramento Cancer Center", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA656",GROUP_DESC:"CA656 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA656",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA656",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA656", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3229 ,protection_group_id: -2229, protection_element_id:-2229], primaryKey: false);
      insert('organizations', [ id: 102215, nci_institute_code: "CA657", name: "Roseville Radiation Oncology Center", city: "Roseville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA657",GROUP_DESC:"CA657 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA657",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA657",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA657", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3230 ,protection_group_id: -2230, protection_element_id:-2230], primaryKey: false);
      insert('organizations', [ id: 102216, nci_institute_code: "CA658", name: "Mercy General Hospital Radiation Oncology Center", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA658",GROUP_DESC:"CA658 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA658",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA658",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA658", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3231 ,protection_group_id: -2231, protection_element_id:-2231], primaryKey: false);
      insert('organizations', [ id: 102217, nci_institute_code: "CA659", name: "Auburn Radiation Oncology Center", city: "Auburn", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA659",GROUP_DESC:"CA659 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA659",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA659",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA659", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3232 ,protection_group_id: -2232, protection_element_id:-2232], primaryKey: false);
      insert('organizations', [ id: 102218, nci_institute_code: "CA660", name: "Marshall Radiation Oncology Center", city: "Cameron Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA660",GROUP_DESC:"CA660 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA660",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA660",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA660", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3233 ,protection_group_id: -2233, protection_element_id:-2233], primaryKey: false);
      insert('organizations', [ id: 102219, nci_institute_code: "CA661", name: "Kenmar Research Institute", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA661",GROUP_DESC:"CA661 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA661",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA661",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA661", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3234 ,protection_group_id: -2234, protection_element_id:-2234], primaryKey: false);
      insert('organizations', [ id: 102220, nci_institute_code: "CA662", name: "Access Clinical Research", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA662",GROUP_DESC:"CA662 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA662",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA662",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA662", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3235 ,protection_group_id: -2235, protection_element_id:-2235], primaryKey: false);
      insert('organizations', [ id: 102221, nci_institute_code: "CA663", name: "Reynolds Medical Corporation", city: "Palm Desert", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA663",GROUP_DESC:"CA663 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA663",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA663",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA663", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3236 ,protection_group_id: -2236, protection_element_id:-2236], primaryKey: false);
      insert('organizations', [ id: 102222, nci_institute_code: "CA664", name: "Sutter Solano Cancer Center", city: "Vallejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA664",GROUP_DESC:"CA664 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA664",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA664",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA664", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3237 ,protection_group_id: -2237, protection_element_id:-2237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 102223, nci_institute_code: "CA666", name: "Mission OB/GYN Medical Group Inc", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA666",GROUP_DESC:"CA666 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA666",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA666",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA666", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3238 ,protection_group_id: -2238, protection_element_id:-2238], primaryKey: false);
      insert('organizations', [ id: 102224, nci_institute_code: "CA667", name: "Breastlink Medical Group Inc", city: "Fountain Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA667",GROUP_DESC:"CA667 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA667",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA667",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA667", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3239 ,protection_group_id: -2239, protection_element_id:-2239], primaryKey: false);
      insert('organizations', [ id: 102225, nci_institute_code: "CA668", name: "Feather River Cancer Center", city: "Paradise", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA668",GROUP_DESC:"CA668 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA668",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA668",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA668", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3240 ,protection_group_id: -2240, protection_element_id:-2240], primaryKey: false);
      insert('organizations', [ id: 102226, nci_institute_code: "CA669", name: "Stanford Cardiothoracic Surgery", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA669",GROUP_DESC:"CA669 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA669",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA669",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA669", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3241 ,protection_group_id: -2241, protection_element_id:-2241], primaryKey: false);
      insert('organizations', [ id: 102227, nci_institute_code: "CA670", name: "Sacramento Center for Hematology and Medical Oncology", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA670",GROUP_DESC:"CA670 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA670",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA670",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA670", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3242 ,protection_group_id: -2242, protection_element_id:-2242], primaryKey: false);
      insert('organizations', [ id: 102228, nci_institute_code: "CA671", name: "Bay Area Breast Surgeons Inc", city: "Berkeley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA671",GROUP_DESC:"CA671 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA671",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA671",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA671", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3243 ,protection_group_id: -2243, protection_element_id:-2243], primaryKey: false);
      insert('organizations', [ id: 102229, nci_institute_code: "CA672", name: "Cancer Center of Irvine", city: "Irvine", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA672",GROUP_DESC:"CA672 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA672",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA672",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA672", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3244 ,protection_group_id: -2244, protection_element_id:-2244], primaryKey: false);
      insert('organizations', [ id: 102230, nci_institute_code: "CA673", name: "Tom K Lee Inc", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA673",GROUP_DESC:"CA673 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA673",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA673",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA673", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3245 ,protection_group_id: -2245, protection_element_id:-2245], primaryKey: false);
      insert('organizations', [ id: 102231, nci_institute_code: "CA674", name: "Herman and Valvo Inc", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA674",GROUP_DESC:"CA674 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA674",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA674",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA674", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3246 ,protection_group_id: -2246, protection_element_id:-2246], primaryKey: false);
      insert('organizations', [ id: 102232, nci_institute_code: "CA675", name: "Monterey Bay Oncology", city: "Monterey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA675",GROUP_DESC:"CA675 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA675",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA675",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA675", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3247 ,protection_group_id: -2247, protection_element_id:-2247], primaryKey: false);
      insert('organizations', [ id: 102233, nci_institute_code: "CA676", name: "Dr Gene Sherman Inc", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA676",GROUP_DESC:"CA676 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA676",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA676",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA676", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3248 ,protection_group_id: -2248, protection_element_id:-2248], primaryKey: false);
      insert('organizations', [ id: 102234, nci_institute_code: "CA677", name: "Gregory B Smith MD PC", city: "St. Helena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA677",GROUP_DESC:"CA677 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA677",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA677",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA677", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3249 ,protection_group_id: -2249, protection_element_id:-2249], primaryKey: false);
      insert('organizations', [ id: 102235, nci_institute_code: "CA678", name: "Hashimi, Labib Abbas, MD (Office)", city: "Chino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA678",GROUP_DESC:"CA678 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA678",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA678",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA678", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3250 ,protection_group_id: -2250, protection_element_id:-2250], primaryKey: false);
      insert('organizations', [ id: 102236, nci_institute_code: "CA679", name: "Contra Costa Oncology", city: "Walnut Creek", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA679",GROUP_DESC:"CA679 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA679",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA679",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA679", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3251 ,protection_group_id: -2251, protection_element_id:-2251], primaryKey: false);
      insert('organizations', [ id: 102237, nci_institute_code: "CA680", name: "Aptium Oncology", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA680",GROUP_DESC:"CA680 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA680",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA680",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA680", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3252 ,protection_group_id: -2252, protection_element_id:-2252], primaryKey: false);
      insert('organizations', [ id: 102238, nci_institute_code: "CA681", name: "Ajay Verma MD Inc", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA681",GROUP_DESC:"CA681 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA681",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA681",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA681", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3253 ,protection_group_id: -2253, protection_element_id:-2253], primaryKey: false);
      insert('organizations', [ id: 102239, nci_institute_code: "CA682", name: "Peninsula Surgical Specialists Medical Group Inc", city: "San Mateo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA682",GROUP_DESC:"CA682 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA682",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA682",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA682", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3254 ,protection_group_id: -2254, protection_element_id:-2254], primaryKey: false);
      insert('organizations', [ id: 102240, nci_institute_code: "CA683", name: "Marin Cancer Institute", city: "Greenbrae", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA683",GROUP_DESC:"CA683 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA683",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA683",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA683", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3255 ,protection_group_id: -2255, protection_element_id:-2255], primaryKey: false);
      insert('organizations', [ id: 102241, nci_institute_code: "CA684", name: "Sequoia Regional Cancer Center", city: "Hanford", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA684",GROUP_DESC:"CA684 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA684",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA684",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA684", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3256 ,protection_group_id: -2256, protection_element_id:-2256], primaryKey: false);
      insert('organizations', [ id: 102242, nci_institute_code: "CA685", name: "The Robert and Beverly Lewis Family Cancer Care Center", city: "Pomona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA685",GROUP_DESC:"CA685 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA685",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA685",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA685", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3257 ,protection_group_id: -2257, protection_element_id:-2257], primaryKey: false);
      insert('organizations', [ id: 102243, nci_institute_code: "CA686", name: "Dr Susan Love Research Foundation", city: "Pacific Palisades", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA686",GROUP_DESC:"CA686 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA686",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA686",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA686", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3258 ,protection_group_id: -2258, protection_element_id:-2258], primaryKey: false);
      insert('organizations', [ id: 102244, nci_institute_code: "CA687", name: "Facey Medical Group", city: "Mission Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA687",GROUP_DESC:"CA687 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA687",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA687",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA687", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3259 ,protection_group_id: -2259, protection_element_id:-2259], primaryKey: false);
      insert('organizations', [ id: 102245, nci_institute_code: "CA688", name: "Yamamoto Medical Group Inc", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA688",GROUP_DESC:"CA688 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA688",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA688",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA688", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3260 ,protection_group_id: -2260, protection_element_id:-2260], primaryKey: false);
      insert('organizations', [ id: 102246, nci_institute_code: "CA689", name: "Tahoe Forest Hospital District", city: "Truckee", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA689",GROUP_DESC:"CA689 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA689",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA689",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA689", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3261 ,protection_group_id: -2261, protection_element_id:-2261], primaryKey: false);
      insert('organizations', [ id: 102247, nci_institute_code: "CA690", name: "Tahoe Forest Cancer Center", city: "Truckee", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA690",GROUP_DESC:"CA690 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA690",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA690",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA690", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3262 ,protection_group_id: -2262, protection_element_id:-2262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 102248, nci_institute_code: "CA691", name: "Cancer Associates of Monterey Peninsula", city: "Monterey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA691",GROUP_DESC:"CA691 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA691",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA691",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA691", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3263 ,protection_group_id: -2263, protection_element_id:-2263], primaryKey: false);
      insert('organizations', [ id: 102249, nci_institute_code: "CA692", name: "Larry G Strieff MD Medical Corporation", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA692",GROUP_DESC:"CA692 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA692",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA692",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA692", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3264 ,protection_group_id: -2264, protection_element_id:-2264], primaryKey: false);
      insert('organizations', [ id: 102250, nci_institute_code: "CA693", name: "Los Angeles Biomedical Research Institute", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA693",GROUP_DESC:"CA693 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA693",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA693",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA693", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3265 ,protection_group_id: -2265, protection_element_id:-2265], primaryKey: false);
      insert('organizations', [ id: 102251, nci_institute_code: "CA694", name: "Blood and Cancer Genetics Institute", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA694",GROUP_DESC:"CA694 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA694",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA694",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA694", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3266 ,protection_group_id: -2266, protection_element_id:-2266], primaryKey: false);
      insert('organizations', [ id: 102252, nci_institute_code: "CA695", name: "Physician Foundation at California Pacific Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA695",GROUP_DESC:"CA695 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA695",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA695",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA695", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3267 ,protection_group_id: -2267, protection_element_id:-2267], primaryKey: false);
      insert('organizations', [ id: 102253, nci_institute_code: "CA696", name: "Mattel Children's Hospital UCLA", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA696",GROUP_DESC:"CA696 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA696",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA696",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA696", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3268 ,protection_group_id: -2268, protection_element_id:-2268], primaryKey: false);
      insert('organizations', [ id: 102254, nci_institute_code: "CA697", name: "Sherman Oaks Radiation Therapy Center", city: "Sherman Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA697",GROUP_DESC:"CA697 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA697",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA697",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA697", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3269 ,protection_group_id: -2269, protection_element_id:-2269], primaryKey: false);
      insert('organizations', [ id: 102255, nci_institute_code: "CA698", name: "Torrance Memorial Breast Diagnostic Center", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA698",GROUP_DESC:"CA698 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA698",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA698",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA698", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3270 ,protection_group_id: -2270, protection_element_id:-2270], primaryKey: false);
      insert('organizations', [ id: 102256, nci_institute_code: "CA699", name: "Southern California Permanente Medical Group", city: "Irvine", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA699",GROUP_DESC:"CA699 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA699",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA699",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA699", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3271 ,protection_group_id: -2271, protection_element_id:-2271], primaryKey: false);
      insert('organizations', [ id: 102257, nci_institute_code: "CA700", name: "Marshall Cancer Services", city: "Cameron Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA700",GROUP_DESC:"CA700 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA700",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA700",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA700", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3272 ,protection_group_id: -2272, protection_element_id:-2272], primaryKey: false);
      insert('organizations', [ id: 102258, nci_institute_code: "CARRA", name: "Consumer Advocates in Research and Related Activities", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CARRA",GROUP_DESC:"CARRA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CARRA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CARRA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CARRA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3273 ,protection_group_id: -2273, protection_element_id:-2273], primaryKey: false);
      insert('organizations', [ id: 102259, nci_institute_code: "CLSG", name: "Canadian Leukemia Studies Group", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CLSG",GROUP_DESC:"CLSG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CLSG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CLSG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CLSG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3274 ,protection_group_id: -2274, protection_element_id:-2274], primaryKey: false);
      insert('organizations', [ id: 102260, nci_institute_code: "CNCC", name: "Coalition of National Cancer Cooperative Groups", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CNCC",GROUP_DESC:"CNCC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CNCC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CNCC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CNCC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3275 ,protection_group_id: -2275, protection_element_id:-2275], primaryKey: false);
      insert('organizations', [ id: 102261, nci_institute_code: "CO001", name: "Exempla Lutheran Medical Center", city: "Wheat Ridge", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO001",GROUP_DESC:"CO001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3276 ,protection_group_id: -2276, protection_element_id:-2276], primaryKey: false);
      insert('organizations', [ id: 102262, nci_institute_code: "CO002", name: "Fitzsimons Army Medical Center", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO002",GROUP_DESC:"CO002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3277 ,protection_group_id: -2277, protection_element_id:-2277], primaryKey: false);
      insert('organizations', [ id: 102263, nci_institute_code: "CO003", name: "American Cancer Research Center and Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO003",GROUP_DESC:"CO003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3278 ,protection_group_id: -2278, protection_element_id:-2278], primaryKey: false);
      insert('organizations', [ id: 102264, nci_institute_code: "CO004", name: "Colorado Gynecologic Oncology Group", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO004",GROUP_DESC:"CO004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3279 ,protection_group_id: -2279, protection_element_id:-2279], primaryKey: false);
      insert('organizations', [ id: 102265, nci_institute_code: "CO005", name: "Denver General", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO005",GROUP_DESC:"CO005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3280 ,protection_group_id: -2280, protection_element_id:-2280], primaryKey: false);
      insert('organizations', [ id: 102266, nci_institute_code: "CO007", name: "Colorado Permanente Medical Group", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO007",GROUP_DESC:"CO007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3281 ,protection_group_id: -2281, protection_element_id:-2281], primaryKey: false);
      insert('organizations', [ id: 102267, nci_institute_code: "CO008", name: "Porter Adventist Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO008",GROUP_DESC:"CO008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3282 ,protection_group_id: -2282, protection_element_id:-2282], primaryKey: false);
      insert('organizations', [ id: 102268, nci_institute_code: "CO009", name: "AMC Cancer Research Center/Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO009",GROUP_DESC:"CO009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3283 ,protection_group_id: -2283, protection_element_id:-2283], primaryKey: false);
      insert('organizations', [ id: 102269, nci_institute_code: "CO010", name: "Presbyterian - Saint Lukes Medical Center - Health One", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO010",GROUP_DESC:"CO010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3284 ,protection_group_id: -2284, protection_element_id:-2284], primaryKey: false);
      insert('organizations', [ id: 102270, nci_institute_code: "CO011", name: "The Childrens Hospital", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO011",GROUP_DESC:"CO011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3285 ,protection_group_id: -2285, protection_element_id:-2285], primaryKey: false);
      insert('organizations', [ id: 102271, nci_institute_code: "CO012", name: "Exempla Saint Joseph Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO012",GROUP_DESC:"CO012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3286 ,protection_group_id: -2286, protection_element_id:-2286], primaryKey: false);
      insert('organizations', [ id: 102272, nci_institute_code: "CO013", name: "Rose Medical Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO013",GROUP_DESC:"CO013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3287 ,protection_group_id: -2287, protection_element_id:-2287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 102273, nci_institute_code: "CO014", name: "Denver Veterans Administration Medical Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO014",GROUP_DESC:"CO014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3288 ,protection_group_id: -2288, protection_element_id:-2288], primaryKey: false);
      insert('organizations', [ id: 102274, nci_institute_code: "CO015", name: "University of Colorado", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO015",GROUP_DESC:"CO015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3289 ,protection_group_id: -2289, protection_element_id:-2289], primaryKey: false);
      insert('organizations', [ id: 102275, nci_institute_code: "CO017", name: "Boulder Community Hospital", city: "Boulder", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO017",GROUP_DESC:"CO017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3290 ,protection_group_id: -2290, protection_element_id:-2290], primaryKey: false);
      insert('organizations', [ id: 102276, nci_institute_code: "CO018", name: "Colorado State University", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO018",GROUP_DESC:"CO018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3291 ,protection_group_id: -2291, protection_element_id:-2291], primaryKey: false);
      insert('organizations', [ id: 102277, nci_institute_code: "CO019", name: "Poudre Valley Hospital", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO019",GROUP_DESC:"CO019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3292 ,protection_group_id: -2292, protection_element_id:-2292], primaryKey: false);
      insert('organizations', [ id: 102278, nci_institute_code: "CO020", name: "North Colorado Medical Center", city: "Greeley", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO020",GROUP_DESC:"CO020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3293 ,protection_group_id: -2293, protection_element_id:-2293], primaryKey: false);
      insert('organizations', [ id: 102279, nci_institute_code: "CO021", name: "Penrose-Saint Francis Healthcare", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO021",GROUP_DESC:"CO021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3294 ,protection_group_id: -2294, protection_element_id:-2294], primaryKey: false);
      insert('organizations', [ id: 102280, nci_institute_code: "CO022", name: "Parkview Episcopal Hospital", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO022",GROUP_DESC:"CO022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3295 ,protection_group_id: -2295, protection_element_id:-2295], primaryKey: false);
      insert('organizations', [ id: 102281, nci_institute_code: "CO023", name: "Saint Mary Corwin Medical Center", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO023",GROUP_DESC:"CO023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3296 ,protection_group_id: -2296, protection_element_id:-2296], primaryKey: false);
      insert('organizations', [ id: 102282, nci_institute_code: "CO024", name: "Montrose Memorial Hospital", city: "Montrose", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO024",GROUP_DESC:"CO024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3297 ,protection_group_id: -2297, protection_element_id:-2297], primaryKey: false);
      insert('organizations', [ id: 102283, nci_institute_code: "CO025", name: "Saint Mary's Hospital and Regional Medical Center", city: "Grand Junction", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO025",GROUP_DESC:"CO025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3298 ,protection_group_id: -2298, protection_element_id:-2298], primaryKey: false);
      insert('organizations', [ id: 102284, nci_institute_code: "CO027", name: "Western Hematology/Oncology Associates., P.C.", city: "Lakewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO027",GROUP_DESC:"CO027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3299 ,protection_group_id: -2299, protection_element_id:-2299], primaryKey: false);
      insert('organizations', [ id: 102285, nci_institute_code: "CO028", name: "Kaiser Permanente", city: "Westminster", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO028",GROUP_DESC:"CO028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3300 ,protection_group_id: -2300, protection_element_id:-2300], primaryKey: false);
      insert('organizations', [ id: 102286, nci_institute_code: "CO029", name: "Memorial Hospital Colorado Springs", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO029",GROUP_DESC:"CO029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3301 ,protection_group_id: -2301, protection_element_id:-2301], primaryKey: false);
      insert('organizations', [ id: 102287, nci_institute_code: "CO030", name: "The Medical Center of Aurora", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO030",GROUP_DESC:"CO030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3302 ,protection_group_id: -2302, protection_element_id:-2302], primaryKey: false);
      insert('organizations', [ id: 102288, nci_institute_code: "CO031", name: "Saint Anthony Central Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO031",GROUP_DESC:"CO031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3303 ,protection_group_id: -2303, protection_element_id:-2303], primaryKey: false);
      insert('organizations', [ id: 102289, nci_institute_code: "CO032", name: "Childhood Hematology-Oncology Association and Memorial Hospital", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO032",GROUP_DESC:"CO032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3304 ,protection_group_id: -2304, protection_element_id:-2304], primaryKey: false);
      insert('organizations', [ id: 102290, nci_institute_code: "CO033", name: "Rocky Mountain Cancer Center, North", city: "Thornton", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO033",GROUP_DESC:"CO033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3305 ,protection_group_id: -2305, protection_element_id:-2305], primaryKey: false);
      insert('organizations', [ id: 102291, nci_institute_code: "CO034", name: "Kaiser Permanente", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO034",GROUP_DESC:"CO034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3306 ,protection_group_id: -2306, protection_element_id:-2306], primaryKey: false);
      insert('organizations', [ id: 102292, nci_institute_code: "CO035", name: "Swedish Medical Center", city: "Englewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO035",GROUP_DESC:"CO035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3307 ,protection_group_id: -2307, protection_element_id:-2307], primaryKey: false);
      insert('organizations', [ id: 102293, nci_institute_code: "CO036", name: "Four Corners Oncology/Hematology", city: "Durango", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO036",GROUP_DESC:"CO036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3308 ,protection_group_id: -2308, protection_element_id:-2308], primaryKey: false);
      insert('organizations', [ id: 102294, nci_institute_code: "CO037", name: "Colorado Cancer Research Program CCOP", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO037",GROUP_DESC:"CO037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3309 ,protection_group_id: -2309, protection_element_id:-2309], primaryKey: false);
      insert('organizations', [ id: 102295, nci_institute_code: "CO038", name: "Greeley Medical Clinic., P.C.", city: "Greeley", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO038",GROUP_DESC:"CO038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3310 ,protection_group_id: -2310, protection_element_id:-2310], primaryKey: false);
      insert('organizations', [ id: 102296, nci_institute_code: "CO040", name: "Colorado Oncology Associates", city: "Englewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO040",GROUP_DESC:"CO040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3311 ,protection_group_id: -2311, protection_element_id:-2311], primaryKey: false);
      insert('organizations', [ id: 102297, nci_institute_code: "CO041", name: "South Colorado Clinic", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO041",GROUP_DESC:"CO041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3312 ,protection_group_id: -2312, protection_element_id:-2312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 102298, nci_institute_code: "CO042", name: "Denver Cardiac Surgery, P.C.", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO042",GROUP_DESC:"CO042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3313 ,protection_group_id: -2313, protection_element_id:-2313], primaryKey: false);
      insert('organizations', [ id: 102299, nci_institute_code: "CO043", name: "Oncology Clinic, P.C.", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO043",GROUP_DESC:"CO043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3314 ,protection_group_id: -2314, protection_element_id:-2314], primaryKey: false);
      insert('organizations', [ id: 102300, nci_institute_code: "CO044", name: "Rocky Mountain Cancer Center.", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO044",GROUP_DESC:"CO044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3315 ,protection_group_id: -2315, protection_element_id:-2315], primaryKey: false);
      insert('organizations', [ id: 102301, nci_institute_code: "CO045", name: "North Suburban Medical Center", city: "Thornton", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO045",GROUP_DESC:"CO045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3316 ,protection_group_id: -2316, protection_element_id:-2316], primaryKey: false);
      insert('organizations', [ id: 102302, nci_institute_code: "CO046", name: "Northern Colorado Oncology/Hema", city: "Greeley", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO046",GROUP_DESC:"CO046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3317 ,protection_group_id: -2317, protection_element_id:-2317], primaryKey: false);
      insert('organizations', [ id: 102303, nci_institute_code: "CO047", name: "Mercy Medical Center", city: "Durango", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO047",GROUP_DESC:"CO047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3318 ,protection_group_id: -2318, protection_element_id:-2318], primaryKey: false);
      insert('organizations', [ id: 102304, nci_institute_code: "CO052", name: "Denver Health Medical Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO052",GROUP_DESC:"CO052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3319 ,protection_group_id: -2319, protection_element_id:-2319], primaryKey: false);
      insert('organizations', [ id: 102305, nci_institute_code: "CO053", name: "Dr Larry A Schafer PC", city: "Wheat Ridge", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO053",GROUP_DESC:"CO053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3320 ,protection_group_id: -2320, protection_element_id:-2320], primaryKey: false);
      insert('organizations', [ id: 102306, nci_institute_code: "CO055", name: "Rocky Mountain Cancer Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO055",GROUP_DESC:"CO055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3321 ,protection_group_id: -2321, protection_element_id:-2321], primaryKey: false);
      insert('organizations', [ id: 102307, nci_institute_code: "CO056", name: "Arapahoe Surgical Associates", city: "Englewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO056",GROUP_DESC:"CO056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3322 ,protection_group_id: -2322, protection_element_id:-2322], primaryKey: false);
      insert('organizations', [ id: 102308, nci_institute_code: "CO057", name: "Colorado Springs", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO057",GROUP_DESC:"CO057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3323 ,protection_group_id: -2323, protection_element_id:-2323], primaryKey: false);
      insert('organizations', [ id: 102309, nci_institute_code: "CO058", name: "Western Slope Oncology Associates. PC", city: "Basalt", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO058",GROUP_DESC:"CO058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3324 ,protection_group_id: -2324, protection_element_id:-2324], primaryKey: false);
      insert('organizations', [ id: 102310, nci_institute_code: "CO059", name: "Hematology and Oncology", city: "Wheat Ridge", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO059",GROUP_DESC:"CO059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3325 ,protection_group_id: -2325, protection_element_id:-2325], primaryKey: false);
      insert('organizations', [ id: 102311, nci_institute_code: "CO060", name: "McKee Medical Center", city: "Loveland", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO060",GROUP_DESC:"CO060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3326 ,protection_group_id: -2326, protection_element_id:-2326], primaryKey: false);
      insert('organizations', [ id: 102312, nci_institute_code: "CO061", name: "Colorado Prevention Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO061",GROUP_DESC:"CO061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3327 ,protection_group_id: -2327, protection_element_id:-2327], primaryKey: false);
      insert('organizations', [ id: 102313, nci_institute_code: "CO062", name: "Poudre Valley Radiation Oncology", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO062",GROUP_DESC:"CO062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3328 ,protection_group_id: -2328, protection_element_id:-2328], primaryKey: false);
      insert('organizations', [ id: 102314, nci_institute_code: "CO063", name: "Rocky Mountain Gynecologic Oncology, P.C.", city: "Englewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO063",GROUP_DESC:"CO063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3329 ,protection_group_id: -2329, protection_element_id:-2329], primaryKey: false);
      insert('organizations', [ id: 102315, nci_institute_code: "CO064", name: "Rocky Mountain Cancer Center", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO064",GROUP_DESC:"CO064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3330 ,protection_group_id: -2330, protection_element_id:-2330], primaryKey: false);
      insert('organizations', [ id: 102316, nci_institute_code: "CO067", name: "Rocky Mountain Cancer Centers", city: "Littleton", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO067",GROUP_DESC:"CO067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3331 ,protection_group_id: -2331, protection_element_id:-2331], primaryKey: false);
      insert('organizations', [ id: 102317, nci_institute_code: "CO068", name: "Rocky Mountain Cancer Centers", city: "Lakewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO068",GROUP_DESC:"CO068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3332 ,protection_group_id: -2332, protection_element_id:-2332], primaryKey: false);
      insert('organizations', [ id: 102318, nci_institute_code: "CO069", name: "Healthe Tech, Inc.", city: "Golden", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO069",GROUP_DESC:"CO069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3333 ,protection_group_id: -2333, protection_element_id:-2333], primaryKey: false);
      insert('organizations', [ id: 102319, nci_institute_code: "CO070", name: "University of Colorado Cancer Center / Anschutz Cancer Pavilion", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO070",GROUP_DESC:"CO070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3334 ,protection_group_id: -2334, protection_element_id:-2334], primaryKey: false);
      insert('organizations', [ id: 102320, nci_institute_code: "CO071", name: "Front Range Cancer Specialists", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO071",GROUP_DESC:"CO071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3335 ,protection_group_id: -2335, protection_element_id:-2335], primaryKey: false);
      insert('organizations', [ id: 102321, nci_institute_code: "CO072", name: "Rocky Mountain Cancer Centers, Boulder", city: "Boulder", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO072",GROUP_DESC:"CO072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3336 ,protection_group_id: -2336, protection_element_id:-2336], primaryKey: false);
      insert('organizations', [ id: 102322, nci_institute_code: "CO073", name: "Saint Anthony Hospital North", city: "Westminster", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO073",GROUP_DESC:"CO073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3337 ,protection_group_id: -2337, protection_element_id:-2337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 102323, nci_institute_code: "CO074", name: "Mile High Oncology, P.C", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO074",GROUP_DESC:"CO074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3338 ,protection_group_id: -2338, protection_element_id:-2338], primaryKey: false);
      insert('organizations', [ id: 102324, nci_institute_code: "CO075", name: "Foothills Surgical Associates", city: "Wheat Ridge", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO075",GROUP_DESC:"CO075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3339 ,protection_group_id: -2339, protection_element_id:-2339], primaryKey: false);
      insert('organizations', [ id: 102325, nci_institute_code: "CO076", name: "San Luis Valley Health Studies", city: "Alamosa", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO076",GROUP_DESC:"CO076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3340 ,protection_group_id: -2340, protection_element_id:-2340], primaryKey: false);
      insert('organizations', [ id: 102326, nci_institute_code: "CO077", name: "Loveland Hematology & Oncology", city: "Loveland", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO077",GROUP_DESC:"CO077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3341 ,protection_group_id: -2341, protection_element_id:-2341], primaryKey: false);
      insert('organizations', [ id: 102327, nci_institute_code: "CO078", name: "Rocky Mountain Cancer Center", city: "Longmont", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO078",GROUP_DESC:"CO078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3342 ,protection_group_id: -2342, protection_element_id:-2342], primaryKey: false);
      insert('organizations', [ id: 102328, nci_institute_code: "CO079", name: "Longmont United Hospital", city: "Longmont", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO079",GROUP_DESC:"CO079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3343 ,protection_group_id: -2343, protection_element_id:-2343], primaryKey: false);
      insert('organizations', [ id: 102329, nci_institute_code: "CO080", name: "Sky Ridge Medical Center", city: "Lone Tree", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO080",GROUP_DESC:"CO080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3344 ,protection_group_id: -2344, protection_element_id:-2344], primaryKey: false);
      insert('organizations', [ id: 102330, nci_institute_code: "CO082", name: "Rocky Mountain Cancer Centers - Pueblo", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO082",GROUP_DESC:"CO082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3345 ,protection_group_id: -2345, protection_element_id:-2345], primaryKey: false);
      insert('organizations', [ id: 102331, nci_institute_code: "CO083", name: "Radiology Imaging Associates", city: "Englewood", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO083",GROUP_DESC:"CO083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3346 ,protection_group_id: -2346, protection_element_id:-2346], primaryKey: false);
      insert('organizations', [ id: 102332, nci_institute_code: "CO084", name: "Childhood Hematology Oncology Associates", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO084",GROUP_DESC:"CO084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3347 ,protection_group_id: -2347, protection_element_id:-2347], primaryKey: false);
      insert('organizations', [ id: 102333, nci_institute_code: "CO085", name: "University of Colorado at Denver Health Sciences Center", city: "Aurora", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO085",GROUP_DESC:"CO085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3348 ,protection_group_id: -2348, protection_element_id:-2348], primaryKey: false);
      insert('organizations', [ id: 102334, nci_institute_code: "CO086", name: "Pike's Peak Urology, PC", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO086",GROUP_DESC:"CO086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3349 ,protection_group_id: -2349, protection_element_id:-2349], primaryKey: false);
      insert('organizations', [ id: 102335, nci_institute_code: "CO087", name: "Rocky Mountain Cancer Centers at the Pavilion", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO087",GROUP_DESC:"CO087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3350 ,protection_group_id: -2350, protection_element_id:-2350], primaryKey: false);
      insert('organizations', [ id: 102336, nci_institute_code: "CO088", name: "Colorado Hematology-Oncology", city: "Lone Tree", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO088",GROUP_DESC:"CO088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3351 ,protection_group_id: -2351, protection_element_id:-2351], primaryKey: false);
      insert('organizations', [ id: 102337, nci_institute_code: "CO089", name: "Cancer Center of the Rockies", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO089",GROUP_DESC:"CO089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3352 ,protection_group_id: -2352, protection_element_id:-2352], primaryKey: false);
      insert('organizations', [ id: 102338, nci_institute_code: "CO090", name: "The Urology Center of Colorado", city: "Wheat Ridge", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO090",GROUP_DESC:"CO090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3353 ,protection_group_id: -2353, protection_element_id:-2353], primaryKey: false);
      insert('organizations', [ id: 102339, nci_institute_code: "CO091", name: "New West Physicians", city: "Golden", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO091",GROUP_DESC:"CO091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3354 ,protection_group_id: -2354, protection_element_id:-2354], primaryKey: false);
      insert('organizations', [ id: 102340, nci_institute_code: "CO092", name: "Memorial Hospital Cancer Center", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO092",GROUP_DESC:"CO092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3355 ,protection_group_id: -2355, protection_element_id:-2355], primaryKey: false);
      insert('organizations', [ id: 102341, nci_institute_code: "CO093", name: "The Shaw Regional Cancer Center", city: "Edwards", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO093",GROUP_DESC:"CO093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3356 ,protection_group_id: -2356, protection_element_id:-2356], primaryKey: false);
      insert('organizations', [ id: 102342, nci_institute_code: "CO094", name: "Saccomanno Research Institute", city: "Grand Junction", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO094",GROUP_DESC:"CO094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3357 ,protection_group_id: -2357, protection_element_id:-2357], primaryKey: false);
      insert('organizations', [ id: 102343, nci_institute_code: "CO095", name: "Southwest Oncology PC", city: "Durango", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO095",GROUP_DESC:"CO095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3358 ,protection_group_id: -2358, protection_element_id:-2358], primaryKey: false);
      insert('organizations', [ id: 102344, nci_institute_code: "CO096", name: "High Plains Oncology PLLC", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO096",GROUP_DESC:"CO096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3359 ,protection_group_id: -2359, protection_element_id:-2359], primaryKey: false);
      insert('organizations', [ id: 102345, nci_institute_code: "CO097", name: "The Reverend Roger Patrick Dorcy Cancer Center at St. Mary-Corwin Medical Center", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO097",GROUP_DESC:"CO097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3360 ,protection_group_id: -2360, protection_element_id:-2360], primaryKey: false);
      insert('organizations', [ id: 102346, nci_institute_code: "COGS", name: "Central Oncology Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.COGS",GROUP_DESC:"COGS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.COGS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.COGS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.COGS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3361 ,protection_group_id: -2361, protection_element_id:-2361], primaryKey: false);
      insert('organizations', [ id: 102347, nci_institute_code: "CRB", name: "National Cancer Institute Clinical Research Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CRB",GROUP_DESC:"CRB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CRB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CRB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CRB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3362 ,protection_group_id: -2362, protection_element_id:-2362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 102348, nci_institute_code: "CT001", name: "Bristol Hospital", city: "Bristol", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT001",GROUP_DESC:"CT001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3363 ,protection_group_id: -2363, protection_element_id:-2363], primaryKey: false);
      insert('organizations', [ id: 102349, nci_institute_code: "CT002", name: "University of Connecticut", city: "Farmington", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT002",GROUP_DESC:"CT002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3364 ,protection_group_id: -2364, protection_element_id:-2364], primaryKey: false);
      insert('organizations', [ id: 102350, nci_institute_code: "CT003", name: "Manchester Memorial Hospital", city: "Manchester", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT003",GROUP_DESC:"CT003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3365 ,protection_group_id: -2365, protection_element_id:-2365], primaryKey: false);
      insert('organizations', [ id: 102351, nci_institute_code: "CT004", name: "Watkins Centre", city: "Manchester", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT004",GROUP_DESC:"CT004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3366 ,protection_group_id: -2366, protection_element_id:-2366], primaryKey: false);
      insert('organizations', [ id: 102352, nci_institute_code: "CT005", name: "The Hospital of Central Connecticut", city: "New Britain", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT005",GROUP_DESC:"CT005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3367 ,protection_group_id: -2367, protection_element_id:-2367], primaryKey: false);
      insert('organizations', [ id: 102353, nci_institute_code: "CT006", name: "Rockville General Hospital", city: "Rockville", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT006",GROUP_DESC:"CT006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3368 ,protection_group_id: -2368, protection_element_id:-2368], primaryKey: false);
      insert('organizations', [ id: 102354, nci_institute_code: "CT007", name: "Sharon Hospital", city: "Sharon", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT007",GROUP_DESC:"CT007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3369 ,protection_group_id: -2369, protection_element_id:-2369], primaryKey: false);
      insert('organizations', [ id: 102355, nci_institute_code: "CT008", name: "Saint Francis Hospital and Medical Center", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT008",GROUP_DESC:"CT008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3370 ,protection_group_id: -2370, protection_element_id:-2370], primaryKey: false);
      insert('organizations', [ id: 102356, nci_institute_code: "CT009", name: "Hartford Hospital", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT009",GROUP_DESC:"CT009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3371 ,protection_group_id: -2371, protection_element_id:-2371], primaryKey: false);
      insert('organizations', [ id: 102357, nci_institute_code: "CT010", name: "Kaiser Permanente Health Plan", city: "East Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT010",GROUP_DESC:"CT010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3372 ,protection_group_id: -2372, protection_element_id:-2372], primaryKey: false);
      insert('organizations', [ id: 102358, nci_institute_code: "CT011", name: "Newington Veterans Administration", city: "Newington", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT011",GROUP_DESC:"CT011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3373 ,protection_group_id: -2373, protection_element_id:-2373], primaryKey: false);
      insert('organizations', [ id: 102359, nci_institute_code: "CT012", name: "Mount Sinai Hospital", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT012",GROUP_DESC:"CT012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3374 ,protection_group_id: -2374, protection_element_id:-2374], primaryKey: false);
      insert('organizations', [ id: 102360, nci_institute_code: "CT013", name: "Heublein Radiation/Oncology Center", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT013",GROUP_DESC:"CT013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3375 ,protection_group_id: -2375, protection_element_id:-2375], primaryKey: false);
      insert('organizations', [ id: 102361, nci_institute_code: "CT015", name: "Brown Day-Kimball Hospital", city: "Putnam", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT015",GROUP_DESC:"CT015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3376 ,protection_group_id: -2376, protection_element_id:-2376], primaryKey: false);
      insert('organizations', [ id: 102362, nci_institute_code: "CT016", name: "Lawrence and Memorial Hospital", city: "New London", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT016",GROUP_DESC:"CT016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3377 ,protection_group_id: -2377, protection_element_id:-2377], primaryKey: false);
      insert('organizations', [ id: 102363, nci_institute_code: "CT017", name: "Uncas-On-Thames Hospital", city: "Norwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT017",GROUP_DESC:"CT017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3378 ,protection_group_id: -2378, protection_element_id:-2378], primaryKey: false);
      insert('organizations', [ id: 102364, nci_institute_code: "CT018", name: "Yale University", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT018",GROUP_DESC:"CT018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3379 ,protection_group_id: -2379, protection_element_id:-2379], primaryKey: false);
      insert('organizations', [ id: 102365, nci_institute_code: "CT019", name: "St. Vincent Medical Center", city: "Fairfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT019",GROUP_DESC:"CT019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3380 ,protection_group_id: -2380, protection_element_id:-2380], primaryKey: false);
      insert('organizations', [ id: 102366, nci_institute_code: "CT020", name: "Meriden-Wallingford Hospital", city: "Meriden", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT020",GROUP_DESC:"CT020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3381 ,protection_group_id: -2381, protection_element_id:-2381], primaryKey: false);
      insert('organizations', [ id: 102367, nci_institute_code: "CT021", name: "Middlesex Hospital", city: "Middletown", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT021",GROUP_DESC:"CT021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3382 ,protection_group_id: -2382, protection_element_id:-2382], primaryKey: false);
      insert('organizations', [ id: 102368, nci_institute_code: "CT022", name: "Bradley Memorial Hospital", city: "Southington", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT022",GROUP_DESC:"CT022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3383 ,protection_group_id: -2383, protection_element_id:-2383], primaryKey: false);
      insert('organizations', [ id: 102369, nci_institute_code: "CT023", name: "Hospital of Saint Raphael", city: "Hew Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT023",GROUP_DESC:"CT023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3384 ,protection_group_id: -2384, protection_element_id:-2384], primaryKey: false);
      insert('organizations', [ id: 102370, nci_institute_code: "CT024", name: "West Haven Veterans Administration Medical Center", city: "West Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT024",GROUP_DESC:"CT024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3385 ,protection_group_id: -2385, protection_element_id:-2385], primaryKey: false);
      insert('organizations', [ id: 102371, nci_institute_code: "CT025", name: "Bridgeport Hospital", city: "Bridgeport", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT025",GROUP_DESC:"CT025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3386 ,protection_group_id: -2386, protection_element_id:-2386], primaryKey: false);
      insert('organizations', [ id: 102372, nci_institute_code: "CT026", name: "Saint Vincent's Medical Center", city: "Bridgeport", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT026",GROUP_DESC:"CT026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3387 ,protection_group_id: -2387, protection_element_id:-2387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 102373, nci_institute_code: "CT027", name: "Saint Mary's Hospital", city: "Waterbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT027",GROUP_DESC:"CT027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3388 ,protection_group_id: -2388, protection_element_id:-2388], primaryKey: false);
      insert('organizations', [ id: 102374, nci_institute_code: "CT028", name: "Waterbury Hospital", city: "Waterbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT028",GROUP_DESC:"CT028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3389 ,protection_group_id: -2389, protection_element_id:-2389], primaryKey: false);
      insert('organizations', [ id: 102375, nci_institute_code: "CT029", name: "Charlotte Hungerford Hospital", city: "Torrington", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT029",GROUP_DESC:"CT029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3390 ,protection_group_id: -2390, protection_element_id:-2390], primaryKey: false);
      insert('organizations', [ id: 102376, nci_institute_code: "CT030", name: "Danbury Hospital", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT030",GROUP_DESC:"CT030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3391 ,protection_group_id: -2391, protection_element_id:-2391], primaryKey: false);
      insert('organizations', [ id: 102377, nci_institute_code: "CT031", name: "Greenwich Hospital", city: "Greenwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT031",GROUP_DESC:"CT031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3392 ,protection_group_id: -2392, protection_element_id:-2392], primaryKey: false);
      insert('organizations', [ id: 102378, nci_institute_code: "CT032", name: "Norwalk Hospital", city: "Norwalk", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT032",GROUP_DESC:"CT032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3393 ,protection_group_id: -2393, protection_element_id:-2393], primaryKey: false);
      insert('organizations', [ id: 102379, nci_institute_code: "CT033", name: "Stamford Hospital", city: "Stamford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT033",GROUP_DESC:"CT033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3394 ,protection_group_id: -2394, protection_element_id:-2394], primaryKey: false);
      insert('organizations', [ id: 102380, nci_institute_code: "CT035", name: "Carl/Dorothy Bennett Cancer Center", city: "Stamford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT035",GROUP_DESC:"CT035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3395 ,protection_group_id: -2395, protection_element_id:-2395], primaryKey: false);
      insert('organizations', [ id: 102381, nci_institute_code: "CT036", name: "Helen & Harry Gray Cancer Center", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT036",GROUP_DESC:"CT036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3396 ,protection_group_id: -2396, protection_element_id:-2396], primaryKey: false);
      insert('organizations', [ id: 102382, nci_institute_code: "CT037", name: "Yale New Haven Hospital", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT037",GROUP_DESC:"CT037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3397 ,protection_group_id: -2397, protection_element_id:-2397], primaryKey: false);
      insert('organizations', [ id: 102383, nci_institute_code: "CT038", name: "Eastern Connecticut Hematology and Oncology Associates", city: "Norwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT038",GROUP_DESC:"CT038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3398 ,protection_group_id: -2398, protection_element_id:-2398], primaryKey: false);
      insert('organizations', [ id: 102384, nci_institute_code: "CT040", name: "North Central Onc/Hem Practice", city: "Enfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT040",GROUP_DESC:"CT040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3399 ,protection_group_id: -2399, protection_element_id:-2399], primaryKey: false);
      insert('organizations', [ id: 102385, nci_institute_code: "CT041", name: "Connecticut Heart Group, P.C.", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT041",GROUP_DESC:"CT041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3400 ,protection_group_id: -2400, protection_element_id:-2400], primaryKey: false);
      insert('organizations', [ id: 102386, nci_institute_code: "CT042", name: "Wm Backus Hospital", city: "Norwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT042",GROUP_DESC:"CT042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3401 ,protection_group_id: -2401, protection_element_id:-2401], primaryKey: false);
      insert('organizations', [ id: 102387, nci_institute_code: "CT043", name: "Connecticut Oncology and Hematology LLP", city: "Torrington", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT043",GROUP_DESC:"CT043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3402 ,protection_group_id: -2402, protection_element_id:-2402], primaryKey: false);
      insert('organizations', [ id: 102388, nci_institute_code: "CT044", name: "Johnson Memorial Hospital", city: "Stafford Springs", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT044",GROUP_DESC:"CT044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3403 ,protection_group_id: -2403, protection_element_id:-2403], primaryKey: false);
      insert('organizations', [ id: 102389, nci_institute_code: "CT045", name: "Milford Hospital", city: "Miford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT045",GROUP_DESC:"CT045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3404 ,protection_group_id: -2404, protection_element_id:-2404], primaryKey: false);
      insert('organizations', [ id: 102390, nci_institute_code: "CT046", name: "Internal Medical Association PC", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT046",GROUP_DESC:"CT046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3405 ,protection_group_id: -2405, protection_element_id:-2405], primaryKey: false);
      insert('organizations', [ id: 102391, nci_institute_code: "CT047", name: "Middlesex Hematology Oncology Association", city: "Middletown", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT047",GROUP_DESC:"CT047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3406 ,protection_group_id: -2406, protection_element_id:-2406], primaryKey: false);
      insert('organizations', [ id: 102392, nci_institute_code: "CT050", name: "Purdue Pharma, L.P.", city: "Norwalk", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT050",GROUP_DESC:"CT050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3407 ,protection_group_id: -2407, protection_element_id:-2407], primaryKey: false);
      insert('organizations', [ id: 102393, nci_institute_code: "CT051", name: "Connecticut Children's Medical Center", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT051",GROUP_DESC:"CT051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3408 ,protection_group_id: -2408, protection_element_id:-2408], primaryKey: false);
      insert('organizations', [ id: 102394, nci_institute_code: "CT052", name: "New Milford Hospital", city: "New Miford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT052",GROUP_DESC:"CT052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3409 ,protection_group_id: -2409, protection_element_id:-2409], primaryKey: false);
      insert('organizations', [ id: 102395, nci_institute_code: "CT053", name: "Oncology Associates of Bridgeport PC", city: "Trumbull", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT053",GROUP_DESC:"CT053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3410 ,protection_group_id: -2410, protection_element_id:-2410], primaryKey: false);
      insert('organizations', [ id: 102396, nci_institute_code: "CT058", name: "Infusion Care", city: "Manchester", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT058",GROUP_DESC:"CT058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3411 ,protection_group_id: -2411, protection_element_id:-2411], primaryKey: false);
      insert('organizations', [ id: 102397, nci_institute_code: "CT059", name: "Pediatric Hematology/Oncology Associates PC", city: "Guilford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT059",GROUP_DESC:"CT059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3412 ,protection_group_id: -2412, protection_element_id:-2412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 102398, nci_institute_code: "CT060", name: "Veterans Memorial Medical Center", city: "Meriden", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT060",GROUP_DESC:"CT060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3413 ,protection_group_id: -2413, protection_element_id:-2413], primaryKey: false);
      insert('organizations', [ id: 102399, nci_institute_code: "CT061", name: "Connecticut Oncology and Hematology", city: "Sharon", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT061",GROUP_DESC:"CT061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3414 ,protection_group_id: -2414, protection_element_id:-2414], primaryKey: false);
      insert('organizations', [ id: 102400, nci_institute_code: "CT062", name: "Spring Glen Medical Center", city: "Hamden", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT062",GROUP_DESC:"CT062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3415 ,protection_group_id: -2415, protection_element_id:-2415], primaryKey: false);
      insert('organizations', [ id: 102401, nci_institute_code: "CT063", name: "Veterans Affairs Connecticut Healthcare System", city: "West Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT063",GROUP_DESC:"CT063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3416 ,protection_group_id: -2416, protection_element_id:-2416], primaryKey: false);
      insert('organizations', [ id: 102402, nci_institute_code: "CT064", name: "Midstate Medical Center", city: "Meriden", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT064",GROUP_DESC:"CT064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3417 ,protection_group_id: -2417, protection_element_id:-2417], primaryKey: false);
      insert('organizations', [ id: 102403, nci_institute_code: "CT066", name: "Connecticut Oncology Group", city: "Middletown", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT066",GROUP_DESC:"CT066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3418 ,protection_group_id: -2418, protection_element_id:-2418], primaryKey: false);
      insert('organizations', [ id: 102404, nci_institute_code: "CT067", name: "Medical Specialists of Fairfield LLC", city: "Fairfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT067",GROUP_DESC:"CT067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3419 ,protection_group_id: -2419, protection_element_id:-2419], primaryKey: false);
      insert('organizations', [ id: 102405, nci_institute_code: "CT068", name: "New London Cancer Center", city: "New London", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT068",GROUP_DESC:"CT068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3420 ,protection_group_id: -2420, protection_element_id:-2420], primaryKey: false);
      insert('organizations', [ id: 102406, nci_institute_code: "CT069", name: "Windham Surgical Group, P.C.", city: "Mansfiled", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT069",GROUP_DESC:"CT069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3421 ,protection_group_id: -2421, protection_element_id:-2421], primaryKey: false);
      insert('organizations', [ id: 102407, nci_institute_code: "CT070", name: "Windham Community Memorial Hospital", city: "Willimantic", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT070",GROUP_DESC:"CT070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3422 ,protection_group_id: -2422, protection_element_id:-2422], primaryKey: false);
      insert('organizations', [ id: 102408, nci_institute_code: "CT071", name: "Hematology Oncology, P.C.", city: "Stamford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT071",GROUP_DESC:"CT071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3423 ,protection_group_id: -2423, protection_element_id:-2423], primaryKey: false);
      insert('organizations', [ id: 102409, nci_institute_code: "CT072", name: "Grove Hill Medical Center", city: "New Britain", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT072",GROUP_DESC:"CT072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3424 ,protection_group_id: -2424, protection_element_id:-2424], primaryKey: false);
      insert('organizations', [ id: 102410, nci_institute_code: "CT073", name: "Advanced Surgical Care", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT073",GROUP_DESC:"CT073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3425 ,protection_group_id: -2425, protection_element_id:-2425], primaryKey: false);
      insert('organizations', [ id: 102411, nci_institute_code: "CT074", name: "Oncology and Hematology Associates, P.C.", city: "New London", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT074",GROUP_DESC:"CT074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3426 ,protection_group_id: -2426, protection_element_id:-2426], primaryKey: false);
      insert('organizations', [ id: 102412, nci_institute_code: "CT075", name: "Colon & Rectal Surgeons of Greater Hartford", city: "Bloomfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT075",GROUP_DESC:"CT075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3427 ,protection_group_id: -2427, protection_element_id:-2427], primaryKey: false);
      insert('organizations', [ id: 102413, nci_institute_code: "CT076", name: "Middlesex Hospital Rehabilitation", city: "Essex", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT076",GROUP_DESC:"CT076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3428 ,protection_group_id: -2428, protection_element_id:-2428], primaryKey: false);
      insert('organizations', [ id: 102414, nci_institute_code: "CT077", name: "Medical Oncology & Hematology Group P.C.", city: "Hamden", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT077",GROUP_DESC:"CT077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3429 ,protection_group_id: -2429, protection_element_id:-2429], primaryKey: false);
      insert('organizations', [ id: 102415, nci_institute_code: "CT078", name: "Medical Oncology & Hematology Group P.C.-Guilford", city: "Guilford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT078",GROUP_DESC:"CT078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3430 ,protection_group_id: -2430, protection_element_id:-2430], primaryKey: false);
      insert('organizations', [ id: 102416, nci_institute_code: "CT079", name: "Medical Hematology & Oncology, PC", city: "Waterbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT079",GROUP_DESC:"CT079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3431 ,protection_group_id: -2431, protection_element_id:-2431], primaryKey: false);
      insert('organizations', [ id: 102417, nci_institute_code: "CT080", name: "Medical Oncology and Hematology, P.C. - New Haven", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT080",GROUP_DESC:"CT080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3432 ,protection_group_id: -2432, protection_element_id:-2432], primaryKey: false);
      insert('organizations', [ id: 102418, nci_institute_code: "CT081", name: "Medical Oncology and Hematology, P.C.-Shelton", city: "Shelton", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT081",GROUP_DESC:"CT081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3433 ,protection_group_id: -2433, protection_element_id:-2433], primaryKey: false);
      insert('organizations', [ id: 102419, nci_institute_code: "CT082", name: "Connecticut Multispecialty, P.C.", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT082",GROUP_DESC:"CT082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3434 ,protection_group_id: -2434, protection_element_id:-2434], primaryKey: false);
      insert('organizations', [ id: 102420, nci_institute_code: "CT083", name: "Joseph J. Bowen, MD,LLC", city: "Waterbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT083",GROUP_DESC:"CT083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3435 ,protection_group_id: -2435, protection_element_id:-2435], primaryKey: false);
      insert('organizations', [ id: 102421, nci_institute_code: "CT084", name: "Praxair Cancer Center", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT084",GROUP_DESC:"CT084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3436 ,protection_group_id: -2436, protection_element_id:-2436], primaryKey: false);
      insert('organizations', [ id: 102422, nci_institute_code: "CT085", name: "The Norma F. Pfriem Breast Care Center - Fairfield", city: "Fairfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT085",GROUP_DESC:"CT085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3437 ,protection_group_id: -2437, protection_element_id:-2437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 102423, nci_institute_code: "CT086", name: "Black Rock Medical Group", city: "Trumbull", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT086",GROUP_DESC:"CT086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3438 ,protection_group_id: -2438, protection_element_id:-2438], primaryKey: false);
      insert('organizations', [ id: 102424, nci_institute_code: "CT087", name: "Davis, Posteraro And Wasser, MD's, LLP", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT087",GROUP_DESC:"CT087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3439 ,protection_group_id: -2439, protection_element_id:-2439], primaryKey: false);
      insert('organizations', [ id: 102425, nci_institute_code: "CT088", name: "The Hope Clinic", city: "Briston", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT088",GROUP_DESC:"CT088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3440 ,protection_group_id: -2440, protection_element_id:-2440], primaryKey: false);
      insert('organizations', [ id: 102426, nci_institute_code: "CT089", name: "Connecticut Surgical Group PC", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT089",GROUP_DESC:"CT089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3441 ,protection_group_id: -2441, protection_element_id:-2441], primaryKey: false);
      insert('organizations', [ id: 102427, nci_institute_code: "CT090", name: "Danbury Office of Physician Services", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT090",GROUP_DESC:"CT090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3442 ,protection_group_id: -2442, protection_element_id:-2442], primaryKey: false);
      insert('organizations', [ id: 102428, nci_institute_code: "CT091", name: "Oncology Associates PC", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT091",GROUP_DESC:"CT091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3443 ,protection_group_id: -2443, protection_element_id:-2443], primaryKey: false);
      insert('organizations', [ id: 102429, nci_institute_code: "CT092", name: "Saint Francis/Mount Sinai Regional Cancer Center", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT092",GROUP_DESC:"CT092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3444 ,protection_group_id: -2444, protection_element_id:-2444], primaryKey: false);
      insert('organizations', [ id: 102430, nci_institute_code: "CT093", name: "Davis Posteraro and Wasser MDs LLP", city: "Manchester", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT093",GROUP_DESC:"CT093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3445 ,protection_group_id: -2445, protection_element_id:-2445], primaryKey: false);
      insert('organizations', [ id: 102431, nci_institute_code: "CT094", name: "Connecticut Oncology and Hematology", city: "New Milford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT094",GROUP_DESC:"CT094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3446 ,protection_group_id: -2446, protection_element_id:-2446], primaryKey: false);
      insert('organizations', [ id: 102432, nci_institute_code: "CT095", name: "Harold Leever Regional Cancer Center", city: "Waterbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT095",GROUP_DESC:"CT095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3447 ,protection_group_id: -2447, protection_element_id:-2447], primaryKey: false);
      insert('organizations', [ id: 102433, nci_institute_code: "CT096", name: "Western Connecticut State University", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT096",GROUP_DESC:"CT096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3448 ,protection_group_id: -2448, protection_element_id:-2448], primaryKey: false);
      insert('organizations', [ id: 102434, nci_institute_code: "CT097", name: "DeQuattro Community Cancer Center", city: "Manchester", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT097",GROUP_DESC:"CT097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3449 ,protection_group_id: -2449, protection_element_id:-2449], primaryKey: false);
      insert('organizations', [ id: 102435, nci_institute_code: "CT098", name: "Boyd Center for Intergrative Health LLC", city: "Greenwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT098",GROUP_DESC:"CT098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3450 ,protection_group_id: -2450, protection_element_id:-2450], primaryKey: false);
      insert('organizations', [ id: 102436, nci_institute_code: "CT099", name: "Medical Oncology & Hematology PC - Woodbridge", city: "Woodbridge", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT099",GROUP_DESC:"CT099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3451 ,protection_group_id: -2451, protection_element_id:-2451], primaryKey: false);
      insert('organizations', [ id: 102437, nci_institute_code: "CT100", name: "Park Avenue Surgical Associates", city: "Trumbull", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT100",GROUP_DESC:"CT100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3452 ,protection_group_id: -2452, protection_element_id:-2452], primaryKey: false);
      insert('organizations', [ id: 102438, nci_institute_code: "CT101", name: "Davis Posteraro and Wasser MD's LLP", city: "Vernon", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT101",GROUP_DESC:"CT101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3453 ,protection_group_id: -2453, protection_element_id:-2453], primaryKey: false);
      insert('organizations', [ id: 102439, nci_institute_code: "CT102", name: "General Thoracic Surgeons of Connecticut", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT102",GROUP_DESC:"CT102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3454 ,protection_group_id: -2454, protection_element_id:-2454], primaryKey: false);
      insert('organizations', [ id: 102440, nci_institute_code: "CT103", name: "Colon and Rectal Surgeons of Southern Connecticut", city: "Bridgeport", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT103",GROUP_DESC:"CT103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3455 ,protection_group_id: -2455, protection_element_id:-2455], primaryKey: false);
      insert('organizations', [ id: 102441, nci_institute_code: "CT104", name: "Connecticut Oncology and Hematology LLP", city: "Milford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT104",GROUP_DESC:"CT104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3456 ,protection_group_id: -2456, protection_element_id:-2456], primaryKey: false);
      insert('organizations', [ id: 102442, nci_institute_code: "CT105", name: "Clinical Center For Neoplastic Diseases PC", city: "Ansonia", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT105",GROUP_DESC:"CT105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3457 ,protection_group_id: -2457, protection_element_id:-2457], primaryKey: false);
      insert('organizations', [ id: 102443, nci_institute_code: "CT106", name: "Yale Medical Oncology", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT106",GROUP_DESC:"CT106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3458 ,protection_group_id: -2458, protection_element_id:-2458], primaryKey: false);
      insert('organizations', [ id: 102444, nci_institute_code: "CT107", name: "Urological Associates of Bridgeport PC", city: "Trumbull", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT107",GROUP_DESC:"CT107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3459 ,protection_group_id: -2459, protection_element_id:-2459], primaryKey: false);
      insert('organizations', [ id: 102445, nci_institute_code: "CT108", name: "Woodland Physician Associates Inc", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT108",GROUP_DESC:"CT108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3460 ,protection_group_id: -2460, protection_element_id:-2460], primaryKey: false);
      insert('organizations', [ id: 102446, nci_institute_code: "CT109", name: "Surgical Associates of Connecticut LLC", city: "Bridgeport", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT109",GROUP_DESC:"CT109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3461 ,protection_group_id: -2461, protection_element_id:-2461], primaryKey: false);
      insert('organizations', [ id: 102447, nci_institute_code: "DC001", name: "Greater Northeast Med Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC001",GROUP_DESC:"DC001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3462 ,protection_group_id: -2462, protection_element_id:-2462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 102448, nci_institute_code: "DC002", name: "D.C. General Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC002",GROUP_DESC:"DC002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3463 ,protection_group_id: -2463, protection_element_id:-2463], primaryKey: false);
      insert('organizations', [ id: 102449, nci_institute_code: "DC003", name: "Howard Medical Division", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC003",GROUP_DESC:"DC003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3464 ,protection_group_id: -2464, protection_element_id:-2464], primaryKey: false);
      insert('organizations', [ id: 102450, nci_institute_code: "DC004", name: "Walter Reed Army Medical Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC004",GROUP_DESC:"DC004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3465 ,protection_group_id: -2465, protection_element_id:-2465], primaryKey: false);
      insert('organizations', [ id: 102451, nci_institute_code: "DC005", name: "Georgetown University Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC005",GROUP_DESC:"DC005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3466 ,protection_group_id: -2466, protection_element_id:-2466], primaryKey: false);
      insert('organizations', [ id: 102452, nci_institute_code: "DC006", name: "Howard University Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC006",GROUP_DESC:"DC006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3467 ,protection_group_id: -2467, protection_element_id:-2467], primaryKey: false);
      insert('organizations', [ id: 102453, nci_institute_code: "DC007", name: "Washington Hospital Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC007",GROUP_DESC:"DC007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3468 ,protection_group_id: -2468, protection_element_id:-2468], primaryKey: false);
      insert('organizations', [ id: 102454, nci_institute_code: "DC008", name: "Children's National Medical Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC008",GROUP_DESC:"DC008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3469 ,protection_group_id: -2469, protection_element_id:-2469], primaryKey: false);
      insert('organizations', [ id: 102455, nci_institute_code: "DC009", name: "Providence Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC009",GROUP_DESC:"DC009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3470 ,protection_group_id: -2470, protection_element_id:-2470], primaryKey: false);
      insert('organizations', [ id: 102456, nci_institute_code: "DC010", name: "Washington Clinic", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC010",GROUP_DESC:"DC010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3471 ,protection_group_id: -2471, protection_element_id:-2471], primaryKey: false);
      insert('organizations', [ id: 102457, nci_institute_code: "DC011", name: "Group Health Association", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC011",GROUP_DESC:"DC011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3472 ,protection_group_id: -2472, protection_element_id:-2472], primaryKey: false);
      insert('organizations', [ id: 102458, nci_institute_code: "DC012", name: "Columbia Hospital for Women", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC012",GROUP_DESC:"DC012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3473 ,protection_group_id: -2473, protection_element_id:-2473], primaryKey: false);
      insert('organizations', [ id: 102459, nci_institute_code: "DC013", name: "Veterans Affairs Medical Center -Washington, D.C.", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC013",GROUP_DESC:"DC013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3474 ,protection_group_id: -2474, protection_element_id:-2474], primaryKey: false);
      insert('organizations', [ id: 102460, nci_institute_code: "DC014", name: "George Washington University Medical Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC014",GROUP_DESC:"DC014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3475 ,protection_group_id: -2475, protection_element_id:-2475], primaryKey: false);
      insert('organizations', [ id: 102461, nci_institute_code: "DC016", name: "Metro Minority Based CCOP", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC016",GROUP_DESC:"DC016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3476 ,protection_group_id: -2476, protection_element_id:-2476], primaryKey: false);
      insert('organizations', [ id: 102462, nci_institute_code: "DC017", name: "Qatar Embassy", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC017",GROUP_DESC:"DC017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3477 ,protection_group_id: -2477, protection_element_id:-2477], primaryKey: false);
      insert('organizations', [ id: 102463, nci_institute_code: "DC018", name: "Sibley Memorial Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC018",GROUP_DESC:"DC018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3478 ,protection_group_id: -2478, protection_element_id:-2478], primaryKey: false);
      insert('organizations', [ id: 102464, nci_institute_code: "DC019", name: "Internal Medicine Oncology Group", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC019",GROUP_DESC:"DC019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3479 ,protection_group_id: -2479, protection_element_id:-2479], primaryKey: false);
      insert('organizations', [ id: 102465, nci_institute_code: "DC020", name: "Hospital for Sick Children", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC020",GROUP_DESC:"DC020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3480 ,protection_group_id: -2480, protection_element_id:-2480], primaryKey: false);
      insert('organizations', [ id: 102466, nci_institute_code: "DC021", name: "Foxhall Surgical Associates, P.C.", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC021",GROUP_DESC:"DC021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3481 ,protection_group_id: -2481, protection_element_id:-2481], primaryKey: false);
      insert('organizations', [ id: 102467, nci_institute_code: "DC022", name: "Comprehensive Breast Center", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC022",GROUP_DESC:"DC022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3482 ,protection_group_id: -2482, protection_element_id:-2482], primaryKey: false);
      insert('organizations', [ id: 102468, nci_institute_code: "DC024", name: "Washington Internal Medical Group", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC024",GROUP_DESC:"DC024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3483 ,protection_group_id: -2483, protection_element_id:-2483], primaryKey: false);
      insert('organizations', [ id: 102469, nci_institute_code: "DC025", name: "Egyptian Embassy", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC025",GROUP_DESC:"DC025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3484 ,protection_group_id: -2484, protection_element_id:-2484], primaryKey: false);
      insert('organizations', [ id: 102470, nci_institute_code: "DC026", name: "MedStar Oncology Network", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC026",GROUP_DESC:"DC026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3485 ,protection_group_id: -2485, protection_element_id:-2485], primaryKey: false);
      insert('organizations', [ id: 102471, nci_institute_code: "DC027", name: "Washington Oncology Hematology Center PC", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC027",GROUP_DESC:"DC027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3486 ,protection_group_id: -2486, protection_element_id:-2486], primaryKey: false);
      insert('organizations', [ id: 102472, nci_institute_code: "DC028", name: "Washington Radiology Associates", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC028",GROUP_DESC:"DC028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3487 ,protection_group_id: -2487, protection_element_id:-2487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 102473, nci_institute_code: "DC029", name: "Foxhall Urology Associates, P.C.", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC029",GROUP_DESC:"DC029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3488 ,protection_group_id: -2488, protection_element_id:-2488], primaryKey: false);
      insert('organizations', [ id: 102474, nci_institute_code: "DC030", name: "MedStar Research Institute", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC030",GROUP_DESC:"DC030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3489 ,protection_group_id: -2489, protection_element_id:-2489], primaryKey: false);
      insert('organizations', [ id: 102475, nci_institute_code: "DC031", name: "George Washington Medical Faculty Associates", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC031",GROUP_DESC:"DC031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3490 ,protection_group_id: -2490, protection_element_id:-2490], primaryKey: false);
      insert('organizations', [ id: 102476, nci_institute_code: "DC033", name: "National Institute for Occupational Safety and Health", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC033",GROUP_DESC:"DC033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3491 ,protection_group_id: -2491, protection_element_id:-2491], primaryKey: false);
      insert('organizations', [ id: 102477, nci_institute_code: "DC034", name: "Weiner Brodsky Sidman Kider PC", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC034",GROUP_DESC:"DC034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3492 ,protection_group_id: -2492, protection_element_id:-2492], primaryKey: false);
      insert('organizations', [ id: 102478, nci_institute_code: "DE001", name: "University of Delaware", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE001",GROUP_DESC:"DE001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3493 ,protection_group_id: -2493, protection_element_id:-2493], primaryKey: false);
      insert('organizations', [ id: 102479, nci_institute_code: "DE002", name: "Christiana Healthcare Services - Christian Hospital", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE002",GROUP_DESC:"DE002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3494 ,protection_group_id: -2494, protection_element_id:-2494], primaryKey: false);
      insert('organizations', [ id: 102480, nci_institute_code: "DE003", name: "Wilmington Veterans Administration Hospital", city: "Elsmere", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE003",GROUP_DESC:"DE003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3495 ,protection_group_id: -2495, protection_element_id:-2495], primaryKey: false);
      insert('organizations', [ id: 102481, nci_institute_code: "DE004", name: "Limestone Medical Center", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE004",GROUP_DESC:"DE004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3496 ,protection_group_id: -2496, protection_element_id:-2496], primaryKey: false);
      insert('organizations', [ id: 102482, nci_institute_code: "DE006", name: "Seashore Medical Associates", city: "Lewes", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE006",GROUP_DESC:"DE006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3497 ,protection_group_id: -2497, protection_element_id:-2497], primaryKey: false);
      insert('organizations', [ id: 102483, nci_institute_code: "DE007", name: "Nanticoke Memorial Hospital", city: "Seaford", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE007",GROUP_DESC:"DE007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3498 ,protection_group_id: -2498, protection_element_id:-2498], primaryKey: false);
      insert('organizations', [ id: 102484, nci_institute_code: "DE008", name: "Alfred I duPont Hospital for Children", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE008",GROUP_DESC:"DE008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3499 ,protection_group_id: -2499, protection_element_id:-2499], primaryKey: false);
      insert('organizations', [ id: 102485, nci_institute_code: "DE010", name: "Oncology Associates/Delaware, P.A.", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE010",GROUP_DESC:"DE010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3500 ,protection_group_id: -2500, protection_element_id:-2500], primaryKey: false);
      insert('organizations', [ id: 102486, nci_institute_code: "DE012", name: "Bayhealth Medical Center at Kent General", city: "Dover", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE012",GROUP_DESC:"DE012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3501 ,protection_group_id: -2501, protection_element_id:-2501], primaryKey: false);
      insert('organizations', [ id: 102487, nci_institute_code: "DE013", name: "Saint Francis Hospital - Wilmington", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE013",GROUP_DESC:"DE013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3502 ,protection_group_id: -2502, protection_element_id:-2502], primaryKey: false);
      insert('organizations', [ id: 102488, nci_institute_code: "DE014", name: "Beebe Medical Center", city: "Lewes", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE014",GROUP_DESC:"DE014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3503 ,protection_group_id: -2503, protection_element_id:-2503], primaryKey: false);
      insert('organizations', [ id: 102489, nci_institute_code: "DE015", name: "Riverside Hospital CCOP", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE015",GROUP_DESC:"DE015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3504 ,protection_group_id: -2504, protection_element_id:-2504], primaryKey: false);
      insert('organizations', [ id: 102490, nci_institute_code: "DE016", name: "Bayhealth Medical Center at Milford Memorial", city: "Milford", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE016",GROUP_DESC:"DE016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3505 ,protection_group_id: -2505, protection_element_id:-2505], primaryKey: false);
      insert('organizations', [ id: 102491, nci_institute_code: "DE018", name: "Millcreek Medical Center", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE018",GROUP_DESC:"DE018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3506 ,protection_group_id: -2506, protection_element_id:-2506], primaryKey: false);
      insert('organizations', [ id: 102492, nci_institute_code: "DE019", name: "Delaware Clinical & Laboratory Physicians, P.A.", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE019",GROUP_DESC:"DE019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3507 ,protection_group_id: -2507, protection_element_id:-2507], primaryKey: false);
      insert('organizations', [ id: 102493, nci_institute_code: "DE020", name: "Regional Hematology and Oncology PA", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE020",GROUP_DESC:"DE020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3508 ,protection_group_id: -2508, protection_element_id:-2508], primaryKey: false);
      insert('organizations', [ id: 102494, nci_institute_code: "DE021", name: "Christiana Hosp Carpenter Mem Clinic", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE021",GROUP_DESC:"DE021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3509 ,protection_group_id: -2509, protection_element_id:-2509], primaryKey: false);
      insert('organizations', [ id: 102495, nci_institute_code: "DE022", name: "Delaware SPECT Imaging Center", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE022",GROUP_DESC:"DE022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3510 ,protection_group_id: -2510, protection_element_id:-2510], primaryKey: false);
      insert('organizations', [ id: 102496, nci_institute_code: "DE023", name: "Christiana Care Health Services, Inc", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE023",GROUP_DESC:"DE023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3511 ,protection_group_id: -2511, protection_element_id:-2511], primaryKey: false);
      insert('organizations', [ id: 102497, nci_institute_code: "DE024", name: "Christiana Gynecologic Oncology, LLC", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE024",GROUP_DESC:"DE024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3512 ,protection_group_id: -2512, protection_element_id:-2512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 102498, nci_institute_code: "DE025", name: "Oncology and Hematology, P. A.", city: "Seaford", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE025",GROUP_DESC:"DE025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3513 ,protection_group_id: -2513, protection_element_id:-2513], primaryKey: false);
      insert('organizations', [ id: 102499, nci_institute_code: "DE026", name: "Brandywine Urology Consultants, P.A.", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE026",GROUP_DESC:"DE026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3514 ,protection_group_id: -2514, protection_element_id:-2514], primaryKey: false);
      insert('organizations', [ id: 102500, nci_institute_code: "DE027", name: "Deleware Surgical Group", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE027",GROUP_DESC:"DE027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3515 ,protection_group_id: -2515, protection_element_id:-2515], primaryKey: false);
      insert('organizations', [ id: 102501, nci_institute_code: "DE028", name: "Helen F. Graham Cancer Center", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE028",GROUP_DESC:"DE028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3516 ,protection_group_id: -2516, protection_element_id:-2516], primaryKey: false);
      insert('organizations', [ id: 102502, nci_institute_code: "DE029", name: "Bayhealth Cancer Center", city: "Dover", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE029",GROUP_DESC:"DE029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3517 ,protection_group_id: -2517, protection_element_id:-2517], primaryKey: false);
      insert('organizations', [ id: 102503, nci_institute_code: "DE030", name: "Delaware Ear Nose & Throat - Head & Neck Surgery PA", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE030",GROUP_DESC:"DE030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3518 ,protection_group_id: -2518, protection_element_id:-2518], primaryKey: false);
      insert('organizations', [ id: 102504, nci_institute_code: "DE031", name: "Urologic Surgical Associates of Delaware", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE031",GROUP_DESC:"DE031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3519 ,protection_group_id: -2519, protection_element_id:-2519], primaryKey: false);
      insert('organizations', [ id: 102505, nci_institute_code: "DE032", name: "Delaware Valley Physicians and Surgeons", city: "Millsboro", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE032",GROUP_DESC:"DE032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3520 ,protection_group_id: -2520, protection_element_id:-2520], primaryKey: false);
      insert('organizations', [ id: 102506, nci_institute_code: "DE033", name: "Digestive Disease Center", city: "Lewes", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE033",GROUP_DESC:"DE033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3521 ,protection_group_id: -2521, protection_element_id:-2521], primaryKey: false);
      insert('organizations', [ id: 102507, nci_institute_code: "DE034", name: "James E Spellman MD LLC", city: "Lewes", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE034",GROUP_DESC:"DE034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3522 ,protection_group_id: -2522, protection_element_id:-2522], primaryKey: false);
      insert('organizations', [ id: 102508, nci_institute_code: "DE035", name: "Pahnke Penman and Whitney PA", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE035",GROUP_DESC:"DE035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3523 ,protection_group_id: -2523, protection_element_id:-2523], primaryKey: false);
      insert('organizations', [ id: 102509, nci_institute_code: "DE036", name: "Raafat Z Abdel-Misih MD PA", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE036",GROUP_DESC:"DE036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3524 ,protection_group_id: -2524, protection_element_id:-2524], primaryKey: false);
      insert('organizations', [ id: 102510, nci_institute_code: "ENSG", name: "European Neuroblastoma Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ENSG",GROUP_DESC:"ENSG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ENSG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ENSG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ENSG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3525 ,protection_group_id: -2525, protection_element_id:-2525], primaryKey: false);
      insert('organizations', [ id: 102511, nci_institute_code: "FL001", name: "Halifax Medical Center", city: "Daytona Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL001",GROUP_DESC:"FL001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3526 ,protection_group_id: -2526, protection_element_id:-2526], primaryKey: false);
      insert('organizations', [ id: 102512, nci_institute_code: "FL002", name: "Saint Vincent's Medical Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL002",GROUP_DESC:"FL002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3527 ,protection_group_id: -2527, protection_element_id:-2527], primaryKey: false);
      insert('organizations', [ id: 102513, nci_institute_code: "FL003", name: "Baptist Regional Cancer Institute", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL003",GROUP_DESC:"FL003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3528 ,protection_group_id: -2528, protection_element_id:-2528], primaryKey: false);
      insert('organizations', [ id: 102514, nci_institute_code: "FL004", name: "Wolfson Childrens Hospital", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL004",GROUP_DESC:"FL004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3529 ,protection_group_id: -2529, protection_element_id:-2529], primaryKey: false);
      insert('organizations', [ id: 102515, nci_institute_code: "FL005", name: "University of Florida Health Science Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL005",GROUP_DESC:"FL005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3530 ,protection_group_id: -2530, protection_element_id:-2530], primaryKey: false);
      insert('organizations', [ id: 102516, nci_institute_code: "FL006", name: "Naval Regional Medical Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL006",GROUP_DESC:"FL006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3531 ,protection_group_id: -2531, protection_element_id:-2531], primaryKey: false);
      insert('organizations', [ id: 102517, nci_institute_code: "FL009", name: "Baptist Hospital", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL009",GROUP_DESC:"FL009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3532 ,protection_group_id: -2532, protection_element_id:-2532], primaryKey: false);
      insert('organizations', [ id: 102518, nci_institute_code: "FL010", name: "West Florida Healthcare", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL010",GROUP_DESC:"FL010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3533 ,protection_group_id: -2533, protection_element_id:-2533], primaryKey: false);
      insert('organizations', [ id: 102519, nci_institute_code: "FL011", name: "Naval Hospital", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL011",GROUP_DESC:"FL011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3534 ,protection_group_id: -2534, protection_element_id:-2534], primaryKey: false);
      insert('organizations', [ id: 102520, nci_institute_code: "FL012", name: "Eglin Air Force Base", city: "Valpariaso", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL012",GROUP_DESC:"FL012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3535 ,protection_group_id: -2535, protection_element_id:-2535], primaryKey: false);
      insert('organizations', [ id: 102521, nci_institute_code: "FL014", name: "Alachua General Hospital", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL014",GROUP_DESC:"FL014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3536 ,protection_group_id: -2536, protection_element_id:-2536], primaryKey: false);
      insert('organizations', [ id: 102522, nci_institute_code: "FL015", name: "University of Florida", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL015",GROUP_DESC:"FL015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3537 ,protection_group_id: -2537, protection_element_id:-2537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 102523, nci_institute_code: "FL016", name: "Plantation General Hospital", city: "Plantation", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL016",GROUP_DESC:"FL016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3538 ,protection_group_id: -2538, protection_element_id:-2538], primaryKey: false);
      insert('organizations', [ id: 102524, nci_institute_code: "FL017", name: "Marion Community Hospital", city: "Ocala", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL017",GROUP_DESC:"FL017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3539 ,protection_group_id: -2539, protection_element_id:-2539], primaryKey: false);
      insert('organizations', [ id: 102525, nci_institute_code: "FL019", name: "Fl Sanitarium-Benevolent Assoc.", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL019",GROUP_DESC:"FL019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3540 ,protection_group_id: -2540, protection_element_id:-2540], primaryKey: false);
      insert('organizations', [ id: 102526, nci_institute_code: "FL020", name: "M D Anderson Cancer Center- Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL020",GROUP_DESC:"FL020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3541 ,protection_group_id: -2541, protection_element_id:-2541], primaryKey: false);
      insert('organizations', [ id: 102527, nci_institute_code: "FL021", name: "Holmes Regional Medical Center", city: "Melbourne", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL021",GROUP_DESC:"FL021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3542 ,protection_group_id: -2542, protection_element_id:-2542], primaryKey: false);
      insert('organizations', [ id: 102528, nci_institute_code: "FL022", name: "Melbourne Internal Medicine Associates", city: "Melbourne", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL022",GROUP_DESC:"FL022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3543 ,protection_group_id: -2543, protection_element_id:-2543], primaryKey: false);
      insert('organizations', [ id: 102529, nci_institute_code: "FL023", name: "Memorial Healthcare System - Joe DiMaggio Children's Hospital", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL023",GROUP_DESC:"FL023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3544 ,protection_group_id: -2544, protection_element_id:-2544], primaryKey: false);
      insert('organizations', [ id: 102530, nci_institute_code: "FL024", name: "Memorial Medical Office Center", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL024",GROUP_DESC:"FL024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3545 ,protection_group_id: -2545, protection_element_id:-2545], primaryKey: false);
      insert('organizations', [ id: 102531, nci_institute_code: "FL028", name: "University of Miami Miller School of Medicine-Sylvester Cancer Center", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL028",GROUP_DESC:"FL028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3546 ,protection_group_id: -2546, protection_element_id:-2546], primaryKey: false);
      insert('organizations', [ id: 102532, nci_institute_code: "FL029", name: "Veterans Administration Medical Center", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL029",GROUP_DESC:"FL029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3547 ,protection_group_id: -2547, protection_element_id:-2547], primaryKey: false);
      insert('organizations', [ id: 102533, nci_institute_code: "FL032", name: "Victoria Hospital Incorporated", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL032",GROUP_DESC:"FL032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3548 ,protection_group_id: -2548, protection_element_id:-2548], primaryKey: false);
      insert('organizations', [ id: 102534, nci_institute_code: "FL033", name: "Mercy Hospital", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL033",GROUP_DESC:"FL033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3549 ,protection_group_id: -2549, protection_element_id:-2549], primaryKey: false);
      insert('organizations', [ id: 102535, nci_institute_code: "FL034", name: "Jackson Memorial Hospital", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL034",GROUP_DESC:"FL034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3550 ,protection_group_id: -2550, protection_element_id:-2550], primaryKey: false);
      insert('organizations', [ id: 102536, nci_institute_code: "FL035", name: "Cedars Medical Center", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL035",GROUP_DESC:"FL035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3551 ,protection_group_id: -2551, protection_element_id:-2551], primaryKey: false);
      insert('organizations', [ id: 102537, nci_institute_code: "FL036", name: "Mount Sinai Medical Center CCOP", city: "Miami Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL036",GROUP_DESC:"FL036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3552 ,protection_group_id: -2552, protection_element_id:-2552], primaryKey: false);
      insert('organizations', [ id: 102538, nci_institute_code: "FL037", name: "South Miami Hospital", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL037",GROUP_DESC:"FL037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3553 ,protection_group_id: -2553, protection_element_id:-2553], primaryKey: false);
      insert('organizations', [ id: 102539, nci_institute_code: "FL038", name: "Variety Childrens Hospital", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL038",GROUP_DESC:"FL038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3554 ,protection_group_id: -2554, protection_element_id:-2554], primaryKey: false);
      insert('organizations', [ id: 102540, nci_institute_code: "FL039", name: "Miami Children's Hospital", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL039",GROUP_DESC:"FL039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3555 ,protection_group_id: -2555, protection_element_id:-2555], primaryKey: false);
      insert('organizations', [ id: 102541, nci_institute_code: "FL041", name: "Parkway Comprehensive Cancer Center", city: "North Miami Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL041",GROUP_DESC:"FL041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3556 ,protection_group_id: -2556, protection_element_id:-2556], primaryKey: false);
      insert('organizations', [ id: 102542, nci_institute_code: "FL043", name: "Holy Cross Hospital", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL043",GROUP_DESC:"FL043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3557 ,protection_group_id: -2557, protection_element_id:-2557], primaryKey: false);
      insert('organizations', [ id: 102543, nci_institute_code: "FL044", name: "Florida Medical Center", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL044",GROUP_DESC:"FL044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3558 ,protection_group_id: -2558, protection_element_id:-2558], primaryKey: false);
      insert('organizations', [ id: 102544, nci_institute_code: "FL045", name: "Broward General Medical Center", city: "Ft. Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL045",GROUP_DESC:"FL045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3559 ,protection_group_id: -2559, protection_element_id:-2559], primaryKey: false);
      insert('organizations', [ id: 102545, nci_institute_code: "FL046", name: "Bradley General Hospital", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL046",GROUP_DESC:"FL046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3560 ,protection_group_id: -2560, protection_element_id:-2560], primaryKey: false);
      insert('organizations', [ id: 102546, nci_institute_code: "FL047", name: "University Hospital", city: "Tamarac", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL047",GROUP_DESC:"FL047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3561 ,protection_group_id: -2561, protection_element_id:-2561], primaryKey: false);
      insert('organizations', [ id: 102547, nci_institute_code: "FL049", name: "Saint Mary's Hospital", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL049",GROUP_DESC:"FL049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3562 ,protection_group_id: -2562, protection_element_id:-2562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 102548, nci_institute_code: "FL050", name: "Boca Raton Community Hospital", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL050",GROUP_DESC:"FL050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3563 ,protection_group_id: -2563, protection_element_id:-2563], primaryKey: false);
      insert('organizations', [ id: 102549, nci_institute_code: "FL051", name: "Veterans Administration Hospital", city: "Bay Pines", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL051",GROUP_DESC:"FL051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3564 ,protection_group_id: -2564, protection_element_id:-2564], primaryKey: false);
      insert('organizations', [ id: 102550, nci_institute_code: "FL052", name: "Manatee Memorial Hospital", city: "Bradenton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL052",GROUP_DESC:"FL052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3565 ,protection_group_id: -2565, protection_element_id:-2565], primaryKey: false);
      insert('organizations', [ id: 102551, nci_institute_code: "FL053", name: "Radiation Therapy Oncology Center At Mease Hospital", city: "Dunedin", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL053",GROUP_DESC:"FL053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3566 ,protection_group_id: -2566, protection_element_id:-2566], primaryKey: false);
      insert('organizations', [ id: 102552, nci_institute_code: "FL054", name: "University of South Florida", city: "Dunedin", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL054",GROUP_DESC:"FL054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3567 ,protection_group_id: -2567, protection_element_id:-2567], primaryKey: false);
      insert('organizations', [ id: 102553, nci_institute_code: "FL055", name: "Sun Coast Hospital", city: "Largo", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL055",GROUP_DESC:"FL055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3568 ,protection_group_id: -2568, protection_element_id:-2568], primaryKey: false);
      insert('organizations', [ id: 102554, nci_institute_code: "FL056", name: "Community Hospital of New Port Richey", city: "New Port Richey", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL056",GROUP_DESC:"FL056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3569 ,protection_group_id: -2569, protection_element_id:-2569], primaryKey: false);
      insert('organizations', [ id: 102555, nci_institute_code: "FL057", name: "Bayonet Point Regional Medical Center", city: "Hudson", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL057",GROUP_DESC:"FL057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3570 ,protection_group_id: -2570, protection_element_id:-2570], primaryKey: false);
      insert('organizations', [ id: 102556, nci_institute_code: "FL058", name: "Sarasota Memorial Hospital", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL058",GROUP_DESC:"FL058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3571 ,protection_group_id: -2571, protection_element_id:-2571], primaryKey: false);
      insert('organizations', [ id: 102557, nci_institute_code: "FL059", name: "Sarasota Oncology Center", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL059",GROUP_DESC:"FL059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3572 ,protection_group_id: -2572, protection_element_id:-2572], primaryKey: false);
      insert('organizations', [ id: 102558, nci_institute_code: "FL060", name: "Tampa General Hospital", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL060",GROUP_DESC:"FL060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3573 ,protection_group_id: -2573, protection_element_id:-2573], primaryKey: false);
      insert('organizations', [ id: 102559, nci_institute_code: "FL061", name: "Saint Joseph Children's Hospital of Tampa", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL061",GROUP_DESC:"FL061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3574 ,protection_group_id: -2574, protection_element_id:-2574], primaryKey: false);
      insert('organizations', [ id: 102560, nci_institute_code: "FL062", name: "Macdill United States Air Force Regional Hospital", city: "Macdill Afb", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL062",GROUP_DESC:"FL062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3575 ,protection_group_id: -2575, protection_element_id:-2575], primaryKey: false);
      insert('organizations', [ id: 102561, nci_institute_code: "FL063", name: "North Florida Hematology/Oncology", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL063",GROUP_DESC:"FL063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3576 ,protection_group_id: -2576, protection_element_id:-2576], primaryKey: false);
      insert('organizations', [ id: 102562, nci_institute_code: "FL064", name: "James A. Haley Veterans Affairs Hospital", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL064",GROUP_DESC:"FL064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3577 ,protection_group_id: -2577, protection_element_id:-2577], primaryKey: false);
      insert('organizations', [ id: 102563, nci_institute_code: "FL065", name: "Moffitt Cancer Center and Research Institute", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL065",GROUP_DESC:"FL065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3578 ,protection_group_id: -2578, protection_element_id:-2578], primaryKey: false);
      insert('organizations', [ id: 102564, nci_institute_code: "FL066", name: "Island Coast Hematology Oncology", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL066",GROUP_DESC:"FL066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3579 ,protection_group_id: -2579, protection_element_id:-2579], primaryKey: false);
      insert('organizations', [ id: 102565, nci_institute_code: "FL067", name: "Bayfront Outpatient Health Clinic", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL067",GROUP_DESC:"FL067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3580 ,protection_group_id: -2580, protection_element_id:-2580], primaryKey: false);
      insert('organizations', [ id: 102566, nci_institute_code: "FL068", name: "All Children's Hospital", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL068",GROUP_DESC:"FL068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3581 ,protection_group_id: -2581, protection_element_id:-2581], primaryKey: false);
      insert('organizations', [ id: 102567, nci_institute_code: "FL069", name: "Doctors Hospital", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL069",GROUP_DESC:"FL069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3582 ,protection_group_id: -2582, protection_element_id:-2582], primaryKey: false);
      insert('organizations', [ id: 102568, nci_institute_code: "FL071", name: "Lakeland Regional Cancer Center", city: "Lakeland", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL071",GROUP_DESC:"FL071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3583 ,protection_group_id: -2583, protection_element_id:-2583], primaryKey: false);
      insert('organizations', [ id: 102569, nci_institute_code: "FL072", name: "Winter Haven Hospital", city: "Winter Haven", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL072",GROUP_DESC:"FL072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3584 ,protection_group_id: -2584, protection_element_id:-2584], primaryKey: false);
      insert('organizations', [ id: 102570, nci_institute_code: "FL073", name: "Lee Memorial Health System", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL073",GROUP_DESC:"FL073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3585 ,protection_group_id: -2585, protection_element_id:-2585], primaryKey: false);
      insert('organizations', [ id: 102571, nci_institute_code: "FL075", name: "Naples Community Hospital Incorporated", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL075",GROUP_DESC:"FL075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3586 ,protection_group_id: -2586, protection_element_id:-2586], primaryKey: false);
      insert('organizations', [ id: 102572, nci_institute_code: "FL078", name: "Baptist Hospital of Miami", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL078",GROUP_DESC:"FL078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3587 ,protection_group_id: -2587, protection_element_id:-2587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 102573, nci_institute_code: "FL080", name: "Mayo Clinic Jacksonville", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL080",GROUP_DESC:"FL080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3588 ,protection_group_id: -2588, protection_element_id:-2588], primaryKey: false);
      insert('organizations', [ id: 102574, nci_institute_code: "FL081", name: "Lynn Regional Cancer Center - West", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL081",GROUP_DESC:"FL081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3589 ,protection_group_id: -2589, protection_element_id:-2589], primaryKey: false);
      insert('organizations', [ id: 102575, nci_institute_code: "FL082", name: "Sacred Heart Medical Oncology Group - Davis Highway", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL082",GROUP_DESC:"FL082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3590 ,protection_group_id: -2590, protection_element_id:-2590], primaryKey: false);
      insert('organizations', [ id: 102576, nci_institute_code: "FL083", name: "Lykes Cancer Center", city: "Largo", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL083",GROUP_DESC:"FL083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3591 ,protection_group_id: -2591, protection_element_id:-2591], primaryKey: false);
      insert('organizations', [ id: 102577, nci_institute_code: "FL084", name: "John Fitzgerald Kennedy Medical Center", city: "Atlantis", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL084",GROUP_DESC:"FL084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3592 ,protection_group_id: -2592, protection_element_id:-2592], primaryKey: false);
      insert('organizations', [ id: 102578, nci_institute_code: "FL085", name: "Morton Plant Hospital", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL085",GROUP_DESC:"FL085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3593 ,protection_group_id: -2593, protection_element_id:-2593], primaryKey: false);
      insert('organizations', [ id: 102579, nci_institute_code: "FL086", name: "Florida Hospital", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL086",GROUP_DESC:"FL086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3594 ,protection_group_id: -2594, protection_element_id:-2594], primaryKey: false);
      insert('organizations', [ id: 102580, nci_institute_code: "FL087", name: "Winter Park Memorial Hospital", city: "Winter Park", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL087",GROUP_DESC:"FL087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3595 ,protection_group_id: -2595, protection_element_id:-2595], primaryKey: false);
      insert('organizations', [ id: 102581, nci_institute_code: "FL088", name: "Parrish Medical Center", city: "Titusville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL088",GROUP_DESC:"FL088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3596 ,protection_group_id: -2596, protection_element_id:-2596], primaryKey: false);
      insert('organizations', [ id: 102582, nci_institute_code: "FL089", name: "Cape Coral Hospital", city: "Cape Coral", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL089",GROUP_DESC:"FL089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3597 ,protection_group_id: -2597, protection_element_id:-2597], primaryKey: false);
      insert('organizations', [ id: 102583, nci_institute_code: "FL090", name: "Cleveland Clinic", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL090",GROUP_DESC:"FL090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3598 ,protection_group_id: -2598, protection_element_id:-2598], primaryKey: false);
      insert('organizations', [ id: 102584, nci_institute_code: "FL091", name: "Memorial Medical Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL091",GROUP_DESC:"FL091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3599 ,protection_group_id: -2599, protection_element_id:-2599], primaryKey: false);
      insert('organizations', [ id: 102585, nci_institute_code: "FL093", name: "Fl Community Cancer Center", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL093",GROUP_DESC:"FL093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3600 ,protection_group_id: -2600, protection_element_id:-2600], primaryKey: false);
      insert('organizations', [ id: 102586, nci_institute_code: "FL094", name: "Malcolm Randal Veterans Administration Medical Center", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL094",GROUP_DESC:"FL094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3601 ,protection_group_id: -2601, protection_element_id:-2601], primaryKey: false);
      insert('organizations', [ id: 102587, nci_institute_code: "FL095", name: "Edna Williams Cancer Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL095",GROUP_DESC:"FL095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3602 ,protection_group_id: -2602, protection_element_id:-2602], primaryKey: false);
      insert('organizations', [ id: 102588, nci_institute_code: "FL096", name: "Kendall Regional Medical Center", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL096",GROUP_DESC:"FL096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3603 ,protection_group_id: -2603, protection_element_id:-2603], primaryKey: false);
      insert('organizations', [ id: 102589, nci_institute_code: "FL098", name: "Medical Center of Port Saint Lucie", city: "Port St. Lucie", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL098",GROUP_DESC:"FL098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3604 ,protection_group_id: -2604, protection_element_id:-2604], primaryKey: false);
      insert('organizations', [ id: 102590, nci_institute_code: "FL099", name: "North Florida Regional Medical Center", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL099",GROUP_DESC:"FL099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3605 ,protection_group_id: -2605, protection_element_id:-2605], primaryKey: false);
      insert('organizations', [ id: 102591, nci_institute_code: "FL100", name: "Florida Cancer Institute", city: "New Port Richey", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL100",GROUP_DESC:"FL100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3606 ,protection_group_id: -2606, protection_element_id:-2606], primaryKey: false);
      insert('organizations', [ id: 102592, nci_institute_code: "FL101", name: "Nemours Children's Clinic - Jacksonville", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL101",GROUP_DESC:"FL101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3607 ,protection_group_id: -2607, protection_element_id:-2607], primaryKey: false);
      insert('organizations', [ id: 102593, nci_institute_code: "FL102", name: "Charlotte Medical Plaza", city: "Port Charlotte", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL102",GROUP_DESC:"FL102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3608 ,protection_group_id: -2608, protection_element_id:-2608], primaryKey: false);
      insert('organizations', [ id: 102594, nci_institute_code: "FL103", name: "Bon-Secours Venice Hospital", city: "Venice", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL103",GROUP_DESC:"FL103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3609 ,protection_group_id: -2609, protection_element_id:-2609], primaryKey: false);
      insert('organizations', [ id: 102595, nci_institute_code: "FL104", name: "Arnold Palmer Hospital for C and W", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL104",GROUP_DESC:"FL104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3610 ,protection_group_id: -2610, protection_element_id:-2610], primaryKey: false);
      insert('organizations', [ id: 102596, nci_institute_code: "FL105", name: "Sacred Heart Hospital", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL105",GROUP_DESC:"FL105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3611 ,protection_group_id: -2611, protection_element_id:-2611], primaryKey: false);
      insert('organizations', [ id: 102597, nci_institute_code: "FL106", name: "21st Century Oncology Inc-Broadway", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL106",GROUP_DESC:"FL106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3612 ,protection_group_id: -2612, protection_element_id:-2612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 102598, nci_institute_code: "FL108", name: "Seven Rivers Community Hospital", city: "LeCanto", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL108",GROUP_DESC:"FL108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3613 ,protection_group_id: -2613, protection_element_id:-2613], primaryKey: false);
      insert('organizations', [ id: 102599, nci_institute_code: "FL109", name: "Williams Cancer Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL109",GROUP_DESC:"FL109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3614 ,protection_group_id: -2614, protection_element_id:-2614], primaryKey: false);
      insert('organizations', [ id: 102600, nci_institute_code: "FL110", name: "Cornerstone Cancer Center", city: "Palm Harbor", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL110",GROUP_DESC:"FL110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3615 ,protection_group_id: -2615, protection_element_id:-2615], primaryKey: false);
      insert('organizations', [ id: 102601, nci_institute_code: "FL111", name: "Good Samaritan Medical Center", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL111",GROUP_DESC:"FL111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3616 ,protection_group_id: -2616, protection_element_id:-2616], primaryKey: false);
      insert('organizations', [ id: 102602, nci_institute_code: "FL112", name: "Florida Hospital Cancer Institute", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL112",GROUP_DESC:"FL112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3617 ,protection_group_id: -2617, protection_element_id:-2617], primaryKey: false);
      insert('organizations', [ id: 102603, nci_institute_code: "FL113", name: "Wuesthoff Hospital", city: "Rockledge", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL113",GROUP_DESC:"FL113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3618 ,protection_group_id: -2618, protection_element_id:-2618], primaryKey: false);
      insert('organizations', [ id: 102604, nci_institute_code: "FL114", name: "The Watson Clinic", city: "Lakeland", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL114",GROUP_DESC:"FL114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3619 ,protection_group_id: -2619, protection_element_id:-2619], primaryKey: false);
      insert('organizations', [ id: 102605, nci_institute_code: "FL116", name: "Bay Area Oncology Cancer Care Center", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL116",GROUP_DESC:"FL116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3620 ,protection_group_id: -2620, protection_element_id:-2620], primaryKey: false);
      insert('organizations', [ id: 102606, nci_institute_code: "FL117", name: "Indian River Surgical Oncology Group., Pa", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL117",GROUP_DESC:"FL117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3621 ,protection_group_id: -2621, protection_element_id:-2621], primaryKey: false);
      insert('organizations', [ id: 102607, nci_institute_code: "FL118", name: "Diagnostic Clinic", city: "Largo", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL118",GROUP_DESC:"FL118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3622 ,protection_group_id: -2622, protection_element_id:-2622], primaryKey: false);
      insert('organizations', [ id: 102608, nci_institute_code: "FL119", name: "Saint Petersburg Suncoast Medical Group", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL119",GROUP_DESC:"FL119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3623 ,protection_group_id: -2623, protection_element_id:-2623], primaryKey: false);
      insert('organizations', [ id: 102609, nci_institute_code: "FL120", name: "Florida CCOP", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL120",GROUP_DESC:"FL120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3624 ,protection_group_id: -2624, protection_element_id:-2624], primaryKey: false);
      insert('organizations', [ id: 102610, nci_institute_code: "FL121", name: "Southwest Florida Regional Medical Center", city: "Fort Meyers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL121",GROUP_DESC:"FL121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3625 ,protection_group_id: -2625, protection_element_id:-2625], primaryKey: false);
      insert('organizations', [ id: 102611, nci_institute_code: "FL123", name: "Radiation Therapy Oncology Center", city: "Dunedin", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL123",GROUP_DESC:"FL123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3626 ,protection_group_id: -2626, protection_element_id:-2626], primaryKey: false);
      insert('organizations', [ id: 102612, nci_institute_code: "FL124", name: "Bethesda Memorial Hospital", city: "Boynton Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL124",GROUP_DESC:"FL124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3627 ,protection_group_id: -2627, protection_element_id:-2627], primaryKey: false);
      insert('organizations', [ id: 102613, nci_institute_code: "FL125", name: "Saint Anthony's Hospital Cancer Care Center", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL125",GROUP_DESC:"FL125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3628 ,protection_group_id: -2628, protection_element_id:-2628], primaryKey: false);
      insert('organizations', [ id: 102614, nci_institute_code: "FL126", name: "Florida Hospital Medical Plaza", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL126",GROUP_DESC:"FL126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3629 ,protection_group_id: -2629, protection_element_id:-2629], primaryKey: false);
      insert('organizations', [ id: 102615, nci_institute_code: "FL127", name: "Ocala Oncology Center", city: "Ocala", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL127",GROUP_DESC:"FL127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3630 ,protection_group_id: -2630, protection_element_id:-2630], primaryKey: false);
      insert('organizations', [ id: 102616, nci_institute_code: "FL128", name: "South Florida Radiotherapy Center", city: "Plantation", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL128",GROUP_DESC:"FL128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3631 ,protection_group_id: -2631, protection_element_id:-2631], primaryKey: false);
      insert('organizations', [ id: 102617, nci_institute_code: "FL129", name: "Gulf Point Oncology", city: "Hudson", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL129",GROUP_DESC:"FL129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3632 ,protection_group_id: -2632, protection_element_id:-2632], primaryKey: false);
      insert('organizations', [ id: 102618, nci_institute_code: "FL130", name: "Memorial Regional Cancer Center", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL130",GROUP_DESC:"FL130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3633 ,protection_group_id: -2633, protection_element_id:-2633], primaryKey: false);
      insert('organizations', [ id: 102619, nci_institute_code: "FL131", name: "Health Central", city: "Ocoee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL131",GROUP_DESC:"FL131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3634 ,protection_group_id: -2634, protection_element_id:-2634], primaryKey: false);
      insert('organizations', [ id: 102620, nci_institute_code: "FL132", name: "Hematology Oncology Consultants, P.A.", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL132",GROUP_DESC:"FL132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3635 ,protection_group_id: -2635, protection_element_id:-2635], primaryKey: false);
      insert('organizations', [ id: 102621, nci_institute_code: "FL133", name: "Flagler Hospital East", city: "St. Augustine", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL133",GROUP_DESC:"FL133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3636 ,protection_group_id: -2636, protection_element_id:-2636], primaryKey: false);
      insert('organizations', [ id: 102622, nci_institute_code: "FL135", name: "Center for Cancer Care", city: "Winter Park", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL135",GROUP_DESC:"FL135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3637 ,protection_group_id: -2637, protection_element_id:-2637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 102623, nci_institute_code: "FL136", name: "Cancer and Blood Disease Center", city: "Lecanto", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL136",GROUP_DESC:"FL136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3638 ,protection_group_id: -2638, protection_element_id:-2638], primaryKey: false);
      insert('organizations', [ id: 102624, nci_institute_code: "FL137", name: "Hematology/Oncology Consultants", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL137",GROUP_DESC:"FL137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3639 ,protection_group_id: -2639, protection_element_id:-2639], primaryKey: false);
      insert('organizations', [ id: 102625, nci_institute_code: "FL138", name: "Boca Raton Comprehensive Cancer Center", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL138",GROUP_DESC:"FL138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3640 ,protection_group_id: -2640, protection_element_id:-2640], primaryKey: false);
      insert('organizations', [ id: 102626, nci_institute_code: "FL139", name: "Geffen Cancer Center and Research Institute", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL139",GROUP_DESC:"FL139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3641 ,protection_group_id: -2641, protection_element_id:-2641], primaryKey: false);
      insert('organizations', [ id: 102627, nci_institute_code: "FL140", name: "Florida Cancer Specialists - Naples Goodlette", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL140",GROUP_DESC:"FL140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3642 ,protection_group_id: -2642, protection_element_id:-2642], primaryKey: false);
      insert('organizations', [ id: 102628, nci_institute_code: "FL143", name: "Hematology Oncology Associates", city: "S. Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL143",GROUP_DESC:"FL143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3643 ,protection_group_id: -2643, protection_element_id:-2643], primaryKey: false);
      insert('organizations', [ id: 102629, nci_institute_code: "FL144", name: "Hematology Oncology Association", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL144",GROUP_DESC:"FL144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3644 ,protection_group_id: -2644, protection_element_id:-2644], primaryKey: false);
      insert('organizations', [ id: 102630, nci_institute_code: "FL145", name: "Hematology and Oncology Associates", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL145",GROUP_DESC:"FL145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3645 ,protection_group_id: -2645, protection_element_id:-2645], primaryKey: false);
      insert('organizations', [ id: 102631, nci_institute_code: "FL146", name: "Hematology - Oncology Associates", city: "Atlantis", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL146",GROUP_DESC:"FL146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3646 ,protection_group_id: -2646, protection_element_id:-2646], primaryKey: false);
      insert('organizations', [ id: 102632, nci_institute_code: "FL147", name: "Oncology Associates South Florida, P.A.", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL147",GROUP_DESC:"FL147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3647 ,protection_group_id: -2647, protection_element_id:-2647], primaryKey: false);
      insert('organizations', [ id: 102633, nci_institute_code: "FL148", name: "Fenton & Associates., Incorporated", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL148",GROUP_DESC:"FL148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3648 ,protection_group_id: -2648, protection_element_id:-2648], primaryKey: false);
      insert('organizations', [ id: 102634, nci_institute_code: "FL149", name: "Internal Medicine Association", city: "Miami Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL149",GROUP_DESC:"FL149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3649 ,protection_group_id: -2649, protection_element_id:-2649], primaryKey: false);
      insert('organizations', [ id: 102635, nci_institute_code: "FL150", name: "Regional Oncology & Hematology Associates", city: "Kissimmee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL150",GROUP_DESC:"FL150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3650 ,protection_group_id: -2650, protection_element_id:-2650], primaryKey: false);
      insert('organizations', [ id: 102636, nci_institute_code: "FL151", name: "Venice Oncology Center", city: "Venice", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL151",GROUP_DESC:"FL151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3651 ,protection_group_id: -2651, protection_element_id:-2651], primaryKey: false);
      insert('organizations', [ id: 102637, nci_institute_code: "FL152", name: "Gold Vann and White Clinic., P.A.", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL152",GROUP_DESC:"FL152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3652 ,protection_group_id: -2652, protection_element_id:-2652], primaryKey: false);
      insert('organizations', [ id: 102638, nci_institute_code: "FL153", name: "Bradenton Oncology Center", city: "Bradenton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL153",GROUP_DESC:"FL153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3653 ,protection_group_id: -2653, protection_element_id:-2653], primaryKey: false);
      insert('organizations', [ id: 102639, nci_institute_code: "FL154", name: "Powell Cancer Pavilion", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL154",GROUP_DESC:"FL154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3654 ,protection_group_id: -2654, protection_element_id:-2654], primaryKey: false);
      insert('organizations', [ id: 102640, nci_institute_code: "FL155", name: "Aventura Medical Center", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL155",GROUP_DESC:"FL155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3655 ,protection_group_id: -2655, protection_element_id:-2655], primaryKey: false);
      insert('organizations', [ id: 102641, nci_institute_code: "FL156", name: "Advanced Medical Specialties -North", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL156",GROUP_DESC:"FL156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3656 ,protection_group_id: -2656, protection_element_id:-2656], primaryKey: false);
      insert('organizations', [ id: 102642, nci_institute_code: "FL157", name: "The Center for Hematology /Oncology-Delray", city: "Delray Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL157",GROUP_DESC:"FL157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3657 ,protection_group_id: -2657, protection_element_id:-2657], primaryKey: false);
      insert('organizations', [ id: 102643, nci_institute_code: "FL158", name: "Palmetto Hospital", city: "Hialeah", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL158",GROUP_DESC:"FL158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3658 ,protection_group_id: -2658, protection_element_id:-2658], primaryKey: false);
      insert('organizations', [ id: 102644, nci_institute_code: "FL159", name: "Florida Community Cancer Centers", city: "Dunedin", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL159",GROUP_DESC:"FL159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3659 ,protection_group_id: -2659, protection_element_id:-2659], primaryKey: false);
      insert('organizations', [ id: 102645, nci_institute_code: "FL160", name: "Gainesville Hematology Oncology Associates", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL160",GROUP_DESC:"FL160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3660 ,protection_group_id: -2660, protection_element_id:-2660], primaryKey: false);
      insert('organizations', [ id: 102646, nci_institute_code: "FL161", name: "Harborside Medical Tower", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL161",GROUP_DESC:"FL161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3661 ,protection_group_id: -2661, protection_element_id:-2661], primaryKey: false);
      insert('organizations', [ id: 102647, nci_institute_code: "FL162", name: "Hematology Oncology Associates", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL162",GROUP_DESC:"FL162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3662 ,protection_group_id: -2662, protection_element_id:-2662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 102648, nci_institute_code: "FL163", name: "Children's Hematology Oncology Group", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL163",GROUP_DESC:"FL163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3663 ,protection_group_id: -2663, protection_element_id:-2663], primaryKey: false);
      insert('organizations', [ id: 102649, nci_institute_code: "FL164", name: "University Community Hospital", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL164",GROUP_DESC:"FL164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3664 ,protection_group_id: -2664, protection_element_id:-2664], primaryKey: false);
      insert('organizations', [ id: 102650, nci_institute_code: "FL166", name: "West Broward Regional Cancer Center", city: "Lauderdale Lakes", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL166",GROUP_DESC:"FL166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3665 ,protection_group_id: -2665, protection_element_id:-2665], primaryKey: false);
      insert('organizations', [ id: 102651, nci_institute_code: "FL167", name: "Hernando Cancer Center", city: "Brooksville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL167",GROUP_DESC:"FL167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3666 ,protection_group_id: -2666, protection_element_id:-2666], primaryKey: false);
      insert('organizations', [ id: 102652, nci_institute_code: "FL168", name: "Florida Cancer Specialists - Fort Myers", city: "Ft. Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL168",GROUP_DESC:"FL168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3667 ,protection_group_id: -2667, protection_element_id:-2667], primaryKey: false);
      insert('organizations', [ id: 102653, nci_institute_code: "FL169", name: "Inter Community Cancer Center", city: "Lisle", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL169",GROUP_DESC:"FL169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3668 ,protection_group_id: -2668, protection_element_id:-2668], primaryKey: false);
      insert('organizations', [ id: 102654, nci_institute_code: "FL170", name: "Hematology/Oncology Associates", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL170",GROUP_DESC:"FL170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3669 ,protection_group_id: -2669, protection_element_id:-2669], primaryKey: false);
      insert('organizations', [ id: 102655, nci_institute_code: "FL171", name: "Boca Raton Radiotherapy Center., Incorporated", city: "Deerfield", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL171",GROUP_DESC:"FL171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3670 ,protection_group_id: -2670, protection_element_id:-2670], primaryKey: false);
      insert('organizations', [ id: 102656, nci_institute_code: "FL172", name: "Naples Medical Center", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL172",GROUP_DESC:"FL172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3671 ,protection_group_id: -2671, protection_element_id:-2671], primaryKey: false);
      insert('organizations', [ id: 102657, nci_institute_code: "FL174", name: "Suite 211", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL174",GROUP_DESC:"FL174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3672 ,protection_group_id: -2672, protection_element_id:-2672], primaryKey: false);
      insert('organizations', [ id: 102658, nci_institute_code: "FL175", name: "Memorial Cancer Center", city: "Ormond Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL175",GROUP_DESC:"FL175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3673 ,protection_group_id: -2673, protection_element_id:-2673], primaryKey: false);
      insert('organizations', [ id: 102659, nci_institute_code: "FL176", name: "League Vs Cancer", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL176",GROUP_DESC:"FL176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3674 ,protection_group_id: -2674, protection_element_id:-2674], primaryKey: false);
      insert('organizations', [ id: 102660, nci_institute_code: "FL177", name: "Lower Florida Keys Health and Science Center", city: "Key West", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL177",GROUP_DESC:"FL177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3675 ,protection_group_id: -2675, protection_element_id:-2675], primaryKey: false);
      insert('organizations', [ id: 102661, nci_institute_code: "FL178", name: "Florida Oncology Center", city: "Winter Park", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL178",GROUP_DESC:"FL178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3676 ,protection_group_id: -2676, protection_element_id:-2676], primaryKey: false);
      insert('organizations', [ id: 102662, nci_institute_code: "FL179", name: "Delray Medical Center", city: "Delray Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL179",GROUP_DESC:"FL179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3677 ,protection_group_id: -2677, protection_element_id:-2677], primaryKey: false);
      insert('organizations', [ id: 102663, nci_institute_code: "FL180", name: "Lakeview Professional Center", city: "Sanford", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL180",GROUP_DESC:"FL180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3678 ,protection_group_id: -2678, protection_element_id:-2678], primaryKey: false);
      insert('organizations', [ id: 102664, nci_institute_code: "FL181", name: "Pasco, Hernando Oncology Association P.A.", city: "New Port Richey", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL181",GROUP_DESC:"FL181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3679 ,protection_group_id: -2679, protection_element_id:-2679], primaryKey: false);
      insert('organizations', [ id: 102665, nci_institute_code: "FL182", name: "Gulf Coast Cancer Treatment Center", city: "Panama City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL182",GROUP_DESC:"FL182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3680 ,protection_group_id: -2680, protection_element_id:-2680], primaryKey: false);
      insert('organizations', [ id: 102666, nci_institute_code: "FL183", name: "Methodist Medical Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL183",GROUP_DESC:"FL183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3681 ,protection_group_id: -2681, protection_element_id:-2681], primaryKey: false);
      insert('organizations', [ id: 102667, nci_institute_code: "FL184", name: "Columbia-Memorial Medical Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL184",GROUP_DESC:"FL184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3682 ,protection_group_id: -2682, protection_element_id:-2682], primaryKey: false);
      insert('organizations', [ id: 102668, nci_institute_code: "FL185", name: "Pinellas Hematology Oncology Center", city: "Seminole", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL185",GROUP_DESC:"FL185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3683 ,protection_group_id: -2683, protection_element_id:-2683], primaryKey: false);
      insert('organizations', [ id: 102669, nci_institute_code: "FL186", name: "Columbia Saint Petersburg Medical Center", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL186",GROUP_DESC:"FL186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3684 ,protection_group_id: -2684, protection_element_id:-2684], primaryKey: false);
      insert('organizations', [ id: 102670, nci_institute_code: "FL188", name: "Lakeview Professional Center", city: "Stanford", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL188",GROUP_DESC:"FL188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3685 ,protection_group_id: -2685, protection_element_id:-2685], primaryKey: false);
      insert('organizations', [ id: 102671, nci_institute_code: "FL190", name: "Jupiter Medical Center", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL190",GROUP_DESC:"FL190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3686 ,protection_group_id: -2686, protection_element_id:-2686], primaryKey: false);
      insert('organizations', [ id: 102672, nci_institute_code: "FL191", name: "South Florida Pediatric Surgeons, P.A.", city: "Coral Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL191",GROUP_DESC:"FL191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3687 ,protection_group_id: -2687, protection_element_id:-2687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 102673, nci_institute_code: "FL192", name: "Nemours Children's Clinic - Fort Meyers", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL192",GROUP_DESC:"FL192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3688 ,protection_group_id: -2688, protection_element_id:-2688], primaryKey: false);
      insert('organizations', [ id: 102674, nci_institute_code: "FL193", name: "First Coast Medical Group", city: "Orange Park", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL193",GROUP_DESC:"FL193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3689 ,protection_group_id: -2689, protection_element_id:-2689], primaryKey: false);
      insert('organizations', [ id: 102675, nci_institute_code: "FL194", name: "Columbia Cancer Research Network of Florida", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL194",GROUP_DESC:"FL194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3690 ,protection_group_id: -2690, protection_element_id:-2690], primaryKey: false);
      insert('organizations', [ id: 102676, nci_institute_code: "FL195", name: "Collier Radiation Therapy Regional Center", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL195",GROUP_DESC:"FL195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3691 ,protection_group_id: -2691, protection_element_id:-2691], primaryKey: false);
      insert('organizations', [ id: 102677, nci_institute_code: "FL196", name: "Comprehensive Cancer Center at JFK Medical Center", city: "Lake Worth", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL196",GROUP_DESC:"FL196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3692 ,protection_group_id: -2692, protection_element_id:-2692], primaryKey: false);
      insert('organizations', [ id: 102678, nci_institute_code: "FL197", name: "Hematology Oncology Associates of Central Brevard", city: "Rockledge", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL197",GROUP_DESC:"FL197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3693 ,protection_group_id: -2693, protection_element_id:-2693], primaryKey: false);
      insert('organizations', [ id: 102679, nci_institute_code: "FL199", name: "Space Coast Medical Associates - Titusville", city: "Titusville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL199",GROUP_DESC:"FL199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3694 ,protection_group_id: -2694, protection_element_id:-2694], primaryKey: false);
      insert('organizations', [ id: 102680, nci_institute_code: "FL200", name: "Nemours Childrens Clinic - Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL200",GROUP_DESC:"FL200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3695 ,protection_group_id: -2695, protection_element_id:-2695], primaryKey: false);
      insert('organizations', [ id: 102681, nci_institute_code: "FL201", name: "Internal Medicine and Oncology", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL201",GROUP_DESC:"FL201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3696 ,protection_group_id: -2696, protection_element_id:-2696], primaryKey: false);
      insert('organizations', [ id: 102682, nci_institute_code: "FL202", name: "Michael H. Greenhawt, M.D.", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL202",GROUP_DESC:"FL202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3697 ,protection_group_id: -2697, protection_element_id:-2697], primaryKey: false);
      insert('organizations', [ id: 102683, nci_institute_code: "FL203", name: "The Children's Hospital of Southwest Florida", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL203",GROUP_DESC:"FL203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3698 ,protection_group_id: -2698, protection_element_id:-2698], primaryKey: false);
      insert('organizations', [ id: 102684, nci_institute_code: "FL204", name: "Hematology & Med. Oncology", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL204",GROUP_DESC:"FL204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3699 ,protection_group_id: -2699, protection_element_id:-2699], primaryKey: false);
      insert('organizations', [ id: 102685, nci_institute_code: "FL205", name: "Hematology Oncology Association.", city: "Boynton Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL205",GROUP_DESC:"FL205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3700 ,protection_group_id: -2700, protection_element_id:-2700], primaryKey: false);
      insert('organizations', [ id: 102686, nci_institute_code: "FL206", name: "Integrated Community Oncology Network - Orange Park Cancer Center", city: "Orange Park", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL206",GROUP_DESC:"FL206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3701 ,protection_group_id: -2701, protection_element_id:-2701], primaryKey: false);
      insert('organizations', [ id: 102687, nci_institute_code: "FL209", name: "Radiant Research Stuart", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL209",GROUP_DESC:"FL209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3702 ,protection_group_id: -2702, protection_element_id:-2702], primaryKey: false);
      insert('organizations', [ id: 102688, nci_institute_code: "FL210", name: "Mid-Atlantic Oncology Group", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL210",GROUP_DESC:"FL210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3703 ,protection_group_id: -2703, protection_element_id:-2703], primaryKey: false);
      insert('organizations', [ id: 102689, nci_institute_code: "FL211", name: "Jacksonville Oncology Group", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL211",GROUP_DESC:"FL211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3704 ,protection_group_id: -2704, protection_element_id:-2704], primaryKey: false);
      insert('organizations', [ id: 102690, nci_institute_code: "FL212", name: "Medical Surgical Specialists", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL212",GROUP_DESC:"FL212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3705 ,protection_group_id: -2705, protection_element_id:-2705], primaryKey: false);
      insert('organizations', [ id: 102691, nci_institute_code: "FL213", name: "Indian River Regional Medical Center", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL213",GROUP_DESC:"FL213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3706 ,protection_group_id: -2706, protection_element_id:-2706], primaryKey: false);
      insert('organizations', [ id: 102692, nci_institute_code: "FL214", name: "Florida Cancer Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL214",GROUP_DESC:"FL214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3707 ,protection_group_id: -2707, protection_element_id:-2707], primaryKey: false);
      insert('organizations', [ id: 102693, nci_institute_code: "FL215", name: "21st Century Oncology, Inc.", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL215",GROUP_DESC:"FL215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3708 ,protection_group_id: -2708, protection_element_id:-2708], primaryKey: false);
      insert('organizations', [ id: 102694, nci_institute_code: "FL217", name: "Radiation Therapy Associates", city: "Cape Coral", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL217",GROUP_DESC:"FL217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3709 ,protection_group_id: -2709, protection_element_id:-2709], primaryKey: false);
      insert('organizations', [ id: 102695, nci_institute_code: "FL218", name: "Blake Medical Center", city: "Bradenton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL218",GROUP_DESC:"FL218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3710 ,protection_group_id: -2710, protection_element_id:-2710], primaryKey: false);
      insert('organizations', [ id: 102696, nci_institute_code: "FL219", name: "Daytona Oncology Center", city: "Daytona Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL219",GROUP_DESC:"FL219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3711 ,protection_group_id: -2711, protection_element_id:-2711], primaryKey: false);
      insert('organizations', [ id: 102697, nci_institute_code: "FL220", name: "Integrated Community Oncology Network- Southside Cancer Center", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL220",GROUP_DESC:"FL220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3712 ,protection_group_id: -2712, protection_element_id:-2712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 102698, nci_institute_code: "FL221", name: "Tallahassee Memorial HealthCare", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL221",GROUP_DESC:"FL221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3713 ,protection_group_id: -2713, protection_element_id:-2713], primaryKey: false);
      insert('organizations', [ id: 102699, nci_institute_code: "FL222", name: "Bayfront-Saint Anthony's Health Care", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL222",GROUP_DESC:"FL222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3714 ,protection_group_id: -2714, protection_element_id:-2714], primaryKey: false);
      insert('organizations', [ id: 102700, nci_institute_code: "FL223", name: "Bay Medical Center", city: "Panama City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL223",GROUP_DESC:"FL223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3715 ,protection_group_id: -2715, protection_element_id:-2715], primaryKey: false);
      insert('organizations', [ id: 102701, nci_institute_code: "FL224", name: "Martin Memorial Hospital - South", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL224",GROUP_DESC:"FL224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3716 ,protection_group_id: -2716, protection_element_id:-2716], primaryKey: false);
      insert('organizations', [ id: 102702, nci_institute_code: "FL225", name: "Hematology Oncology Associates", city: "Lake Worth", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL225",GROUP_DESC:"FL225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3717 ,protection_group_id: -2717, protection_element_id:-2717], primaryKey: false);
      insert('organizations', [ id: 102703, nci_institute_code: "FL226", name: "Suncoast Medical Clinic", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL226",GROUP_DESC:"FL226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3718 ,protection_group_id: -2718, protection_element_id:-2718], primaryKey: false);
      insert('organizations', [ id: 102704, nci_institute_code: "FL227", name: "Coastal Oncology", city: "Jackson Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL227",GROUP_DESC:"FL227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3719 ,protection_group_id: -2719, protection_element_id:-2719], primaryKey: false);
      insert('organizations', [ id: 102705, nci_institute_code: "FL228", name: "Oncology Hematology Associates of West Broward", city: "Tamarac", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL228",GROUP_DESC:"FL228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3720 ,protection_group_id: -2720, protection_element_id:-2720], primaryKey: false);
      insert('organizations', [ id: 102706, nci_institute_code: "FL229", name: "West Side Regional", city: "Plantation", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL229",GROUP_DESC:"FL229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3721 ,protection_group_id: -2721, protection_element_id:-2721], primaryKey: false);
      insert('organizations', [ id: 102707, nci_institute_code: "FL230", name: "Radiation Therapy Associates, P.A.", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL230",GROUP_DESC:"FL230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3722 ,protection_group_id: -2722, protection_element_id:-2722], primaryKey: false);
      insert('organizations', [ id: 102708, nci_institute_code: "FL231", name: "Martin Memorial Medical Center - North", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL231",GROUP_DESC:"FL231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3723 ,protection_group_id: -2723, protection_element_id:-2723], primaryKey: false);
      insert('organizations', [ id: 102709, nci_institute_code: "FL233", name: "Impact Center of South Broward", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL233",GROUP_DESC:"FL233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3724 ,protection_group_id: -2724, protection_element_id:-2724], primaryKey: false);
      insert('organizations', [ id: 102710, nci_institute_code: "FL234", name: "Mid-Florida Hematology & Oncology Centers PA", city: "Orange City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL234",GROUP_DESC:"FL234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3725 ,protection_group_id: -2725, protection_element_id:-2725], primaryKey: false);
      insert('organizations', [ id: 102711, nci_institute_code: "FL235", name: "South Florida Oncology-Hematology", city: "Pembroke Pines", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL235",GROUP_DESC:"FL235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3726 ,protection_group_id: -2726, protection_element_id:-2726], primaryKey: false);
      insert('organizations', [ id: 102712, nci_institute_code: "FL236", name: "Martin Memorial Cancer Center", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL236",GROUP_DESC:"FL236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3727 ,protection_group_id: -2727, protection_element_id:-2727], primaryKey: false);
      insert('organizations', [ id: 102713, nci_institute_code: "FL237", name: "Cleveland Clinic Hospital", city: "Weston", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL237",GROUP_DESC:"FL237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3728 ,protection_group_id: -2728, protection_element_id:-2728], primaryKey: false);
      insert('organizations', [ id: 102714, nci_institute_code: "FL238", name: "Oncology and Hematology Consultants", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL238",GROUP_DESC:"FL238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3729 ,protection_group_id: -2729, protection_element_id:-2729], primaryKey: false);
      insert('organizations', [ id: 102715, nci_institute_code: "FL239", name: "Clarke and Daughtrey Medical Group, PA", city: "Lakeland", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL239",GROUP_DESC:"FL239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3730 ,protection_group_id: -2730, protection_element_id:-2730], primaryKey: false);
      insert('organizations', [ id: 102716, nci_institute_code: "FL241", name: "Hematology and Oncology Associates of Treasure Coast", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL241",GROUP_DESC:"FL241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3731 ,protection_group_id: -2731, protection_element_id:-2731], primaryKey: false);
      insert('organizations', [ id: 102717, nci_institute_code: "FL242", name: "Saint Luke's Hospital", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL242",GROUP_DESC:"FL242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3732 ,protection_group_id: -2732, protection_element_id:-2732], primaryKey: false);
      insert('organizations', [ id: 102718, nci_institute_code: "FL244", name: "Surgery Group of South Florida", city: "Plantation", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL244",GROUP_DESC:"FL244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3733 ,protection_group_id: -2733, protection_element_id:-2733], primaryKey: false);
      insert('organizations', [ id: 102719, nci_institute_code: "FL245", name: "Florida Cancer Specialists - Bonita Springs", city: "Bonita Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL245",GROUP_DESC:"FL245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3734 ,protection_group_id: -2734, protection_element_id:-2734], primaryKey: false);
      insert('organizations', [ id: 102720, nci_institute_code: "FL247", name: "South Florida Bone Marrow Stem Cell Transplant", city: "Boynton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL247",GROUP_DESC:"FL247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3735 ,protection_group_id: -2735, protection_element_id:-2735], primaryKey: false);
      insert('organizations', [ id: 102721, nci_institute_code: "FL248", name: "Cancer Research Network Inc", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL248",GROUP_DESC:"FL248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3736 ,protection_group_id: -2736, protection_element_id:-2736], primaryKey: false);
      insert('organizations', [ id: 102722, nci_institute_code: "FL249", name: "Women's Cancer Association", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL249",GROUP_DESC:"FL249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3737 ,protection_group_id: -2737, protection_element_id:-2737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 102723, nci_institute_code: "FL250", name: "Montgomery and Associates, M.D., P.A.", city: "St. Augustine", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL250",GROUP_DESC:"FL250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3738 ,protection_group_id: -2738, protection_element_id:-2738], primaryKey: false);
      insert('organizations', [ id: 102724, nci_institute_code: "FL251", name: "University of South Florida College of Medicine", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL251",GROUP_DESC:"FL251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3739 ,protection_group_id: -2739, protection_element_id:-2739], primaryKey: false);
      insert('organizations', [ id: 102725, nci_institute_code: "FL252", name: "21st Century Oncology", city: "Port Charlotte", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL252",GROUP_DESC:"FL252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3740 ,protection_group_id: -2740, protection_element_id:-2740], primaryKey: false);
      insert('organizations', [ id: 102726, nci_institute_code: "FL254", name: "Surgical Group of Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL254",GROUP_DESC:"FL254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3741 ,protection_group_id: -2741, protection_element_id:-2741], primaryKey: false);
      insert('organizations', [ id: 102727, nci_institute_code: "FL255", name: "Breast Care Center of the Treasure Coast", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL255",GROUP_DESC:"FL255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3742 ,protection_group_id: -2742, protection_element_id:-2742], primaryKey: false);
      insert('organizations', [ id: 102728, nci_institute_code: "FL257", name: "Northwest Oncology Hematology Associates", city: "Coral Spring", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL257",GROUP_DESC:"FL257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3743 ,protection_group_id: -2743, protection_element_id:-2743], primaryKey: false);
      insert('organizations', [ id: 102729, nci_institute_code: "FL258", name: "Florida Cancer Specialists - Port Charlotte", city: "Port Charlotte", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL258",GROUP_DESC:"FL258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3744 ,protection_group_id: -2744, protection_element_id:-2744], primaryKey: false);
      insert('organizations', [ id: 102730, nci_institute_code: "FL259", name: "South Florida Oncology Hematology", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL259",GROUP_DESC:"FL259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3745 ,protection_group_id: -2745, protection_element_id:-2745], primaryKey: false);
      insert('organizations', [ id: 102731, nci_institute_code: "FL260", name: "South Florida Oncology Hematology", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL260",GROUP_DESC:"FL260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3746 ,protection_group_id: -2746, protection_element_id:-2746], primaryKey: false);
      insert('organizations', [ id: 102732, nci_institute_code: "FL261", name: "Associates in Cancer Care", city: "Ft. Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL261",GROUP_DESC:"FL261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3747 ,protection_group_id: -2747, protection_element_id:-2747], primaryKey: false);
      insert('organizations', [ id: 102733, nci_institute_code: "FL262", name: "Urology Treatment of Pinellas", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL262",GROUP_DESC:"FL262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3748 ,protection_group_id: -2748, protection_element_id:-2748], primaryKey: false);
      insert('organizations', [ id: 102734, nci_institute_code: "FL263", name: "Integrated Community Oncology Network- St Vincents", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL263",GROUP_DESC:"FL263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3749 ,protection_group_id: -2749, protection_element_id:-2749], primaryKey: false);
      insert('organizations', [ id: 102735, nci_institute_code: "FL264", name: "Memorial Hospital West", city: "Pembroke Pines", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL264",GROUP_DESC:"FL264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3750 ,protection_group_id: -2750, protection_element_id:-2750], primaryKey: false);
      insert('organizations', [ id: 102736, nci_institute_code: "FL265", name: "21st Century Oncology", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL265",GROUP_DESC:"FL265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3751 ,protection_group_id: -2751, protection_element_id:-2751], primaryKey: false);
      insert('organizations', [ id: 102737, nci_institute_code: "FL266", name: "Florida Cancer Specialists - Naples Pine Ridge", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL266",GROUP_DESC:"FL266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3752 ,protection_group_id: -2752, protection_element_id:-2752], primaryKey: false);
      insert('organizations', [ id: 102738, nci_institute_code: "FL269", name: "Southeast Florida Hematology/Oncology Group", city: "Ft Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL269",GROUP_DESC:"FL269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3753 ,protection_group_id: -2753, protection_element_id:-2753], primaryKey: false);
      insert('organizations', [ id: 102739, nci_institute_code: "FL270", name: "IVAX Research, Inc.", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL270",GROUP_DESC:"FL270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3754 ,protection_group_id: -2754, protection_element_id:-2754], primaryKey: false);
      insert('organizations', [ id: 102740, nci_institute_code: "FL271", name: "Integrated Community Oncology Network - Cancer Center of Putnam", city: "Palatka", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL271",GROUP_DESC:"FL271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3755 ,protection_group_id: -2755, protection_element_id:-2755], primaryKey: false);
      insert('organizations', [ id: 102741, nci_institute_code: "FL272", name: "North Broward Medical Center", city: "Deerfield Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL272",GROUP_DESC:"FL272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3756 ,protection_group_id: -2756, protection_element_id:-2756], primaryKey: false);
      insert('organizations', [ id: 102742, nci_institute_code: "FL273", name: "Suncoast Internal Medical Consultants", city: "Largo", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL273",GROUP_DESC:"FL273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3757 ,protection_group_id: -2757, protection_element_id:-2757], primaryKey: false);
      insert('organizations', [ id: 102743, nci_institute_code: "FL274", name: "Tampa Bay Oncology-Hematology Associates", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL274",GROUP_DESC:"FL274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3758 ,protection_group_id: -2758, protection_element_id:-2758], primaryKey: false);
      insert('organizations', [ id: 102744, nci_institute_code: "FL275", name: "Community Cancer Center of Northern Florida", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL275",GROUP_DESC:"FL275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3759 ,protection_group_id: -2759, protection_element_id:-2759], primaryKey: false);
      insert('organizations', [ id: 102745, nci_institute_code: "FL276", name: "Colon & Rectal Clinic of Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL276",GROUP_DESC:"FL276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3760 ,protection_group_id: -2760, protection_element_id:-2760], primaryKey: false);
      insert('organizations', [ id: 102746, nci_institute_code: "FL277", name: "Osceola Cancer Center", city: "Kissimmee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL277",GROUP_DESC:"FL277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3761 ,protection_group_id: -2761, protection_element_id:-2761], primaryKey: false);
      insert('organizations', [ id: 102747, nci_institute_code: "FL278", name: "21st Century Oncology - Plantation", city: "Plantation", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL278",GROUP_DESC:"FL278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3762 ,protection_group_id: -2762, protection_element_id:-2762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 102748, nci_institute_code: "FL279", name: "Women's Health Specialists", city: "Jenson Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL279",GROUP_DESC:"FL279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3763 ,protection_group_id: -2763, protection_element_id:-2763], primaryKey: false);
      insert('organizations', [ id: 102749, nci_institute_code: "FL280", name: "Cardiac Surgery Associates of Tampa", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL280",GROUP_DESC:"FL280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3764 ,protection_group_id: -2764, protection_element_id:-2764], primaryKey: false);
      insert('organizations', [ id: 102750, nci_institute_code: "FL281", name: "South Florida Oncology & Hematology Consultants", city: "Lauderhill", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL281",GROUP_DESC:"FL281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3765 ,protection_group_id: -2765, protection_element_id:-2765], primaryKey: false);
      insert('organizations', [ id: 102751, nci_institute_code: "FL282", name: "Center for Cancer Care & Research", city: "Lakeland", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL282",GROUP_DESC:"FL282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3766 ,protection_group_id: -2766, protection_element_id:-2766], primaryKey: false);
      insert('organizations', [ id: 102752, nci_institute_code: "FL283", name: "Florida Cancer Specialists - Venice Healthpark", city: "Venice", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL283",GROUP_DESC:"FL283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3767 ,protection_group_id: -2767, protection_element_id:-2767], primaryKey: false);
      insert('organizations', [ id: 102753, nci_institute_code: "FL284", name: "Robert R. Carroll, M.D., P.A.Inc", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL284",GROUP_DESC:"FL284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3768 ,protection_group_id: -2768, protection_element_id:-2768], primaryKey: false);
      insert('organizations', [ id: 102754, nci_institute_code: "FL285", name: "Shands Jacksonville", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL285",GROUP_DESC:"FL285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3769 ,protection_group_id: -2769, protection_element_id:-2769], primaryKey: false);
      insert('organizations', [ id: 102755, nci_institute_code: "FL286", name: "Florida Hospital Cancer Institute Waterman", city: "Tavares", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL286",GROUP_DESC:"FL286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3770 ,protection_group_id: -2770, protection_element_id:-2770], primaryKey: false);
      insert('organizations', [ id: 102756, nci_institute_code: "FL287", name: "Florida Cancer Institute - Hudson", city: "Hudson", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL287",GROUP_DESC:"FL287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3771 ,protection_group_id: -2771, protection_element_id:-2771], primaryKey: false);
      insert('organizations', [ id: 102757, nci_institute_code: "FL288", name: "Palm Beach Cancer Institute-Main Office", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL288",GROUP_DESC:"FL288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3772 ,protection_group_id: -2772, protection_element_id:-2772], primaryKey: false);
      insert('organizations', [ id: 102758, nci_institute_code: "FL289", name: "Perinatal and Gynecologic Specialists of the Palm Beaches Inc", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL289",GROUP_DESC:"FL289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3773 ,protection_group_id: -2773, protection_element_id:-2773], primaryKey: false);
      insert('organizations', [ id: 102759, nci_institute_code: "FL290", name: "Laparoscopic Center of South Florida", city: "Coral Gables", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL290",GROUP_DESC:"FL290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3774 ,protection_group_id: -2774, protection_element_id:-2774], primaryKey: false);
      insert('organizations', [ id: 102760, nci_institute_code: "FL291", name: "Martin Luther King Clinic", city: "Homestead", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL291",GROUP_DESC:"FL291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3775 ,protection_group_id: -2775, protection_element_id:-2775], primaryKey: false);
      insert('organizations', [ id: 102761, nci_institute_code: "FL292", name: "Surgical Associates of West Florida", city: "Safety Harbor", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL292",GROUP_DESC:"FL292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3776 ,protection_group_id: -2776, protection_element_id:-2776], primaryKey: false);
      insert('organizations', [ id: 102762, nci_institute_code: "FL293", name: "Oncology Physicians", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL293",GROUP_DESC:"FL293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3777 ,protection_group_id: -2777, protection_element_id:-2777], primaryKey: false);
      insert('organizations', [ id: 102763, nci_institute_code: "FL294", name: "MetCare Oncology", city: "Ormond Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL294",GROUP_DESC:"FL294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3778 ,protection_group_id: -2778, protection_element_id:-2778], primaryKey: false);
      insert('organizations', [ id: 102764, nci_institute_code: "FL295", name: "Florida Radiation Oncology Group", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL295",GROUP_DESC:"FL295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3779 ,protection_group_id: -2779, protection_element_id:-2779], primaryKey: false);
      insert('organizations', [ id: 102765, nci_institute_code: "FL296", name: "Cancer Care Centers of Florida", city: "Hudson", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL296",GROUP_DESC:"FL296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3780 ,protection_group_id: -2780, protection_element_id:-2780], primaryKey: false);
      insert('organizations', [ id: 102766, nci_institute_code: "FL297", name: "Comprehensive Gynecologic Oncology, PA", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL297",GROUP_DESC:"FL297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3781 ,protection_group_id: -2781, protection_element_id:-2781], primaryKey: false);
      insert('organizations', [ id: 102767, nci_institute_code: "FL298", name: "Fawcett Memorial Hospital", city: "Port Charlotte", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL298",GROUP_DESC:"FL298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3782 ,protection_group_id: -2782, protection_element_id:-2782], primaryKey: false);
      insert('organizations', [ id: 102768, nci_institute_code: "FL299", name: "Florida Cancer Specialists - Sarasota Downtown", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL299",GROUP_DESC:"FL299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3783 ,protection_group_id: -2783, protection_element_id:-2783], primaryKey: false);
      insert('organizations', [ id: 102769, nci_institute_code: "FL300", name: "Radiation Oncology Centers", city: "Port Charlotte", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL300",GROUP_DESC:"FL300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3784 ,protection_group_id: -2784, protection_element_id:-2784], primaryKey: false);
      insert('organizations', [ id: 102770, nci_institute_code: "FL301", name: "Florida Cancer Specialists - Sarasota", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL301",GROUP_DESC:"FL301 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL301",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL301",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL301", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3785 ,protection_group_id: -2785, protection_element_id:-2785], primaryKey: false);
      insert('organizations', [ id: 102771, nci_institute_code: "FL302", name: "Florida Cancer Specialists - Venice Island", city: "Venice", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL302",GROUP_DESC:"FL302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3786 ,protection_group_id: -2786, protection_element_id:-2786], primaryKey: false);
      insert('organizations', [ id: 102772, nci_institute_code: "FL303", name: "Lee Cancer Clinic", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL303",GROUP_DESC:"FL303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3787 ,protection_group_id: -2787, protection_element_id:-2787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 102773, nci_institute_code: "FL304", name: "Palms West Radiation Therapy", city: "Loxahatchee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL304",GROUP_DESC:"FL304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3788 ,protection_group_id: -2788, protection_element_id:-2788], primaryKey: false);
      insert('organizations', [ id: 102774, nci_institute_code: "FL305", name: "Florida Cancer Specialists - Cape Coral", city: "Cape Coral", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL305",GROUP_DESC:"FL305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3789 ,protection_group_id: -2789, protection_element_id:-2789], primaryKey: false);
      insert('organizations', [ id: 102775, nci_institute_code: "FL306", name: "Gulf Coast Oncology Associates", city: "Largo", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL306",GROUP_DESC:"FL306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3790 ,protection_group_id: -2790, protection_element_id:-2790], primaryKey: false);
      insert('organizations', [ id: 102776, nci_institute_code: "FL307", name: "Florida Cancer Specialists - Bradenton", city: "Bradenton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL307",GROUP_DESC:"FL307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3791 ,protection_group_id: -2791, protection_element_id:-2791], primaryKey: false);
      insert('organizations', [ id: 102777, nci_institute_code: "FL308", name: "Gulfcoast Oncology Associates", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL308",GROUP_DESC:"FL308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3792 ,protection_group_id: -2792, protection_element_id:-2792], primaryKey: false);
      insert('organizations', [ id: 102778, nci_institute_code: "FL309", name: "Doral Oncology Center", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL309",GROUP_DESC:"FL309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3793 ,protection_group_id: -2793, protection_element_id:-2793], primaryKey: false);
      insert('organizations', [ id: 102779, nci_institute_code: "FL310", name: "Cleveland Clinic Florida - Weston", city: "Weston", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL310",GROUP_DESC:"FL310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3794 ,protection_group_id: -2794, protection_element_id:-2794], primaryKey: false);
      insert('organizations', [ id: 102780, nci_institute_code: "FL311", name: "Hope Medical Group", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL311",GROUP_DESC:"FL311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3795 ,protection_group_id: -2795, protection_element_id:-2795], primaryKey: false);
      insert('organizations', [ id: 102781, nci_institute_code: "FL312", name: "Florida Hematology and Oncology Specialists, PA", city: "Altamonte Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL312",GROUP_DESC:"FL312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3796 ,protection_group_id: -2796, protection_element_id:-2796], primaryKey: false);
      insert('organizations', [ id: 102782, nci_institute_code: "FL313", name: "Florida Hematology and Oncology Specialists, PA-Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL313",GROUP_DESC:"FL313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3797 ,protection_group_id: -2797, protection_element_id:-2797], primaryKey: false);
      insert('organizations', [ id: 102783, nci_institute_code: "FL314", name: "Surgical Associates of Volusia", city: "Daytona Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL314",GROUP_DESC:"FL314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3798 ,protection_group_id: -2798, protection_element_id:-2798], primaryKey: false);
      insert('organizations', [ id: 102784, nci_institute_code: "FL315", name: "The Center for Hematology/Oncology at Boca Raton", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL315",GROUP_DESC:"FL315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3799 ,protection_group_id: -2799, protection_element_id:-2799], primaryKey: false);
      insert('organizations', [ id: 102785, nci_institute_code: "FL316", name: "Cancer Institute of Florida, PA", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL316",GROUP_DESC:"FL316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3800 ,protection_group_id: -2800, protection_element_id:-2800], primaryKey: false);
      insert('organizations', [ id: 102786, nci_institute_code: "FL317", name: "Advanced Medical Specialties", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL317",GROUP_DESC:"FL317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3801 ,protection_group_id: -2801, protection_element_id:-2801], primaryKey: false);
      insert('organizations', [ id: 102787, nci_institute_code: "FL318", name: "Cancer Care Center", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL318",GROUP_DESC:"FL318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3802 ,protection_group_id: -2802, protection_element_id:-2802], primaryKey: false);
      insert('organizations', [ id: 102788, nci_institute_code: "FL319", name: "Broward Oncology Associates", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL319",GROUP_DESC:"FL319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3803 ,protection_group_id: -2803, protection_element_id:-2803], primaryKey: false);
      insert('organizations', [ id: 102789, nci_institute_code: "FL320", name: "Mount Sinai Comprehensive Cancer Center", city: "Miami Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL320",GROUP_DESC:"FL320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3804 ,protection_group_id: -2804, protection_element_id:-2804], primaryKey: false);
      insert('organizations', [ id: 102790, nci_institute_code: "FL321", name: "Harbor Branch Ocean", city: "Ft. Pierce", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL321",GROUP_DESC:"FL321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3805 ,protection_group_id: -2805, protection_element_id:-2805], primaryKey: false);
      insert('organizations', [ id: 102791, nci_institute_code: "FL322", name: "Cancer Institute of Florida, PA", city: "Celebration", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL322",GROUP_DESC:"FL322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3806 ,protection_group_id: -2806, protection_element_id:-2806], primaryKey: false);
      insert('organizations', [ id: 102792, nci_institute_code: "FL323", name: "Bay Oncology Center", city: "Panama City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL323",GROUP_DESC:"FL323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3807 ,protection_group_id: -2807, protection_element_id:-2807], primaryKey: false);
      insert('organizations', [ id: 102793, nci_institute_code: "FL324", name: "Naples Community Hospital Regional Cancer Institute - East", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL324",GROUP_DESC:"FL324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3808 ,protection_group_id: -2808, protection_element_id:-2808], primaryKey: false);
      insert('organizations', [ id: 102794, nci_institute_code: "FL325", name: "Naples Community Hospital Regional Cancer Institute - West", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL325",GROUP_DESC:"FL325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3809 ,protection_group_id: -2809, protection_element_id:-2809], primaryKey: false);
      insert('organizations', [ id: 102795, nci_institute_code: "FL326", name: "21st Century Oncology - Coral Springs", city: "Coral Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL326",GROUP_DESC:"FL326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3810 ,protection_group_id: -2810, protection_element_id:-2810], primaryKey: false);
      insert('organizations', [ id: 102796, nci_institute_code: "FL327", name: "21st Century Oncology - Arcadia", city: "Arcadia", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL327",GROUP_DESC:"FL327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3811 ,protection_group_id: -2811, protection_element_id:-2811], primaryKey: false);
      insert('organizations', [ id: 102797, nci_institute_code: "FL328", name: "21st Century Oncology - Naples", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL328",GROUP_DESC:"FL328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3812 ,protection_group_id: -2812, protection_element_id:-2812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 102798, nci_institute_code: "FL329", name: "21st Century Oncology - Englewood", city: "Englewood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL329",GROUP_DESC:"FL329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3813 ,protection_group_id: -2813, protection_element_id:-2813], primaryKey: false);
      insert('organizations', [ id: 102799, nci_institute_code: "FL330", name: "21st Century Oncology - Cape Coral", city: "Cape Coral", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL330",GROUP_DESC:"FL330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3814 ,protection_group_id: -2814, protection_element_id:-2814], primaryKey: false);
      insert('organizations', [ id: 102800, nci_institute_code: "FL331", name: "21st Century Oncology - Deerfield Beach", city: "Deerfield Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL331",GROUP_DESC:"FL331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3815 ,protection_group_id: -2815, protection_element_id:-2815], primaryKey: false);
      insert('organizations', [ id: 102801, nci_institute_code: "FL332", name: "21st Century Oncology - Sarasota", city: "Sarasota", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL332",GROUP_DESC:"FL332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3816 ,protection_group_id: -2816, protection_element_id:-2816], primaryKey: false);
      insert('organizations', [ id: 102802, nci_institute_code: "FL333", name: "21st Century Oncology - Venice", city: "Venice", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL333",GROUP_DESC:"FL333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3817 ,protection_group_id: -2817, protection_element_id:-2817], primaryKey: false);
      insert('organizations', [ id: 102803, nci_institute_code: "FL334", name: "Coastal Oncology", city: "Ormond Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL334",GROUP_DESC:"FL334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3818 ,protection_group_id: -2818, protection_element_id:-2818], primaryKey: false);
      insert('organizations', [ id: 102804, nci_institute_code: "FL335", name: "Integrated Community Oncology Network- Central Business Office", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL335",GROUP_DESC:"FL335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3819 ,protection_group_id: -2819, protection_element_id:-2819], primaryKey: false);
      insert('organizations', [ id: 102805, nci_institute_code: "FL336", name: "Integrated Community Oncology Network - Flagler Cancer Center", city: "Saint Augustine", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL336",GROUP_DESC:"FL336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3820 ,protection_group_id: -2820, protection_element_id:-2820], primaryKey: false);
      insert('organizations', [ id: 102806, nci_institute_code: "FL337", name: "Florida Gynecologic Oncology", city: "Bonita Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL337",GROUP_DESC:"FL337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3821 ,protection_group_id: -2821, protection_element_id:-2821], primaryKey: false);
      insert('organizations', [ id: 102807, nci_institute_code: "FL338", name: "Florida Gynecologic Oncology", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL338",GROUP_DESC:"FL338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3822 ,protection_group_id: -2822, protection_element_id:-2822], primaryKey: false);
      insert('organizations', [ id: 102808, nci_institute_code: "FL339", name: "Nemours Children's Clinic - Pensacola", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL339",GROUP_DESC:"FL339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3823 ,protection_group_id: -2823, protection_element_id:-2823], primaryKey: false);
      insert('organizations', [ id: 102809, nci_institute_code: "FL340", name: "North Bay Surgical Associates", city: "New Port Richey", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL340",GROUP_DESC:"FL340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3824 ,protection_group_id: -2824, protection_element_id:-2824], primaryKey: false);
      insert('organizations', [ id: 102810, nci_institute_code: "FL341", name: "Integrated Community Oncology Network- Baptist Medical Center South", city: "Jascksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL341",GROUP_DESC:"FL341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3825 ,protection_group_id: -2825, protection_element_id:-2825], primaryKey: false);
      insert('organizations', [ id: 102811, nci_institute_code: "FL342", name: "Regional Consultants in Hematology and Oncology", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL342",GROUP_DESC:"FL342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3826 ,protection_group_id: -2826, protection_element_id:-2826], primaryKey: false);
      insert('organizations', [ id: 102812, nci_institute_code: "FL343", name: "Ear Nose and Throat Specialists of Florida", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL343",GROUP_DESC:"FL343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3827 ,protection_group_id: -2827, protection_element_id:-2827], primaryKey: false);
      insert('organizations', [ id: 102813, nci_institute_code: "FL344", name: "Florida Cancer Specialists - Fort Myers South", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL344",GROUP_DESC:"FL344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3828 ,protection_group_id: -2828, protection_element_id:-2828], primaryKey: false);
      insert('organizations', [ id: 102814, nci_institute_code: "FL345", name: "The Colorectal Institute", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL345",GROUP_DESC:"FL345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3829 ,protection_group_id: -2829, protection_element_id:-2829], primaryKey: false);
      insert('organizations', [ id: 102815, nci_institute_code: "FL346", name: "Heart Lung Surgical Institute", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL346",GROUP_DESC:"FL346 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL346",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL346",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL346", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3830 ,protection_group_id: -2830, protection_element_id:-2830], primaryKey: false);
      insert('organizations', [ id: 102816, nci_institute_code: "FL347", name: "Clearwater Hematology and Oncology Associates PA", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL347",GROUP_DESC:"FL347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3831 ,protection_group_id: -2831, protection_element_id:-2831], primaryKey: false);
      insert('organizations', [ id: 102817, nci_institute_code: "FL348", name: "Cardiovascular and Thoracic Surgeons of Greater Fort Lauderdale LLP", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL348",GROUP_DESC:"FL348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3832 ,protection_group_id: -2832, protection_element_id:-2832], primaryKey: false);
      insert('organizations', [ id: 102818, nci_institute_code: "FL349", name: "Lynn Regional Cancer Center Delray", city: "Delray Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL349",GROUP_DESC:"FL349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3833 ,protection_group_id: -2833, protection_element_id:-2833], primaryKey: false);
      insert('organizations', [ id: 102819, nci_institute_code: "FL350", name: "Southeast Gynecologic Oncology Associates", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL350",GROUP_DESC:"FL350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3834 ,protection_group_id: -2834, protection_element_id:-2834], primaryKey: false);
      insert('organizations', [ id: 102820, nci_institute_code: "FL351", name: "Palm Beach Cancer Institute", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL351",GROUP_DESC:"FL351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3835 ,protection_group_id: -2835, protection_element_id:-2835], primaryKey: false);
      insert('organizations', [ id: 102821, nci_institute_code: "FL352", name: "Vero Beach Hematology Oncology", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL352",GROUP_DESC:"FL352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3836 ,protection_group_id: -2836, protection_element_id:-2836], primaryKey: false);
      insert('organizations', [ id: 102822, nci_institute_code: "FL353", name: "Lake County Oncology and Hematology PA", city: "Tavares", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL353",GROUP_DESC:"FL353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3837 ,protection_group_id: -2837, protection_element_id:-2837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 102823, nci_institute_code: "FL354", name: "Integrated Community Oncology Network", city: "Ponte Vedra Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL354",GROUP_DESC:"FL354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3838 ,protection_group_id: -2838, protection_element_id:-2838], primaryKey: false);
      insert('organizations', [ id: 102824, nci_institute_code: "FL355", name: "Bone Marrow Transplant Center", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL355",GROUP_DESC:"FL355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3839 ,protection_group_id: -2839, protection_element_id:-2839], primaryKey: false);
      insert('organizations', [ id: 102825, nci_institute_code: "FL356", name: "Pediatric Hematology-Oncology Associates", city: "Saint Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL356",GROUP_DESC:"FL356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3840 ,protection_group_id: -2840, protection_element_id:-2840], primaryKey: false);
      insert('organizations', [ id: 102826, nci_institute_code: "FL357", name: "Tampa Bay Area Cancer Consultants P A", city: "Palm Harbor", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL357",GROUP_DESC:"FL357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3841 ,protection_group_id: -2841, protection_element_id:-2841], primaryKey: false);
      insert('organizations', [ id: 102827, nci_institute_code: "FL358", name: "Mount Sinai Comprehensive Cancer Center at Aventura", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL358",GROUP_DESC:"FL358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3842 ,protection_group_id: -2842, protection_element_id:-2842], primaryKey: false);
      insert('organizations', [ id: 102828, nci_institute_code: "FL359", name: "Gulfcoast Oncology Associates", city: "Safety Harbor", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL359",GROUP_DESC:"FL359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3843 ,protection_group_id: -2843, protection_element_id:-2843], primaryKey: false);
      insert('organizations', [ id: 102829, nci_institute_code: "FL360", name: "21 Century Oncology-Lehigh Acres", city: "Lehigh Acres", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL360",GROUP_DESC:"FL360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3844 ,protection_group_id: -2844, protection_element_id:-2844], primaryKey: false);
      insert('organizations', [ id: 102830, nci_institute_code: "FL361", name: "Countryside Cancer Center", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL361",GROUP_DESC:"FL361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3845 ,protection_group_id: -2845, protection_element_id:-2845], primaryKey: false);
      insert('organizations', [ id: 102831, nci_institute_code: "FL362", name: "Memorial Regional Hospital", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL362",GROUP_DESC:"FL362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3846 ,protection_group_id: -2846, protection_element_id:-2846], primaryKey: false);
      insert('organizations', [ id: 102832, nci_institute_code: "FL363", name: "Leopoldo B Gonzalez MD PA", city: "Saint Augustine", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL363",GROUP_DESC:"FL363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3847 ,protection_group_id: -2847, protection_element_id:-2847], primaryKey: false);
      insert('organizations', [ id: 102833, nci_institute_code: "FL364", name: "West Coast Gynecology", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL364",GROUP_DESC:"FL364 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL364",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL364",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL364", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3848 ,protection_group_id: -2848, protection_element_id:-2848], primaryKey: false);
      insert('organizations', [ id: 102834, nci_institute_code: "FL365", name: "Saint Joseph's Hospital", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL365",GROUP_DESC:"FL365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3849 ,protection_group_id: -2849, protection_element_id:-2849], primaryKey: false);
      insert('organizations', [ id: 102835, nci_institute_code: "FL366", name: "Jupiter Hematology and Oncology Associates", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL366",GROUP_DESC:"FL366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3850 ,protection_group_id: -2850, protection_element_id:-2850], primaryKey: false);
      insert('organizations', [ id: 102836, nci_institute_code: "FL367", name: "Integrated Community Oncology Network - Florida Cancer Center - Beaches", city: "Jacksonville Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL367",GROUP_DESC:"FL367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3851 ,protection_group_id: -2851, protection_element_id:-2851], primaryKey: false);
      insert('organizations', [ id: 102837, nci_institute_code: "FL368", name: "Cardiac Surgical Associates-St Petersburg-Northside", city: "St. Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL368",GROUP_DESC:"FL368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3852 ,protection_group_id: -2852, protection_element_id:-2852], primaryKey: false);
      insert('organizations', [ id: 102838, nci_institute_code: "FL369", name: "Southeastern Surgical Group", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL369",GROUP_DESC:"FL369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3853 ,protection_group_id: -2853, protection_element_id:-2853], primaryKey: false);
      insert('organizations', [ id: 102839, nci_institute_code: "FL370", name: "South Florida Oncology and Hematology", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL370",GROUP_DESC:"FL370 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL370",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL370",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL370", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3854 ,protection_group_id: -2854, protection_element_id:-2854], primaryKey: false);
      insert('organizations', [ id: 102840, nci_institute_code: "FL371", name: "University of Florida Proton Therapy Institute", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL371",GROUP_DESC:"FL371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3855 ,protection_group_id: -2855, protection_element_id:-2855], primaryKey: false);
      insert('organizations', [ id: 102841, nci_institute_code: "FL372", name: "Aventura Comprehensive Cancer Center", city: "Aventura", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL372",GROUP_DESC:"FL372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3856 ,protection_group_id: -2856, protection_element_id:-2856], primaryKey: false);
      insert('organizations', [ id: 102842, nci_institute_code: "FL373", name: "The Center for Hematology Oncology Boynton Beach", city: "Boynton Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL373",GROUP_DESC:"FL373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3857 ,protection_group_id: -2857, protection_element_id:-2857], primaryKey: false);
      insert('organizations', [ id: 102843, nci_institute_code: "FL374", name: "Urologic Specialists PA", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL374",GROUP_DESC:"FL374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3858 ,protection_group_id: -2858, protection_element_id:-2858], primaryKey: false);
      insert('organizations', [ id: 102844, nci_institute_code: "FL375", name: "21st Century Oncology - Bradenton", city: "Bradenton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL375",GROUP_DESC:"FL375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3859 ,protection_group_id: -2859, protection_element_id:-2859], primaryKey: false);
      insert('organizations', [ id: 102845, nci_institute_code: "FL376", name: "Florida Cancer Institute- Brooksville", city: "Spring Hill", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL376",GROUP_DESC:"FL376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3860 ,protection_group_id: -2860, protection_element_id:-2860], primaryKey: false);
      insert('organizations', [ id: 102846, nci_institute_code: "FL377", name: "Robert R Carroll MD PA", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL377",GROUP_DESC:"FL377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3861 ,protection_group_id: -2861, protection_element_id:-2861], primaryKey: false);
      insert('organizations', [ id: 102847, nci_institute_code: "FL378", name: "St Augustine Cancer Center", city: "St. Augustine", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL378",GROUP_DESC:"FL378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3862 ,protection_group_id: -2862, protection_element_id:-2862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 102848, nci_institute_code: "FL379", name: "Florida Cancer Institute - Altamonte Springs", city: "Altamonte Springs", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL379",GROUP_DESC:"FL379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3863 ,protection_group_id: -2863, protection_element_id:-2863], primaryKey: false);
      insert('organizations', [ id: 102849, nci_institute_code: "FL380", name: "Florida Cancer Institute-Spring Hill", city: "Spring Hill", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL380",GROUP_DESC:"FL380 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL380",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL380",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL380", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3864 ,protection_group_id: -2864, protection_element_id:-2864], primaryKey: false);
      insert('organizations', [ id: 102850, nci_institute_code: "FL381", name: "The Cancer Center Medical Oncology Group", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL381",GROUP_DESC:"FL381 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL381",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL381",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL381", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3865 ,protection_group_id: -2865, protection_element_id:-2865], primaryKey: false);
      insert('organizations', [ id: 102851, nci_institute_code: "FL382", name: "Treasure Coast Surgical Group PA", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL382",GROUP_DESC:"FL382 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL382",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL382",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL382", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3866 ,protection_group_id: -2866, protection_element_id:-2866], primaryKey: false);
      insert('organizations', [ id: 102852, nci_institute_code: "FL383", name: "Florida Cancer Research Institute", city: "Davie", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL383",GROUP_DESC:"FL383 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL383",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL383",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL383", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3867 ,protection_group_id: -2867, protection_element_id:-2867], primaryKey: false);
      insert('organizations', [ id: 102853, nci_institute_code: "FL384", name: "21st Century Oncology", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL384",GROUP_DESC:"FL384 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL384",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL384",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL384", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3868 ,protection_group_id: -2868, protection_element_id:-2868], primaryKey: false);
      insert('organizations', [ id: 102854, nci_institute_code: "FL385", name: "Space Coast Medical Associates - Merritt Island", city: "Merritt Island", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL385",GROUP_DESC:"FL385 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL385",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL385",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL385", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3869 ,protection_group_id: -2869, protection_element_id:-2869], primaryKey: false);
      insert('organizations', [ id: 102855, nci_institute_code: "FL386", name: "Headache and Headpain Center", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL386",GROUP_DESC:"FL386 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL386",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL386",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL386", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3870 ,protection_group_id: -2870, protection_element_id:-2870], primaryKey: false);
      insert('organizations', [ id: 102856, nci_institute_code: "FL387", name: "Richard D Kimmel DO FCCP PA", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL387",GROUP_DESC:"FL387 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL387",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL387",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL387", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3871 ,protection_group_id: -2871, protection_element_id:-2871], primaryKey: false);
      insert('organizations', [ id: 102857, nci_institute_code: "FL388", name: "Gulfcoast Oncology Associates", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL388",GROUP_DESC:"FL388 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL388",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL388",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL388", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3872 ,protection_group_id: -2872, protection_element_id:-2872], primaryKey: false);
      insert('organizations', [ id: 102858, nci_institute_code: "FL389", name: "Panhandle Cancer Center", city: "Panama City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL389",GROUP_DESC:"FL389 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL389",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL389",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL389", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3873 ,protection_group_id: -2873, protection_element_id:-2873], primaryKey: false);
      insert('organizations', [ id: 102859, nci_institute_code: "FL390", name: "Northwest Florida Hematology Oncology", city: "Panama City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL390",GROUP_DESC:"FL390 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL390",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL390",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL390", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3874 ,protection_group_id: -2874, protection_element_id:-2874], primaryKey: false);
      insert('organizations', [ id: 102860, nci_institute_code: "FL391", name: "Cardiac Surgical Associates-St Petersburg", city: "St Petersburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL391",GROUP_DESC:"FL391 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL391",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL391",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL391", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3875 ,protection_group_id: -2875, protection_element_id:-2875], primaryKey: false);
      insert('organizations', [ id: 102861, nci_institute_code: "FL392", name: "Suncoast Surgical Associates", city: "Brandon", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL392",GROUP_DESC:"FL392 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL392",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL392",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL392", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3876 ,protection_group_id: -2876, protection_element_id:-2876], primaryKey: false);
      insert('organizations', [ id: 102862, nci_institute_code: "FL393", name: "Florida Oncology Network PA", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL393",GROUP_DESC:"FL393 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL393",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL393",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL393", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3877 ,protection_group_id: -2877, protection_element_id:-2877], primaryKey: false);
      insert('organizations', [ id: 102863, nci_institute_code: "FL394", name: "Tallahassee Pulmonary Clinic PA", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL394",GROUP_DESC:"FL394 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL394",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL394",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL394", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3878 ,protection_group_id: -2878, protection_element_id:-2878], primaryKey: false);
      insert('organizations', [ id: 102864, nci_institute_code: "GA001", name: "Dekalb Medical Center", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA001",GROUP_DESC:"GA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3879 ,protection_group_id: -2879, protection_element_id:-2879], primaryKey: false);
      insert('organizations', [ id: 102865, nci_institute_code: "GA002", name: "Veterans Administration Medical Center", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA002",GROUP_DESC:"GA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3880 ,protection_group_id: -2880, protection_element_id:-2880], primaryKey: false);
      insert('organizations', [ id: 102866, nci_institute_code: "GA003", name: "Grady Health System", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA003",GROUP_DESC:"GA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3881 ,protection_group_id: -2881, protection_element_id:-2881], primaryKey: false);
      insert('organizations', [ id: 102867, nci_institute_code: "GA005", name: "Emory University", city: "Altanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA005",GROUP_DESC:"GA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3882 ,protection_group_id: -2882, protection_element_id:-2882], primaryKey: false);
      insert('organizations', [ id: 102868, nci_institute_code: "GA006", name: "Greater Memorial Hospital", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA006",GROUP_DESC:"GA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3883 ,protection_group_id: -2883, protection_element_id:-2883], primaryKey: false);
      insert('organizations', [ id: 102869, nci_institute_code: "GA007", name: "Georgia Baptist Medical Center", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA007",GROUP_DESC:"GA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3884 ,protection_group_id: -2884, protection_element_id:-2884], primaryKey: false);
      insert('organizations', [ id: 102870, nci_institute_code: "GA008", name: "Henrietta Egelston Hospital", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA008",GROUP_DESC:"GA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3885 ,protection_group_id: -2885, protection_element_id:-2885], primaryKey: false);
      insert('organizations', [ id: 102871, nci_institute_code: "GA010", name: "U. S. Army Hospital", city: "Fort Mcpherson", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA010",GROUP_DESC:"GA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3886 ,protection_group_id: -2886, protection_element_id:-2886], primaryKey: false);
      insert('organizations', [ id: 102872, nci_institute_code: "GA011", name: "Saint Joseph's Hospital of Atlanta", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA011",GROUP_DESC:"GA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3887 ,protection_group_id: -2887, protection_element_id:-2887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 102873, nci_institute_code: "GA012", name: "South Fulton Medical Center", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA012",GROUP_DESC:"GA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3888 ,protection_group_id: -2888, protection_element_id:-2888], primaryKey: false);
      insert('organizations', [ id: 102874, nci_institute_code: "GA013", name: "Crawford-Long Hospital of Emory University", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA013",GROUP_DESC:"GA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3889 ,protection_group_id: -2889, protection_element_id:-2889], primaryKey: false);
      insert('organizations', [ id: 102875, nci_institute_code: "GA015", name: "University of Georgia", city: "Athens", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA015",GROUP_DESC:"GA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3890 ,protection_group_id: -2890, protection_element_id:-2890], primaryKey: false);
      insert('organizations', [ id: 102876, nci_institute_code: "GA016", name: "Enoch Callaway Cancer Center", city: "Lagrange", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA016",GROUP_DESC:"GA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3891 ,protection_group_id: -2891, protection_element_id:-2891], primaryKey: false);
      insert('organizations', [ id: 102877, nci_institute_code: "GA017", name: "Eisenhower Army Medical Center", city: "Fort Gordon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA017",GROUP_DESC:"GA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3892 ,protection_group_id: -2892, protection_element_id:-2892], primaryKey: false);
      insert('organizations', [ id: 102878, nci_institute_code: "GA018", name: "University Hospital", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA018",GROUP_DESC:"GA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3893 ,protection_group_id: -2893, protection_element_id:-2893], primaryKey: false);
      insert('organizations', [ id: 102879, nci_institute_code: "GA019", name: "Veterans Administration Medical Center, Augusta", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA019",GROUP_DESC:"GA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3894 ,protection_group_id: -2894, protection_element_id:-2894], primaryKey: false);
      insert('organizations', [ id: 102880, nci_institute_code: "GA020", name: "Medical College of Georgia", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA020",GROUP_DESC:"GA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3895 ,protection_group_id: -2895, protection_element_id:-2895], primaryKey: false);
      insert('organizations', [ id: 102881, nci_institute_code: "GA021", name: "Taimadge Memorial Hospital", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA021",GROUP_DESC:"GA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3896 ,protection_group_id: -2896, protection_element_id:-2896], primaryKey: false);
      insert('organizations', [ id: 102882, nci_institute_code: "GA022", name: "Memorial Health University Medical Center", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA022",GROUP_DESC:"GA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3897 ,protection_group_id: -2897, protection_element_id:-2897], primaryKey: false);
      insert('organizations', [ id: 102883, nci_institute_code: "GA023", name: "Martin Hospital", city: "Fort Benning", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA023",GROUP_DESC:"GA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3898 ,protection_group_id: -2898, protection_element_id:-2898], primaryKey: false);
      insert('organizations', [ id: 102884, nci_institute_code: "GA024", name: "Northeast Georgia Medical Center", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA024",GROUP_DESC:"GA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3899 ,protection_group_id: -2899, protection_element_id:-2899], primaryKey: false);
      insert('organizations', [ id: 102885, nci_institute_code: "GA025", name: "Southern Regional Medical Center", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA025",GROUP_DESC:"GA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3900 ,protection_group_id: -2900, protection_element_id:-2900], primaryKey: false);
      insert('organizations', [ id: 102886, nci_institute_code: "GA026", name: "Children's Healthcare of Atlanta at Scottish Rite", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA026",GROUP_DESC:"GA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3901 ,protection_group_id: -2901, protection_element_id:-2901], primaryKey: false);
      insert('organizations', [ id: 102887, nci_institute_code: "GA027", name: "Piedmont Hospital", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA027",GROUP_DESC:"GA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3902 ,protection_group_id: -2902, protection_element_id:-2902], primaryKey: false);
      insert('organizations', [ id: 102888, nci_institute_code: "GA028", name: "Hamilton Medical Center", city: "Dalton", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA028",GROUP_DESC:"GA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3903 ,protection_group_id: -2903, protection_element_id:-2903], primaryKey: false);
      insert('organizations', [ id: 102889, nci_institute_code: "GA029", name: "Medical Center of Central Georgia", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA029",GROUP_DESC:"GA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3904 ,protection_group_id: -2904, protection_element_id:-2904], primaryKey: false);
      insert('organizations', [ id: 102890, nci_institute_code: "GA031", name: "Northside Hospital", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA031",GROUP_DESC:"GA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3905 ,protection_group_id: -2905, protection_element_id:-2905], primaryKey: false);
      insert('organizations', [ id: 102891, nci_institute_code: "GA032", name: "Gwinnett Medical Center", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA032",GROUP_DESC:"GA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3906 ,protection_group_id: -2906, protection_element_id:-2906], primaryKey: false);
      insert('organizations', [ id: 102892, nci_institute_code: "GA033", name: "Athens Regional Medical Center", city: "Athens", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA033",GROUP_DESC:"GA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3907 ,protection_group_id: -2907, protection_element_id:-2907], primaryKey: false);
      insert('organizations', [ id: 102893, nci_institute_code: "GA034", name: "Atlanta Regional CCOP", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA034",GROUP_DESC:"GA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3908 ,protection_group_id: -2908, protection_element_id:-2908], primaryKey: false);
      insert('organizations', [ id: 102894, nci_institute_code: "GA035", name: "Children's Healthcare of Atlanta - Egleston", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA035",GROUP_DESC:"GA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3909 ,protection_group_id: -2909, protection_element_id:-2909], primaryKey: false);
      insert('organizations', [ id: 102895, nci_institute_code: "GA036", name: "Atlanta Center for Cancer Research", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA036",GROUP_DESC:"GA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3910 ,protection_group_id: -2910, protection_element_id:-2910], primaryKey: false);
      insert('organizations', [ id: 102896, nci_institute_code: "GA037", name: "Code Available", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA037",GROUP_DESC:"GA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3911 ,protection_group_id: -2911, protection_element_id:-2911], primaryKey: false);
      insert('organizations', [ id: 102897, nci_institute_code: "GA038", name: "Georgia Oncology Hematology Clinic", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA038",GROUP_DESC:"GA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3912 ,protection_group_id: -2912, protection_element_id:-2912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 102898, nci_institute_code: "GA039", name: "Wellstar Kennestone Hospital", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA039",GROUP_DESC:"GA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3913 ,protection_group_id: -2913, protection_element_id:-2913], primaryKey: false);
      insert('organizations', [ id: 102899, nci_institute_code: "GA040", name: "Phoebe Putney Memorial Hospital", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA040",GROUP_DESC:"GA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3914 ,protection_group_id: -2914, protection_element_id:-2914], primaryKey: false);
      insert('organizations', [ id: 102900, nci_institute_code: "GA041", name: "Morehouse School of Medicine", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA041",GROUP_DESC:"GA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3915 ,protection_group_id: -2915, protection_element_id:-2915], primaryKey: false);
      insert('organizations', [ id: 102901, nci_institute_code: "GA042", name: "Summit Cancer Care", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA042",GROUP_DESC:"GA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3916 ,protection_group_id: -2916, protection_element_id:-2916], primaryKey: false);
      insert('organizations', [ id: 102902, nci_institute_code: "GA043", name: "Grady MBCCOP", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA043",GROUP_DESC:"GA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3917 ,protection_group_id: -2917, protection_element_id:-2917], primaryKey: false);
      insert('organizations', [ id: 102903, nci_institute_code: "GA044", name: "Georgia Cancer Specialists P.C.", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA044",GROUP_DESC:"GA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3918 ,protection_group_id: -2918, protection_element_id:-2918], primaryKey: false);
      insert('organizations', [ id: 102904, nci_institute_code: "GA045", name: "John B. Amos Community Cancer Center", city: "Columbus", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA045",GROUP_DESC:"GA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3919 ,protection_group_id: -2919, protection_element_id:-2919], primaryKey: false);
      insert('organizations', [ id: 102905, nci_institute_code: "GA046", name: "West Paces Medical Center", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA046",GROUP_DESC:"GA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3920 ,protection_group_id: -2920, protection_element_id:-2920], primaryKey: false);
      insert('organizations', [ id: 102906, nci_institute_code: "GA047", name: "Suburban Hematology-Oncology", city: "Snellville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA047",GROUP_DESC:"GA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3921 ,protection_group_id: -2921, protection_element_id:-2921], primaryKey: false);
      insert('organizations', [ id: 102907, nci_institute_code: "GA048", name: "The Medical Center Incorporated", city: "Columbus", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA048",GROUP_DESC:"GA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3922 ,protection_group_id: -2922, protection_element_id:-2922], primaryKey: false);
      insert('organizations', [ id: 102908, nci_institute_code: "GA050", name: "Cancer Center of Georgia", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA050",GROUP_DESC:"GA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3923 ,protection_group_id: -2923, protection_element_id:-2923], primaryKey: false);
      insert('organizations', [ id: 102909, nci_institute_code: "GA051", name: "Habersham Ear Nose and Throat Clinic, P.C.", city: "Demorest", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA051",GROUP_DESC:"GA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3924 ,protection_group_id: -2924, protection_element_id:-2924], primaryKey: false);
      insert('organizations', [ id: 102910, nci_institute_code: "GA052", name: "Kaiser Permanente", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA052",GROUP_DESC:"GA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3925 ,protection_group_id: -2925, protection_element_id:-2925], primaryKey: false);
      insert('organizations', [ id: 102911, nci_institute_code: "GA053", name: "Atlanta Hematology/Oncology Associates., P.C.", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA053",GROUP_DESC:"GA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3926 ,protection_group_id: -2926, protection_element_id:-2926], primaryKey: false);
      insert('organizations', [ id: 102912, nci_institute_code: "GA054", name: "N.W. Georgia Oncology Ctrs., P.C.", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA054",GROUP_DESC:"GA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3927 ,protection_group_id: -2927, protection_element_id:-2927], primaryKey: false);
      insert('organizations', [ id: 102913, nci_institute_code: "GA055", name: "Oncology Hematology Associates", city: "Columbus", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA055",GROUP_DESC:"GA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3928 ,protection_group_id: -2928, protection_element_id:-2928], primaryKey: false);
      insert('organizations', [ id: 102914, nci_institute_code: "GA057", name: "Atlanta Cancer Care", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA057",GROUP_DESC:"GA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3929 ,protection_group_id: -2929, protection_element_id:-2929], primaryKey: false);
      insert('organizations', [ id: 102915, nci_institute_code: "GA058", name: "South Atlanta Hematology Oncology, Incorporated", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA058",GROUP_DESC:"GA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3930 ,protection_group_id: -2930, protection_element_id:-2930], primaryKey: false);
      insert('organizations', [ id: 102916, nci_institute_code: "GA059", name: "Regional Radiation Oncology Center at Rome", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA059",GROUP_DESC:"GA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3931 ,protection_group_id: -2931, protection_element_id:-2931], primaryKey: false);
      insert('organizations', [ id: 102917, nci_institute_code: "GA061", name: "Pearlman Comprehensive Cancer Center", city: "Valdosta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA061",GROUP_DESC:"GA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3932 ,protection_group_id: -2932, protection_element_id:-2932], primaryKey: false);
      insert('organizations', [ id: 102918, nci_institute_code: "GA062", name: "Georgia Cancer Specialists- Northside", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA062",GROUP_DESC:"GA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3933 ,protection_group_id: -2933, protection_element_id:-2933], primaryKey: false);
      insert('organizations', [ id: 102919, nci_institute_code: "GA063", name: "Northwest Georgia Hematology Oncology", city: "Dalton", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA063",GROUP_DESC:"GA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3934 ,protection_group_id: -2934, protection_element_id:-2934], primaryKey: false);
      insert('organizations', [ id: 102920, nci_institute_code: "GA064", name: "Southern E.N.T. Surg., P.C.", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA064",GROUP_DESC:"GA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3935 ,protection_group_id: -2935, protection_element_id:-2935], primaryKey: false);
      insert('organizations', [ id: 102921, nci_institute_code: "GA065", name: "Gynecologic Oncology, P.C.", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA065",GROUP_DESC:"GA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3936 ,protection_group_id: -2936, protection_element_id:-2936], primaryKey: false);
      insert('organizations', [ id: 102922, nci_institute_code: "GA066", name: "Atlanta Cancer Care", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA066",GROUP_DESC:"GA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3937 ,protection_group_id: -2937, protection_element_id:-2937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 102923, nci_institute_code: "GA067", name: "Lewis Hall Singletary Oncology Center", city: "Thomasville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA067",GROUP_DESC:"GA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3938 ,protection_group_id: -2938, protection_element_id:-2938], primaryKey: false);
      insert('organizations', [ id: 102924, nci_institute_code: "GA069", name: "South Georgia Medical Center", city: "Valdosta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA069",GROUP_DESC:"GA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3939 ,protection_group_id: -2939, protection_element_id:-2939], primaryKey: false);
      insert('organizations', [ id: 102925, nci_institute_code: "GA070", name: "Southeast Georgia Regional Medical Center", city: "Brunswick", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA070",GROUP_DESC:"GA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3940 ,protection_group_id: -2940, protection_element_id:-2940], primaryKey: false);
      insert('organizations', [ id: 102926, nci_institute_code: "GA071", name: "Georgia Neurology Assocs., P.C.", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA071",GROUP_DESC:"GA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3941 ,protection_group_id: -2941, protection_element_id:-2941], primaryKey: false);
      insert('organizations', [ id: 102927, nci_institute_code: "GA073", name: "The Phoebe Cancer Center", city: "Albany", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA073",GROUP_DESC:"GA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3942 ,protection_group_id: -2942, protection_element_id:-2942], primaryKey: false);
      insert('organizations', [ id: 102928, nci_institute_code: "GA074", name: "Georgia Cancer Specialists, P.C.", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA074",GROUP_DESC:"GA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3943 ,protection_group_id: -2943, protection_element_id:-2943], primaryKey: false);
      insert('organizations', [ id: 102929, nci_institute_code: "GA075", name: "Clark-Holder Clinic", city: "La Grange", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA075",GROUP_DESC:"GA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3944 ,protection_group_id: -2944, protection_element_id:-2944], primaryKey: false);
      insert('organizations', [ id: 102930, nci_institute_code: "GA076", name: " Cobb Hospital and Medical Center", city: "Royston", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA076",GROUP_DESC:"GA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3945 ,protection_group_id: -2945, protection_element_id:-2945], primaryKey: false);
      insert('organizations', [ id: 102931, nci_institute_code: "GA078", name: "Augusta Oncology Associates PC", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA078",GROUP_DESC:"GA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3946 ,protection_group_id: -2946, protection_element_id:-2946], primaryKey: false);
      insert('organizations', [ id: 102932, nci_institute_code: "GA079", name: "Blood and Marrow Transplant Group of Georgia", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA079",GROUP_DESC:"GA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3947 ,protection_group_id: -2947, protection_element_id:-2947], primaryKey: false);
      insert('organizations', [ id: 102933, nci_institute_code: "GA080", name: "Georgia Cancer Specialists", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA080",GROUP_DESC:"GA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3948 ,protection_group_id: -2948, protection_element_id:-2948], primaryKey: false);
      insert('organizations', [ id: 102934, nci_institute_code: "GA081", name: "Emory University School Of Medicine", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA081",GROUP_DESC:"GA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3949 ,protection_group_id: -2949, protection_element_id:-2949], primaryKey: false);
      insert('organizations', [ id: 102935, nci_institute_code: "GA082", name: "Southeastern Gynecologic Oncology, LLP", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA082",GROUP_DESC:"GA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3950 ,protection_group_id: -2950, protection_element_id:-2950], primaryKey: false);
      insert('organizations', [ id: 102936, nci_institute_code: "GA083", name: "Georgia Cancer Specialists, P.C.", city: "Roswell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA083",GROUP_DESC:"GA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3951 ,protection_group_id: -2951, protection_element_id:-2951], primaryKey: false);
      insert('organizations', [ id: 102937, nci_institute_code: "GA084", name: "Center for Disease Control and Prevention", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA084",GROUP_DESC:"GA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3952 ,protection_group_id: -2952, protection_element_id:-2952], primaryKey: false);
      insert('organizations', [ id: 102938, nci_institute_code: "GA085", name: "Well Star Cobb Hospital", city: "Austell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA085",GROUP_DESC:"GA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3953 ,protection_group_id: -2953, protection_element_id:-2953], primaryKey: false);
      insert('organizations', [ id: 102939, nci_institute_code: "GA087", name: "Candler Hospital", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA087",GROUP_DESC:"GA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3954 ,protection_group_id: -2954, protection_element_id:-2954], primaryKey: false);
      insert('organizations', [ id: 102940, nci_institute_code: "GA088", name: "Georgia Cancer Treatment Center", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA088",GROUP_DESC:"GA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3955 ,protection_group_id: -2955, protection_element_id:-2955], primaryKey: false);
      insert('organizations', [ id: 102941, nci_institute_code: "GA089", name: "Northeast Georgia Cancer Care LLC", city: "Athens", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA089",GROUP_DESC:"GA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3956 ,protection_group_id: -2956, protection_element_id:-2956], primaryKey: false);
      insert('organizations', [ id: 102942, nci_institute_code: "GA090", name: "Central Georgia Cancer Care PC", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA090",GROUP_DESC:"GA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3957 ,protection_group_id: -2957, protection_element_id:-2957], primaryKey: false);
      insert('organizations', [ id: 102943, nci_institute_code: "GA091", name: "Atlanta Cancer Care", city: "Conyers", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA091",GROUP_DESC:"GA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3958 ,protection_group_id: -2958, protection_element_id:-2958], primaryKey: false);
      insert('organizations', [ id: 102944, nci_institute_code: "GA092", name: "Northeast Georgia Diagnostic Clinic", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA092",GROUP_DESC:"GA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3959 ,protection_group_id: -2959, protection_element_id:-2959], primaryKey: false);
      insert('organizations', [ id: 102945, nci_institute_code: "GA093", name: "Quantum Medical Radiology", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA093",GROUP_DESC:"GA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3960 ,protection_group_id: -2960, protection_element_id:-2960], primaryKey: false);
      insert('organizations', [ id: 102946, nci_institute_code: "GA094", name: "Coastal Hematology and Oncology, P.C.", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA094",GROUP_DESC:"GA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3961 ,protection_group_id: -2961, protection_element_id:-2961], primaryKey: false);
      insert('organizations', [ id: 102947, nci_institute_code: "GA095", name: "Floyd Medical Center", city: "Rome", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA095",GROUP_DESC:"GA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3962 ,protection_group_id: -2962, protection_element_id:-2962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 102948, nci_institute_code: "GA096", name: "Midtown Urology, PC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA096",GROUP_DESC:"GA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3963 ,protection_group_id: -2963, protection_element_id:-2963], primaryKey: false);
      insert('organizations', [ id: 102949, nci_institute_code: "GA097", name: "Northwest Georgia Oncology Center", city: "Cartersville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA097",GROUP_DESC:"GA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3964 ,protection_group_id: -2964, protection_element_id:-2964], primaryKey: false);
      insert('organizations', [ id: 102950, nci_institute_code: "GA098", name: "Peachtree Hematology/Oncology Consultants, PC", city: "Fayetteville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA098",GROUP_DESC:"GA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3965 ,protection_group_id: -2965, protection_element_id:-2965], primaryKey: false);
      insert('organizations', [ id: 102951, nci_institute_code: "GA099", name: "Southeastern Surgical Oncology, P. A.", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA099",GROUP_DESC:"GA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3966 ,protection_group_id: -2966, protection_element_id:-2966], primaryKey: false);
      insert('organizations', [ id: 102952, nci_institute_code: "GA100", name: "Georgia Cancer Specialists, P.C.", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA100",GROUP_DESC:"GA100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3967 ,protection_group_id: -2967, protection_element_id:-2967], primaryKey: false);
      insert('organizations', [ id: 102953, nci_institute_code: "GA101", name: "Val Oak Professional Corporation", city: "Valdosta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA101",GROUP_DESC:"GA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3968 ,protection_group_id: -2968, protection_element_id:-2968], primaryKey: false);
      insert('organizations', [ id: 102954, nci_institute_code: "GA102", name: "Kaiser Southeast Permanente Medical Group Incorporated", city: "Tucker", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA102",GROUP_DESC:"GA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3969 ,protection_group_id: -2969, protection_element_id:-2969], primaryKey: false);
      insert('organizations', [ id: 102955, nci_institute_code: "GA103", name: "Georgia Urology", city: "Snellville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA103",GROUP_DESC:"GA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3970 ,protection_group_id: -2970, protection_element_id:-2970], primaryKey: false);
      insert('organizations', [ id: 102956, nci_institute_code: "GA104", name: "Northside / Alpharetta Cancer Center", city: "Alpharetta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA104",GROUP_DESC:"GA104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3971 ,protection_group_id: -2971, protection_element_id:-2971], primaryKey: false);
      insert('organizations', [ id: 102957, nci_institute_code: "GA105", name: "Augusta Oncology Associates PC", city: "Augusta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA105",GROUP_DESC:"GA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3972 ,protection_group_id: -2972, protection_element_id:-2972], primaryKey: false);
      insert('organizations', [ id: 102958, nci_institute_code: "GA106", name: "St. Joseph's/Candler Health System", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA106",GROUP_DESC:"GA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3973 ,protection_group_id: -2973, protection_element_id:-2973], primaryKey: false);
      insert('organizations', [ id: 102959, nci_institute_code: "GA107", name: "American Cancer Society", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA107",GROUP_DESC:"GA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3974 ,protection_group_id: -2974, protection_element_id:-2974], primaryKey: false);
      insert('organizations', [ id: 102960, nci_institute_code: "GA108", name: "Georgia Cancer Specialists", city: "Austell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA108",GROUP_DESC:"GA108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3975 ,protection_group_id: -2975, protection_element_id:-2975], primaryKey: false);
      insert('organizations', [ id: 102961, nci_institute_code: "GA109", name: "Pharamatrophic Management Services, Incorporated", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA109",GROUP_DESC:"GA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3976 ,protection_group_id: -2976, protection_element_id:-2976], primaryKey: false);
      insert('organizations', [ id: 102962, nci_institute_code: "GA110", name: "Northwest Georgia Oncology Centers, P.C.", city: "Austell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA110",GROUP_DESC:"GA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3977 ,protection_group_id: -2977, protection_element_id:-2977], primaryKey: false);
      insert('organizations', [ id: 102963, nci_institute_code: "GA112", name: "Savannah Oncology Center", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA112",GROUP_DESC:"GA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3978 ,protection_group_id: -2978, protection_element_id:-2978], primaryKey: false);
      insert('organizations', [ id: 102964, nci_institute_code: "GA113", name: "The Longstreet Clinic, P.C.", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA113",GROUP_DESC:"GA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3979 ,protection_group_id: -2979, protection_element_id:-2979], primaryKey: false);
      insert('organizations', [ id: 102965, nci_institute_code: "GA114", name: "WellStar Health System", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA114",GROUP_DESC:"GA114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3980 ,protection_group_id: -2980, protection_element_id:-2980], primaryKey: false);
      insert('organizations', [ id: 102966, nci_institute_code: "GA115", name: "Dublin Hematology Oncology Care", city: "Dublin", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA115",GROUP_DESC:"GA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3981 ,protection_group_id: -2981, protection_element_id:-2981], primaryKey: false);
      insert('organizations', [ id: 102967, nci_institute_code: "GA116", name: "The Breast Center", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA116",GROUP_DESC:"GA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3982 ,protection_group_id: -2982, protection_element_id:-2982], primaryKey: false);
      insert('organizations', [ id: 102968, nci_institute_code: "GA117", name: "Gwinnett Medical Center", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA117",GROUP_DESC:"GA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3983 ,protection_group_id: -2983, protection_element_id:-2983], primaryKey: false);
      insert('organizations', [ id: 102969, nci_institute_code: "GA118", name: "Radiation Oncology Services, Inc.", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA118",GROUP_DESC:"GA118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3984 ,protection_group_id: -2984, protection_element_id:-2984], primaryKey: false);
      insert('organizations', [ id: 102970, nci_institute_code: "GA119", name: "Southern Regional Medical Center", city: "Stockridge", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA119",GROUP_DESC:"GA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3985 ,protection_group_id: -2985, protection_element_id:-2985], primaryKey: false);
      insert('organizations', [ id: 102971, nci_institute_code: "GA120", name: "Comprehensive Gynecology,P.C.", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA120",GROUP_DESC:"GA120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3986 ,protection_group_id: -2986, protection_element_id:-2986], primaryKey: false);
      insert('organizations', [ id: 102972, nci_institute_code: "GA121", name: "South Fulton Medical Center", city: "East Point", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA121",GROUP_DESC:"GA121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3987 ,protection_group_id: -2987, protection_element_id:-2987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 102973, nci_institute_code: "GA122", name: "Northwest Georgia Oncology Center, P.C.", city: "Carrollton", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA122",GROUP_DESC:"GA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3988 ,protection_group_id: -2988, protection_element_id:-2988], primaryKey: false);
      insert('organizations', [ id: 102974, nci_institute_code: "GA123", name: "Georgia Surgical Associates", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA123",GROUP_DESC:"GA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3989 ,protection_group_id: -2989, protection_element_id:-2989], primaryKey: false);
      insert('organizations', [ id: 102975, nci_institute_code: "GA124", name: "Georgia Cancer Specialists", city: "Riverdale", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA124",GROUP_DESC:"GA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3990 ,protection_group_id: -2990, protection_element_id:-2990], primaryKey: false);
      insert('organizations', [ id: 102976, nci_institute_code: "GA125", name: "Rockdale County Radiation Therapy Center", city: "Conyers", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA125",GROUP_DESC:"GA125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3991 ,protection_group_id: -2991, protection_element_id:-2991], primaryKey: false);
      insert('organizations', [ id: 102977, nci_institute_code: "GA126", name: "Metro Surgical Associates", city: "Lithonia", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA126",GROUP_DESC:"GA126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3992 ,protection_group_id: -2992, protection_element_id:-2992], primaryKey: false);
      insert('organizations', [ id: 102978, nci_institute_code: "GA127", name: "Georgia Cancer Specialists - Lawrenceville", city: "Lawrenceville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA127",GROUP_DESC:"GA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3993 ,protection_group_id: -2993, protection_element_id:-2993], primaryKey: false);
      insert('organizations', [ id: 102979, nci_institute_code: "GA128", name: "Peachtree Hematology Oncology Consultants PC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA128",GROUP_DESC:"GA128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3994 ,protection_group_id: -2994, protection_element_id:-2994], primaryKey: false);
      insert('organizations', [ id: 102980, nci_institute_code: "GA129", name: "Surgical Associates of Columbus", city: "Columbus", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA129",GROUP_DESC:"GA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3995 ,protection_group_id: -2995, protection_element_id:-2995], primaryKey: false);
      insert('organizations', [ id: 102981, nci_institute_code: "GA130", name: "Savannah Surgical Oncology, LLC", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA130",GROUP_DESC:"GA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3996 ,protection_group_id: -2996, protection_element_id:-2996], primaryKey: false);
      insert('organizations', [ id: 102982, nci_institute_code: "GA131", name: "Urology Specialists of Coastal Georgia, P.C.", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA131",GROUP_DESC:"GA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3997 ,protection_group_id: -2997, protection_element_id:-2997], primaryKey: false);
      insert('organizations', [ id: 102983, nci_institute_code: "GA132", name: "Cobb Surgical Associates", city: "Austell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA132",GROUP_DESC:"GA132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3998 ,protection_group_id: -2998, protection_element_id:-2998], primaryKey: false);
      insert('organizations', [ id: 102984, nci_institute_code: "GA133", name: "Northside/Dunwoody Cancer Center", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA133",GROUP_DESC:"GA133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3999 ,protection_group_id: -2999, protection_element_id:-2999], primaryKey: false);
      insert('organizations', [ id: 102985, nci_institute_code: "GA134", name: "Suburban Hematology - Oncology", city: "Duluth", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA134",GROUP_DESC:"GA134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4000 ,protection_group_id: -3000, protection_element_id:-3000], primaryKey: false);
      insert('organizations', [ id: 102986, nci_institute_code: "GA135", name: "Surgical Oncology of Northeast Georgia", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA135",GROUP_DESC:"GA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4001 ,protection_group_id: -3001, protection_element_id:-3001], primaryKey: false);
      insert('organizations', [ id: 102987, nci_institute_code: "GA137", name: "Kaiser Permanente Southwood", city: "Jonesboro", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA137",GROUP_DESC:"GA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4002 ,protection_group_id: -3002, protection_element_id:-3002], primaryKey: false);
      insert('organizations', [ id: 102988, nci_institute_code: "GA138", name: "Northside Respiratory Care", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA138",GROUP_DESC:"GA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4003 ,protection_group_id: -3003, protection_element_id:-3003], primaryKey: false);
      insert('organizations', [ id: 102989, nci_institute_code: "GA139", name: "Gainesville Surgical Associates", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA139",GROUP_DESC:"GA139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4004 ,protection_group_id: -3004, protection_element_id:-3004], primaryKey: false);
      insert('organizations', [ id: 102990, nci_institute_code: "GA140", name: "Oncology Specialists of North Georgia, LLC", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA140",GROUP_DESC:"GA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4005 ,protection_group_id: -3005, protection_element_id:-3005], primaryKey: false);
      insert('organizations', [ id: 102991, nci_institute_code: "GA141", name: "Northeast Georgia Surgical Associates", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA141",GROUP_DESC:"GA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4006 ,protection_group_id: -3006, protection_element_id:-3006], primaryKey: false);
      insert('organizations', [ id: 102992, nci_institute_code: "GA142", name: "Central Georgia Gynecologic Oncology", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA142",GROUP_DESC:"GA142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4007 ,protection_group_id: -3007, protection_element_id:-3007], primaryKey: false);
      insert('organizations', [ id: 102993, nci_institute_code: "GA143", name: "Georgia Cancer Specialists- St Joseph's", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA143",GROUP_DESC:"GA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4008 ,protection_group_id: -3008, protection_element_id:-3008], primaryKey: false);
      insert('organizations', [ id: 102994, nci_institute_code: "GA144", name: "Northwest Georgia Oncology Center, P.C. - Woodstock Center", city: "Woodstock", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA144",GROUP_DESC:"GA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4009 ,protection_group_id: -3009, protection_element_id:-3009], primaryKey: false);
      insert('organizations', [ id: 102995, nci_institute_code: "GA145", name: "Cobb Center for Radiation Therapy", city: "Austell", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA145",GROUP_DESC:"GA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4010 ,protection_group_id: -3010, protection_element_id:-3010], primaryKey: false);
      insert('organizations', [ id: 102996, nci_institute_code: "GA146", name: "Radiotherapy Clinics of Georgia", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA146",GROUP_DESC:"GA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4011 ,protection_group_id: -3011, protection_element_id:-3011], primaryKey: false);
      insert('organizations', [ id: 102997, nci_institute_code: "GA147", name: "Wellstar Health Radiation Therapy", city: "Marietta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -3012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA147",GROUP_DESC:"GA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -3012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -3012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:4012 ,protection_group_id: -3012, protection_element_id:-3012], primaryKey: false);
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
