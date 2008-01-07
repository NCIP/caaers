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
    }

    void m0() {
        // all0 (25 terms)
      insert('organizations', [ id: 106998, nci_institute_code: "TN124", name: "Tennessee Cancer Specialists -Baptist Medical Tower", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN124",GROUP_DESC:"TN124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8013 ,protection_group_id: -7013, protection_element_id:-7013], primaryKey: false);
      insert('organizations', [ id: 106999, nci_institute_code: "TN125", name: "Chattanooga Gynecological Oncology", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN125",GROUP_DESC:"TN125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8014 ,protection_group_id: -7014, protection_element_id:-7014], primaryKey: false);
      insert('organizations', [ id: 107000, nci_institute_code: "TN126", name: "Kimsey Radiation Oncology PLLC", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN126",GROUP_DESC:"TN126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8015 ,protection_group_id: -7015, protection_element_id:-7015], primaryKey: false);
      insert('organizations', [ id: 107001, nci_institute_code: "TN127", name: "Saint Mary's North Cancer Center", city: "Powell", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN127",GROUP_DESC:"TN127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8016 ,protection_group_id: -7016, protection_element_id:-7016], primaryKey: false);
      insert('organizations', [ id: 107002, nci_institute_code: "TN128", name: "Tennessee Oncology PLLC", city: "Smyrna", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN128",GROUP_DESC:"TN128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8017 ,protection_group_id: -7017, protection_element_id:-7017], primaryKey: false);
      insert('organizations', [ id: 107003, nci_institute_code: "TN129", name: "Tennessee Oncology, PLLC", city: "Lebanon", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN129",GROUP_DESC:"TN129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8018 ,protection_group_id: -7018, protection_element_id:-7018], primaryKey: false);
      insert('organizations', [ id: 107004, nci_institute_code: "TN130", name: "Thompson Cancer Survival Center at Methodist", city: "Oak Ridge", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN130",GROUP_DESC:"TN130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8019 ,protection_group_id: -7019, protection_element_id:-7019], primaryKey: false);
      insert('organizations', [ id: 107005, nci_institute_code: "TN131", name: "Kingsport Hematology-Oncology Associates", city: "Kingsport", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN131",GROUP_DESC:"TN131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8020 ,protection_group_id: -7020, protection_element_id:-7020], primaryKey: false);
      insert('organizations', [ id: 107006, nci_institute_code: "TN132", name: "Associates in Oncology & Hematology", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN132",GROUP_DESC:"TN132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8021 ,protection_group_id: -7021, protection_element_id:-7021], primaryKey: false);
      insert('organizations', [ id: 107007, nci_institute_code: "TN133", name: "Tennessee Oncology PLLC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN133",GROUP_DESC:"TN133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8022 ,protection_group_id: -7022, protection_element_id:-7022], primaryKey: false);
      insert('organizations', [ id: 107008, nci_institute_code: "TN134", name: "Radiation Medicine Specialists PC", city: "Oak Ridge", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN134",GROUP_DESC:"TN134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8023 ,protection_group_id: -7023, protection_element_id:-7023], primaryKey: false);
      insert('organizations', [ id: 107009, nci_institute_code: "TN135", name: "Tennessee Oncology PLLC", city: "Clarksville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN135",GROUP_DESC:"TN135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8024 ,protection_group_id: -7024, protection_element_id:-7024], primaryKey: false);
      insert('organizations', [ id: 107010, nci_institute_code: "TN136", name: "Tennessee Oncology PLLC", city: "Gallatin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN136",GROUP_DESC:"TN136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8025 ,protection_group_id: -7025, protection_element_id:-7025], primaryKey: false);
      insert('organizations', [ id: 107011, nci_institute_code: "TN137", name: "Colombia Radiation Oncology", city: "Murfreesboro", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN137",GROUP_DESC:"TN137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8026 ,protection_group_id: -7026, protection_element_id:-7026], primaryKey: false);
      insert('organizations', [ id: 107012, nci_institute_code: "TN138", name: "Middle Tennessee Medical Center", city: "Murfreesboro", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN138",GROUP_DESC:"TN138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8027 ,protection_group_id: -7027, protection_element_id:-7027], primaryKey: false);
      insert('organizations', [ id: 107013, nci_institute_code: "TN139", name: "The West Clinic - Mid-Town", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN139",GROUP_DESC:"TN139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8028 ,protection_group_id: -7028, protection_element_id:-7028], primaryKey: false);
      insert('organizations', [ id: 107014, nci_institute_code: "TN140", name: "Alliance of Cardiac Thoracic and Vascular Surgeons", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN140",GROUP_DESC:"TN140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8029 ,protection_group_id: -7029, protection_element_id:-7029], primaryKey: false);
      insert('organizations', [ id: 107015, nci_institute_code: "TN141", name: "Thompson Cancer Survival Center - West", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN141",GROUP_DESC:"TN141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8030 ,protection_group_id: -7030, protection_element_id:-7030], primaryKey: false);
      insert('organizations', [ id: 107016, nci_institute_code: "TN142", name: "Cancer Outreach Associates of Tennessee PC", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN142",GROUP_DESC:"TN142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8031 ,protection_group_id: -7031, protection_element_id:-7031], primaryKey: false);
      insert('organizations', [ id: 107017, nci_institute_code: "TN143", name: "Baptist Memorial Hospital for Women", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN143",GROUP_DESC:"TN143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8032 ,protection_group_id: -7032, protection_element_id:-7032], primaryKey: false);
      insert('organizations', [ id: 107018, nci_institute_code: "TN144", name: "Cookeville Regional Medical Center", city: "Cookeville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN144",GROUP_DESC:"TN144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8033 ,protection_group_id: -7033, protection_element_id:-7033], primaryKey: false);
      insert('organizations', [ id: 107019, nci_institute_code: "TN145", name: "Knoxville Comprehensive Breast Center", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN145",GROUP_DESC:"TN145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8034 ,protection_group_id: -7034, protection_element_id:-7034], primaryKey: false);
      insert('organizations', [ id: 107020, nci_institute_code: "TN146", name: "Columbia Surgery Group", city: "Columbia", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN146",GROUP_DESC:"TN146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8035 ,protection_group_id: -7035, protection_element_id:-7035], primaryKey: false);
      insert('organizations', [ id: 107021, nci_institute_code: "TN147", name: "Thompson Oncology Group", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN147",GROUP_DESC:"TN147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8036 ,protection_group_id: -7036, protection_element_id:-7036], primaryKey: false);
      insert('organizations', [ id: 107022, nci_institute_code: "TN148", name: "The Surgical Clinic PLLC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN148",GROUP_DESC:"TN148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8037 ,protection_group_id: -7037, protection_element_id:-7037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 107023, nci_institute_code: "TN149", name: "Maury Regional Hospital", city: "Columbia", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN149",GROUP_DESC:"TN149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8038 ,protection_group_id: -7038, protection_element_id:-7038], primaryKey: false);
      insert('organizations', [ id: 107024, nci_institute_code: "TN150", name: "Tennessee Cancer Specialists PLLC", city: "Powell", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN150",GROUP_DESC:"TN150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8039 ,protection_group_id: -7039, protection_element_id:-7039], primaryKey: false);
      insert('organizations', [ id: 107025, nci_institute_code: "TN151", name: "Cumberland Surgical Associates", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN151",GROUP_DESC:"TN151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8040 ,protection_group_id: -7040, protection_element_id:-7040], primaryKey: false);
      insert('organizations', [ id: 107026, nci_institute_code: "TN152", name: "University Surgical Associates", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN152",GROUP_DESC:"TN152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8041 ,protection_group_id: -7041, protection_element_id:-7041], primaryKey: false);
      insert('organizations', [ id: 107027, nci_institute_code: "TN153", name: "Hall and Martin MDs PC", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN153",GROUP_DESC:"TN153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8042 ,protection_group_id: -7042, protection_element_id:-7042], primaryKey: false);
      insert('organizations', [ id: 107028, nci_institute_code: "TN154", name: "Morristown Regional Cancer Center", city: "Morristown", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN154",GROUP_DESC:"TN154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8043 ,protection_group_id: -7043, protection_element_id:-7043], primaryKey: false);
      insert('organizations', [ id: 107029, nci_institute_code: "TN155", name: "Saint Thomas Research Institute", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN155",GROUP_DESC:"TN155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8044 ,protection_group_id: -7044, protection_element_id:-7044], primaryKey: false);
      insert('organizations', [ id: 107030, nci_institute_code: "TN156", name: "Vanderbilt-Ingram Cancer Center at Franklin", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN156",GROUP_DESC:"TN156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8045 ,protection_group_id: -7045, protection_element_id:-7045], primaryKey: false);
      insert('organizations', [ id: 107031, nci_institute_code: "TN157", name: "Vijay R Patil MD", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN157",GROUP_DESC:"TN157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8046 ,protection_group_id: -7046, protection_element_id:-7046], primaryKey: false);
      insert('organizations', [ id: 107032, nci_institute_code: "TN158", name: "Women's Institute for Specialized Health", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN158",GROUP_DESC:"TN158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8047 ,protection_group_id: -7047, protection_element_id:-7047], primaryKey: false);
      insert('organizations', [ id: 107033, nci_institute_code: "TN159", name: "John L Gwin General Surgery PLLC", city: "Chattanoga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN159",GROUP_DESC:"TN159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8048 ,protection_group_id: -7048, protection_element_id:-7048], primaryKey: false);
      insert('organizations', [ id: 107034, nci_institute_code: "TN160", name: "The West Clinic - Collierville", city: "Collierville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN160",GROUP_DESC:"TN160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8049 ,protection_group_id: -7049, protection_element_id:-7049], primaryKey: false);
      insert('organizations', [ id: 107035, nci_institute_code: "TN161", name: "B Stephens Dudley MD PC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN161",GROUP_DESC:"TN161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8050 ,protection_group_id: -7050, protection_element_id:-7050], primaryKey: false);
      insert('organizations', [ id: 107036, nci_institute_code: "TPN", name: "Total Parenteral Nutrition Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TPN",GROUP_DESC:"TPN group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TPN",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TPN",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TPN", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8051 ,protection_group_id: -7051, protection_element_id:-7051], primaryKey: false);
      insert('organizations', [ id: 107037, nci_institute_code: "TX002", name: "Dallas VA Medical Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX002",GROUP_DESC:"TX002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8052 ,protection_group_id: -7052, protection_element_id:-7052], primaryKey: false);
      insert('organizations', [ id: 107038, nci_institute_code: "TX003", name: "Doctors Health Facilities Dba", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX003",GROUP_DESC:"TX003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8053 ,protection_group_id: -7053, protection_element_id:-7053], primaryKey: false);
      insert('organizations', [ id: 107039, nci_institute_code: "TX004", name: "Medical City Dallas Hospital", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX004",GROUP_DESC:"TX004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8054 ,protection_group_id: -7054, protection_element_id:-7054], primaryKey: false);
      insert('organizations', [ id: 107040, nci_institute_code: "TX005", name: "Presbyterian Hospital of Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX005",GROUP_DESC:"TX005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8055 ,protection_group_id: -7055, protection_element_id:-7055], primaryKey: false);
      insert('organizations', [ id: 107041, nci_institute_code: "TX006", name: "Parkland Memorial Hospital", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX006",GROUP_DESC:"TX006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8056 ,protection_group_id: -7056, protection_element_id:-7056], primaryKey: false);
      insert('organizations', [ id: 107042, nci_institute_code: "TX007", name: "Saint Paul Hospital", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX007",GROUP_DESC:"TX007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8057 ,protection_group_id: -7057, protection_element_id:-7057], primaryKey: false);
      insert('organizations', [ id: 107043, nci_institute_code: "TX008", name: "Harris Methodist", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX008",GROUP_DESC:"TX008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8058 ,protection_group_id: -7058, protection_element_id:-7058], primaryKey: false);
      insert('organizations', [ id: 107044, nci_institute_code: "TX009", name: "Childrens Medical Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX009",GROUP_DESC:"TX009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8059 ,protection_group_id: -7059, protection_element_id:-7059], primaryKey: false);
      insert('organizations', [ id: 107045, nci_institute_code: "TX010", name: "Wadley Institution of Molecular Medicine", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX010",GROUP_DESC:"TX010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8060 ,protection_group_id: -7060, protection_element_id:-7060], primaryKey: false);
      insert('organizations', [ id: 107046, nci_institute_code: "TX011", name: "University of Texas Southwestern Medical Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX011",GROUP_DESC:"TX011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8061 ,protection_group_id: -7061, protection_element_id:-7061], primaryKey: false);
      insert('organizations', [ id: 107047, nci_institute_code: "TX012", name: "Baylor University Medical Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX012",GROUP_DESC:"TX012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8062 ,protection_group_id: -7062, protection_element_id:-7062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 107048, nci_institute_code: "TX013", name: "Wadley Research Institute", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX013",GROUP_DESC:"TX013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8063 ,protection_group_id: -7063, protection_element_id:-7063], primaryKey: false);
      insert('organizations', [ id: 107049, nci_institute_code: "TX014", name: "Methodist Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX014",GROUP_DESC:"TX014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8064 ,protection_group_id: -7064, protection_element_id:-7064], primaryKey: false);
      insert('organizations', [ id: 107050, nci_institute_code: "TX017", name: "Trinity Mother Francis Hospital", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX017",GROUP_DESC:"TX017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8065 ,protection_group_id: -7065, protection_element_id:-7065], primaryKey: false);
      insert('organizations', [ id: 107051, nci_institute_code: "TX018", name: "University of Texas Health Center at Tyler", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX018",GROUP_DESC:"TX018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8066 ,protection_group_id: -7066, protection_element_id:-7066], primaryKey: false);
      insert('organizations', [ id: 107052, nci_institute_code: "TX019", name: "Memorial Hospital", city: "Nacogdoches", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX019",GROUP_DESC:"TX019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8067 ,protection_group_id: -7067, protection_element_id:-7067], primaryKey: false);
      insert('organizations', [ id: 107053, nci_institute_code: "TX020", name: "Cook Children's Medical Center", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX020",GROUP_DESC:"TX020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8068 ,protection_group_id: -7068, protection_element_id:-7068], primaryKey: false);
      insert('organizations', [ id: 107054, nci_institute_code: "TX021", name: "Saint Joseph Hospital", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX021",GROUP_DESC:"TX021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8069 ,protection_group_id: -7069, protection_element_id:-7069], primaryKey: false);
      insert('organizations', [ id: 107055, nci_institute_code: "TX022", name: "MD Anderson Cancer Network", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX022",GROUP_DESC:"TX022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8070 ,protection_group_id: -7070, protection_element_id:-7070], primaryKey: false);
      insert('organizations', [ id: 107056, nci_institute_code: "TX023", name: "John Peter Smith Hospital", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX023",GROUP_DESC:"TX023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8071 ,protection_group_id: -7071, protection_element_id:-7071], primaryKey: false);
      insert('organizations', [ id: 107057, nci_institute_code: "TX024", name: "United States Air Force Regional Hospital", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX024",GROUP_DESC:"TX024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8072 ,protection_group_id: -7072, protection_element_id:-7072], primaryKey: false);
      insert('organizations', [ id: 107058, nci_institute_code: "TX025", name: "Texas Woman's University", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX025",GROUP_DESC:"TX025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8073 ,protection_group_id: -7073, protection_element_id:-7073], primaryKey: false);
      insert('organizations', [ id: 107059, nci_institute_code: "TX026", name: "Olin E Teague Veterns Administratiom Center", city: "Temple", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX026",GROUP_DESC:"TX026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8074 ,protection_group_id: -7074, protection_element_id:-7074], primaryKey: false);
      insert('organizations', [ id: 107060, nci_institute_code: "TX027", name: "Scott and White Memorial Hospital", city: "Temple", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX027",GROUP_DESC:"TX027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8075 ,protection_group_id: -7075, protection_element_id:-7075], primaryKey: false);
      insert('organizations', [ id: 107061, nci_institute_code: "TX028", name: "Providence Hospital", city: "Waco", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX028",GROUP_DESC:"TX028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8076 ,protection_group_id: -7076, protection_element_id:-7076], primaryKey: false);
      insert('organizations', [ id: 107062, nci_institute_code: "TX029", name: "Hillcrest Baptist Medical Center", city: "Waco", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX029",GROUP_DESC:"TX029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8077 ,protection_group_id: -7077, protection_element_id:-7077], primaryKey: false);
      insert('organizations', [ id: 107063, nci_institute_code: "TX030", name: "Shannon Medical Center", city: "San Angelo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX030",GROUP_DESC:"TX030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8078 ,protection_group_id: -7078, protection_element_id:-7078], primaryKey: false);
      insert('organizations', [ id: 107064, nci_institute_code: "TX031", name: "Diagnostic Center Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX031",GROUP_DESC:"TX031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8079 ,protection_group_id: -7079, protection_element_id:-7079], primaryKey: false);
      insert('organizations', [ id: 107065, nci_institute_code: "TX032", name: "Saint Joseph Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX032",GROUP_DESC:"TX032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8080 ,protection_group_id: -7080, protection_element_id:-7080], primaryKey: false);
      insert('organizations', [ id: 107066, nci_institute_code: "TX033", name: "Stehlin Foundation", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX033",GROUP_DESC:"TX033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8081 ,protection_group_id: -7081, protection_element_id:-7081], primaryKey: false);
      insert('organizations', [ id: 107067, nci_institute_code: "TX034", name: "Park Plaza Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX034",GROUP_DESC:"TX034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8082 ,protection_group_id: -7082, protection_element_id:-7082], primaryKey: false);
      insert('organizations', [ id: 107068, nci_institute_code: "TX035", name: "M D Anderson Cancer Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX035",GROUP_DESC:"TX035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8083 ,protection_group_id: -7083, protection_element_id:-7083], primaryKey: false);
      insert('organizations', [ id: 107069, nci_institute_code: "TX036", name: "Methodist Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX036",GROUP_DESC:"TX036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8084 ,protection_group_id: -7084, protection_element_id:-7084], primaryKey: false);
      insert('organizations', [ id: 107070, nci_institute_code: "TX037", name: "Stehlin Foundation for Cancer Research", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX037",GROUP_DESC:"TX037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8085 ,protection_group_id: -7085, protection_element_id:-7085], primaryKey: false);
      insert('organizations', [ id: 107071, nci_institute_code: "TX038", name: "University Texas System Cancer Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX038",GROUP_DESC:"TX038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8086 ,protection_group_id: -7086, protection_element_id:-7086], primaryKey: false);
      insert('organizations', [ id: 107072, nci_institute_code: "TX039", name: "Memorial Hermann Texas Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX039",GROUP_DESC:"TX039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8087 ,protection_group_id: -7087, protection_element_id:-7087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 107073, nci_institute_code: "TX040", name: "Texas Children's Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX040",GROUP_DESC:"TX040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8088 ,protection_group_id: -7088, protection_element_id:-7088], primaryKey: false);
      insert('organizations', [ id: 107074, nci_institute_code: "TX041", name: "Baylor College of Medicine", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX041",GROUP_DESC:"TX041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8089 ,protection_group_id: -7089, protection_element_id:-7089], primaryKey: false);
      insert('organizations', [ id: 107075, nci_institute_code: "TX042", name: "Veterans Administration Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX042",GROUP_DESC:"TX042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8090 ,protection_group_id: -7090, protection_element_id:-7090], primaryKey: false);
      insert('organizations', [ id: 107076, nci_institute_code: "TX043", name: "Bellaire General Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX043",GROUP_DESC:"TX043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8091 ,protection_group_id: -7091, protection_element_id:-7091], primaryKey: false);
      insert('organizations', [ id: 107077, nci_institute_code: "TX045", name: "University of Texas Medical Branch", city: "Galveston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX045",GROUP_DESC:"TX045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8092 ,protection_group_id: -7092, protection_element_id:-7092], primaryKey: false);
      insert('organizations', [ id: 107078, nci_institute_code: "TX046", name: "Clear Lake Regional Medical Center", city: "Webster", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX046",GROUP_DESC:"TX046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8093 ,protection_group_id: -7093, protection_element_id:-7093], primaryKey: false);
      insert('organizations', [ id: 107079, nci_institute_code: "TX047", name: "Texas A&M", city: "College Station", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX047",GROUP_DESC:"TX047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8094 ,protection_group_id: -7094, protection_element_id:-7094], primaryKey: false);
      insert('organizations', [ id: 107080, nci_institute_code: "TX048", name: "Nix Memorial Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX048",GROUP_DESC:"TX048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8095 ,protection_group_id: -7095, protection_element_id:-7095], primaryKey: false);
      insert('organizations', [ id: 107081, nci_institute_code: "TX049", name: "Robert B. Green Memorial Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX049",GROUP_DESC:"TX049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8096 ,protection_group_id: -7096, protection_element_id:-7096], primaryKey: false);
      insert('organizations', [ id: 107082, nci_institute_code: "TX050", name: "Audie L. Murphy Veterans Affairs Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX050",GROUP_DESC:"TX050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8097 ,protection_group_id: -7097, protection_element_id:-7097], primaryKey: false);
      insert('organizations', [ id: 107083, nci_institute_code: "TX051", name: "Saint Lukes Lutheran", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX051",GROUP_DESC:"TX051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8098 ,protection_group_id: -7098, protection_element_id:-7098], primaryKey: false);
      insert('organizations', [ id: 107084, nci_institute_code: "TX052", name: "Methodist Children's Hospital of South Texas", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX052",GROUP_DESC:"TX052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8099 ,protection_group_id: -7099, protection_element_id:-7099], primaryKey: false);
      insert('organizations', [ id: 107085, nci_institute_code: "TX053", name: "US Oncology Inc", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX053",GROUP_DESC:"TX053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8100 ,protection_group_id: -7100, protection_element_id:-7100], primaryKey: false);
      insert('organizations', [ id: 107086, nci_institute_code: "TX054", name: "Covenant Medical Center-Lakeside", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX054",GROUP_DESC:"TX054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8101 ,protection_group_id: -7101, protection_element_id:-7101], primaryKey: false);
      insert('organizations', [ id: 107087, nci_institute_code: "TX055", name: "Brooke Army Medical Center", city: "Fort Sam Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX055",GROUP_DESC:"TX055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8102 ,protection_group_id: -7102, protection_element_id:-7102], primaryKey: false);
      insert('organizations', [ id: 107088, nci_institute_code: "TX056", name: "Wilford Hall Medical Center", city: "Lackland AFB", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX056",GROUP_DESC:"TX056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8103 ,protection_group_id: -7103, protection_element_id:-7103], primaryKey: false);
      insert('organizations', [ id: 107089, nci_institute_code: "TX058", name: "Memorial Hospital & Medical Center", city: "Midland", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX058",GROUP_DESC:"TX058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8104 ,protection_group_id: -7104, protection_element_id:-7104], primaryKey: false);
      insert('organizations', [ id: 107090, nci_institute_code: "TX059", name: "University of Texas Health Science Center", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX059",GROUP_DESC:"TX059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8105 ,protection_group_id: -7105, protection_element_id:-7105], primaryKey: false);
      insert('organizations', [ id: 107091, nci_institute_code: "TX060", name: "Santa Rosa Health Care", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX060",GROUP_DESC:"TX060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8106 ,protection_group_id: -7106, protection_element_id:-7106], primaryKey: false);
      insert('organizations', [ id: 107092, nci_institute_code: "TX061", name: "Childrens Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX061",GROUP_DESC:"TX061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8107 ,protection_group_id: -7107, protection_element_id:-7107], primaryKey: false);
      insert('organizations', [ id: 107093, nci_institute_code: "TX062", name: "Memorial Medical Center Hospital", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX062",GROUP_DESC:"TX062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8108 ,protection_group_id: -7108, protection_element_id:-7108], primaryKey: false);
      insert('organizations', [ id: 107094, nci_institute_code: "TX063", name: "Brackenridge Hospital", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX063",GROUP_DESC:"TX063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8109 ,protection_group_id: -7109, protection_element_id:-7109], primaryKey: false);
      insert('organizations', [ id: 107095, nci_institute_code: "TX064", name: "Holy Cross Hospital", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX064",GROUP_DESC:"TX064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8110 ,protection_group_id: -7110, protection_element_id:-7110], primaryKey: false);
      insert('organizations', [ id: 107096, nci_institute_code: "TX066", name: "High Plains Baptist Hospital", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX066",GROUP_DESC:"TX066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8111 ,protection_group_id: -7111, protection_element_id:-7111], primaryKey: false);
      insert('organizations', [ id: 107097, nci_institute_code: "TX067", name: "Texas Tech University Health Science Center-Amarillo", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX067",GROUP_DESC:"TX067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8112 ,protection_group_id: -7112, protection_element_id:-7112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 107098, nci_institute_code: "TX068", name: "The Don and Sybil Harrington Cancer Center", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX068",GROUP_DESC:"TX068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8113 ,protection_group_id: -7113, protection_element_id:-7113], primaryKey: false);
      insert('organizations', [ id: 107099, nci_institute_code: "TX070", name: "University Medical Center", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX070",GROUP_DESC:"TX070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8114 ,protection_group_id: -7114, protection_element_id:-7114], primaryKey: false);
      insert('organizations', [ id: 107100, nci_institute_code: "TX071", name: "Texas Tech University Health Sciences Center", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX071",GROUP_DESC:"TX071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8115 ,protection_group_id: -7115, protection_element_id:-7115], primaryKey: false);
      insert('organizations', [ id: 107101, nci_institute_code: "TX072", name: "Hendrick Medical Center", city: "Abilene", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX072",GROUP_DESC:"TX072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8116 ,protection_group_id: -7116, protection_element_id:-7116], primaryKey: false);
      insert('organizations', [ id: 107102, nci_institute_code: "TX073", name: "Medical Center Hospital", city: "Odessa", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX073",GROUP_DESC:"TX073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8117 ,protection_group_id: -7117, protection_element_id:-7117], primaryKey: false);
      insert('organizations', [ id: 107103, nci_institute_code: "TX076", name: "Texas Technology University", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX076",GROUP_DESC:"TX076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8118 ,protection_group_id: -7118, protection_element_id:-7118], primaryKey: false);
      insert('organizations', [ id: 107104, nci_institute_code: "TX077", name: "William Beaumont Army Medical Center", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX077",GROUP_DESC:"TX077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8119 ,protection_group_id: -7119, protection_element_id:-7119], primaryKey: false);
      insert('organizations', [ id: 107105, nci_institute_code: "TX079", name: "Driscoll Children's Hospital", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX079",GROUP_DESC:"TX079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8120 ,protection_group_id: -7120, protection_element_id:-7120], primaryKey: false);
      insert('organizations', [ id: 107106, nci_institute_code: "TX080", name: "East Texas Medical Center", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX080",GROUP_DESC:"TX080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8121 ,protection_group_id: -7121, protection_element_id:-7121], primaryKey: false);
      insert('organizations', [ id: 107107, nci_institute_code: "TX081", name: "Osteopathic Medical Center", city: "Bedford", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX081",GROUP_DESC:"TX081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8122 ,protection_group_id: -7122, protection_element_id:-7122], primaryKey: false);
      insert('organizations', [ id: 107108, nci_institute_code: "TX082", name: "Memorial Hosp-Memorial City", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX082",GROUP_DESC:"TX082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8123 ,protection_group_id: -7123, protection_element_id:-7123], primaryKey: false);
      insert('organizations', [ id: 107109, nci_institute_code: "TX083", name: "Kelsey-Seybold Clinic", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX083",GROUP_DESC:"TX083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8124 ,protection_group_id: -7124, protection_element_id:-7124], primaryKey: false);
      insert('organizations', [ id: 107110, nci_institute_code: "TX084", name: "Tyler Hematology/Oncology", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX084",GROUP_DESC:"TX084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8125 ,protection_group_id: -7125, protection_element_id:-7125], primaryKey: false);
      insert('organizations', [ id: 107111, nci_institute_code: "TX085", name: "Baptist Memorial Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX085",GROUP_DESC:"TX085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8126 ,protection_group_id: -7126, protection_element_id:-7126], primaryKey: false);
      insert('organizations', [ id: 107112, nci_institute_code: "TX086", name: "Saint Elizabeth Hospital", city: "Beaumont", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX086",GROUP_DESC:"TX086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8127 ,protection_group_id: -7127, protection_element_id:-7127], primaryKey: false);
      insert('organizations', [ id: 107113, nci_institute_code: "TX087", name: "South Texas Medical Clinics", city: "Wharton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX087",GROUP_DESC:"TX087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8128 ,protection_group_id: -7128, protection_element_id:-7128], primaryKey: false);
      insert('organizations', [ id: 107114, nci_institute_code: "TX088", name: "Ben Taub General Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX088",GROUP_DESC:"TX088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8129 ,protection_group_id: -7129, protection_element_id:-7129], primaryKey: false);
      insert('organizations', [ id: 107115, nci_institute_code: "TX089", name: "Kaiser Permanente", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX089",GROUP_DESC:"TX089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8130 ,protection_group_id: -7130, protection_element_id:-7130], primaryKey: false);
      insert('organizations', [ id: 107116, nci_institute_code: "TX090", name: "Medical Center Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX090",GROUP_DESC:"TX090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8131 ,protection_group_id: -7131, protection_element_id:-7131], primaryKey: false);
      insert('organizations', [ id: 107117, nci_institute_code: "TX091", name: "Citizens Medical Center", city: "Victoria", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX091",GROUP_DESC:"TX091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8132 ,protection_group_id: -7132, protection_element_id:-7132], primaryKey: false);
      insert('organizations', [ id: 107118, nci_institute_code: "TX092", name: "Memorial Hermann Southwest Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX092",GROUP_DESC:"TX092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8133 ,protection_group_id: -7133, protection_element_id:-7133], primaryKey: false);
      insert('organizations', [ id: 107119, nci_institute_code: "TX093", name: "Valley Baptist Medical Center - Harlingen", city: "Harlingen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX093",GROUP_DESC:"TX093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8134 ,protection_group_id: -7134, protection_element_id:-7134], primaryKey: false);
      insert('organizations', [ id: 107120, nci_institute_code: "TX094", name: "Brazosport Regional Health System", city: "Lake Jackson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX094",GROUP_DESC:"TX094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8135 ,protection_group_id: -7135, protection_element_id:-7135], primaryKey: false);
      insert('organizations', [ id: 107121, nci_institute_code: "TX095", name: "University of Texas Medical School", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX095",GROUP_DESC:"TX095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8136 ,protection_group_id: -7136, protection_element_id:-7136], primaryKey: false);
      insert('organizations', [ id: 107122, nci_institute_code: "TX096", name: "Arlington Cancer Center", city: "Arlington", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX096",GROUP_DESC:"TX096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8137 ,protection_group_id: -7137, protection_element_id:-7137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 107123, nci_institute_code: "TX098", name: "Cancer Therapy and Research Center", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX098",GROUP_DESC:"TX098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8138 ,protection_group_id: -7138, protection_element_id:-7138], primaryKey: false);
      insert('organizations', [ id: 107124, nci_institute_code: "TX099", name: "Dell Children's Medical Center of Central Texas", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX099",GROUP_DESC:"TX099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8139 ,protection_group_id: -7139, protection_element_id:-7139], primaryKey: false);
      insert('organizations', [ id: 107125, nci_institute_code: "TX100", name: "Covenant Children's Hospital", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX100",GROUP_DESC:"TX100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8140 ,protection_group_id: -7140, protection_element_id:-7140], primaryKey: false);
      insert('organizations', [ id: 107126, nci_institute_code: "TX101", name: "Saint Luke's Episcopal Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX101",GROUP_DESC:"TX101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8141 ,protection_group_id: -7141, protection_element_id:-7141], primaryKey: false);
      insert('organizations', [ id: 107127, nci_institute_code: "TX102", name: "All Saints Episcopal", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX102",GROUP_DESC:"TX102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8142 ,protection_group_id: -7142, protection_element_id:-7142], primaryKey: false);
      insert('organizations', [ id: 107128, nci_institute_code: "TX103", name: "Providence Memorial Hospital", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX103",GROUP_DESC:"TX103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8143 ,protection_group_id: -7143, protection_element_id:-7143], primaryKey: false);
      insert('organizations', [ id: 107129, nci_institute_code: "TX104", name: "Houston Cancer Institute", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX104",GROUP_DESC:"TX104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8144 ,protection_group_id: -7144, protection_element_id:-7144], primaryKey: false);
      insert('organizations', [ id: 107130, nci_institute_code: "TX105", name: "Northwest Texas Hospitals", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX105",GROUP_DESC:"TX105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8145 ,protection_group_id: -7145, protection_element_id:-7145], primaryKey: false);
      insert('organizations', [ id: 107131, nci_institute_code: "TX107", name: "Columbia Medical Center", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX107",GROUP_DESC:"TX107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8146 ,protection_group_id: -7146, protection_element_id:-7146], primaryKey: false);
      insert('organizations', [ id: 107132, nci_institute_code: "TX108", name: "Longview Cancer Center", city: "Longview", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX108",GROUP_DESC:"TX108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8147 ,protection_group_id: -7147, protection_element_id:-7147], primaryKey: false);
      insert('organizations', [ id: 107133, nci_institute_code: "TX109", name: "Spring Branch Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX109",GROUP_DESC:"TX109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8148 ,protection_group_id: -7148, protection_element_id:-7148], primaryKey: false);
      insert('organizations', [ id: 107134, nci_institute_code: "TX110", name: "Detar Hospital", city: "Victoria", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX110",GROUP_DESC:"TX110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8149 ,protection_group_id: -7149, protection_element_id:-7149], primaryKey: false);
      insert('organizations', [ id: 107135, nci_institute_code: "TX111", name: "Northeast Medical Center Hospital", city: "Humble", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX111",GROUP_DESC:"TX111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8150 ,protection_group_id: -7150, protection_element_id:-7150], primaryKey: false);
      insert('organizations', [ id: 107136, nci_institute_code: "TX112", name: "San Antonio CCOP", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX112",GROUP_DESC:"TX112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8151 ,protection_group_id: -7151, protection_element_id:-7151], primaryKey: false);
      insert('organizations', [ id: 107137, nci_institute_code: "TX113", name: "Sierra Medical Center", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX113",GROUP_DESC:"TX113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8152 ,protection_group_id: -7152, protection_element_id:-7152], primaryKey: false);
      insert('organizations', [ id: 107138, nci_institute_code: "TX115", name: "Edwards Cancer Center", city: "Bedford", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX115",GROUP_DESC:"TX115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8153 ,protection_group_id: -7153, protection_element_id:-7153], primaryKey: false);
      insert('organizations', [ id: 107139, nci_institute_code: "TX116", name: "El Paso Ear Nose Throat Association", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX116",GROUP_DESC:"TX116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8154 ,protection_group_id: -7154, protection_element_id:-7154], primaryKey: false);
      insert('organizations', [ id: 107140, nci_institute_code: "TX117", name: "Central Texas Oncology Associates", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX117",GROUP_DESC:"TX117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8155 ,protection_group_id: -7155, protection_element_id:-7155], primaryKey: false);
      insert('organizations', [ id: 107141, nci_institute_code: "TX118", name: "Hematology Oncology Association of San Antonio Texas", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX118",GROUP_DESC:"TX118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8156 ,protection_group_id: -7156, protection_element_id:-7156], primaryKey: false);
      insert('organizations', [ id: 107142, nci_institute_code: "TX120", name: "The Stratum", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX120",GROUP_DESC:"TX120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8157 ,protection_group_id: -7157, protection_element_id:-7157], primaryKey: false);
      insert('organizations', [ id: 107143, nci_institute_code: "TX121", name: "Texas Oncology - South Austin Cancer Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX121",GROUP_DESC:"TX121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8158 ,protection_group_id: -7158, protection_element_id:-7158], primaryKey: false);
      insert('organizations', [ id: 107144, nci_institute_code: "TX122", name: "Memorial Hospital Southwest", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX122",GROUP_DESC:"TX122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8159 ,protection_group_id: -7159, protection_element_id:-7159], primaryKey: false);
      insert('organizations', [ id: 107145, nci_institute_code: "TX123", name: "Wichita Falls Clinic", city: "Wichita Falls", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX123",GROUP_DESC:"TX123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8160 ,protection_group_id: -7160, protection_element_id:-7160], primaryKey: false);
      insert('organizations', [ id: 107146, nci_institute_code: "TX124", name: "Texas Cancer Center-Abilene", city: "Abilene", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX124",GROUP_DESC:"TX124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8161 ,protection_group_id: -7161, protection_element_id:-7161], primaryKey: false);
      insert('organizations', [ id: 107147, nci_institute_code: "TX125", name: "Diagnostic Clinic of San Antonio", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX125",GROUP_DESC:"TX125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8162 ,protection_group_id: -7162, protection_element_id:-7162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 107148, nci_institute_code: "TX126", name: "Oncology Medical Associates", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX126",GROUP_DESC:"TX126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8163 ,protection_group_id: -7163, protection_element_id:-7163], primaryKey: false);
      insert('organizations', [ id: 107149, nci_institute_code: "TX127", name: "Diagnostic Clinic of Houston", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX127",GROUP_DESC:"TX127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8164 ,protection_group_id: -7164, protection_element_id:-7164], primaryKey: false);
      insert('organizations', [ id: 107150, nci_institute_code: "TX128", name: "Urologic Surgical Association PA", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX128",GROUP_DESC:"TX128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8165 ,protection_group_id: -7165, protection_element_id:-7165], primaryKey: false);
      insert('organizations', [ id: 107151, nci_institute_code: "TX129", name: "San Antonio Gastroenterology Assoc., Pa", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX129",GROUP_DESC:"TX129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8166 ,protection_group_id: -7166, protection_element_id:-7166], primaryKey: false);
      insert('organizations', [ id: 107152, nci_institute_code: "TX130", name: "Oncology Consultants, P.A.", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX130",GROUP_DESC:"TX130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8167 ,protection_group_id: -7167, protection_element_id:-7167], primaryKey: false);
      insert('organizations', [ id: 107153, nci_institute_code: "TX131", name: "Texas Oncology , P.A.", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX131",GROUP_DESC:"TX131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8168 ,protection_group_id: -7168, protection_element_id:-7168], primaryKey: false);
      insert('organizations', [ id: 107154, nci_institute_code: "TX132", name: "Texas Oncology PA", city: "Irving", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX132",GROUP_DESC:"TX132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8169 ,protection_group_id: -7169, protection_element_id:-7169], primaryKey: false);
      insert('organizations', [ id: 107155, nci_institute_code: "TX133", name: "El Paso Cancer Treatment Center", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX133",GROUP_DESC:"TX133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8170 ,protection_group_id: -7170, protection_element_id:-7170], primaryKey: false);
      insert('organizations', [ id: 107156, nci_institute_code: "TX134", name: "West Texas Cancer Center", city: "Odessa", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX134",GROUP_DESC:"TX134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8171 ,protection_group_id: -7171, protection_element_id:-7171], primaryKey: false);
      insert('organizations', [ id: 107157, nci_institute_code: "TX135", name: "Tarrant County Oncology Alliance", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX135",GROUP_DESC:"TX135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8172 ,protection_group_id: -7172, protection_element_id:-7172], primaryKey: false);
      insert('organizations', [ id: 107158, nci_institute_code: "TX136", name: "Southeast Dallas Health Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX136",GROUP_DESC:"TX136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8173 ,protection_group_id: -7173, protection_element_id:-7173], primaryKey: false);
      insert('organizations', [ id: 107159, nci_institute_code: "TX137", name: "Southwest Regional Cancer Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX137",GROUP_DESC:"TX137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8174 ,protection_group_id: -7174, protection_element_id:-7174], primaryKey: false);
      insert('organizations', [ id: 107160, nci_institute_code: "TX138", name: "Algur H. Meadows Imaging Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX138",GROUP_DESC:"TX138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8175 ,protection_group_id: -7175, protection_element_id:-7175], primaryKey: false);
      insert('organizations', [ id: 107161, nci_institute_code: "TX139", name: "West Texas Blood Cancer Center., Ste B & C", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX139",GROUP_DESC:"TX139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8176 ,protection_group_id: -7176, protection_element_id:-7176], primaryKey: false);
      insert('organizations', [ id: 107162, nci_institute_code: "TX140", name: "US Texas Pediatric CCOP", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX140",GROUP_DESC:"TX140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8177 ,protection_group_id: -7177, protection_element_id:-7177], primaryKey: false);
      insert('organizations', [ id: 107163, nci_institute_code: "TX141", name: "Houston Northwest Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX141",GROUP_DESC:"TX141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8178 ,protection_group_id: -7178, protection_element_id:-7178], primaryKey: false);
      insert('organizations', [ id: 107164, nci_institute_code: "TX142", name: "Columbia Medical Center of Plano", city: "Plano", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX142",GROUP_DESC:"TX142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8179 ,protection_group_id: -7179, protection_element_id:-7179], primaryKey: false);
      insert('organizations', [ id: 107165, nci_institute_code: "TX143", name: "Macgregor Medical Association", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX143",GROUP_DESC:"TX143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8180 ,protection_group_id: -7180, protection_element_id:-7180], primaryKey: false);
      insert('organizations', [ id: 107166, nci_institute_code: "TX144", name: "Waco Medcial Group", city: "Waco", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX144",GROUP_DESC:"TX144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8181 ,protection_group_id: -7181, protection_element_id:-7181], primaryKey: false);
      insert('organizations', [ id: 107167, nci_institute_code: "TX145", name: "Pediatric Surgical Associates", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX145",GROUP_DESC:"TX145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8182 ,protection_group_id: -7182, protection_element_id:-7182], primaryKey: false);
      insert('organizations', [ id: 107168, nci_institute_code: "TX146", name: "Texas Cancer Center-Sherman", city: "Sherman", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX146",GROUP_DESC:"TX146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8183 ,protection_group_id: -7183, protection_element_id:-7183], primaryKey: false);
      insert('organizations', [ id: 107169, nci_institute_code: "TX147", name: "Bayshore Cancer Center", city: "Pasadena", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX147",GROUP_DESC:"TX147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8184 ,protection_group_id: -7184, protection_element_id:-7184], primaryKey: false);
      insert('organizations', [ id: 107170, nci_institute_code: "TX149", name: "Oncology Hematology Assoc", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX149",GROUP_DESC:"TX149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8185 ,protection_group_id: -7185, protection_element_id:-7185], primaryKey: false);
      insert('organizations', [ id: 107171, nci_institute_code: "TX150", name: "Mocrief Cancer Center-The University of Texas Southwestern Medical Center", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX150",GROUP_DESC:"TX150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8186 ,protection_group_id: -7186, protection_element_id:-7186], primaryKey: false);
      insert('organizations', [ id: 107172, nci_institute_code: "TX151", name: "Plaza Medical Center", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX151",GROUP_DESC:"TX151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8187 ,protection_group_id: -7187, protection_element_id:-7187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 107173, nci_institute_code: "TX152", name: "Texas Oncology PA Beaumont", city: "Beaumont", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX152",GROUP_DESC:"TX152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8188 ,protection_group_id: -7188, protection_element_id:-7188], primaryKey: false);
      insert('organizations', [ id: 107174, nci_institute_code: "TX153", name: "North Texas Regional Cancer Center", city: "Plano", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX153",GROUP_DESC:"TX153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8189 ,protection_group_id: -7189, protection_element_id:-7189], primaryKey: false);
      insert('organizations', [ id: 107175, nci_institute_code: "TX154", name: "Tyler Cancer Center", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX154",GROUP_DESC:"TX154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8190 ,protection_group_id: -7190, protection_element_id:-7190], primaryKey: false);
      insert('organizations', [ id: 107176, nci_institute_code: "TX155", name: "Texas Oncology, El Paso", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX155",GROUP_DESC:"TX155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8191 ,protection_group_id: -7191, protection_element_id:-7191], primaryKey: false);
      insert('organizations', [ id: 107177, nci_institute_code: "TX157", name: "Allison Cancer Center", city: "Midland", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX157",GROUP_DESC:"TX157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8192 ,protection_group_id: -7192, protection_element_id:-7192], primaryKey: false);
      insert('organizations', [ id: 107178, nci_institute_code: "TX158", name: "CHRISTUS Santa Rosa Childrens Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX158",GROUP_DESC:"TX158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8193 ,protection_group_id: -7193, protection_element_id:-7193], primaryKey: false);
      insert('organizations', [ id: 107179, nci_institute_code: "TX159", name: "Texas Oncology, P.A.", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX159",GROUP_DESC:"TX159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8194 ,protection_group_id: -7194, protection_element_id:-7194], primaryKey: false);
      insert('organizations', [ id: 107180, nci_institute_code: "TX162", name: "Simmons Cancer Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX162",GROUP_DESC:"TX162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8195 ,protection_group_id: -7195, protection_element_id:-7195], primaryKey: false);
      insert('organizations', [ id: 107181, nci_institute_code: "TX163", name: "Hill County Memorial Hospital", city: "Frederickburg", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX163",GROUP_DESC:"TX163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8196 ,protection_group_id: -7196, protection_element_id:-7196], primaryKey: false);
      insert('organizations', [ id: 107182, nci_institute_code: "TX164", name: "Elmer Pacheco", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX164",GROUP_DESC:"TX164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8197 ,protection_group_id: -7197, protection_element_id:-7197], primaryKey: false);
      insert('organizations', [ id: 107183, nci_institute_code: "TX165", name: "Saint Joseph Regional Cancer Center", city: "Bryan", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX165",GROUP_DESC:"TX165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8198 ,protection_group_id: -7198, protection_element_id:-7198], primaryKey: false);
      insert('organizations', [ id: 107184, nci_institute_code: "TX166", name: "Baptist Hospital South East Texas", city: "Beaumont", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX166",GROUP_DESC:"TX166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8199 ,protection_group_id: -7199, protection_element_id:-7199], primaryKey: false);
      insert('organizations', [ id: 107185, nci_institute_code: "TX167", name: "Veterans Affairs Medical Center Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX167",GROUP_DESC:"TX167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8200 ,protection_group_id: -7200, protection_element_id:-7200], primaryKey: false);
      insert('organizations', [ id: 107186, nci_institute_code: "TX169", name: "The Cancer Center Associates", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX169",GROUP_DESC:"TX169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8201 ,protection_group_id: -7201, protection_element_id:-7201], primaryKey: false);
      insert('organizations', [ id: 107187, nci_institute_code: "TX170", name: "Children's Hematology & Oncology Center", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX170",GROUP_DESC:"TX170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8202 ,protection_group_id: -7202, protection_element_id:-7202], primaryKey: false);
      insert('organizations', [ id: 107188, nci_institute_code: "TX171", name: "Texas College of Osteo", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX171",GROUP_DESC:"TX171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8203 ,protection_group_id: -7203, protection_element_id:-7203], primaryKey: false);
      insert('organizations', [ id: 107189, nci_institute_code: "TX173", name: "University of Texas Health Science Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX173",GROUP_DESC:"TX173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8204 ,protection_group_id: -7204, protection_element_id:-7204], primaryKey: false);
      insert('organizations', [ id: 107190, nci_institute_code: "TX174", name: "Seton Medical Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX174",GROUP_DESC:"TX174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8205 ,protection_group_id: -7205, protection_element_id:-7205], primaryKey: false);
      insert('organizations', [ id: 107191, nci_institute_code: "TX175", name: "Hca Denton Regional Medical Center", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX175",GROUP_DESC:"TX175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8206 ,protection_group_id: -7206, protection_element_id:-7206], primaryKey: false);
      insert('organizations', [ id: 107192, nci_institute_code: "TX176", name: "Texas Cancer Center", city: "Abilene", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX176",GROUP_DESC:"TX176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8207 ,protection_group_id: -7207, protection_element_id:-7207], primaryKey: false);
      insert('organizations', [ id: 107193, nci_institute_code: "TX177", name: "Memorial Health System of East Texas/Arthur Temple Sr. Regional Cancer Center", city: "Lufkin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX177",GROUP_DESC:"TX177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8208 ,protection_group_id: -7208, protection_element_id:-7208], primaryKey: false);
      insert('organizations', [ id: 107194, nci_institute_code: "TX179", name: "Baylor Institute for Immunology Research", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX179",GROUP_DESC:"TX179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8209 ,protection_group_id: -7209, protection_element_id:-7209], primaryKey: false);
      insert('organizations', [ id: 107195, nci_institute_code: "TX180", name: "Baptist Health System", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX180",GROUP_DESC:"TX180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8210 ,protection_group_id: -7210, protection_element_id:-7210], primaryKey: false);
      insert('organizations', [ id: 107196, nci_institute_code: "TX181", name: "Covenant Medical Center", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX181",GROUP_DESC:"TX181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8211 ,protection_group_id: -7211, protection_element_id:-7211], primaryKey: false);
      insert('organizations', [ id: 107197, nci_institute_code: "TX182", name: "University Hospital", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX182",GROUP_DESC:"TX182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8212 ,protection_group_id: -7212, protection_element_id:-7212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 107198, nci_institute_code: "TX183", name: "Baylor Medical Center", city: "Garland", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX183",GROUP_DESC:"TX183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8213 ,protection_group_id: -7213, protection_element_id:-7213], primaryKey: false);
      insert('organizations', [ id: 107199, nci_institute_code: "TX184", name: "Richardson Regional Medical Center", city: "Richardson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX184",GROUP_DESC:"TX184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8214 ,protection_group_id: -7214, protection_element_id:-7214], primaryKey: false);
      insert('organizations', [ id: 107200, nci_institute_code: "TX185", name: "Baylor Medical Center at Irving", city: "Irving", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX185",GROUP_DESC:"TX185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8215 ,protection_group_id: -7215, protection_element_id:-7215], primaryKey: false);
      insert('organizations', [ id: 107201, nci_institute_code: "TX186", name: "Christus Spohn Hospital South", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX186",GROUP_DESC:"TX186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8216 ,protection_group_id: -7216, protection_element_id:-7216], primaryKey: false);
      insert('organizations', [ id: 107202, nci_institute_code: "TX187", name: "Saint David's Medical Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX187",GROUP_DESC:"TX187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8217 ,protection_group_id: -7217, protection_element_id:-7217], primaryKey: false);
      insert('organizations', [ id: 107203, nci_institute_code: "TX188", name: "Pediatric Hematology and Oncology Associates of South Texas", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX188",GROUP_DESC:"TX188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8218 ,protection_group_id: -7218, protection_element_id:-7218], primaryKey: false);
      insert('organizations', [ id: 107204, nci_institute_code: "TX189", name: "South Texas Oncology and Hematology PA", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX189",GROUP_DESC:"TX189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8219 ,protection_group_id: -7219, protection_element_id:-7219], primaryKey: false);
      insert('organizations', [ id: 107205, nci_institute_code: "TX190", name: "Doctor's Hospital of Laredo", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX190",GROUP_DESC:"TX190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8220 ,protection_group_id: -7220, protection_element_id:-7220], primaryKey: false);
      insert('organizations', [ id: 107206, nci_institute_code: "TX191", name: "Lubbock Diagnostic Clinic", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX191",GROUP_DESC:"TX191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8221 ,protection_group_id: -7221, protection_element_id:-7221], primaryKey: false);
      insert('organizations', [ id: 107207, nci_institute_code: "TX192", name: "University of North Texas Health Science Center", city: "Forth Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX192",GROUP_DESC:"TX192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8222 ,protection_group_id: -7222, protection_element_id:-7222], primaryKey: false);
      insert('organizations', [ id: 107208, nci_institute_code: "TX193", name: "Hope Cancer Center", city: "College Station", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX193",GROUP_DESC:"TX193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8223 ,protection_group_id: -7223, protection_element_id:-7223], primaryKey: false);
      insert('organizations', [ id: 107209, nci_institute_code: "TX194", name: "Dba Texas Cancer Care", city: "Forth Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX194",GROUP_DESC:"TX194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8224 ,protection_group_id: -7224, protection_element_id:-7224], primaryKey: false);
      insert('organizations', [ id: 107210, nci_institute_code: "TX195", name: "Zale Lipshy University Hospital", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX195",GROUP_DESC:"TX195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8225 ,protection_group_id: -7225, protection_element_id:-7225], primaryKey: false);
      insert('organizations', [ id: 107211, nci_institute_code: "TX197", name: "San Antonio Tumor and Blood Clinic", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX197",GROUP_DESC:"TX197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8226 ,protection_group_id: -7226, protection_element_id:-7226], primaryKey: false);
      insert('organizations', [ id: 107212, nci_institute_code: "TX198", name: "The Center for Cancer and Blood Disorders-Fort Worth", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX198",GROUP_DESC:"TX198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8227 ,protection_group_id: -7227, protection_element_id:-7227], primaryKey: false);
      insert('organizations', [ id: 107213, nci_institute_code: "TX199", name: "Denton Regional Medical Center", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX199",GROUP_DESC:"TX199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8228 ,protection_group_id: -7228, protection_element_id:-7228], primaryKey: false);
      insert('organizations', [ id: 107214, nci_institute_code: "TX200", name: "Las Palmas Regional Oncology", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX200",GROUP_DESC:"TX200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8229 ,protection_group_id: -7229, protection_element_id:-7229], primaryKey: false);
      insert('organizations', [ id: 107215, nci_institute_code: "TX201", name: "Las Palmas Hospital", city: "EI Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX201",GROUP_DESC:"TX201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8230 ,protection_group_id: -7230, protection_element_id:-7230], primaryKey: false);
      insert('organizations', [ id: 107216, nci_institute_code: "TX202", name: "Saint Luke's Baptist Health System", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX202",GROUP_DESC:"TX202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8231 ,protection_group_id: -7231, protection_element_id:-7231], primaryKey: false);
      insert('organizations', [ id: 107217, nci_institute_code: "TX203", name: "Harris Methodist Southwest", city: "Bedford", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX203",GROUP_DESC:"TX203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8232 ,protection_group_id: -7232, protection_element_id:-7232], primaryKey: false);
      insert('organizations', [ id: 107218, nci_institute_code: "TX205", name: "Lone Star Oncology Consultants, PA", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX205",GROUP_DESC:"TX205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8233 ,protection_group_id: -7233, protection_element_id:-7233], primaryKey: false);
      insert('organizations', [ id: 107219, nci_institute_code: "TX206", name: "Laredo Medical Center", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX206",GROUP_DESC:"TX206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8234 ,protection_group_id: -7234, protection_element_id:-7234], primaryKey: false);
      insert('organizations', [ id: 107220, nci_institute_code: "TX207", name: "South Texas Cancer Center", city: "Harlingen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX207",GROUP_DESC:"TX207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8235 ,protection_group_id: -7235, protection_element_id:-7235], primaryKey: false);
      insert('organizations', [ id: 107221, nci_institute_code: "TX208", name: "Waco Cancer Care/Res Ctr/Providence Hosp", city: "Waco", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX208",GROUP_DESC:"TX208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8236 ,protection_group_id: -7236, protection_element_id:-7236], primaryKey: false);
      insert('organizations', [ id: 107222, nci_institute_code: "TX209", name: "Beeler-Manske Clinic", city: "Texas City", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX209",GROUP_DESC:"TX209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8237 ,protection_group_id: -7237, protection_element_id:-7237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 107223, nci_institute_code: "TX210", name: "Harold C. Simmons Cancer Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX210",GROUP_DESC:"TX210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8238 ,protection_group_id: -7238, protection_element_id:-7238], primaryKey: false);
      insert('organizations', [ id: 107224, nci_institute_code: "TX211", name: "Bexar County Medical Hem. & Onc. Specialists, P.A.", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX211",GROUP_DESC:"TX211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8239 ,protection_group_id: -7239, protection_element_id:-7239], primaryKey: false);
      insert('organizations', [ id: 107225, nci_institute_code: "TX212", name: "Network Cancer Care", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX212",GROUP_DESC:"TX212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8240 ,protection_group_id: -7240, protection_element_id:-7240], primaryKey: false);
      insert('organizations', [ id: 107226, nci_institute_code: "TX213", name: "Cancer Care Centers of South Texas - Village Drive South", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX213",GROUP_DESC:"TX213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8241 ,protection_group_id: -7241, protection_element_id:-7241], primaryKey: false);
      insert('organizations', [ id: 107227, nci_institute_code: "TX214", name: "Flight Medicine Clinic", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX214",GROUP_DESC:"TX214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8242 ,protection_group_id: -7242, protection_element_id:-7242], primaryKey: false);
      insert('organizations', [ id: 107228, nci_institute_code: "TX215", name: "Thomas Street Clinic", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX215",GROUP_DESC:"TX215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8243 ,protection_group_id: -7243, protection_element_id:-7243], primaryKey: false);
      insert('organizations', [ id: 107229, nci_institute_code: "TX217", name: "University of Texas Southwest Medical Center", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX217",GROUP_DESC:"TX217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8244 ,protection_group_id: -7244, protection_element_id:-7244], primaryKey: false);
      insert('organizations', [ id: 107230, nci_institute_code: "TX218", name: "Southwest Urology Associates", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX218",GROUP_DESC:"TX218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8245 ,protection_group_id: -7245, protection_element_id:-7245], primaryKey: false);
      insert('organizations', [ id: 107231, nci_institute_code: "TX219", name: "University of Texas Medical Branch-Family Medicine", city: "Galveston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX219",GROUP_DESC:"TX219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8246 ,protection_group_id: -7246, protection_element_id:-7246], primaryKey: false);
      insert('organizations', [ id: 107232, nci_institute_code: "TX220", name: "Urology Clinics of North Texas", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX220",GROUP_DESC:"TX220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8247 ,protection_group_id: -7247, protection_element_id:-7247], primaryKey: false);
      insert('organizations', [ id: 107233, nci_institute_code: "TX221", name: "South Texas Oncology and Hematology, P. A.", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX221",GROUP_DESC:"TX221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8248 ,protection_group_id: -7248, protection_element_id:-7248], primaryKey: false);
      insert('organizations', [ id: 107234, nci_institute_code: "TX222", name: "Doctors Regional Cancer Treatment Center", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX222",GROUP_DESC:"TX222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8249 ,protection_group_id: -7249, protection_element_id:-7249], primaryKey: false);
      insert('organizations', [ id: 107235, nci_institute_code: "TX223", name: "Texas Prostate Branchtherapy Services", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX223",GROUP_DESC:"TX223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8250 ,protection_group_id: -7250, protection_element_id:-7250], primaryKey: false);
      insert('organizations', [ id: 107236, nci_institute_code: "TX224", name: "Dallas Surgical Group", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX224",GROUP_DESC:"TX224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8251 ,protection_group_id: -7251, protection_element_id:-7251], primaryKey: false);
      insert('organizations', [ id: 107237, nci_institute_code: "TX225", name: "Center for Oncology Research and Treatment", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX225",GROUP_DESC:"TX225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8252 ,protection_group_id: -7252, protection_element_id:-7252], primaryKey: false);
      insert('organizations', [ id: 107238, nci_institute_code: "TX226", name: "Temple Cancer Clinic", city: "Temple", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX226",GROUP_DESC:"TX226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8253 ,protection_group_id: -7253, protection_element_id:-7253], primaryKey: false);
      insert('organizations', [ id: 107239, nci_institute_code: "TX227", name: "Covenant Medical Arts Clinic", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX227",GROUP_DESC:"TX227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8254 ,protection_group_id: -7254, protection_element_id:-7254], primaryKey: false);
      insert('organizations', [ id: 107240, nci_institute_code: "TX228", name: "Texas Transplant Institute", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX228",GROUP_DESC:"TX228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8255 ,protection_group_id: -7255, protection_element_id:-7255], primaryKey: false);
      insert('organizations', [ id: 107241, nci_institute_code: "TX229", name: "West Texas Surgical Associates", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX229",GROUP_DESC:"TX229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8256 ,protection_group_id: -7256, protection_element_id:-7256], primaryKey: false);
      insert('organizations', [ id: 107242, nci_institute_code: "TX230", name: "South Texas Oncology & Hematology, P.A.", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX230",GROUP_DESC:"TX230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8257 ,protection_group_id: -7257, protection_element_id:-7257], primaryKey: false);
      insert('organizations', [ id: 107243, nci_institute_code: "TX231", name: "Vannie Cook Children's Clinic", city: "McAllen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX231",GROUP_DESC:"TX231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8258 ,protection_group_id: -7258, protection_element_id:-7258], primaryKey: false);
      insert('organizations', [ id: 107244, nci_institute_code: "TX232", name: "Valley Oncology Pharmacy", city: "McAllen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX232",GROUP_DESC:"TX232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8259 ,protection_group_id: -7259, protection_element_id:-7259], primaryKey: false);
      insert('organizations', [ id: 107245, nci_institute_code: "TX233", name: "Capital Surgeons Group, PLLC", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX233",GROUP_DESC:"TX233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8260 ,protection_group_id: -7260, protection_element_id:-7260], primaryKey: false);
      insert('organizations', [ id: 107246, nci_institute_code: "TX234", name: "Las Colinas Hematology/Oncology", city: "Irving", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX234",GROUP_DESC:"TX234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8261 ,protection_group_id: -7261, protection_element_id:-7261], primaryKey: false);
      insert('organizations', [ id: 107247, nci_institute_code: "TX235", name: "Methodist Charlton Medical Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX235",GROUP_DESC:"TX235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8262 ,protection_group_id: -7262, protection_element_id:-7262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 107248, nci_institute_code: "TX236", name: "Covenant Children's Hospital", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX236",GROUP_DESC:"TX236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8263 ,protection_group_id: -7263, protection_element_id:-7263], primaryKey: false);
      insert('organizations', [ id: 107249, nci_institute_code: "TX237", name: "Amarillo Veterans Administration Health Care System", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX237",GROUP_DESC:"TX237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8264 ,protection_group_id: -7264, protection_element_id:-7264], primaryKey: false);
      insert('organizations', [ id: 107250, nci_institute_code: "TX238", name: "Cancer Care Centers of South Texas - Stone Oak", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX238",GROUP_DESC:"TX238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8265 ,protection_group_id: -7265, protection_element_id:-7265], primaryKey: false);
      insert('organizations', [ id: 107251, nci_institute_code: "TX239", name: "Julie and Ben Rogers Cancer Institute", city: "Beaumont", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX239",GROUP_DESC:"TX239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8266 ,protection_group_id: -7266, protection_element_id:-7266], primaryKey: false);
      insert('organizations', [ id: 107252, nci_institute_code: "TX240", name: "Collom & Carney Clinic", city: "Texarkana", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX240",GROUP_DESC:"TX240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8267 ,protection_group_id: -7267, protection_element_id:-7267], primaryKey: false);
      insert('organizations', [ id: 107253, nci_institute_code: "TX241", name: "Texas Oncology PA", city: "Wichita Falls", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX241",GROUP_DESC:"TX241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8268 ,protection_group_id: -7268, protection_element_id:-7268], primaryKey: false);
      insert('organizations', [ id: 107254, nci_institute_code: "TX242", name: "San Antonio Pediatric Surgical Associates", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX242",GROUP_DESC:"TX242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8269 ,protection_group_id: -7269, protection_element_id:-7269], primaryKey: false);
      insert('organizations', [ id: 107255, nci_institute_code: "TX243", name: "Dallas Oncology Consultants", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX243",GROUP_DESC:"TX243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8270 ,protection_group_id: -7270, protection_element_id:-7270], primaryKey: false);
      insert('organizations', [ id: 107256, nci_institute_code: "TX244", name: "Coastal Ben Oncology", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX244",GROUP_DESC:"TX244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8271 ,protection_group_id: -7271, protection_element_id:-7271], primaryKey: false);
      insert('organizations', [ id: 107257, nci_institute_code: "TX245", name: "Blood and Cancer Center of East Texas", city: "Tyler", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX245",GROUP_DESC:"TX245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8272 ,protection_group_id: -7272, protection_element_id:-7272], primaryKey: false);
      insert('organizations', [ id: 107258, nci_institute_code: "TX246", name: "Salem Oncology Centre", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX246",GROUP_DESC:"TX246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8273 ,protection_group_id: -7273, protection_element_id:-7273], primaryKey: false);
      insert('organizations', [ id: 107259, nci_institute_code: "TX247", name: "Houston Northwest Radiotherapy Center, LLC", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX247",GROUP_DESC:"TX247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8274 ,protection_group_id: -7274, protection_element_id:-7274], primaryKey: false);
      insert('organizations', [ id: 107260, nci_institute_code: "TX248", name: "The Center for Integrative Cancer Medicine", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX248",GROUP_DESC:"TX248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8275 ,protection_group_id: -7275, protection_element_id:-7275], primaryKey: false);
      insert('organizations', [ id: 107261, nci_institute_code: "TX249", name: "Southeast Texas Oncology Partners", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX249",GROUP_DESC:"TX249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8276 ,protection_group_id: -7276, protection_element_id:-7276], primaryKey: false);
      insert('organizations', [ id: 107262, nci_institute_code: "TX250", name: "Cancer Physicians Associated", city: "College Station", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX250",GROUP_DESC:"TX250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8277 ,protection_group_id: -7277, protection_element_id:-7277], primaryKey: false);
      insert('organizations', [ id: 107263, nci_institute_code: "TX251", name: "Synergos, Inc.", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX251",GROUP_DESC:"TX251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8278 ,protection_group_id: -7278, protection_element_id:-7278], primaryKey: false);
      insert('organizations', [ id: 107264, nci_institute_code: "TX252", name: "University of Texas", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX252",GROUP_DESC:"TX252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8279 ,protection_group_id: -7279, protection_element_id:-7279], primaryKey: false);
      insert('organizations', [ id: 107265, nci_institute_code: "TX253", name: "North Texas Colen and Rectal", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX253",GROUP_DESC:"TX253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8280 ,protection_group_id: -7280, protection_element_id:-7280], primaryKey: false);
      insert('organizations', [ id: 107266, nci_institute_code: "TX254", name: "Texas Pulmonary and Critical Care Consultants PA", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX254",GROUP_DESC:"TX254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8281 ,protection_group_id: -7281, protection_element_id:-7281], primaryKey: false);
      insert('organizations', [ id: 107267, nci_institute_code: "TX255", name: "South Texas Oncology and Hematology, P.A.", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX255",GROUP_DESC:"TX255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8282 ,protection_group_id: -7282, protection_element_id:-7282], primaryKey: false);
      insert('organizations', [ id: 107268, nci_institute_code: "TX256", name: "Baylor Regional Medical Center At Grapevine", city: "Grapevine", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX256",GROUP_DESC:"TX256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8283 ,protection_group_id: -7283, protection_element_id:-7283], primaryKey: false);
      insert('organizations', [ id: 107269, nci_institute_code: "TX257", name: "San Jacinto Cancer Center", city: "Baytown", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX257",GROUP_DESC:"TX257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8284 ,protection_group_id: -7284, protection_element_id:-7284], primaryKey: false);
      insert('organizations', [ id: 107270, nci_institute_code: "TX258", name: "Texas Oncology - Round Rock Cancer Center", city: "Round Rock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX258",GROUP_DESC:"TX258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8285 ,protection_group_id: -7285, protection_element_id:-7285], primaryKey: false);
      insert('organizations', [ id: 107271, nci_institute_code: "TX259", name: "Texas Cancer Center Dallas Southwest", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX259",GROUP_DESC:"TX259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8286 ,protection_group_id: -7286, protection_element_id:-7286], primaryKey: false);
      insert('organizations', [ id: 107272, nci_institute_code: "TX260", name: "Texas Cancer Clinic", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX260",GROUP_DESC:"TX260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8287 ,protection_group_id: -7287, protection_element_id:-7287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 107273, nci_institute_code: "TX261", name: "Medical Oncology Hematology of South Texas", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX261",GROUP_DESC:"TX261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8288 ,protection_group_id: -7288, protection_element_id:-7288], primaryKey: false);
      insert('organizations', [ id: 107274, nci_institute_code: "TX262", name: "Cancer Care Centers of South Texas - Fredericksburg", city: "Fredericksburg", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX262",GROUP_DESC:"TX262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8289 ,protection_group_id: -7289, protection_element_id:-7289], primaryKey: false);
      insert('organizations', [ id: 107275, nci_institute_code: "TX263", name: "South Texas Urology & Urologic Oncology, PA", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX263",GROUP_DESC:"TX263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8290 ,protection_group_id: -7290, protection_element_id:-7290], primaryKey: false);
      insert('organizations', [ id: 107276, nci_institute_code: "TX264", name: "Texas Oncology, PA", city: "Waco", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX264",GROUP_DESC:"TX264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8291 ,protection_group_id: -7291, protection_element_id:-7291], primaryKey: false);
      insert('organizations', [ id: 107277, nci_institute_code: "TX265", name: "Dallas Oncology Consultants, PA", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX265",GROUP_DESC:"TX265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8292 ,protection_group_id: -7292, protection_element_id:-7292], primaryKey: false);
      insert('organizations', [ id: 107278, nci_institute_code: "TX266", name: "Cancer Care Centers of South Texas - Village Drive North", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX266",GROUP_DESC:"TX266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8293 ,protection_group_id: -7293, protection_element_id:-7293], primaryKey: false);
      insert('organizations', [ id: 107279, nci_institute_code: "TX267", name: "Texas Oncology PA", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX267",GROUP_DESC:"TX267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8294 ,protection_group_id: -7294, protection_element_id:-7294], primaryKey: false);
      insert('organizations', [ id: 107280, nci_institute_code: "TX268", name: "Deke Slayton Cancer Center", city: "Webster", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX268",GROUP_DESC:"TX268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8295 ,protection_group_id: -7295, protection_element_id:-7295], primaryKey: false);
      insert('organizations', [ id: 107281, nci_institute_code: "TX269", name: "General and Oncology Surgery Associates", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX269",GROUP_DESC:"TX269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8296 ,protection_group_id: -7296, protection_element_id:-7296], primaryKey: false);
      insert('organizations', [ id: 107282, nci_institute_code: "TX270", name: "The Surgical Institute", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX270",GROUP_DESC:"TX270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8297 ,protection_group_id: -7297, protection_element_id:-7297], primaryKey: false);
      insert('organizations', [ id: 107283, nci_institute_code: "TX271", name: "The Methodist Hospital Breast Care Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX271",GROUP_DESC:"TX271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8298 ,protection_group_id: -7298, protection_element_id:-7298], primaryKey: false);
      insert('organizations', [ id: 107284, nci_institute_code: "TX272", name: "Children's Ambulatory Blood and Cancer Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX272",GROUP_DESC:"TX272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8299 ,protection_group_id: -7299, protection_element_id:-7299], primaryKey: false);
      insert('organizations', [ id: 107285, nci_institute_code: "TX273", name: "Urology San Antonio Research PA", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX273",GROUP_DESC:"TX273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8300 ,protection_group_id: -7300, protection_element_id:-7300], primaryKey: false);
      insert('organizations', [ id: 107286, nci_institute_code: "TX274", name: "Dallas Cancer Specialists PA", city: "Garland", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX274",GROUP_DESC:"TX274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8301 ,protection_group_id: -7301, protection_element_id:-7301], primaryKey: false);
      insert('organizations', [ id: 107287, nci_institute_code: "TX275", name: "Texas Oncology PA", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX275",GROUP_DESC:"TX275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8302 ,protection_group_id: -7302, protection_element_id:-7302], primaryKey: false);
      insert('organizations', [ id: 107288, nci_institute_code: "TX276", name: "Texas Cancer Center - Mesquite", city: "Mesquite", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX276",GROUP_DESC:"TX276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8303 ,protection_group_id: -7303, protection_element_id:-7303], primaryKey: false);
      insert('organizations', [ id: 107289, nci_institute_code: "TX277", name: "Dallas Fort Worth Sarcoma Group P A", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX277",GROUP_DESC:"TX277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8304 ,protection_group_id: -7304, protection_element_id:-7304], primaryKey: false);
      insert('organizations', [ id: 107290, nci_institute_code: "TX278", name: "Texas Cancer Center - Medical City Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX278",GROUP_DESC:"TX278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8305 ,protection_group_id: -7305, protection_element_id:-7305], primaryKey: false);
      insert('organizations', [ id: 107291, nci_institute_code: "TX279", name: "Texas Oncology PA- Bedford", city: "Bedford", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX279",GROUP_DESC:"TX279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8306 ,protection_group_id: -7306, protection_element_id:-7306], primaryKey: false);
      insert('organizations', [ id: 107292, nci_institute_code: "TX280", name: "Texas Oncology - Central Austin Cancer Center", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX280",GROUP_DESC:"TX280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8307 ,protection_group_id: -7307, protection_element_id:-7307], primaryKey: false);
      insert('organizations', [ id: 107293, nci_institute_code: "TX281", name: "Surgical Associates of Irving-Coppell LLP", city: "Irving", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX281",GROUP_DESC:"TX281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8308 ,protection_group_id: -7308, protection_element_id:-7308], primaryKey: false);
      insert('organizations', [ id: 107294, nci_institute_code: "TX282", name: "Baylor Regional Medical Center at Plano", city: "Plano", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX282",GROUP_DESC:"TX282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8309 ,protection_group_id: -7309, protection_element_id:-7309], primaryKey: false);
      insert('organizations', [ id: 107295, nci_institute_code: "TX283", name: "Richardson Regional Medical Center - North Collins", city: "Richardson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX283",GROUP_DESC:"TX283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8310 ,protection_group_id: -7310, protection_element_id:-7310], primaryKey: false);
      insert('organizations', [ id: 107296, nci_institute_code: "TX284", name: "Cancer Specialists of South Texas PA", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX284",GROUP_DESC:"TX284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8311 ,protection_group_id: -7311, protection_element_id:-7311], primaryKey: false);
      insert('organizations', [ id: 107297, nci_institute_code: "TX285", name: "Cardiothoracic and Vascular Surgeons", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX285",GROUP_DESC:"TX285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8312 ,protection_group_id: -7312, protection_element_id:-7312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 107298, nci_institute_code: "TX286", name: "Joe Arrington Cancer Research and Treatment Center", city: "Lubbock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX286",GROUP_DESC:"TX286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8313 ,protection_group_id: -7313, protection_element_id:-7313], primaryKey: false);
      insert('organizations', [ id: 107299, nci_institute_code: "TX287", name: "Kelsey Research Foundation", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX287",GROUP_DESC:"TX287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8314 ,protection_group_id: -7314, protection_element_id:-7314], primaryKey: false);
      insert('organizations', [ id: 107300, nci_institute_code: "TX288", name: "Mary Crowley Medical Research Center", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX288",GROUP_DESC:"TX288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8315 ,protection_group_id: -7315, protection_element_id:-7315], primaryKey: false);
      insert('organizations', [ id: 107301, nci_institute_code: "TX289", name: "Colorectal Surgical Associates PA", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX289",GROUP_DESC:"TX289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8316 ,protection_group_id: -7316, protection_element_id:-7316], primaryKey: false);
      insert('organizations', [ id: 107302, nci_institute_code: "TX290", name: "Colorectal Surgical Associates PA", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX290",GROUP_DESC:"TX290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8317 ,protection_group_id: -7317, protection_element_id:-7317], primaryKey: false);
      insert('organizations', [ id: 107303, nci_institute_code: "TX291", name: "LEM Consulting LLC", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX291",GROUP_DESC:"TX291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8318 ,protection_group_id: -7318, protection_element_id:-7318], primaryKey: false);
      insert('organizations', [ id: 107304, nci_institute_code: "TX292", name: "Texas Oncology PA - Amarillo", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX292",GROUP_DESC:"TX292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8319 ,protection_group_id: -7319, protection_element_id:-7319], primaryKey: false);
      insert('organizations', [ id: 107305, nci_institute_code: "TX293", name: "Todd Shenkenberg MD PA", city: "Harlingen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX293",GROUP_DESC:"TX293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8320 ,protection_group_id: -7320, protection_element_id:-7320], primaryKey: false);
      insert('organizations', [ id: 107306, nci_institute_code: "TX294", name: "Coastal Bend Cancer Center", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX294",GROUP_DESC:"TX294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8321 ,protection_group_id: -7321, protection_element_id:-7321], primaryKey: false);
      insert('organizations', [ id: 107307, nci_institute_code: "TX295", name: "Christus Spohn Shoreline Hospital", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX295",GROUP_DESC:"TX295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8322 ,protection_group_id: -7322, protection_element_id:-7322], primaryKey: false);
      insert('organizations', [ id: 107308, nci_institute_code: "TX296", name: "Cancer Treatment Institute", city: "El Paso", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX296",GROUP_DESC:"TX296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8323 ,protection_group_id: -7323, protection_element_id:-7323], primaryKey: false);
      insert('organizations', [ id: 107309, nci_institute_code: "TX297", name: "Surgical Diseases of the Breast", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX297",GROUP_DESC:"TX297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8324 ,protection_group_id: -7324, protection_element_id:-7324], primaryKey: false);
      insert('organizations', [ id: 107310, nci_institute_code: "TX298", name: "Cancer Therapy Research Center Institute for Drug Development", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX298",GROUP_DESC:"TX298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8325 ,protection_group_id: -7325, protection_element_id:-7325], primaryKey: false);
      insert('organizations', [ id: 107311, nci_institute_code: "TX299", name: "Elizabeth Jekot MD Breast Imaging Center", city: "Richardson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX299",GROUP_DESC:"TX299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8326 ,protection_group_id: -7326, protection_element_id:-7326], primaryKey: false);
      insert('organizations', [ id: 107312, nci_institute_code: "TX300", name: "Radiation Treatment Center at Bellaire", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX300",GROUP_DESC:"TX300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8327 ,protection_group_id: -7327, protection_element_id:-7327], primaryKey: false);
      insert('organizations', [ id: 107313, nci_institute_code: "TX301", name: "Hope Oncology", city: "Richardson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX301",GROUP_DESC:"TX301 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX301",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX301",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX301", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8328 ,protection_group_id: -7328, protection_element_id:-7328], primaryKey: false);
      insert('organizations', [ id: 107314, nci_institute_code: "TX302", name: "Mary Milam MD PA", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX302",GROUP_DESC:"TX302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8329 ,protection_group_id: -7329, protection_element_id:-7329], primaryKey: false);
      insert('organizations', [ id: 107315, nci_institute_code: "TX303", name: "Rice University", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX303",GROUP_DESC:"TX303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8330 ,protection_group_id: -7330, protection_element_id:-7330], primaryKey: false);
      insert('organizations', [ id: 107316, nci_institute_code: "TX304", name: "Driscoll Children's Specialty Clinic", city: "McAllen", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX304",GROUP_DESC:"TX304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8331 ,protection_group_id: -7331, protection_element_id:-7331], primaryKey: false);
      insert('organizations', [ id: 107317, nci_institute_code: "TX305", name: "Richard A Artim MD PA", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX305",GROUP_DESC:"TX305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8332 ,protection_group_id: -7332, protection_element_id:-7332], primaryKey: false);
      insert('organizations', [ id: 107318, nci_institute_code: "TX306", name: "The Methodist Hospital Research Institute", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX306",GROUP_DESC:"TX306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8333 ,protection_group_id: -7333, protection_element_id:-7333], primaryKey: false);
      insert('organizations', [ id: 107319, nci_institute_code: "TX307", name: "Unzeitig Gary Walter MD (Office)", city: "Laredo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX307",GROUP_DESC:"TX307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8334 ,protection_group_id: -7334, protection_element_id:-7334], primaryKey: false);
      insert('organizations', [ id: 107320, nci_institute_code: "TX308", name: "Texas Oncology Cancer Center Sugar Land", city: "Sugar Land", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX308",GROUP_DESC:"TX308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8335 ,protection_group_id: -7335, protection_element_id:-7335], primaryKey: false);
      insert('organizations', [ id: 107321, nci_institute_code: "TX309", name: "South Texas Accelerated Research Therapeutics", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX309",GROUP_DESC:"TX309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8336 ,protection_group_id: -7336, protection_element_id:-7336], primaryKey: false);
      insert('organizations', [ id: 107322, nci_institute_code: "TX310", name: "Baylor Breast Care Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX310",GROUP_DESC:"TX310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8337 ,protection_group_id: -7337, protection_element_id:-7337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 107323, nci_institute_code: "TX311", name: "Texas Oncology - Midtown Austin", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX311",GROUP_DESC:"TX311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8338 ,protection_group_id: -7338, protection_element_id:-7338], primaryKey: false);
      insert('organizations', [ id: 107324, nci_institute_code: "TX312", name: "Titus Regional Medical Center", city: "Mount Pleasant", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX312",GROUP_DESC:"TX312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8339 ,protection_group_id: -7339, protection_element_id:-7339], primaryKey: false);
      insert('organizations', [ id: 107325, nci_institute_code: "TX313", name: "The Klabzuba Cancer Center", city: "Fort Worth", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX313",GROUP_DESC:"TX313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8340 ,protection_group_id: -7340, protection_element_id:-7340], primaryKey: false);
      insert('organizations', [ id: 107326, nci_institute_code: "TX314", name: "The Center for Cancer and Blood Disorders-Weatherford", city: "Weatherford", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX314",GROUP_DESC:"TX314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8341 ,protection_group_id: -7341, protection_element_id:-7341], primaryKey: false);
      insert('organizations', [ id: 107327, nci_institute_code: "TX315", name: "The Center for Cancer and Blood Disorders-Burleson", city: "Burleson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX315",GROUP_DESC:"TX315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8342 ,protection_group_id: -7342, protection_element_id:-7342], primaryKey: false);
      insert('organizations', [ id: 107328, nci_institute_code: "TX316", name: "The Center for Cancer and Blood Disorders-Cleburne", city: "Cleburne", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX316",GROUP_DESC:"TX316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8343 ,protection_group_id: -7343, protection_element_id:-7343], primaryKey: false);
      insert('organizations', [ id: 107329, nci_institute_code: "TX317", name: "The Center for Cancer and Blood Disorders-Mineral Wells", city: "Mineral Wells", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX317",GROUP_DESC:"TX317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8344 ,protection_group_id: -7344, protection_element_id:-7344], primaryKey: false);
      insert('organizations', [ id: 107330, nci_institute_code: "UBUENA", name: "University of Buenos Aires", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UBUENA",GROUP_DESC:"UBUENA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UBUENA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UBUENA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UBUENA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8345 ,protection_group_id: -7345, protection_element_id:-7345], primaryKey: false);
      insert('organizations', [ id: 107331, nci_institute_code: "UKCCSG", name: "United Kingdom Children's Cancer Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UKCCSG",GROUP_DESC:"UKCCSG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UKCCSG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UKCCSG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UKCCSG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8346 ,protection_group_id: -7346, protection_element_id:-7346], primaryKey: false);
      insert('organizations', [ id: 107332, nci_institute_code: "UORG", name: "Uro-Oncology Research Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UORG",GROUP_DESC:"UORG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UORG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UORG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UORG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8347 ,protection_group_id: -7347, protection_element_id:-7347], primaryKey: false);
      insert('organizations', [ id: 107333, nci_institute_code: "UT001", name: "Holy Cross Hospital", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT001",GROUP_DESC:"UT001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8348 ,protection_group_id: -7348, protection_element_id:-7348], primaryKey: false);
      insert('organizations', [ id: 107334, nci_institute_code: "UT002", name: "Primary Children's Medical Center", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT002",GROUP_DESC:"UT002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8349 ,protection_group_id: -7349, protection_element_id:-7349], primaryKey: false);
      insert('organizations', [ id: 107335, nci_institute_code: "UT003", name: "University of Utah", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT003",GROUP_DESC:"UT003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8350 ,protection_group_id: -7350, protection_element_id:-7350], primaryKey: false);
      insert('organizations', [ id: 107336, nci_institute_code: "UT004", name: "LDS Hospital", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT004",GROUP_DESC:"UT004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8351 ,protection_group_id: -7351, protection_element_id:-7351], primaryKey: false);
      insert('organizations', [ id: 107337, nci_institute_code: "UT005", name: "Salt Lake City Veterans Affairs Medical Center", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT005",GROUP_DESC:"UT005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8352 ,protection_group_id: -7352, protection_element_id:-7352], primaryKey: false);
      insert('organizations', [ id: 107338, nci_institute_code: "UT006", name: "Saint Benedicts Hospital", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT006",GROUP_DESC:"UT006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8353 ,protection_group_id: -7353, protection_element_id:-7353], primaryKey: false);
      insert('organizations', [ id: 107339, nci_institute_code: "UT007", name: "McKay-Dee Hospital Center", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT007",GROUP_DESC:"UT007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8354 ,protection_group_id: -7354, protection_element_id:-7354], primaryKey: false);
      insert('organizations', [ id: 107340, nci_institute_code: "UT008", name: "Utah Valley Regional Medical Center", city: "Provo", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT008",GROUP_DESC:"UT008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8355 ,protection_group_id: -7355, protection_element_id:-7355], primaryKey: false);
      insert('organizations', [ id: 107341, nci_institute_code: "UT009", name: "Logan Regional Hospital", city: "Logan", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT009",GROUP_DESC:"UT009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8356 ,protection_group_id: -7356, protection_element_id:-7356], primaryKey: false);
      insert('organizations', [ id: 107342, nci_institute_code: "UT010", name: "Dixie Medical Center Regional Cancer Center", city: "St. George", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT010",GROUP_DESC:"UT010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8357 ,protection_group_id: -7357, protection_element_id:-7357], primaryKey: false);
      insert('organizations', [ id: 107343, nci_institute_code: "UT011", name: "Hca Saint Mark's Hospital", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT011",GROUP_DESC:"UT011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8358 ,protection_group_id: -7358, protection_element_id:-7358], primaryKey: false);
      insert('organizations', [ id: 107344, nci_institute_code: "UT012", name: "Cottonwood Hospital Medical Center", city: "Murray", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT012",GROUP_DESC:"UT012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8359 ,protection_group_id: -7359, protection_element_id:-7359], primaryKey: false);
      insert('organizations', [ id: 107345, nci_institute_code: "UT013", name: "Fhp Utah Hospital", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT013",GROUP_DESC:"UT013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8360 ,protection_group_id: -7360, protection_element_id:-7360], primaryKey: false);
      insert('organizations', [ id: 107346, nci_institute_code: "UT014", name: "Ogden Regional Medical Center", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT014",GROUP_DESC:"UT014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8361 ,protection_group_id: -7361, protection_element_id:-7361], primaryKey: false);
      insert('organizations', [ id: 107347, nci_institute_code: "UT015", name: "Ihc Health Center Salt Lake Clinic", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT015",GROUP_DESC:"UT015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8362 ,protection_group_id: -7362, protection_element_id:-7362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 107348, nci_institute_code: "UT016", name: "Central Utah Clinic", city: "Provo", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT016",GROUP_DESC:"UT016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8363 ,protection_group_id: -7363, protection_element_id:-7363], primaryKey: false);
      insert('organizations', [ id: 107349, nci_institute_code: "UT018", name: "Special Gynecology and Oncology", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT018",GROUP_DESC:"UT018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8364 ,protection_group_id: -7364, protection_element_id:-7364], primaryKey: false);
      insert('organizations', [ id: 107350, nci_institute_code: "UT019", name: "Utah Cancer Specialists-Salt Lake City", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT019",GROUP_DESC:"UT019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8365 ,protection_group_id: -7365, protection_element_id:-7365], primaryKey: false);
      insert('organizations', [ id: 107351, nci_institute_code: "UT020", name: "Wasatch Hematology/Oncology Associates", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT020",GROUP_DESC:"UT020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8366 ,protection_group_id: -7366, protection_element_id:-7366], primaryKey: false);
      insert('organizations', [ id: 107352, nci_institute_code: "UT022", name: "University of Utah Health Network", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT022",GROUP_DESC:"UT022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8367 ,protection_group_id: -7367, protection_element_id:-7367], primaryKey: false);
      insert('organizations', [ id: 107353, nci_institute_code: "UT023", name: "South Valley Surgical Associates", city: "Sandy", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT023",GROUP_DESC:"UT023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8368 ,protection_group_id: -7368, protection_element_id:-7368], primaryKey: false);
      insert('organizations', [ id: 107354, nci_institute_code: "UT024", name: "American Fork Hospital", city: "American Fork", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT024",GROUP_DESC:"UT024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8369 ,protection_group_id: -7369, protection_element_id:-7369], primaryKey: false);
      insert('organizations', [ id: 107355, nci_institute_code: "UT025", name: "Utah County Surgical Associates", city: "Provo", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT025",GROUP_DESC:"UT025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8370 ,protection_group_id: -7370, protection_element_id:-7370], primaryKey: false);
      insert('organizations', [ id: 107356, nci_institute_code: "UT026", name: "Central Utah Medical Clinic Inc.", city: "American Fork", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT026",GROUP_DESC:"UT026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8371 ,protection_group_id: -7371, protection_element_id:-7371], primaryKey: false);
      insert('organizations', [ id: 107357, nci_institute_code: "UT027", name: "Intermountain Hematology Oncology Associates, PC", city: "Bountiful", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT027",GROUP_DESC:"UT027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8372 ,protection_group_id: -7372, protection_element_id:-7372], primaryKey: false);
      insert('organizations', [ id: 107358, nci_institute_code: "UT028", name: "Intermountain Health Care", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT028",GROUP_DESC:"UT028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8373 ,protection_group_id: -7373, protection_element_id:-7373], primaryKey: false);
      insert('organizations', [ id: 107359, nci_institute_code: "UT029", name: "IHC Health Center", city: "Bountiful", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT029",GROUP_DESC:"UT029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8374 ,protection_group_id: -7374, protection_element_id:-7374], primaryKey: false);
      insert('organizations', [ id: 107360, nci_institute_code: "UT030", name: "IHC Memorial Clinic", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT030",GROUP_DESC:"UT030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8375 ,protection_group_id: -7375, protection_element_id:-7375], primaryKey: false);
      insert('organizations', [ id: 107361, nci_institute_code: "UT031", name: "Snow Canyon Clinic", city: "Ivins", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT031",GROUP_DESC:"UT031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8376 ,protection_group_id: -7376, protection_element_id:-7376], primaryKey: false);
      insert('organizations', [ id: 107362, nci_institute_code: "UT032", name: "Foothill Family Clinic", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT032",GROUP_DESC:"UT032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8377 ,protection_group_id: -7377, protection_element_id:-7377], primaryKey: false);
      insert('organizations', [ id: 107363, nci_institute_code: "UT033", name: "Middleton Urology Associates", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT033",GROUP_DESC:"UT033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8378 ,protection_group_id: -7378, protection_element_id:-7378], primaryKey: false);
      insert('organizations', [ id: 107364, nci_institute_code: "UT034", name: "IHC West Jordan Family Clinic", city: "West Jordan", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT034",GROUP_DESC:"UT034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8379 ,protection_group_id: -7379, protection_element_id:-7379], primaryKey: false);
      insert('organizations', [ id: 107365, nci_institute_code: "UT035", name: "Bryner Clinic", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT035",GROUP_DESC:"UT035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8380 ,protection_group_id: -7380, protection_element_id:-7380], primaryKey: false);
      insert('organizations', [ id: 107366, nci_institute_code: "UT036", name: "IHC Central Orem Family Fractice", city: "Orem", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT036",GROUP_DESC:"UT036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8381 ,protection_group_id: -7381, protection_element_id:-7381], primaryKey: false);
      insert('organizations', [ id: 107367, nci_institute_code: "UT037", name: "IHC Health Center", city: "Layton", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT037",GROUP_DESC:"UT037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8382 ,protection_group_id: -7382, protection_element_id:-7382], primaryKey: false);
      insert('organizations', [ id: 107368, nci_institute_code: "UT038", name: "Dixie Regional Medical Center", city: "Saint George", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT038",GROUP_DESC:"UT038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8383 ,protection_group_id: -7383, protection_element_id:-7383], primaryKey: false);
      insert('organizations', [ id: 107369, nci_institute_code: "UT039", name: "Valley View Medical Center", city: "Cedar City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT039",GROUP_DESC:"UT039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8384 ,protection_group_id: -7384, protection_element_id:-7384], primaryKey: false);
      insert('organizations', [ id: 107370, nci_institute_code: "UT040", name: "Middleton Urological Associates", city: "Murray", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT040",GROUP_DESC:"UT040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8385 ,protection_group_id: -7385, protection_element_id:-7385], primaryKey: false);
      insert('organizations', [ id: 107371, nci_institute_code: "UT041", name: "Utah Advanced Laparoscopy", city: "American Fork", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT041",GROUP_DESC:"UT041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8386 ,protection_group_id: -7386, protection_element_id:-7386], primaryKey: false);
      insert('organizations', [ id: 107372, nci_institute_code: "UT042", name: "Southern Utah Urology Associates", city: "Cedar City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT042",GROUP_DESC:"UT042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8387 ,protection_group_id: -7387, protection_element_id:-7387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 107373, nci_institute_code: "UT043", name: "IHC Health Center - Sandy", city: "Sandy", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT043",GROUP_DESC:"UT043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8388 ,protection_group_id: -7388, protection_element_id:-7388], primaryKey: false);
      insert('organizations', [ id: 107374, nci_institute_code: "UT044", name: "Snow Canyon Cancer Clinic", city: "Ivins", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT044",GROUP_DESC:"UT044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8389 ,protection_group_id: -7389, protection_element_id:-7389], primaryKey: false);
      insert('organizations', [ id: 107375, nci_institute_code: "UT045", name: "Southwest Surgical Associates", city: "St. George", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT045",GROUP_DESC:"UT045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8390 ,protection_group_id: -7390, protection_element_id:-7390], primaryKey: false);
      insert('organizations', [ id: 107376, nci_institute_code: "UT046", name: "Huntsman Cancer Hospital", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT046",GROUP_DESC:"UT046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8391 ,protection_group_id: -7391, protection_element_id:-7391], primaryKey: false);
      insert('organizations', [ id: 107377, nci_institute_code: "UT047", name: "Utah Hematology Oncology", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT047",GROUP_DESC:"UT047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8392 ,protection_group_id: -7392, protection_element_id:-7392], primaryKey: false);
      insert('organizations', [ id: 107378, nci_institute_code: "UT048", name: "Bryner Surgical Specialists", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT048",GROUP_DESC:"UT048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8393 ,protection_group_id: -7393, protection_element_id:-7393], primaryKey: false);
      insert('organizations', [ id: 107379, nci_institute_code: "UT049", name: "GammaWest Brachytherapy LLC-Business Office", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT049",GROUP_DESC:"UT049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8394 ,protection_group_id: -7394, protection_element_id:-7394], primaryKey: false);
      insert('organizations', [ id: 107380, nci_institute_code: "UT050", name: "GammaWest Brachytherapy LLC", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT050",GROUP_DESC:"UT050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8395 ,protection_group_id: -7395, protection_element_id:-7395], primaryKey: false);
      insert('organizations', [ id: 107381, nci_institute_code: "UT051", name: "GammaWest Brachytherapy LLC - Salt Lake City", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT051",GROUP_DESC:"UT051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8396 ,protection_group_id: -7396, protection_element_id:-7396], primaryKey: false);
      insert('organizations', [ id: 107382, nci_institute_code: "UT052", name: "Huntsman Cancer Institute", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT052",GROUP_DESC:"UT052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8397 ,protection_group_id: -7397, protection_element_id:-7397], primaryKey: false);
      insert('organizations', [ id: 107383, nci_institute_code: "UT053", name: "Huntsman Intermountain Cancer Center", city: "American Fork", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT053",GROUP_DESC:"UT053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8398 ,protection_group_id: -7398, protection_element_id:-7398], primaryKey: false);
      insert('organizations', [ id: 107384, nci_institute_code: "UT054", name: "Intermountain Cardiovascular Associates", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT054",GROUP_DESC:"UT054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8399 ,protection_group_id: -7399, protection_element_id:-7399], primaryKey: false);
      insert('organizations', [ id: 107385, nci_institute_code: "UT055", name: "Legant, Patricia MD (office)", city: "Salt Lake City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT055",GROUP_DESC:"UT055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8400 ,protection_group_id: -7400, protection_element_id:-7400], primaryKey: false);
      insert('organizations', [ id: 107386, nci_institute_code: "UT056", name: "Intermountain Medical Center", city: "Murray", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT056",GROUP_DESC:"UT056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8401 ,protection_group_id: -7401, protection_element_id:-7401], primaryKey: false);
      insert('organizations', [ id: 107387, nci_institute_code: "UT057", name: "Sandra L Maxwell Cancer Center", city: "Cedar City", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT057",GROUP_DESC:"UT057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8402 ,protection_group_id: -7402, protection_element_id:-7402], primaryKey: false);
      insert('organizations', [ id: 107388, nci_institute_code: "UT058", name: "Utah Cancer Specialists-Provo", city: "Provo", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT058",GROUP_DESC:"UT058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8403 ,protection_group_id: -7403, protection_element_id:-7403], primaryKey: false);
      insert('organizations', [ id: 107389, nci_institute_code: "UT059", name: "Utah Hematology Oncology PC", city: "Ogden", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT059",GROUP_DESC:"UT059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8404 ,protection_group_id: -7404, protection_element_id:-7404], primaryKey: false);
      insert('organizations', [ id: 107390, nci_institute_code: "UT060", name: "Utah Cancer Specialists-Layton", city: "Layton", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT060",GROUP_DESC:"UT060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8405 ,protection_group_id: -7405, protection_element_id:-7405], primaryKey: false);
      insert('organizations', [ id: 107391, nci_institute_code: "VA001", name: "Hazelton Laboratories", city: "Falls Church", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA001",GROUP_DESC:"VA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8406 ,protection_group_id: -7406, protection_element_id:-7406], primaryKey: false);
      insert('organizations', [ id: 107392, nci_institute_code: "VA002", name: "Inova Fairfax Hospital", city: "Falls Church", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA002",GROUP_DESC:"VA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8407 ,protection_group_id: -7407, protection_element_id:-7407], primaryKey: false);
      insert('organizations', [ id: 107393, nci_institute_code: "VA004", name: "Kaiser Permanente - Springfield Medical Center", city: "Springfield", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA004",GROUP_DESC:"VA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8408 ,protection_group_id: -7408, protection_element_id:-7408], primaryKey: false);
      insert('organizations', [ id: 107394, nci_institute_code: "VA005", name: "Arlington Hospital", city: "Arlington", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA005",GROUP_DESC:"VA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8409 ,protection_group_id: -7409, protection_element_id:-7409], primaryKey: false);
      insert('organizations', [ id: 107395, nci_institute_code: "VA006", name: "Alexandria Hospital", city: "Alexandria", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA006",GROUP_DESC:"VA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8410 ,protection_group_id: -7410, protection_element_id:-7410], primaryKey: false);
      insert('organizations', [ id: 107396, nci_institute_code: "VA007", name: "Rappahannock General Hospital", city: "Kilmarnock", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA007",GROUP_DESC:"VA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8411 ,protection_group_id: -7411, protection_element_id:-7411], primaryKey: false);
      insert('organizations', [ id: 107397, nci_institute_code: "VA008", name: "Winchester Memorial Hospital", city: "Winchester", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA008",GROUP_DESC:"VA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8412 ,protection_group_id: -7412, protection_element_id:-7412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 107398, nci_institute_code: "VA009", name: "University of Virginia", city: "Charlottesville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA009",GROUP_DESC:"VA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8413 ,protection_group_id: -7413, protection_element_id:-7413], primaryKey: false);
      insert('organizations', [ id: 107399, nci_institute_code: "VA010", name: "Virginia Commonwealth University", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA010",GROUP_DESC:"VA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8414 ,protection_group_id: -7414, protection_element_id:-7414], primaryKey: false);
      insert('organizations', [ id: 107400, nci_institute_code: "VA011", name: "Retreat Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA011",GROUP_DESC:"VA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8415 ,protection_group_id: -7415, protection_element_id:-7415], primaryKey: false);
      insert('organizations', [ id: 107401, nci_institute_code: "VA012", name: "Chippenham - Johnson", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA012",GROUP_DESC:"VA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8416 ,protection_group_id: -7416, protection_element_id:-7416], primaryKey: false);
      insert('organizations', [ id: 107402, nci_institute_code: "VA013", name: "Bon Secours Saint Mary's Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA013",GROUP_DESC:"VA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8417 ,protection_group_id: -7417, protection_element_id:-7417], primaryKey: false);
      insert('organizations', [ id: 107403, nci_institute_code: "VA014", name: "City Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA014",GROUP_DESC:"VA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8418 ,protection_group_id: -7418, protection_element_id:-7418], primaryKey: false);
      insert('organizations', [ id: 107404, nci_institute_code: "VA015", name: "Hunter Holmes McGuire Veterans Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA015",GROUP_DESC:"VA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8419 ,protection_group_id: -7419, protection_element_id:-7419], primaryKey: false);
      insert('organizations', [ id: 107405, nci_institute_code: "VA016", name: "General Hospital of Virginia Beach", city: "Virginia Beach", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA016",GROUP_DESC:"VA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8420 ,protection_group_id: -7420, protection_element_id:-7420], primaryKey: false);
      insert('organizations', [ id: 107406, nci_institute_code: "VA018", name: "Children's Center for Cancer and Blood Disorders in Northern Virginia", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA018",GROUP_DESC:"VA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8421 ,protection_group_id: -7421, protection_element_id:-7421], primaryKey: false);
      insert('organizations', [ id: 107407, nci_institute_code: "VA019", name: "Depaul Hospital", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA019",GROUP_DESC:"VA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8422 ,protection_group_id: -7422, protection_element_id:-7422], primaryKey: false);
      insert('organizations', [ id: 107408, nci_institute_code: "VA020", name: "Childrens Hospital-King's Daughters", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA020",GROUP_DESC:"VA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8423 ,protection_group_id: -7423, protection_element_id:-7423], primaryKey: false);
      insert('organizations', [ id: 107409, nci_institute_code: "VA021", name: "Eastern Virginia Medical School", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA021",GROUP_DESC:"VA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8424 ,protection_group_id: -7424, protection_element_id:-7424], primaryKey: false);
      insert('organizations', [ id: 107410, nci_institute_code: "VA022", name: "USPHS Hospital, Norfolk", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA022",GROUP_DESC:"VA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8425 ,protection_group_id: -7425, protection_element_id:-7425], primaryKey: false);
      insert('organizations', [ id: 107411, nci_institute_code: "VA023", name: "Veterans Administration Medical Center", city: "Hampton", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA023",GROUP_DESC:"VA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8426 ,protection_group_id: -7426, protection_element_id:-7426], primaryKey: false);
      insert('organizations', [ id: 107412, nci_institute_code: "VA024", name: "Naval Medical Center - Portsmouth", city: "Portsmouth", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA024",GROUP_DESC:"VA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8427 ,protection_group_id: -7427, protection_element_id:-7427], primaryKey: false);
      insert('organizations', [ id: 107413, nci_institute_code: "VA025", name: "Petersburg General Hospital", city: "Petersburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA025",GROUP_DESC:"VA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8428 ,protection_group_id: -7428, protection_element_id:-7428], primaryKey: false);
      insert('organizations', [ id: 107414, nci_institute_code: "VA026", name: "Oncology and Hematology Associates of Southwest Virginia", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA026",GROUP_DESC:"VA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8429 ,protection_group_id: -7429, protection_element_id:-7429], primaryKey: false);
      insert('organizations', [ id: 107415, nci_institute_code: "VA027", name: "Veterans Administration Medical Center", city: "Salem", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA027",GROUP_DESC:"VA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8430 ,protection_group_id: -7430, protection_element_id:-7430], primaryKey: false);
      insert('organizations', [ id: 107416, nci_institute_code: "VA028", name: "Danville Regional Medical Center", city: "Danville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA028",GROUP_DESC:"VA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8431 ,protection_group_id: -7431, protection_element_id:-7431], primaryKey: false);
      insert('organizations', [ id: 107417, nci_institute_code: "VA030", name: "Emmett Memorial Hospital", city: "Clifton Forge", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA030",GROUP_DESC:"VA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8432 ,protection_group_id: -7432, protection_element_id:-7432], primaryKey: false);
      insert('organizations', [ id: 107418, nci_institute_code: "VA031", name: "Naval Hospital, Lynchburg", city: "Lynchburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA031",GROUP_DESC:"VA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8433 ,protection_group_id: -7433, protection_element_id:-7433], primaryKey: false);
      insert('organizations', [ id: 107419, nci_institute_code: "VA033", name: "Harold Pugh Can Res Institute", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA033",GROUP_DESC:"VA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8434 ,protection_group_id: -7434, protection_element_id:-7434], primaryKey: false);
      insert('organizations', [ id: 107420, nci_institute_code: "VA034", name: "Lynchburg Hematology-Oncology Clinic", city: "Lynchburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA034",GROUP_DESC:"VA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8435 ,protection_group_id: -7435, protection_element_id:-7435], primaryKey: false);
      insert('organizations', [ id: 107421, nci_institute_code: "VA036", name: "Kaiser Permanente - Falls Church Medical Center", city: "Falls Church", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA036",GROUP_DESC:"VA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8436 ,protection_group_id: -7436, protection_element_id:-7436], primaryKey: false);
      insert('organizations', [ id: 107422, nci_institute_code: "VA037", name: "Norfolk Community Hospital", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA037",GROUP_DESC:"VA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8437 ,protection_group_id: -7437, protection_element_id:-7437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 107423, nci_institute_code: "VA038", name: "Riverside Regional Medical Center", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA038",GROUP_DESC:"VA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8438 ,protection_group_id: -7438, protection_element_id:-7438], primaryKey: false);
      insert('organizations', [ id: 107424, nci_institute_code: "VA039", name: "Virginia Oncology Associates", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA039",GROUP_DESC:"VA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8439 ,protection_group_id: -7439, protection_element_id:-7439], primaryKey: false);
      insert('organizations', [ id: 107425, nci_institute_code: "VA040", name: "Mount Vernon Hospital", city: "Alexandria", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA040",GROUP_DESC:"VA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8440 ,protection_group_id: -7440, protection_element_id:-7440], primaryKey: false);
      insert('organizations', [ id: 107426, nci_institute_code: "VA042", name: "Mid-Atlantic Hematology/Oncology Spec Ltd.", city: "Chesapeake", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA042",GROUP_DESC:"VA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8441 ,protection_group_id: -7441, protection_element_id:-7441], primaryKey: false);
      insert('organizations', [ id: 107427, nci_institute_code: "VA043", name: "Memorial Hospital Of Martinsville", city: "Martinsville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA043",GROUP_DESC:"VA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8442 ,protection_group_id: -7442, protection_element_id:-7442], primaryKey: false);
      insert('organizations', [ id: 107428, nci_institute_code: "VA045", name: "Medical Specialists of Fredricksburg", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA045",GROUP_DESC:"VA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8443 ,protection_group_id: -7443, protection_element_id:-7443], primaryKey: false);
      insert('organizations', [ id: 107429, nci_institute_code: "VA046", name: "Humana Hospital Clinic Valley", city: "Richlands", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA046",GROUP_DESC:"VA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8444 ,protection_group_id: -7444, protection_element_id:-7444], primaryKey: false);
      insert('organizations', [ id: 107430, nci_institute_code: "VA047", name: "James River Cancer Center", city: "Hampton", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA047",GROUP_DESC:"VA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8445 ,protection_group_id: -7445, protection_element_id:-7445], primaryKey: false);
      insert('organizations', [ id: 107431, nci_institute_code: "VA048", name: "Martha Jefferson Hospital", city: "Charlottesville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA048",GROUP_DESC:"VA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8446 ,protection_group_id: -7446, protection_element_id:-7446], primaryKey: false);
      insert('organizations', [ id: 107432, nci_institute_code: "VA049", name: "Lewis-Gale Hospital", city: "Salem", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA049",GROUP_DESC:"VA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8447 ,protection_group_id: -7447, protection_element_id:-7447], primaryKey: false);
      insert('organizations', [ id: 107433, nci_institute_code: "VA050", name: "Carilion Roanoke Community Hospital", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA050",GROUP_DESC:"VA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8448 ,protection_group_id: -7448, protection_element_id:-7448], primaryKey: false);
      insert('organizations', [ id: 107434, nci_institute_code: "VA051", name: "McGuire Clinic Cancer Center", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA051",GROUP_DESC:"VA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8449 ,protection_group_id: -7449, protection_element_id:-7449], primaryKey: false);
      insert('organizations', [ id: 107435, nci_institute_code: "VA052", name: "Sentara Hospitals", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA052",GROUP_DESC:"VA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8450 ,protection_group_id: -7450, protection_element_id:-7450], primaryKey: false);
      insert('organizations', [ id: 107436, nci_institute_code: "VA053", name: "CJW Medical Center - Johnston-Willis Campus", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA053",GROUP_DESC:"VA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8451 ,protection_group_id: -7451, protection_element_id:-7451], primaryKey: false);
      insert('organizations', [ id: 107437, nci_institute_code: "VA054", name: "Richmond Memorial Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA054",GROUP_DESC:"VA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8452 ,protection_group_id: -7452, protection_element_id:-7452], primaryKey: false);
      insert('organizations', [ id: 107438, nci_institute_code: "VA055", name: "Oncology Consortium of The Virginias", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA055",GROUP_DESC:"VA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8453 ,protection_group_id: -7453, protection_element_id:-7453], primaryKey: false);
      insert('organizations', [ id: 107439, nci_institute_code: "VA056", name: "Mary Washington Hospital", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA056",GROUP_DESC:"VA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8454 ,protection_group_id: -7454, protection_element_id:-7454], primaryKey: false);
      insert('organizations', [ id: 107440, nci_institute_code: "VA057", name: "Houck and Associates", city: "Winchester", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA057",GROUP_DESC:"VA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8455 ,protection_group_id: -7455, protection_element_id:-7455], primaryKey: false);
      insert('organizations', [ id: 107441, nci_institute_code: "VA059", name: "Center for Cancer Care", city: "Charlottesville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA059",GROUP_DESC:"VA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8456 ,protection_group_id: -7456, protection_element_id:-7456], primaryKey: false);
      insert('organizations', [ id: 107442, nci_institute_code: "VA060", name: "Virginia Oncology Associates- Williamsburg", city: "Williamsburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA060",GROUP_DESC:"VA060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8457 ,protection_group_id: -7457, protection_element_id:-7457], primaryKey: false);
      insert('organizations', [ id: 107443, nci_institute_code: "VA061", name: "CCOP, Fairfax", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA061",GROUP_DESC:"VA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8458 ,protection_group_id: -7458, protection_element_id:-7458], primaryKey: false);
      insert('organizations', [ id: 107444, nci_institute_code: "VA062", name: "Carilion Center of Roanoke", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA062",GROUP_DESC:"VA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8459 ,protection_group_id: -7459, protection_element_id:-7459], primaryKey: false);
      insert('organizations', [ id: 107445, nci_institute_code: "VA063", name: "Rockingham Memorial Hospital Regional Cancer Center", city: "Harrisonburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA063",GROUP_DESC:"VA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8460 ,protection_group_id: -7460, protection_element_id:-7460], primaryKey: false);
      insert('organizations', [ id: 107446, nci_institute_code: "VA064", name: "Gynecologists of Tidewater, P.C.", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA064",GROUP_DESC:"VA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8461 ,protection_group_id: -7461, protection_element_id:-7461], primaryKey: false);
      insert('organizations', [ id: 107447, nci_institute_code: "VA065", name: "Fairfax-Northern Virginia Hematology-Oncology, P.C.", city: "Annandale", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA065",GROUP_DESC:"VA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8462 ,protection_group_id: -7462, protection_element_id:-7462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 107448, nci_institute_code: "VA066", name: "Shenandoah Onc Assoc., P.C.", city: "Winchester", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA066",GROUP_DESC:"VA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8463 ,protection_group_id: -7463, protection_element_id:-7463], primaryKey: false);
      insert('organizations', [ id: 107449, nci_institute_code: "VA067", name: "Virginia Cancer Institute", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA067",GROUP_DESC:"VA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8464 ,protection_group_id: -7464, protection_element_id:-7464], primaryKey: false);
      insert('organizations', [ id: 107450, nci_institute_code: "VA068", name: "E. Shore Physicians & Surg", city: "Nasawadox", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA068",GROUP_DESC:"VA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8465 ,protection_group_id: -7465, protection_element_id:-7465], primaryKey: false);
      insert('organizations', [ id: 107451, nci_institute_code: "VA069", name: "Hanover Medical Park", city: "Mechanicville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA069",GROUP_DESC:"VA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8466 ,protection_group_id: -7466, protection_element_id:-7466], primaryKey: false);
      insert('organizations', [ id: 107452, nci_institute_code: "VA070", name: "Devine-Fiveash Uro., Ltd", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA070",GROUP_DESC:"VA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8467 ,protection_group_id: -7467, protection_element_id:-7467], primaryKey: false);
      insert('organizations', [ id: 107453, nci_institute_code: "VA071", name: "Danville Hematology/Oncology", city: "Danville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA071",GROUP_DESC:"VA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8468 ,protection_group_id: -7468, protection_element_id:-7468], primaryKey: false);
      insert('organizations', [ id: 107454, nci_institute_code: "VA073", name: "Prince Williams Hospital", city: "Manassas", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA073",GROUP_DESC:"VA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8469 ,protection_group_id: -7469, protection_element_id:-7469], primaryKey: false);
      insert('organizations', [ id: 107455, nci_institute_code: "VA074", name: "Medical Specialists, Incorporated", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA074",GROUP_DESC:"VA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8470 ,protection_group_id: -7470, protection_element_id:-7470], primaryKey: false);
      insert('organizations', [ id: 107456, nci_institute_code: "VA075", name: "Virginia Baptist Hospital", city: "Lynchburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA075",GROUP_DESC:"VA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8471 ,protection_group_id: -7471, protection_element_id:-7471], primaryKey: false);
      insert('organizations', [ id: 107457, nci_institute_code: "VA076", name: "Southside Community Hospital", city: "Farmville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA076",GROUP_DESC:"VA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8472 ,protection_group_id: -7472, protection_element_id:-7472], primaryKey: false);
      insert('organizations', [ id: 107458, nci_institute_code: "VA077", name: "The Courtyard Medical Office Building", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA077",GROUP_DESC:"VA077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8473 ,protection_group_id: -7473, protection_element_id:-7473], primaryKey: false);
      insert('organizations', [ id: 107459, nci_institute_code: "VA078", name: "Cancer Specialists of Tidewater Ltd", city: "Chesapeake", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA078",GROUP_DESC:"VA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8474 ,protection_group_id: -7474, protection_element_id:-7474], primaryKey: false);
      insert('organizations', [ id: 107460, nci_institute_code: "VA079", name: "Hematology Oncology Consultants/Tidewater, Limited.,", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA079",GROUP_DESC:"VA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8475 ,protection_group_id: -7475, protection_element_id:-7475], primaryKey: false);
      insert('organizations', [ id: 107461, nci_institute_code: "VA084", name: "Virginia Oncology Associates", city: "Hampton", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA084",GROUP_DESC:"VA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8476 ,protection_group_id: -7476, protection_element_id:-7476], primaryKey: false);
      insert('organizations', [ id: 107462, nci_institute_code: "VA086", name: "Northern Virginia Oncology Group", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA086",GROUP_DESC:"VA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8477 ,protection_group_id: -7477, protection_element_id:-7477], primaryKey: false);
      insert('organizations', [ id: 107463, nci_institute_code: "VA087", name: "Norfolk Surgical Group, Ltd.", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA087",GROUP_DESC:"VA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8478 ,protection_group_id: -7478, protection_element_id:-7478], primaryKey: false);
      insert('organizations', [ id: 107464, nci_institute_code: "VA088", name: "Virginia Oncology Associates - Lake Wright", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA088",GROUP_DESC:"VA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8479 ,protection_group_id: -7479, protection_element_id:-7479], primaryKey: false);
      insert('organizations', [ id: 107465, nci_institute_code: "VA090", name: "Bon Secours Commonwealth Gynecologic Oncology", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA090",GROUP_DESC:"VA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8480 ,protection_group_id: -7480, protection_element_id:-7480], primaryKey: false);
      insert('organizations', [ id: 107466, nci_institute_code: "VA091", name: "Maryview Medical Center", city: "Portsmouth", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA091",GROUP_DESC:"VA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8481 ,protection_group_id: -7481, protection_element_id:-7481], primaryKey: false);
      insert('organizations', [ id: 107467, nci_institute_code: "VA092", name: "Pulaski Community Hospital", city: "Pulaski", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA092",GROUP_DESC:"VA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8482 ,protection_group_id: -7482, protection_element_id:-7482], primaryKey: false);
      insert('organizations', [ id: 107468, nci_institute_code: "VA093", name: "Fauquier Hospital", city: "Warrenton", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA093",GROUP_DESC:"VA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8483 ,protection_group_id: -7483, protection_element_id:-7483], primaryKey: false);
      insert('organizations', [ id: 107469, nci_institute_code: "VA094", name: "Henrico Doctor's Hospital", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA094",GROUP_DESC:"VA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8484 ,protection_group_id: -7484, protection_element_id:-7484], primaryKey: false);
      insert('organizations', [ id: 107470, nci_institute_code: "VA095", name: "Community Memorial Health Center", city: "South Hill", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA095",GROUP_DESC:"VA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8485 ,protection_group_id: -7485, protection_element_id:-7485], primaryKey: false);
      insert('organizations', [ id: 107471, nci_institute_code: "VA096", name: "Medical Associates of South West Virginia", city: "Blacksburgh", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA096",GROUP_DESC:"VA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8486 ,protection_group_id: -7486, protection_element_id:-7486], primaryKey: false);
      insert('organizations', [ id: 107472, nci_institute_code: "VA097", name: "Medical Oncology/Hematology Associates of Northern Virginia Ltd.", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA097",GROUP_DESC:"VA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8487 ,protection_group_id: -7487, protection_element_id:-7487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 107473, nci_institute_code: "VA098", name: "Delta Hematology Oncology Associates", city: "Portsmouth", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA098",GROUP_DESC:"VA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8488 ,protection_group_id: -7488, protection_element_id:-7488], primaryKey: false);
      insert('organizations', [ id: 107474, nci_institute_code: "VA099", name: "Virginia Physicians Incorporated", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA099",GROUP_DESC:"VA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8489 ,protection_group_id: -7489, protection_element_id:-7489], primaryKey: false);
      insert('organizations', [ id: 107475, nci_institute_code: "VA100", name: "Kaiser Permanente - Fair Oaks Medical Center", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA100",GROUP_DESC:"VA100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8490 ,protection_group_id: -7490, protection_element_id:-7490], primaryKey: false);
      insert('organizations', [ id: 107476, nci_institute_code: "VA101", name: "Cancer Outreach Associates PC", city: "Abingdon", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA101",GROUP_DESC:"VA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8491 ,protection_group_id: -7491, protection_element_id:-7491], primaryKey: false);
      insert('organizations', [ id: 107477, nci_institute_code: "VA102", name: "Loudon Hospital Cancer Center", city: "Sterling", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA102",GROUP_DESC:"VA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8492 ,protection_group_id: -7492, protection_element_id:-7492], primaryKey: false);
      insert('organizations', [ id: 107478, nci_institute_code: "VA103", name: "Oncology and Hematology of South West Virginia", city: "Christiansburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA103",GROUP_DESC:"VA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8493 ,protection_group_id: -7493, protection_element_id:-7493], primaryKey: false);
      insert('organizations', [ id: 107479, nci_institute_code: "VA105", name: "Southwest VA Regional Cancer Center", city: "Norton", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA105",GROUP_DESC:"VA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8494 ,protection_group_id: -7494, protection_element_id:-7494], primaryKey: false);
      insert('organizations', [ id: 107480, nci_institute_code: "VA106", name: "Onco/Hema Assoc of Southwest VA", city: "Radford", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA106",GROUP_DESC:"VA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8495 ,protection_group_id: -7495, protection_element_id:-7495], primaryKey: false);
      insert('organizations', [ id: 107481, nci_institute_code: "VA107", name: "Medical Oncology and Hematology of Northern Virginia", city: "Reston", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA107",GROUP_DESC:"VA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8496 ,protection_group_id: -7496, protection_element_id:-7496], primaryKey: false);
      insert('organizations', [ id: 107482, nci_institute_code: "VA109", name: "Fairfax Northern VA Hema\\Oncology", city: "Alexandria", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA109",GROUP_DESC:"VA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8497 ,protection_group_id: -7497, protection_element_id:-7497], primaryKey: false);
      insert('organizations', [ id: 107483, nci_institute_code: "VA110", name: "Inova Fair Oaks Hospital", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA110",GROUP_DESC:"VA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8498 ,protection_group_id: -7498, protection_element_id:-7498], primaryKey: false);
      insert('organizations', [ id: 107484, nci_institute_code: "VA111", name: "Fairfax Northern Virginia Hematology and Oncology PC", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA111",GROUP_DESC:"VA111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8499 ,protection_group_id: -7499, protection_element_id:-7499], primaryKey: false);
      insert('organizations', [ id: 107485, nci_institute_code: "VA112", name: "Richmond Radiation Oncology Center", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA112",GROUP_DESC:"VA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8500 ,protection_group_id: -7500, protection_element_id:-7500], primaryKey: false);
      insert('organizations', [ id: 107486, nci_institute_code: "VA113", name: "Oyster Point Surgical Associates", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA113",GROUP_DESC:"VA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8501 ,protection_group_id: -7501, protection_element_id:-7501], primaryKey: false);
      insert('organizations', [ id: 107487, nci_institute_code: "VA115", name: "Cancer Center of the Piedmont, Inc", city: "Danville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA115",GROUP_DESC:"VA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8502 ,protection_group_id: -7502, protection_element_id:-7502], primaryKey: false);
      insert('organizations', [ id: 107488, nci_institute_code: "VA116", name: "Virginia Surgery Associates, P.C.", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA116",GROUP_DESC:"VA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8503 ,protection_group_id: -7503, protection_element_id:-7503], primaryKey: false);
      insert('organizations', [ id: 107489, nci_institute_code: "VA117", name: "Carilion New River Valley Medical Center", city: "Christiansburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA117",GROUP_DESC:"VA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8504 ,protection_group_id: -7504, protection_element_id:-7504], primaryKey: false);
      insert('organizations', [ id: 107490, nci_institute_code: "VA118", name: "Hematology Oncology Patient Enterprise (H.O.P.E)", city: "Fishersville", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA118",GROUP_DESC:"VA118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8505 ,protection_group_id: -7505, protection_element_id:-7505], primaryKey: false);
      insert('organizations', [ id: 107491, nci_institute_code: "VA119", name: "Culpeper Regional Hospital", city: "Culpeper", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA119",GROUP_DESC:"VA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8506 ,protection_group_id: -7506, protection_element_id:-7506], primaryKey: false);
      insert('organizations', [ id: 107492, nci_institute_code: "VA120", name: "Fairfax-Northern VA Hematology & Oncology, PC", city: "Woodbridge", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA120",GROUP_DESC:"VA120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8507 ,protection_group_id: -7507, protection_element_id:-7507], primaryKey: false);
      insert('organizations', [ id: 107493, nci_institute_code: "VA122", name: "Breast Care Specialists/Blue Ridge PC", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA122",GROUP_DESC:"VA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8508 ,protection_group_id: -7508, protection_element_id:-7508], primaryKey: false);
      insert('organizations', [ id: 107494, nci_institute_code: "VA123", name: "Northern Virginia Pelvic Surgery Associates", city: "Annandale", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA123",GROUP_DESC:"VA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8509 ,protection_group_id: -7509, protection_element_id:-7509], primaryKey: false);
      insert('organizations', [ id: 107495, nci_institute_code: "VA124", name: "Virginia Hematology and Oncology, PLLC", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA124",GROUP_DESC:"VA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8510 ,protection_group_id: -7510, protection_element_id:-7510], primaryKey: false);
      insert('organizations', [ id: 107496, nci_institute_code: "VA125", name: "Virginia Oncology Care", city: "Richlands", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA125",GROUP_DESC:"VA125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8511 ,protection_group_id: -7511, protection_element_id:-7511], primaryKey: false);
      insert('organizations', [ id: 107497, nci_institute_code: "VA126", name: "Devine-Tidewater Urology", city: "Virginia Beach", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA126",GROUP_DESC:"VA126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8512 ,protection_group_id: -7512, protection_element_id:-7512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 107498, nci_institute_code: "VA127", name: "Devine-Tidewater Urology-Norfolk", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA127",GROUP_DESC:"VA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8513 ,protection_group_id: -7513, protection_element_id:-7513], primaryKey: false);
      insert('organizations', [ id: 107499, nci_institute_code: "VA128", name: "Urology of Virginia PC", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA128",GROUP_DESC:"VA128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8514 ,protection_group_id: -7514, protection_element_id:-7514], primaryKey: false);
      insert('organizations', [ id: 107500, nci_institute_code: "VA129", name: "Virginia Oncology Associates- Suffolk", city: "Suffolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA129",GROUP_DESC:"VA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8515 ,protection_group_id: -7515, protection_element_id:-7515], primaryKey: false);
      insert('organizations', [ id: 107501, nci_institute_code: "VA130", name: "Breast Care Specialists, P.C.", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA130",GROUP_DESC:"VA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8516 ,protection_group_id: -7516, protection_element_id:-7516], primaryKey: false);
      insert('organizations', [ id: 107502, nci_institute_code: "VA131", name: "Carilion Gynecologic Oncology Associates", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA131",GROUP_DESC:"VA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8517 ,protection_group_id: -7517, protection_element_id:-7517], primaryKey: false);
      insert('organizations', [ id: 107503, nci_institute_code: "VA132", name: "Salem Surgical Associates, P.C.", city: "Salem", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA132",GROUP_DESC:"VA132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8518 ,protection_group_id: -7518, protection_element_id:-7518], primaryKey: false);
      insert('organizations', [ id: 107504, nci_institute_code: "VA133", name: "Fairfax Northern Virginia Hematology and Oncology, PC", city: "Manassas", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA133",GROUP_DESC:"VA133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8519 ,protection_group_id: -7519, protection_element_id:-7519], primaryKey: false);
      insert('organizations', [ id: 107505, nci_institute_code: "VA135", name: "Oncology and Hematology Associates of Southwest Virginia Inc", city: "Salem", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA135",GROUP_DESC:"VA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8520 ,protection_group_id: -7520, protection_element_id:-7520], primaryKey: false);
      insert('organizations', [ id: 107506, nci_institute_code: "VA136", name: "Peninsula Cancer Institute", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA136",GROUP_DESC:"VA136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8521 ,protection_group_id: -7521, protection_element_id:-7521], primaryKey: false);
      insert('organizations', [ id: 107507, nci_institute_code: "VA137", name: "Peninsula Cancer Institute", city: "Williamsburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA137",GROUP_DESC:"VA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8522 ,protection_group_id: -7522, protection_element_id:-7522], primaryKey: false);
      insert('organizations', [ id: 107508, nci_institute_code: "VA138", name: "Arlington Fairfax Hematology & Oncology PC", city: "Arlington", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA138",GROUP_DESC:"VA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8523 ,protection_group_id: -7523, protection_element_id:-7523], primaryKey: false);
      insert('organizations', [ id: 107509, nci_institute_code: "VA139", name: "Fairfax Northern Virginia Hematology and Oncology PC", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA139",GROUP_DESC:"VA139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8524 ,protection_group_id: -7524, protection_element_id:-7524], primaryKey: false);
      insert('organizations', [ id: 107510, nci_institute_code: "VA140", name: "Fredericksburg Oncology Inc", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA140",GROUP_DESC:"VA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8525 ,protection_group_id: -7525, protection_element_id:-7525], primaryKey: false);
      insert('organizations', [ id: 107511, nci_institute_code: "VA141", name: "George Mason University", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA141",GROUP_DESC:"VA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8526 ,protection_group_id: -7526, protection_element_id:-7526], primaryKey: false);
      insert('organizations', [ id: 107512, nci_institute_code: "VA143", name: "Riverside Middle Peninsula Cancer Center", city: "Gloucester", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA143",GROUP_DESC:"VA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8527 ,protection_group_id: -7527, protection_element_id:-7527], primaryKey: false);
      insert('organizations', [ id: 107513, nci_institute_code: "VA144", name: "OPUS Clinical Reaserch LLC", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA144",GROUP_DESC:"VA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8528 ,protection_group_id: -7528, protection_element_id:-7528], primaryKey: false);
      insert('organizations', [ id: 107514, nci_institute_code: "VA145", name: "Fairfax Family Practice", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA145",GROUP_DESC:"VA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8529 ,protection_group_id: -7529, protection_element_id:-7529], primaryKey: false);
      insert('organizations', [ id: 107515, nci_institute_code: "VA146", name: "Johnston Memorial Hospital", city: "Abingdon", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA146",GROUP_DESC:"VA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8530 ,protection_group_id: -7530, protection_element_id:-7530], primaryKey: false);
      insert('organizations', [ id: 107516, nci_institute_code: "VA147", name: "Williamsburg Radiation Therapy Center", city: "Williamsburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA147",GROUP_DESC:"VA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8531 ,protection_group_id: -7531, protection_element_id:-7531], primaryKey: false);
      insert('organizations', [ id: 107517, nci_institute_code: "VA148", name: "Reston Hospital Center", city: "Reston", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA148",GROUP_DESC:"VA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8532 ,protection_group_id: -7532, protection_element_id:-7532], primaryKey: false);
      insert('organizations', [ id: 107518, nci_institute_code: "VA149", name: "Surgical Oncology Associates", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA149",GROUP_DESC:"VA149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8533 ,protection_group_id: -7533, protection_element_id:-7533], primaryKey: false);
      insert('organizations', [ id: 107519, nci_institute_code: "VA150", name: "Rockingham Memorial Hospital", city: "Harrisonburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA150",GROUP_DESC:"VA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8534 ,protection_group_id: -7534, protection_element_id:-7534], primaryKey: false);
      insert('organizations', [ id: 107520, nci_institute_code: "VA151", name: "CLG Associates LLC", city: "Alexandria", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA151",GROUP_DESC:"VA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8535 ,protection_group_id: -7535, protection_element_id:-7535], primaryKey: false);
      insert('organizations', [ id: 107521, nci_institute_code: "VA152", name: "Carilion Pediatric Surgery", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA152",GROUP_DESC:"VA152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8536 ,protection_group_id: -7536, protection_element_id:-7536], primaryKey: false);
      insert('organizations', [ id: 107522, nci_institute_code: "VA153", name: "Edward Via Virginia College of Osteopathic Medicine", city: "Blacksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA153",GROUP_DESC:"VA153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8537 ,protection_group_id: -7537, protection_element_id:-7537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 107523, nci_institute_code: "VA154", name: "NoVa Oncology Associates PC", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA154",GROUP_DESC:"VA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8538 ,protection_group_id: -7538, protection_element_id:-7538], primaryKey: false);
      insert('organizations', [ id: 107524, nci_institute_code: "VA155", name: "Virginia Oncology Associates", city: "Virginia Beach", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA155",GROUP_DESC:"VA155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8539 ,protection_group_id: -7539, protection_element_id:-7539], primaryKey: false);
      insert('organizations', [ id: 107525, nci_institute_code: "VA156", name: "Hampton Roads Surgical Specialists", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA156",GROUP_DESC:"VA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8540 ,protection_group_id: -7540, protection_element_id:-7540], primaryKey: false);
      insert('organizations', [ id: 107526, nci_institute_code: "VA157", name: "Surgical Associates of Richmond", city: "Richmond", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA157",GROUP_DESC:"VA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8541 ,protection_group_id: -7541, protection_element_id:-7541], primaryKey: false);
      insert('organizations', [ id: 107527, nci_institute_code: "VA158", name: "Riverside Cancer Care Center Radiation Oncology", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA158",GROUP_DESC:"VA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8542 ,protection_group_id: -7542, protection_element_id:-7542], primaryKey: false);
      insert('organizations', [ id: 107528, nci_institute_code: "VA159", name: "Williamsburg Surgery", city: "Williamsburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA159",GROUP_DESC:"VA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8543 ,protection_group_id: -7543, protection_element_id:-7543], primaryKey: false);
      insert('organizations', [ id: 107529, nci_institute_code: "VA160", name: "Riverside Gynecologic Oncology", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA160",GROUP_DESC:"VA160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8544 ,protection_group_id: -7544, protection_element_id:-7544], primaryKey: false);
      insert('organizations', [ id: 107530, nci_institute_code: "VA161", name: "C3: Colorectal Cancer Coalition", city: "Alexandria", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA161",GROUP_DESC:"VA161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8545 ,protection_group_id: -7545, protection_element_id:-7545], primaryKey: false);
      insert('organizations', [ id: 107531, nci_institute_code: "VA162", name: "Neurosurgical Associates PC", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA162",GROUP_DESC:"VA162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8546 ,protection_group_id: -7546, protection_element_id:-7546], primaryKey: false);
      insert('organizations', [ id: 107532, nci_institute_code: "VALG", name: "Veteran's Administration Lung Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VALG",GROUP_DESC:"VALG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VALG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VALG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VALG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8547 ,protection_group_id: -7547, protection_element_id:-7547], primaryKey: false);
      insert('organizations', [ id: 107533, nci_institute_code: "VASOG", name: "Veteran's Administration Surgical Oncology Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VASOG",GROUP_DESC:"VASOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VASOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VASOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VASOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8548 ,protection_group_id: -7548, protection_element_id:-7548], primaryKey: false);
      insert('organizations', [ id: 107534, nci_institute_code: "VT001", name: "White River Junction Veteran Administration Medical Center", city: "White River Junction", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT001",GROUP_DESC:"VT001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8549 ,protection_group_id: -7549, protection_element_id:-7549], primaryKey: false);
      insert('organizations', [ id: 107535, nci_institute_code: "VT002", name: "Medical Center Hospital of Vermont", city: "Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT002",GROUP_DESC:"VT002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8550 ,protection_group_id: -7550, protection_element_id:-7550], primaryKey: false);
      insert('organizations', [ id: 107536, nci_institute_code: "VT003", name: "Fletcher Allen Health Care", city: "Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT003",GROUP_DESC:"VT003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8551 ,protection_group_id: -7551, protection_element_id:-7551], primaryKey: false);
      insert('organizations', [ id: 107537, nci_institute_code: "VT004", name: "University of Vermont", city: "Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT004",GROUP_DESC:"VT004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8552 ,protection_group_id: -7552, protection_element_id:-7552], primaryKey: false);
      insert('organizations', [ id: 107538, nci_institute_code: "VT005", name: "Southwestern Vermont Medical Center", city: "Bennington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT005",GROUP_DESC:"VT005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8553 ,protection_group_id: -7553, protection_element_id:-7553], primaryKey: false);
      insert('organizations', [ id: 107539, nci_institute_code: "VT006", name: "Rutland Regional Medical Center", city: "Rutland", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT006",GROUP_DESC:"VT006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8554 ,protection_group_id: -7554, protection_element_id:-7554], primaryKey: false);
      insert('organizations', [ id: 107540, nci_institute_code: "VT007", name: "Vermont Cancer Center", city: "Colchester", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT007",GROUP_DESC:"VT007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8555 ,protection_group_id: -7555, protection_element_id:-7555], primaryKey: false);
      insert('organizations', [ id: 107541, nci_institute_code: "VT008", name: "Southwestern Oncology Research Department", city: "Bennington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT008",GROUP_DESC:"VT008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8556 ,protection_group_id: -7556, protection_element_id:-7556], primaryKey: false);
      insert('organizations', [ id: 107542, nci_institute_code: "VT009", name: "Brattleboro Memorial Hospital", city: "Brattleboro", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT009",GROUP_DESC:"VT009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8557 ,protection_group_id: -7557, protection_element_id:-7557], primaryKey: false);
      insert('organizations', [ id: 107543, nci_institute_code: "VT010", name: "Springfield Hospital", city: "Springfield", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT010",GROUP_DESC:"VT010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8558 ,protection_group_id: -7558, protection_element_id:-7558], primaryKey: false);
      insert('organizations', [ id: 107544, nci_institute_code: "VT011", name: "Northeastern", city: "St. Johnsbury", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT011",GROUP_DESC:"VT011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8559 ,protection_group_id: -7559, protection_element_id:-7559], primaryKey: false);
      insert('organizations', [ id: 107545, nci_institute_code: "VT012", name: "Central Vermont Hospital", city: "Barre", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT012",GROUP_DESC:"VT012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8560 ,protection_group_id: -7560, protection_element_id:-7560], primaryKey: false);
      insert('organizations', [ id: 107546, nci_institute_code: "VT013", name: "Central Vermont Medical Center", city: "Barre", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT013",GROUP_DESC:"VT013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8561 ,protection_group_id: -7561, protection_element_id:-7561], primaryKey: false);
      insert('organizations', [ id: 107547, nci_institute_code: "VT014", name: "Associates In Urology, P.C.", city: "Bennington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT014",GROUP_DESC:"VT014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8562 ,protection_group_id: -7562, protection_element_id:-7562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 107548, nci_institute_code: "VT015", name: "Gifford Medical Center", city: "Randolph", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT015",GROUP_DESC:"VT015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8563 ,protection_group_id: -7563, protection_element_id:-7563], primaryKey: false);
      insert('organizations', [ id: 107549, nci_institute_code: "VT016", name: "Copley Hospital", city: "Morrisville", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT016",GROUP_DESC:"VT016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8564 ,protection_group_id: -7564, protection_element_id:-7564], primaryKey: false);
      insert('organizations', [ id: 107550, nci_institute_code: "VT017", name: "Veterans Administration Medical Center", city: "Water River Junction", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT017",GROUP_DESC:"VT017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8565 ,protection_group_id: -7565, protection_element_id:-7565], primaryKey: false);
      insert('organizations', [ id: 107551, nci_institute_code: "VT018", name: "University of Vermont", city: "Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT018",GROUP_DESC:"VT018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8566 ,protection_group_id: -7566, protection_element_id:-7566], primaryKey: false);
      insert('organizations', [ id: 107552, nci_institute_code: "VT019", name: "Mountainview Medical", city: "Berlin", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT019",GROUP_DESC:"VT019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8567 ,protection_group_id: -7567, protection_element_id:-7567], primaryKey: false);
      insert('organizations', [ id: 107553, nci_institute_code: "VT020", name: "Green Mountain Urology, Inc", city: "Colchester", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT020",GROUP_DESC:"VT020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8568 ,protection_group_id: -7568, protection_element_id:-7568], primaryKey: false);
      insert('organizations', [ id: 107554, nci_institute_code: "VT021", name: "North Country Health", city: "Newport", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT021",GROUP_DESC:"VT021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8569 ,protection_group_id: -7569, protection_element_id:-7569], primaryKey: false);
      insert('organizations', [ id: 107555, nci_institute_code: "VT022", name: "Mount Ascutney Hospital and Health Center", city: "Windsor", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT022",GROUP_DESC:"VT022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8570 ,protection_group_id: -7570, protection_element_id:-7570], primaryKey: false);
      insert('organizations', [ id: 107556, nci_institute_code: "VT023", name: "Southwestern Vermont Health Care Oncolgy Associates", city: "Bennington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT023",GROUP_DESC:"VT023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8571 ,protection_group_id: -7571, protection_element_id:-7571], primaryKey: false);
      insert('organizations', [ id: 107557, nci_institute_code: "VT024", name: "Lake Champlain Gynecologic Oncology, P.C.", city: "South Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT024",GROUP_DESC:"VT024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8572 ,protection_group_id: -7572, protection_element_id:-7572], primaryKey: false);
      insert('organizations', [ id: 107558, nci_institute_code: "VT025", name: "Fletcher Allen Health Care-Medical Center Campus", city: "Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT025",GROUP_DESC:"VT025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8573 ,protection_group_id: -7573, protection_element_id:-7573], primaryKey: false);
      insert('organizations', [ id: 107559, nci_institute_code: "VT026", name: "Morrisville Family Healthcare", city: "Morrisville", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT026",GROUP_DESC:"VT026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8574 ,protection_group_id: -7574, protection_element_id:-7574], primaryKey: false);
      insert('organizations', [ id: 107560, nci_institute_code: "VT027", name: "Flecher Allen Health Care/University of Vermont College of Medicine", city: "South Burlington", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT027",GROUP_DESC:"VT027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8575 ,protection_group_id: -7575, protection_element_id:-7575], primaryKey: false);
      insert('organizations', [ id: 107561, nci_institute_code: "VT029", name: "Norris Cotton Cancer Center", city: "Saint Johnsbury", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT029",GROUP_DESC:"VT029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8576 ,protection_group_id: -7576, protection_element_id:-7576], primaryKey: false);
      insert('organizations', [ id: 107562, nci_institute_code: "WA001", name: "Overlake Hospital Medical Center", city: "Bellevue", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA001",GROUP_DESC:"WA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8577 ,protection_group_id: -7577, protection_element_id:-7577], primaryKey: false);
      insert('organizations', [ id: 107563, nci_institute_code: "WA002", name: "Stevens Memorial", city: "Edmonds", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA002",GROUP_DESC:"WA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8578 ,protection_group_id: -7578, protection_element_id:-7578], primaryKey: false);
      insert('organizations', [ id: 107564, nci_institute_code: "WA003", name: "Evergreen Hospital Medical Center", city: "Kirkland", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA003",GROUP_DESC:"WA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8579 ,protection_group_id: -7579, protection_element_id:-7579], primaryKey: false);
      insert('organizations', [ id: 107565, nci_institute_code: "WA004", name: "Valley Medical Center", city: "Renton", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA004",GROUP_DESC:"WA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8580 ,protection_group_id: -7580, protection_element_id:-7580], primaryKey: false);
      insert('organizations', [ id: 107566, nci_institute_code: "WA005", name: "Virginia Mason Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA005",GROUP_DESC:"WA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8581 ,protection_group_id: -7581, protection_element_id:-7581], primaryKey: false);
      insert('organizations', [ id: 107567, nci_institute_code: "WA007", name: "Swedish Hospital Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA007",GROUP_DESC:"WA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8582 ,protection_group_id: -7582, protection_element_id:-7582], primaryKey: false);
      insert('organizations', [ id: 107568, nci_institute_code: "WA008", name: "Fred Hutchinson Cancer Research Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA008",GROUP_DESC:"WA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8583 ,protection_group_id: -7583, protection_element_id:-7583], primaryKey: false);
      insert('organizations', [ id: 107569, nci_institute_code: "WA009", name: "Ballard Community Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA009",GROUP_DESC:"WA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8584 ,protection_group_id: -7584, protection_element_id:-7584], primaryKey: false);
      insert('organizations', [ id: 107570, nci_institute_code: "WA010", name: "Veterans Administration Center, Seattle", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA010",GROUP_DESC:"WA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8585 ,protection_group_id: -7585, protection_element_id:-7585], primaryKey: false);
      insert('organizations', [ id: 107571, nci_institute_code: "WA011", name: "Group Health Cooperative of Puget Sound Oncology Consortium", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA011",GROUP_DESC:"WA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8586 ,protection_group_id: -7586, protection_element_id:-7586], primaryKey: false);
      insert('organizations', [ id: 107572, nci_institute_code: "WA012", name: "Seattle Public Health Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA012",GROUP_DESC:"WA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8587 ,protection_group_id: -7587, protection_element_id:-7587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 107573, nci_institute_code: "WA013", name: "Central Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA013",GROUP_DESC:"WA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8588 ,protection_group_id: -7588, protection_element_id:-7588], primaryKey: false);
      insert('organizations', [ id: 107574, nci_institute_code: "WA014", name: "Childrens Orthopedic Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA014",GROUP_DESC:"WA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8589 ,protection_group_id: -7589, protection_element_id:-7589], primaryKey: false);
      insert('organizations', [ id: 107575, nci_institute_code: "WA015", name: "Neorx Corporation", city: "Seatlle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA015",GROUP_DESC:"WA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8590 ,protection_group_id: -7590, protection_element_id:-7590], primaryKey: false);
      insert('organizations', [ id: 107576, nci_institute_code: "WA017", name: "Northwest Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA017",GROUP_DESC:"WA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8591 ,protection_group_id: -7591, protection_element_id:-7591], primaryKey: false);
      insert('organizations', [ id: 107577, nci_institute_code: "WA018", name: "Pacific Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA018",GROUP_DESC:"WA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8592 ,protection_group_id: -7592, protection_element_id:-7592], primaryKey: false);
      insert('organizations', [ id: 107578, nci_institute_code: "WA019", name: "Highline Community Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA019",GROUP_DESC:"WA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8593 ,protection_group_id: -7593, protection_element_id:-7593], primaryKey: false);
      insert('organizations', [ id: 107579, nci_institute_code: "WA020", name: "University of Washington Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA020",GROUP_DESC:"WA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8594 ,protection_group_id: -7594, protection_element_id:-7594], primaryKey: false);
      insert('organizations', [ id: 107580, nci_institute_code: "WA021", name: "Providence General Medical Center", city: "Everett", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA021",GROUP_DESC:"WA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8595 ,protection_group_id: -7595, protection_element_id:-7595], primaryKey: false);
      insert('organizations', [ id: 107581, nci_institute_code: "WA022", name: "General Hospital of Everett", city: "Everett", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA022",GROUP_DESC:"WA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8596 ,protection_group_id: -7596, protection_element_id:-7596], primaryKey: false);
      insert('organizations', [ id: 107582, nci_institute_code: "WA023", name: "St. Lukes General Hospital", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA023",GROUP_DESC:"WA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8597 ,protection_group_id: -7597, protection_element_id:-7597], primaryKey: false);
      insert('organizations', [ id: 107583, nci_institute_code: "WA024", name: "Skagit Valley Hospital", city: "Mt. Vernon", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA024",GROUP_DESC:"WA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8598 ,protection_group_id: -7598, protection_element_id:-7598], primaryKey: false);
      insert('organizations', [ id: 107584, nci_institute_code: "WA025", name: "United General Hospital", city: "Sedro-Wooley", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA025",GROUP_DESC:"WA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8599 ,protection_group_id: -7599, protection_element_id:-7599], primaryKey: false);
      insert('organizations', [ id: 107585, nci_institute_code: "WA026", name: "Harrison Medical Center", city: "Bremerton", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA026",GROUP_DESC:"WA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8600 ,protection_group_id: -7600, protection_element_id:-7600], primaryKey: false);
      insert('organizations', [ id: 107586, nci_institute_code: "WA027", name: "Madigan Army Medical Center", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA027",GROUP_DESC:"WA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8601 ,protection_group_id: -7601, protection_element_id:-7601], primaryKey: false);
      insert('organizations', [ id: 107587, nci_institute_code: "WA028", name: "Olympic Memorial Hospital", city: "Port Angeles", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA028",GROUP_DESC:"WA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8602 ,protection_group_id: -7602, protection_element_id:-7602], primaryKey: false);
      insert('organizations', [ id: 107588, nci_institute_code: "WA029", name: "Good Samaritan Community Hospital", city: "Puyallup", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA029",GROUP_DESC:"WA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8603 ,protection_group_id: -7603, protection_element_id:-7603], primaryKey: false);
      insert('organizations', [ id: 107589, nci_institute_code: "WA030", name: "Tacoma General Hospital", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA030",GROUP_DESC:"WA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8604 ,protection_group_id: -7604, protection_element_id:-7604], primaryKey: false);
      insert('organizations', [ id: 107590, nci_institute_code: "WA031", name: "Mary Bridge Children's Hospital and Health Center", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA031",GROUP_DESC:"WA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8605 ,protection_group_id: -7605, protection_element_id:-7605], primaryKey: false);
      insert('organizations', [ id: 107591, nci_institute_code: "WA032", name: "Humana Hospital Tacoma", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA032",GROUP_DESC:"WA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8606 ,protection_group_id: -7606, protection_element_id:-7606], primaryKey: false);
      insert('organizations', [ id: 107592, nci_institute_code: "WA033", name: "Veterans Administration Medical Center, Tacoma", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA033",GROUP_DESC:"WA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8607 ,protection_group_id: -7607, protection_element_id:-7607], primaryKey: false);
      insert('organizations', [ id: 107593, nci_institute_code: "WA034", name: "Grays Harbor Community Hospital", city: "Aberdeen", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA034",GROUP_DESC:"WA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8608 ,protection_group_id: -7608, protection_element_id:-7608], primaryKey: false);
      insert('organizations', [ id: 107594, nci_institute_code: "WA036", name: "Saint John Medical Center", city: "Longview", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA036",GROUP_DESC:"WA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8609 ,protection_group_id: -7609, protection_element_id:-7609], primaryKey: false);
      insert('organizations', [ id: 107595, nci_institute_code: "WA037", name: "Southwest Washington Medical Center", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA037",GROUP_DESC:"WA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8610 ,protection_group_id: -7610, protection_element_id:-7610], primaryKey: false);
      insert('organizations', [ id: 107596, nci_institute_code: "WA039", name: "Central Washington Hospital", city: "Wenatchee", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA039",GROUP_DESC:"WA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8611 ,protection_group_id: -7611, protection_element_id:-7611], primaryKey: false);
      insert('organizations', [ id: 107597, nci_institute_code: "WA041", name: "Mid-Valley Hospital", city: "Omak", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA041",GROUP_DESC:"WA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8612 ,protection_group_id: -7612, protection_element_id:-7612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 107598, nci_institute_code: "WA042", name: "Providence Yakima Medical Center", city: "Yakima", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA042",GROUP_DESC:"WA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8613 ,protection_group_id: -7613, protection_element_id:-7613], primaryKey: false);
      insert('organizations', [ id: 107599, nci_institute_code: "WA043", name: "Yakima Valley Memorial Hospital North Star Lodge Cancer Center", city: "Yakima", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA043",GROUP_DESC:"WA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8614 ,protection_group_id: -7614, protection_element_id:-7614], primaryKey: false);
      insert('organizations', [ id: 107600, nci_institute_code: "WA046", name: "Rockwood Clinic", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA046",GROUP_DESC:"WA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8615 ,protection_group_id: -7615, protection_element_id:-7615], primaryKey: false);
      insert('organizations', [ id: 107601, nci_institute_code: "WA047", name: "Department of Veterans Affairs Medical Center, Spokane", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA047",GROUP_DESC:"WA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8616 ,protection_group_id: -7616, protection_element_id:-7616], primaryKey: false);
      insert('organizations', [ id: 107602, nci_institute_code: "WA048", name: "Holy Family Hospital", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA048",GROUP_DESC:"WA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8617 ,protection_group_id: -7617, protection_element_id:-7617], primaryKey: false);
      insert('organizations', [ id: 107603, nci_institute_code: "WA049", name: "Deaconess Medical Center", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA049",GROUP_DESC:"WA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8618 ,protection_group_id: -7618, protection_element_id:-7618], primaryKey: false);
      insert('organizations', [ id: 107604, nci_institute_code: "WA050", name: "Sacred Heart Medical Center", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA050",GROUP_DESC:"WA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8619 ,protection_group_id: -7619, protection_element_id:-7619], primaryKey: false);
      insert('organizations', [ id: 107605, nci_institute_code: "WA053", name: "Saint Mary Regional Cancer Center", city: "Walla Walla", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA053",GROUP_DESC:"WA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8620 ,protection_group_id: -7620, protection_element_id:-7620], primaryKey: false);
      insert('organizations', [ id: 107606, nci_institute_code: "WA054", name: "Virginia Mason Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA054",GROUP_DESC:"WA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8621 ,protection_group_id: -7621, protection_element_id:-7621], primaryKey: false);
      insert('organizations', [ id: 107607, nci_institute_code: "WA055", name: "Saint Joseph Medical Center", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA055",GROUP_DESC:"WA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8622 ,protection_group_id: -7622, protection_element_id:-7622], primaryKey: false);
      insert('organizations', [ id: 107608, nci_institute_code: "WA056", name: "Multicare Health System", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA056",GROUP_DESC:"WA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8623 ,protection_group_id: -7623, protection_element_id:-7623], primaryKey: false);
      insert('organizations', [ id: 107609, nci_institute_code: "WA058", name: "First Hill Women's Clinic", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA058",GROUP_DESC:"WA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8624 ,protection_group_id: -7624, protection_element_id:-7624], primaryKey: false);
      insert('organizations', [ id: 107610, nci_institute_code: "WA059", name: "Group Health Cooperative", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA059",GROUP_DESC:"WA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8625 ,protection_group_id: -7625, protection_element_id:-7625], primaryKey: false);
      insert('organizations', [ id: 107611, nci_institute_code: "WA061", name: "Children's Hospital and Regional Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA061",GROUP_DESC:"WA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8626 ,protection_group_id: -7626, protection_element_id:-7626], primaryKey: false);
      insert('organizations', [ id: 107612, nci_institute_code: "WA062", name: "Providence Medical Center", city: "Seatle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA062",GROUP_DESC:"WA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8627 ,protection_group_id: -7627, protection_element_id:-7627], primaryKey: false);
      insert('organizations', [ id: 107613, nci_institute_code: "WA063", name: "Vancouver Medical Office", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA063",GROUP_DESC:"WA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8628 ,protection_group_id: -7628, protection_element_id:-7628], primaryKey: false);
      insert('organizations', [ id: 107614, nci_institute_code: "WA064", name: "Northwest CCOP", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA064",GROUP_DESC:"WA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8629 ,protection_group_id: -7629, protection_element_id:-7629], primaryKey: false);
      insert('organizations', [ id: 107615, nci_institute_code: "WA065", name: "Vancouver Clinic", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA065",GROUP_DESC:"WA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8630 ,protection_group_id: -7630, protection_element_id:-7630], primaryKey: false);
      insert('organizations', [ id: 107616, nci_institute_code: "WA067", name: "Tacoma Radiation Center", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA067",GROUP_DESC:"WA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8631 ,protection_group_id: -7631, protection_element_id:-7631], primaryKey: false);
      insert('organizations', [ id: 107617, nci_institute_code: "WA069", name: "Saint Joseph Hospital", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA069",GROUP_DESC:"WA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8632 ,protection_group_id: -7632, protection_element_id:-7632], primaryKey: false);
      insert('organizations', [ id: 107618, nci_institute_code: "WA070", name: "Rainier Physicians - Northwest Medical Specialties", city: "Puyallup", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA070",GROUP_DESC:"WA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8633 ,protection_group_id: -7633, protection_element_id:-7633], primaryKey: false);
      insert('organizations', [ id: 107619, nci_institute_code: "WA071", name: "Wenatchee Valley Medical Center", city: "Wenatchee", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA071",GROUP_DESC:"WA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8634 ,protection_group_id: -7634, protection_element_id:-7634], primaryKey: false);
      insert('organizations', [ id: 107620, nci_institute_code: "WA072", name: "Virginia Mason CCOP", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA072",GROUP_DESC:"WA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8635 ,protection_group_id: -7635, protection_element_id:-7635], primaryKey: false);
      insert('organizations', [ id: 107621, nci_institute_code: "WA073", name: "The Polyclinic", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA073",GROUP_DESC:"WA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8636 ,protection_group_id: -7636, protection_element_id:-7636], primaryKey: false);
      insert('organizations', [ id: 107622, nci_institute_code: "WA074", name: "Puget Sound Cancer Center", city: "Edmonds", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA074",GROUP_DESC:"WA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8637 ,protection_group_id: -7637, protection_element_id:-7637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 107623, nci_institute_code: "WA075", name: "Southlake Clinic", city: "Renton", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA075",GROUP_DESC:"WA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8638 ,protection_group_id: -7638, protection_element_id:-7638], primaryKey: false);
      insert('organizations', [ id: 107624, nci_institute_code: "WA076", name: "Western Washington Cancer Treatment Center", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA076",GROUP_DESC:"WA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8639 ,protection_group_id: -7639, protection_element_id:-7639], primaryKey: false);
      insert('organizations', [ id: 107625, nci_institute_code: "WA077", name: "Olympia Radiological Association Limited.,", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA077",GROUP_DESC:"WA077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8640 ,protection_group_id: -7640, protection_element_id:-7640], primaryKey: false);
      insert('organizations', [ id: 107626, nci_institute_code: "WA078", name: "Medical Oncology/Hematology Association", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA078",GROUP_DESC:"WA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8641 ,protection_group_id: -7641, protection_element_id:-7641], primaryKey: false);
      insert('organizations', [ id: 107627, nci_institute_code: "WA079", name: "Memorial Clinic", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA079",GROUP_DESC:"WA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8642 ,protection_group_id: -7642, protection_element_id:-7642], primaryKey: false);
      insert('organizations', [ id: 107628, nci_institute_code: "WA080", name: "Northwest Gastroenterology Clinic", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA080",GROUP_DESC:"WA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8643 ,protection_group_id: -7643, protection_element_id:-7643], primaryKey: false);
      insert('organizations', [ id: 107629, nci_institute_code: "WA081", name: "Group Health Cooperative- Tacoma Medical Center", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA081",GROUP_DESC:"WA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8644 ,protection_group_id: -7644, protection_element_id:-7644], primaryKey: false);
      insert('organizations', [ id: 107630, nci_institute_code: "WA082", name: "Longview Surgery", city: "Longview", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA082",GROUP_DESC:"WA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8645 ,protection_group_id: -7645, protection_element_id:-7645], primaryKey: false);
      insert('organizations', [ id: 107631, nci_institute_code: "WA083", name: "Spokane Oncology Hematology Associates", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA083",GROUP_DESC:"WA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8646 ,protection_group_id: -7646, protection_element_id:-7646], primaryKey: false);
      insert('organizations', [ id: 107632, nci_institute_code: "WA084", name: "Internal Medicine Associates/Yakima", city: "Yakima", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA084",GROUP_DESC:"WA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8647 ,protection_group_id: -7647, protection_element_id:-7647], primaryKey: false);
      insert('organizations', [ id: 107633, nci_institute_code: "WA085", name: "Auburn Regional Medical Center", city: "Auburn", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA085",GROUP_DESC:"WA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8648 ,protection_group_id: -7648, protection_element_id:-7648], primaryKey: false);
      insert('organizations', [ id: 107634, nci_institute_code: "WA086", name: "Allenmore Hospital", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA086",GROUP_DESC:"WA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8649 ,protection_group_id: -7649, protection_element_id:-7649], primaryKey: false);
      insert('organizations', [ id: 107635, nci_institute_code: "WA087", name: "Capital Medical Center", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA087",GROUP_DESC:"WA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8650 ,protection_group_id: -7650, protection_element_id:-7650], primaryKey: false);
      insert('organizations', [ id: 107636, nci_institute_code: "WA088", name: "Providence - Saint Peter Hospital", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA088",GROUP_DESC:"WA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8651 ,protection_group_id: -7651, protection_element_id:-7651], primaryKey: false);
      insert('organizations', [ id: 107637, nci_institute_code: "WA089", name: "Saint Clare Hospital", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA089",GROUP_DESC:"WA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8652 ,protection_group_id: -7652, protection_element_id:-7652], primaryKey: false);
      insert('organizations', [ id: 107638, nci_institute_code: "WA090", name: "Saint Francis Hospital", city: "Federal Way", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA090",GROUP_DESC:"WA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8653 ,protection_group_id: -7653, protection_element_id:-7653], primaryKey: false);
      insert('organizations', [ id: 107639, nci_institute_code: "WA091", name: "Group Health Cooperative", city: "Redmond", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA091",GROUP_DESC:"WA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8654 ,protection_group_id: -7654, protection_element_id:-7654], primaryKey: false);
      insert('organizations', [ id: 107640, nci_institute_code: "WA092", name: "Harborview Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA092",GROUP_DESC:"WA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8655 ,protection_group_id: -7655, protection_element_id:-7655], primaryKey: false);
      insert('organizations', [ id: 107641, nci_institute_code: "WA093", name: "Providence Centralia Hospital", city: "Centralia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA093",GROUP_DESC:"WA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8656 ,protection_group_id: -7656, protection_element_id:-7656], primaryKey: false);
      insert('organizations', [ id: 107642, nci_institute_code: "WA094", name: "Southwest Tumor Institute", city: "Burien", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA094",GROUP_DESC:"WA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8657 ,protection_group_id: -7657, protection_element_id:-7657], primaryKey: false);
      insert('organizations', [ id: 107643, nci_institute_code: "WA096", name: "Urology Clinic of Southwest Washington PS", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA096",GROUP_DESC:"WA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8658 ,protection_group_id: -7658, protection_element_id:-7658], primaryKey: false);
      insert('organizations', [ id: 107644, nci_institute_code: "WA097", name: "Cancer Care Northwest", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA097",GROUP_DESC:"WA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8659 ,protection_group_id: -7659, protection_element_id:-7659], primaryKey: false);
      insert('organizations', [ id: 107645, nci_institute_code: "WA099", name: "Highline Community Hospital", city: "Burien", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA099",GROUP_DESC:"WA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8660 ,protection_group_id: -7660, protection_element_id:-7660], primaryKey: false);
      insert('organizations', [ id: 107646, nci_institute_code: "WA100", name: "Cascade Cancer Center", city: "Kirkland", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA100",GROUP_DESC:"WA100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8661 ,protection_group_id: -7661, protection_element_id:-7661], primaryKey: false);
      insert('organizations', [ id: 107647, nci_institute_code: "WA101", name: "Cancer Care Northwest - Spokane South", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA101",GROUP_DESC:"WA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8662 ,protection_group_id: -7662, protection_element_id:-7662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 107648, nci_institute_code: "WA102", name: "Seattle Cancer Care Alliance", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA102",GROUP_DESC:"WA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8663 ,protection_group_id: -7663, protection_element_id:-7663], primaryKey: false);
      insert('organizations', [ id: 107649, nci_institute_code: "WA103", name: "Everett Clinic Ctr for Cancer Care", city: "Everett", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA103",GROUP_DESC:"WA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8664 ,protection_group_id: -7664, protection_element_id:-7664], primaryKey: false);
      insert('organizations', [ id: 107650, nci_institute_code: "WA104", name: "Madrona Medical Group PC", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA104",GROUP_DESC:"WA104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8665 ,protection_group_id: -7665, protection_element_id:-7665], primaryKey: false);
      insert('organizations', [ id: 107651, nci_institute_code: "WA105", name: "Eastside Oncology/Hematology", city: "Bellevue", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA105",GROUP_DESC:"WA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8666 ,protection_group_id: -7666, protection_element_id:-7666], primaryKey: false);
      insert('organizations', [ id: 107652, nci_institute_code: "WA106", name: "North Star Lodge Cancer Center", city: "Yakima", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA106",GROUP_DESC:"WA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8667 ,protection_group_id: -7667, protection_element_id:-7667], primaryKey: false);
      insert('organizations', [ id: 107653, nci_institute_code: "WA107", name: "Western Washington Oncology, INC. P.S.", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA107",GROUP_DESC:"WA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8668 ,protection_group_id: -7668, protection_element_id:-7668], primaryKey: false);
      insert('organizations', [ id: 107654, nci_institute_code: "WA108", name: "Northwest Cancer Specialists", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA108",GROUP_DESC:"WA108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8669 ,protection_group_id: -7669, protection_element_id:-7669], primaryKey: false);
      insert('organizations', [ id: 107655, nci_institute_code: "WA109", name: "Pacific Gynecology Specialists", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA109",GROUP_DESC:"WA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8670 ,protection_group_id: -7670, protection_element_id:-7670], primaryKey: false);
      insert('organizations', [ id: 107656, nci_institute_code: "WA110", name: "Pacific Medical Center-First Hill", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA110",GROUP_DESC:"WA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8671 ,protection_group_id: -7671, protection_element_id:-7671], primaryKey: false);
      insert('organizations', [ id: 107657, nci_institute_code: "WA111", name: "Kaiser Permanente-Vancouver", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA111",GROUP_DESC:"WA111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8672 ,protection_group_id: -7672, protection_element_id:-7672], primaryKey: false);
      insert('organizations', [ id: 107658, nci_institute_code: "WA112", name: "Tumor Institute at Swedish Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA112",GROUP_DESC:"WA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8673 ,protection_group_id: -7673, protection_element_id:-7673], primaryKey: false);
      insert('organizations', [ id: 107659, nci_institute_code: "WA113", name: "Olympic Hematology & Oncology Associates", city: "Bremerton", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA113",GROUP_DESC:"WA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8674 ,protection_group_id: -7674, protection_element_id:-7674], primaryKey: false);
      insert('organizations', [ id: 107660, nci_institute_code: "WA114", name: "Skagit Valley Hospital Regional Cancer Care Center", city: "Mount Vernon", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA114",GROUP_DESC:"WA114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8675 ,protection_group_id: -7675, protection_element_id:-7675], primaryKey: false);
      insert('organizations', [ id: 107661, nci_institute_code: "WA115", name: "Alliance Lung Cancer Center", city: "Vnacouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA115",GROUP_DESC:"WA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8676 ,protection_group_id: -7676, protection_element_id:-7676], primaryKey: false);
      insert('organizations', [ id: 107662, nci_institute_code: "WA116", name: "PhenoPath Laboratories, PLLC", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA116",GROUP_DESC:"WA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8677 ,protection_group_id: -7677, protection_element_id:-7677], primaryKey: false);
      insert('organizations', [ id: 107663, nci_institute_code: "WA117", name: "Medical Oncology Associates", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA117",GROUP_DESC:"WA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8678 ,protection_group_id: -7678, protection_element_id:-7678], primaryKey: false);
      insert('organizations', [ id: 107664, nci_institute_code: "WA118", name: "The Women's Clinic/Vancouver, P.S.", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA118",GROUP_DESC:"WA118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8679 ,protection_group_id: -7679, protection_element_id:-7679], primaryKey: false);
      insert('organizations', [ id: 107665, nci_institute_code: "WA119", name: "Medical Oncology Clinic", city: "Covington", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA119",GROUP_DESC:"WA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8680 ,protection_group_id: -7680, protection_element_id:-7680], primaryKey: false);
      insert('organizations', [ id: 107666, nci_institute_code: "WA120", name: "Northwest Medical Specialties, PLLC", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA120",GROUP_DESC:"WA120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8681 ,protection_group_id: -7681, protection_element_id:-7681], primaryKey: false);
      insert('organizations', [ id: 107667, nci_institute_code: "WA121", name: "Olympic Medical Cancer Care Center", city: "Sequim", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA121",GROUP_DESC:"WA121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8682 ,protection_group_id: -7682, protection_element_id:-7682], primaryKey: false);
      insert('organizations', [ id: 107668, nci_institute_code: "WA122", name: "Cancer Institutes of Washington PLLC", city: "Yakima", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA122",GROUP_DESC:"WA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8683 ,protection_group_id: -7683, protection_element_id:-7683], primaryKey: false);
      insert('organizations', [ id: 107669, nci_institute_code: "WA123", name: "Jamestown Family Health Clinic", city: "Sequim", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA123",GROUP_DESC:"WA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8684 ,protection_group_id: -7684, protection_element_id:-7684], primaryKey: false);
      insert('organizations', [ id: 107670, nci_institute_code: "WA124", name: "Cascadia Clinical Trials", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA124",GROUP_DESC:"WA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8685 ,protection_group_id: -7685, protection_element_id:-7685], primaryKey: false);
      insert('organizations', [ id: 107671, nci_institute_code: "WA126", name: "Radiation Oncology", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA126",GROUP_DESC:"WA126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8686 ,protection_group_id: -7686, protection_element_id:-7686], primaryKey: false);
      insert('organizations', [ id: 107672, nci_institute_code: "WA127", name: "Associated Surgeons", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA127",GROUP_DESC:"WA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8687 ,protection_group_id: -7687, protection_element_id:-7687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 107673, nci_institute_code: "WA129", name: "Northwest Breast Association", city: "Bellevue", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA129",GROUP_DESC:"WA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8688 ,protection_group_id: -7688, protection_element_id:-7688], primaryKey: false);
      insert('organizations', [ id: 107674, nci_institute_code: "WA130", name: "Central Admixture Pharmacy", city: "Kent", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA130",GROUP_DESC:"WA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8689 ,protection_group_id: -7689, protection_element_id:-7689], primaryKey: false);
      insert('organizations', [ id: 107675, nci_institute_code: "WA131", name: "Bellingham Breast Center", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA131",GROUP_DESC:"WA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8690 ,protection_group_id: -7690, protection_element_id:-7690], primaryKey: false);
      insert('organizations', [ id: 107676, nci_institute_code: "WA132", name: "North Sound Family Medicine", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA132",GROUP_DESC:"WA132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8691 ,protection_group_id: -7691, protection_element_id:-7691], primaryKey: false);
      insert('organizations', [ id: 107677, nci_institute_code: "WA133", name: "Western Institutional Review Board", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA133",GROUP_DESC:"WA133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8692 ,protection_group_id: -7692, protection_element_id:-7692], primaryKey: false);
      insert('organizations', [ id: 107678, nci_institute_code: "WA134", name: "Swedish Cancer Institute at Northwest Hospital and Medical Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA134",GROUP_DESC:"WA134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8693 ,protection_group_id: -7693, protection_element_id:-7693], primaryKey: false);
      insert('organizations', [ id: 107679, nci_institute_code: "WA135", name: "VA Puget Sound Health Care System", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA135",GROUP_DESC:"WA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8694 ,protection_group_id: -7694, protection_element_id:-7694], primaryKey: false);
      insert('organizations', [ id: 107680, nci_institute_code: "WA136", name: "Riverfront Medical Center/GH", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA136",GROUP_DESC:"WA136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8695 ,protection_group_id: -7695, protection_element_id:-7695], primaryKey: false);
      insert('organizations', [ id: 107681, nci_institute_code: "WA137", name: "Western Washington Oncology, Inc., P.S.", city: "Lacey", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA137",GROUP_DESC:"WA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8696 ,protection_group_id: -7696, protection_element_id:-7696], primaryKey: false);
      insert('organizations', [ id: 107682, nci_institute_code: "WA138", name: "Seattle Urological Associates", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA138",GROUP_DESC:"WA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8697 ,protection_group_id: -7697, protection_element_id:-7697], primaryKey: false);
      insert('organizations', [ id: 107683, nci_institute_code: "WA139", name: "Cancer Research and Biostatistics", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA139",GROUP_DESC:"WA139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8698 ,protection_group_id: -7698, protection_element_id:-7698], primaryKey: false);
      insert('organizations', [ id: 107684, nci_institute_code: "WA140", name: "The Cancer Center of Southwest Washington", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA140",GROUP_DESC:"WA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8699 ,protection_group_id: -7699, protection_element_id:-7699], primaryKey: false);
      insert('organizations', [ id: 107685, nci_institute_code: "WA141", name: "Swedish Cancer Institute - First Hill Campus", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA141",GROUP_DESC:"WA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8700 ,protection_group_id: -7700, protection_element_id:-7700], primaryKey: false);
      insert('organizations', [ id: 107686, nci_institute_code: "WA142", name: "Seattle Children's Hospital", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA142",GROUP_DESC:"WA142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8701 ,protection_group_id: -7701, protection_element_id:-7701], primaryKey: false);
      insert('organizations', [ id: 107687, nci_institute_code: "WA143", name: "Telethon Institute for Child Health Research", city: "West Perth", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA143",GROUP_DESC:"WA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8702 ,protection_group_id: -7702, protection_element_id:-7702], primaryKey: false);
      insert('organizations', [ id: 107688, nci_institute_code: "WA144", name: "Seattle Institute for Biomedical and Clinical Research", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA144",GROUP_DESC:"WA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8703 ,protection_group_id: -7703, protection_element_id:-7703], primaryKey: false);
      insert('organizations', [ id: 107689, nci_institute_code: "WA145", name: "Swedish Medical Center Providence Campus", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA145",GROUP_DESC:"WA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8704 ,protection_group_id: -7704, protection_element_id:-7704], primaryKey: false);
      insert('organizations', [ id: 107690, nci_institute_code: "WA146", name: "Spokane Radiation Oncology Associates", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA146",GROUP_DESC:"WA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8705 ,protection_group_id: -7705, protection_element_id:-7705], primaryKey: false);
      insert('organizations', [ id: 107691, nci_institute_code: "WA147", name: "Surgical Specialists of Spokane", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA147",GROUP_DESC:"WA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8706 ,protection_group_id: -7706, protection_element_id:-7706], primaryKey: false);
      insert('organizations', [ id: 107692, nci_institute_code: "WA148", name: "Benaroya Research Institute at Virginia Mason", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA148",GROUP_DESC:"WA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8707 ,protection_group_id: -7707, protection_element_id:-7707], primaryKey: false);
      insert('organizations', [ id: 107693, nci_institute_code: "WA149", name: "Rockwood Cancer Treatment Center", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA149",GROUP_DESC:"WA149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8708 ,protection_group_id: -7708, protection_element_id:-7708], primaryKey: false);
      insert('organizations', [ id: 107694, nci_institute_code: "WA150", name: "Minor & James Medical PLLC", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA150",GROUP_DESC:"WA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8709 ,protection_group_id: -7709, protection_element_id:-7709], primaryKey: false);
      insert('organizations', [ id: 107695, nci_institute_code: "WA151", name: "Providence Cancer Center", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA151",GROUP_DESC:"WA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8710 ,protection_group_id: -7710, protection_element_id:-7710], primaryKey: false);
      insert('organizations', [ id: 107696, nci_institute_code: "WA152", name: "Tender Care", city: "Bothell", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA152",GROUP_DESC:"WA152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8711 ,protection_group_id: -7711, protection_element_id:-7711], primaryKey: false);
      insert('organizations', [ id: 107697, nci_institute_code: "WA153", name: "Frazier Healthcare Ventures", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA153",GROUP_DESC:"WA153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8712 ,protection_group_id: -7712, protection_element_id:-7712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 107698, nci_institute_code: "WA154", name: "Columbia Basin Hematology and Oncology PLLC", city: "Kennewick", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA154",GROUP_DESC:"WA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8713 ,protection_group_id: -7713, protection_element_id:-7713], primaryKey: false);
      insert('organizations', [ id: 107699, nci_institute_code: "WA155", name: "Northwest Cancer Specialists", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA155",GROUP_DESC:"WA155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8714 ,protection_group_id: -7714, protection_element_id:-7714], primaryKey: false);
      insert('organizations', [ id: 107700, nci_institute_code: "WA156", name: "Partnership Oncology", city: "Bellevue", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA156",GROUP_DESC:"WA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8715 ,protection_group_id: -7715, protection_element_id:-7715], primaryKey: false);
      insert('organizations', [ id: 107701, nci_institute_code: "WA157", name: "Southwest Washington Thoracic and Vascular Surgery", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA157",GROUP_DESC:"WA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8716 ,protection_group_id: -7716, protection_element_id:-7716], primaryKey: false);
      insert('organizations', [ id: 107702, nci_institute_code: "WA158", name: "Inland Imaging Associates PS", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA158",GROUP_DESC:"WA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8717 ,protection_group_id: -7717, protection_element_id:-7717], primaryKey: false);
      insert('organizations', [ id: 107703, nci_institute_code: "WA159", name: "First Hill Surgeons", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA159",GROUP_DESC:"WA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8718 ,protection_group_id: -7718, protection_element_id:-7718], primaryKey: false);
      insert('organizations', [ id: 107704, nci_institute_code: "WA160", name: "Capital Oncology PLLC", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA160",GROUP_DESC:"WA160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8719 ,protection_group_id: -7719, protection_element_id:-7719], primaryKey: false);
      insert('organizations', [ id: 107705, nci_institute_code: "WA161", name: "Seattle Prostate Institute", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA161",GROUP_DESC:"WA161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8720 ,protection_group_id: -7720, protection_element_id:-7720], primaryKey: false);
      insert('organizations', [ id: 107706, nci_institute_code: "WA162", name: "Providence Regional Cancer Partnership", city: "Everett", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA162",GROUP_DESC:"WA162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8721 ,protection_group_id: -7721, protection_element_id:-7721], primaryKey: false);
      insert('organizations', [ id: 107707, nci_institute_code: "WA163", name: "Mary Bridge Hematology Oncology Clinic", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA163",GROUP_DESC:"WA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8722 ,protection_group_id: -7722, protection_element_id:-7722], primaryKey: false);
      insert('organizations', [ id: 107708, nci_institute_code: "WA164", name: "Partner Oncology", city: "Lakewood", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA164",GROUP_DESC:"WA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8723 ,protection_group_id: -7723, protection_element_id:-7723], primaryKey: false);
      insert('organizations', [ id: 107709, nci_institute_code: "WA165", name: "Inland Imaging at Beacon Hill", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA165",GROUP_DESC:"WA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8724 ,protection_group_id: -7724, protection_element_id:-7724], primaryKey: false);
      insert('organizations', [ id: 107710, nci_institute_code: "WA166", name: "North Spokane Pulmonary Clinic", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA166",GROUP_DESC:"WA166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8725 ,protection_group_id: -7725, protection_element_id:-7725], primaryKey: false);
      insert('organizations', [ id: 107711, nci_institute_code: "WCCG", name: "Western Cancer Chemotherapy Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WCCG",GROUP_DESC:"WCCG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WCCG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WCCG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WCCG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8726 ,protection_group_id: -7726, protection_element_id:-7726], primaryKey: false);
      insert('organizations', [ id: 107712, nci_institute_code: "WCG", name: "Weski Cancer Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WCG",GROUP_DESC:"WCG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WCG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WCG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WCG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8727 ,protection_group_id: -7727, protection_element_id:-7727], primaryKey: false);
      insert('organizations', [ id: 107713, nci_institute_code: "WI003", name: "Saint Catherines Hospital", city: "Kenosha", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI003",GROUP_DESC:"WI003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8728 ,protection_group_id: -7728, protection_element_id:-7728], primaryKey: false);
      insert('organizations', [ id: 107714, nci_institute_code: "WI004", name: "Zablocki Veterans Administration Medical Center", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI004",GROUP_DESC:"WI004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8729 ,protection_group_id: -7729, protection_element_id:-7729], primaryKey: false);
      insert('organizations', [ id: 107715, nci_institute_code: "WI005", name: "Medical College of Wisconsin-Wood", city: "Wood", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI005",GROUP_DESC:"WI005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8730 ,protection_group_id: -7730, protection_element_id:-7730], primaryKey: false);
      insert('organizations', [ id: 107716, nci_institute_code: "WI006", name: "Children's Hospital of Wisconsin", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI006",GROUP_DESC:"WI006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8731 ,protection_group_id: -7731, protection_element_id:-7731], primaryKey: false);
      insert('organizations', [ id: 107717, nci_institute_code: "WI007", name: "Aurora Sinai Medical Center", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI007",GROUP_DESC:"WI007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8732 ,protection_group_id: -7732, protection_element_id:-7732], primaryKey: false);
      insert('organizations', [ id: 107718, nci_institute_code: "WI008", name: "Columbia Saint Mary's Hospital -Milwaukee", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI008",GROUP_DESC:"WI008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8733 ,protection_group_id: -7733, protection_element_id:-7733], primaryKey: false);
      insert('organizations', [ id: 107719, nci_institute_code: "WI009", name: "Marshfield Clinic-Lakeland Ctr", city: "Minocqua", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI009",GROUP_DESC:"WI009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8734 ,protection_group_id: -7734, protection_element_id:-7734], primaryKey: false);
      insert('organizations', [ id: 107720, nci_institute_code: "WI010", name: "Columbia Saint Mary's Hospital-Columbia", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI010",GROUP_DESC:"WI010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8735 ,protection_group_id: -7735, protection_element_id:-7735], primaryKey: false);
      insert('organizations', [ id: 107721, nci_institute_code: "WI011", name: "Aurora Saint Luke's Medical Center", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI011",GROUP_DESC:"WI011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8736 ,protection_group_id: -7736, protection_element_id:-7736], primaryKey: false);
      insert('organizations', [ id: 107722, nci_institute_code: "WI012", name: "John Doyne Hospital", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI012",GROUP_DESC:"WI012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8737 ,protection_group_id: -7737, protection_element_id:-7737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 107723, nci_institute_code: "WI013", name: "Medical College of Wisconsin", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI013",GROUP_DESC:"WI013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8738 ,protection_group_id: -7738, protection_element_id:-7738], primaryKey: false);
      insert('organizations', [ id: 107724, nci_institute_code: "WI014", name: "Saint Luke's Hospital, Racine", city: "Racine", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI014",GROUP_DESC:"WI014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8739 ,protection_group_id: -7739, protection_element_id:-7739], primaryKey: false);
      insert('organizations', [ id: 107725, nci_institute_code: "WI015", name: "Mercy Health Systems", city: "Janesville", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI015",GROUP_DESC:"WI015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8740 ,protection_group_id: -7740, protection_element_id:-7740], primaryKey: false);
      insert('organizations', [ id: 107726, nci_institute_code: "WI016", name: "Janesville Riverview Clinic", city: "Janesville", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI016",GROUP_DESC:"WI016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8741 ,protection_group_id: -7741, protection_element_id:-7741], primaryKey: false);
      insert('organizations', [ id: 107727, nci_institute_code: "WI017", name: "Saint Clare Hospital Monroe Clinic", city: "Monroe", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI017",GROUP_DESC:"WI017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8742 ,protection_group_id: -7742, protection_element_id:-7742], primaryKey: false);
      insert('organizations', [ id: 107728, nci_institute_code: "WI018", name: "William S.Middleton VA Medical Center", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI018",GROUP_DESC:"WI018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8743 ,protection_group_id: -7743, protection_element_id:-7743], primaryKey: false);
      insert('organizations', [ id: 107729, nci_institute_code: "WI019", name: "Sacred Heart Hospital", city: "Eau Claire", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI019",GROUP_DESC:"WI019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8744 ,protection_group_id: -7744, protection_element_id:-7744], primaryKey: false);
      insert('organizations', [ id: 107730, nci_institute_code: "WI020", name: "University of Wisconsin Hospital and Clinics", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI020",GROUP_DESC:"WI020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8745 ,protection_group_id: -7745, protection_element_id:-7745], primaryKey: false);
      insert('organizations', [ id: 107731, nci_institute_code: "WI021", name: "Meriter Hospital", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI021",GROUP_DESC:"WI021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8746 ,protection_group_id: -7746, protection_element_id:-7746], primaryKey: false);
      insert('organizations', [ id: 107732, nci_institute_code: "WI022", name: "Saint Nicholas Hospital", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI022",GROUP_DESC:"WI022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8747 ,protection_group_id: -7747, protection_element_id:-7747], primaryKey: false);
      insert('organizations', [ id: 107733, nci_institute_code: "WI023", name: "Wisconsin Clinical Cancer Center", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI023",GROUP_DESC:"WI023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8748 ,protection_group_id: -7748, protection_element_id:-7748], primaryKey: false);
      insert('organizations', [ id: 107734, nci_institute_code: "WI027", name: "Saint Vincent Hospital", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI027",GROUP_DESC:"WI027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8749 ,protection_group_id: -7749, protection_element_id:-7749], primaryKey: false);
      insert('organizations', [ id: 107735, nci_institute_code: "WI028", name: "Aspirus Wausau Hospital Center", city: "Wausau", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI028",GROUP_DESC:"WI028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8750 ,protection_group_id: -7750, protection_element_id:-7750], primaryKey: false);
      insert('organizations', [ id: 107736, nci_institute_code: "WI029", name: "Gundersen Lutheran", city: "La Crosse", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI029",GROUP_DESC:"WI029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8751 ,protection_group_id: -7751, protection_element_id:-7751], primaryKey: false);
      insert('organizations', [ id: 107737, nci_institute_code: "WI030", name: "Saint Joseph's Hospital", city: "Marshfield", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI030",GROUP_DESC:"WI030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8752 ,protection_group_id: -7752, protection_element_id:-7752], primaryKey: false);
      insert('organizations', [ id: 107738, nci_institute_code: "WI031", name: "Marshfield Clinic", city: "Marshfield", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI031",GROUP_DESC:"WI031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8753 ,protection_group_id: -7753, protection_element_id:-7753], primaryKey: false);
      insert('organizations', [ id: 107739, nci_institute_code: "WI032", name: "Lacrosse Lutheran Hospital", city: "La Crosse", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI032",GROUP_DESC:"WI032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8754 ,protection_group_id: -7754, protection_element_id:-7754], primaryKey: false);
      insert('organizations', [ id: 107740, nci_institute_code: "WI035", name: "Saint Francis - Skemp", city: "La Crosse", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI035",GROUP_DESC:"WI035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8755 ,protection_group_id: -7755, protection_element_id:-7755], primaryKey: false);
      insert('organizations', [ id: 107741, nci_institute_code: "WI036", name: "Luther Hospital", city: "Eau Claire", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI036",GROUP_DESC:"WI036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8756 ,protection_group_id: -7756, protection_element_id:-7756], primaryKey: false);
      insert('organizations', [ id: 107742, nci_institute_code: "WI038", name: "Mercy Medical Center", city: "Oshkosh", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI038",GROUP_DESC:"WI038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8757 ,protection_group_id: -7757, protection_element_id:-7757], primaryKey: false);
      insert('organizations', [ id: 107743, nci_institute_code: "WI039", name: "Saint Elizabeth Hospital", city: "Appleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI039",GROUP_DESC:"WI039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8758 ,protection_group_id: -7758, protection_element_id:-7758], primaryKey: false);
      insert('organizations', [ id: 107744, nci_institute_code: "WI040", name: "Fond Du Lac Clinic", city: "Fond Du Lac", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI040",GROUP_DESC:"WI040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8759 ,protection_group_id: -7759, protection_element_id:-7759], primaryKey: false);
      insert('organizations', [ id: 107745, nci_institute_code: "WI042", name: "Nicolet Clinic, S.C.", city: "Neenah", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI042",GROUP_DESC:"WI042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8760 ,protection_group_id: -7760, protection_element_id:-7760], primaryKey: false);
      insert('organizations', [ id: 107746, nci_institute_code: "WI043", name: "Wheaton Franciscan Healthcare - Saint Joseph", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI043",GROUP_DESC:"WI043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8761 ,protection_group_id: -7761, protection_element_id:-7761], primaryKey: false);
      insert('organizations', [ id: 107747, nci_institute_code: "WI044", name: "Oncology Alliance - Milwaukee South", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI044",GROUP_DESC:"WI044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8762 ,protection_group_id: -7762, protection_element_id:-7762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 107748, nci_institute_code: "WI045", name: "Midelfort Clinic", city: "Eau Claire", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI045",GROUP_DESC:"WI045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8763 ,protection_group_id: -7763, protection_element_id:-7763], primaryKey: false);
      insert('organizations', [ id: 107749, nci_institute_code: "WI046", name: "Southern Wisconsin Radiotherapy Center", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI046",GROUP_DESC:"WI046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8764 ,protection_group_id: -7764, protection_element_id:-7764], primaryKey: false);
      insert('organizations', [ id: 107750, nci_institute_code: "WI047", name: "Saint Michael Hospital", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI047",GROUP_DESC:"WI047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8765 ,protection_group_id: -7765, protection_element_id:-7765], primaryKey: false);
      insert('organizations', [ id: 107751, nci_institute_code: "WI048", name: "Racine Medical Clinic", city: "Racine", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI048",GROUP_DESC:"WI048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8766 ,protection_group_id: -7766, protection_element_id:-7766], primaryKey: false);
      insert('organizations', [ id: 107752, nci_institute_code: "WI049", name: "Trinity Memorial Hospital", city: "Cudahy", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI049",GROUP_DESC:"WI049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8767 ,protection_group_id: -7767, protection_element_id:-7767], primaryKey: false);
      insert('organizations', [ id: 107753, nci_institute_code: "WI050", name: "Burlington Memorial Hospital", city: "Burlington", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI050",GROUP_DESC:"WI050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8768 ,protection_group_id: -7768, protection_element_id:-7768], primaryKey: false);
      insert('organizations', [ id: 107754, nci_institute_code: "WI051", name: "Waukesha Memorial Hospital", city: "Waukesha", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI051",GROUP_DESC:"WI051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8769 ,protection_group_id: -7769, protection_element_id:-7769], primaryKey: false);
      insert('organizations', [ id: 107755, nci_institute_code: "WI052", name: "Saint Mary's Hospital", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI052",GROUP_DESC:"WI052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8770 ,protection_group_id: -7770, protection_element_id:-7770], primaryKey: false);
      insert('organizations', [ id: 107756, nci_institute_code: "WI053", name: "Midwest Children's Cancer Center", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI053",GROUP_DESC:"WI053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8771 ,protection_group_id: -7771, protection_element_id:-7771], primaryKey: false);
      insert('organizations', [ id: 107757, nci_institute_code: "WI054", name: "University of Wisconsin Women's Health Center", city: "Middleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI054",GROUP_DESC:"WI054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8772 ,protection_group_id: -7772, protection_element_id:-7772], primaryKey: false);
      insert('organizations', [ id: 107758, nci_institute_code: "WI055", name: "Saint Francis Hospital", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI055",GROUP_DESC:"WI055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8773 ,protection_group_id: -7773, protection_element_id:-7773], primaryKey: false);
      insert('organizations', [ id: 107759, nci_institute_code: "WI056", name: "Froedtert Hospital", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI056",GROUP_DESC:"WI056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8774 ,protection_group_id: -7774, protection_element_id:-7774], primaryKey: false);
      insert('organizations', [ id: 107760, nci_institute_code: "WI057", name: "University Hospital", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI057",GROUP_DESC:"WI057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8775 ,protection_group_id: -7775, protection_element_id:-7775], primaryKey: false);
      insert('organizations', [ id: 107761, nci_institute_code: "WI058", name: "Saint Mary's Hospital", city: "Rhinelander", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI058",GROUP_DESC:"WI058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8776 ,protection_group_id: -7776, protection_element_id:-7776], primaryKey: false);
      insert('organizations', [ id: 107762, nci_institute_code: "WI059", name: "Dean Clinic", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI059",GROUP_DESC:"WI059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8777 ,protection_group_id: -7777, protection_element_id:-7777], primaryKey: false);
      insert('organizations', [ id: 107763, nci_institute_code: "WI060", name: "Saint Agnes Hospital", city: "Fond Du Lac", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI060",GROUP_DESC:"WI060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8778 ,protection_group_id: -7778, protection_element_id:-7778], primaryKey: false);
      insert('organizations', [ id: 107764, nci_institute_code: "WI061", name: "Wheaton Franciscan Cancer Care - All Saints", city: "Racine", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI061",GROUP_DESC:"WI061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8779 ,protection_group_id: -7779, protection_element_id:-7779], primaryKey: false);
      insert('organizations', [ id: 107765, nci_institute_code: "WI062", name: "Fox Valley Hematology and Oncology", city: "Appleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI062",GROUP_DESC:"WI062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8780 ,protection_group_id: -7780, protection_element_id:-7780], primaryKey: false);
      insert('organizations', [ id: 107766, nci_institute_code: "WI063", name: "Manitowoc Clinic", city: "Manitowoc", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI063",GROUP_DESC:"WI063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8781 ,protection_group_id: -7781, protection_element_id:-7781], primaryKey: false);
      insert('organizations', [ id: 107767, nci_institute_code: "WI064", name: "Beloit Clinic", city: "Beloit", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI064",GROUP_DESC:"WI064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8782 ,protection_group_id: -7782, protection_element_id:-7782], primaryKey: false);
      insert('organizations', [ id: 107768, nci_institute_code: "WI065", name: "Beloit Memorial Hospital", city: "Beloit", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI065",GROUP_DESC:"WI065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8783 ,protection_group_id: -7783, protection_element_id:-7783], primaryKey: false);
      insert('organizations', [ id: 107769, nci_institute_code: "WI066", name: "Urological Surgeons Limited", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI066",GROUP_DESC:"WI066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8784 ,protection_group_id: -7784, protection_element_id:-7784], primaryKey: false);
      insert('organizations', [ id: 107770, nci_institute_code: "WI067", name: "Saint Mary's Hospital", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI067",GROUP_DESC:"WI067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8785 ,protection_group_id: -7785, protection_element_id:-7785], primaryKey: false);
      insert('organizations', [ id: 107771, nci_institute_code: "WI069", name: "Marshfield Clinic/Chippewa Center", city: "Chippewa Falls", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI069",GROUP_DESC:"WI069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8786 ,protection_group_id: -7786, protection_element_id:-7786], primaryKey: false);
      insert('organizations', [ id: 107772, nci_institute_code: "WI072", name: "Sheboygan Medical Clinic", city: "Sheboygan", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI072",GROUP_DESC:"WI072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8787 ,protection_group_id: -7787, protection_element_id:-7787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 107773, nci_institute_code: "WI073", name: "Milwaukee Medical Clinic", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI073",GROUP_DESC:"WI073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8788 ,protection_group_id: -7788, protection_element_id:-7788], primaryKey: false);
      insert('organizations', [ id: 107774, nci_institute_code: "WI074", name: "S. Wi Radiotherapy Ctr", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI074",GROUP_DESC:"WI074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8789 ,protection_group_id: -7789, protection_element_id:-7789], primaryKey: false);
      insert('organizations', [ id: 107775, nci_institute_code: "WI075", name: "Affinity Health System - Lincoln Street Clinic", city: "Neenah", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI075",GROUP_DESC:"WI075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8790 ,protection_group_id: -7790, protection_element_id:-7790], primaryKey: false);
      insert('organizations', [ id: 107776, nci_institute_code: "WI076", name: "University of Wisconsin Medical School", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI076",GROUP_DESC:"WI076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8791 ,protection_group_id: -7791, protection_element_id:-7791], primaryKey: false);
      insert('organizations', [ id: 107777, nci_institute_code: "WI077", name: "Franciscan Skemp Healthcare", city: "La Crosse", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI077",GROUP_DESC:"WI077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8792 ,protection_group_id: -7792, protection_element_id:-7792], primaryKey: false);
      insert('organizations', [ id: 107778, nci_institute_code: "WI078", name: "Kenosha Hospital Medical Center Incorporated", city: "Kenosha", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI078",GROUP_DESC:"WI078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8793 ,protection_group_id: -7793, protection_element_id:-7793], primaryKey: false);
      insert('organizations', [ id: 107779, nci_institute_code: "WI080", name: "Prevea Clinic", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI080",GROUP_DESC:"WI080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8794 ,protection_group_id: -7794, protection_element_id:-7794], primaryKey: false);
      insert('organizations', [ id: 107780, nci_institute_code: "WI081", name: "Saint Mary's Medical Center", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI081",GROUP_DESC:"WI081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8795 ,protection_group_id: -7795, protection_element_id:-7795], primaryKey: false);
      insert('organizations', [ id: 107781, nci_institute_code: "WI082", name: "Hematology and Oncology Consultants", city: "Racine", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI082",GROUP_DESC:"WI082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8796 ,protection_group_id: -7796, protection_element_id:-7796], primaryKey: false);
      insert('organizations', [ id: 107782, nci_institute_code: "WI083", name: "West Allis Memorial Hospital", city: "West Allis", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI083",GROUP_DESC:"WI083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8797 ,protection_group_id: -7797, protection_element_id:-7797], primaryKey: false);
      insert('organizations', [ id: 107783, nci_institute_code: "WI084", name: "Ashland Memorial Medical Center", city: "Ashland", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI084",GROUP_DESC:"WI084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8798 ,protection_group_id: -7798, protection_element_id:-7798], primaryKey: false);
      insert('organizations', [ id: 107784, nci_institute_code: "WI085", name: "Medical Consultants Limited", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI085",GROUP_DESC:"WI085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8799 ,protection_group_id: -7799, protection_element_id:-7799], primaryKey: false);
      insert('organizations', [ id: 107785, nci_institute_code: "WI086", name: "Oncology Associates, P.C.", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI086",GROUP_DESC:"WI086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8800 ,protection_group_id: -7800, protection_element_id:-7800], primaryKey: false);
      insert('organizations', [ id: 107786, nci_institute_code: "WI087", name: "Oconomowoc Memorial Hospital-ProHealth Care Inc", city: "Oconomowoc", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI087",GROUP_DESC:"WI087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8801 ,protection_group_id: -7801, protection_element_id:-7801], primaryKey: false);
      insert('organizations', [ id: 107787, nci_institute_code: "WI088", name: "Community Memorial Hospital", city: "Menomonee Falls", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI088",GROUP_DESC:"WI088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8802 ,protection_group_id: -7802, protection_element_id:-7802], primaryKey: false);
      insert('organizations', [ id: 107788, nci_institute_code: "WI089", name: "Bellin Memorial Hospital", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI089",GROUP_DESC:"WI089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8803 ,protection_group_id: -7803, protection_element_id:-7803], primaryKey: false);
      insert('organizations', [ id: 107789, nci_institute_code: "WI090", name: "Central Wi Cancer Program", city: "Oshkosh", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI090",GROUP_DESC:"WI090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8804 ,protection_group_id: -7804, protection_element_id:-7804], primaryKey: false);
      insert('organizations', [ id: 107790, nci_institute_code: "WI091", name: "Community Memorial Hospital Cancer Care Center", city: "Menomonee Falls", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI091",GROUP_DESC:"WI091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8805 ,protection_group_id: -7805, protection_element_id:-7805], primaryKey: false);
      insert('organizations', [ id: 107791, nci_institute_code: "WI092", name: "Bay Area Medical Center", city: "Marinette", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI092",GROUP_DESC:"WI092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8806 ,protection_group_id: -7806, protection_element_id:-7806], primaryKey: false);
      insert('organizations', [ id: 107792, nci_institute_code: "WI093", name: "Riverview Hospital", city: "Wisconsin Rapids", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI093",GROUP_DESC:"WI093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8807 ,protection_group_id: -7807, protection_element_id:-7807], primaryKey: false);
      insert('organizations', [ id: 107793, nci_institute_code: "WI094", name: "Hess Memorial Hospital (Mile Bluff Clinic)", city: "Mauston", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI094",GROUP_DESC:"WI094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8808 ,protection_group_id: -7808, protection_element_id:-7808], primaryKey: false);
      insert('organizations', [ id: 107794, nci_institute_code: "WI095", name: "Milwaukee Veterans Administration Center (Clement J Zablocki VA Medical Center)", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI095",GROUP_DESC:"WI095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8809 ,protection_group_id: -7809, protection_element_id:-7809], primaryKey: false);
      insert('organizations', [ id: 107795, nci_institute_code: "WI096", name: "Holy Family Memorial Hospital", city: "Manitowoc", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI096",GROUP_DESC:"WI096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8810 ,protection_group_id: -7810, protection_element_id:-7810], primaryKey: false);
      insert('organizations', [ id: 107796, nci_institute_code: "WI098", name: "Central Wisconsin Cancer Program", city: "Fond Du Lac", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI098",GROUP_DESC:"WI098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8811 ,protection_group_id: -7811, protection_element_id:-7811], primaryKey: false);
      insert('organizations', [ id: 107797, nci_institute_code: "WI099", name: "Oncology Alliance-Glendale", city: "Glendale", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI099",GROUP_DESC:"WI099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8812 ,protection_group_id: -7812, protection_element_id:-7812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 107798, nci_institute_code: "WI100", name: "Lakeshore Medical Clinic", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI100",GROUP_DESC:"WI100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8813 ,protection_group_id: -7813, protection_element_id:-7813], primaryKey: false);
      insert('organizations', [ id: 107799, nci_institute_code: "WI101", name: "Covance Clinical Research Unit", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI101",GROUP_DESC:"WI101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8814 ,protection_group_id: -7814, protection_element_id:-7814], primaryKey: false);
      insert('organizations', [ id: 107800, nci_institute_code: "WI102", name: "University Station Clinics", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI102",GROUP_DESC:"WI102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8815 ,protection_group_id: -7815, protection_element_id:-7815], primaryKey: false);
      insert('organizations', [ id: 107801, nci_institute_code: "WI103", name: "Marshfield Clinic-Wausau Center", city: "Wausau", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI103",GROUP_DESC:"WI103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8816 ,protection_group_id: -7816, protection_element_id:-7816], primaryKey: false);
      insert('organizations', [ id: 107802, nci_institute_code: "WI104", name: "Oncology Alliance - Franklin", city: "Franklin", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI104",GROUP_DESC:"WI104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8817 ,protection_group_id: -7817, protection_element_id:-7817], primaryKey: false);
      insert('organizations', [ id: 107803, nci_institute_code: "WI105", name: "Saint Michael's Hospital", city: "Stevens Point", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI105",GROUP_DESC:"WI105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8818 ,protection_group_id: -7818, protection_element_id:-7818], primaryKey: false);
      insert('organizations', [ id: 107804, nci_institute_code: "WI107", name: "Aurora Health Care", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI107",GROUP_DESC:"WI107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8819 ,protection_group_id: -7819, protection_element_id:-7819], primaryKey: false);
      insert('organizations', [ id: 107805, nci_institute_code: "WI108", name: "Aurora Women's Pavilion of West Allis Memorial Hospital", city: "West Allis", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI108",GROUP_DESC:"WI108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8820 ,protection_group_id: -7820, protection_element_id:-7820], primaryKey: false);
      insert('organizations', [ id: 107806, nci_institute_code: "WI109", name: "Marshfield Clinic Indian Head Center", city: "Rice Lake", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI109",GROUP_DESC:"WI109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8821 ,protection_group_id: -7821, protection_element_id:-7821], primaryKey: false);
      insert('organizations', [ id: 107807, nci_institute_code: "WI111", name: "UW Health Oncology-One South Park", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI111",GROUP_DESC:"WI111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8822 ,protection_group_id: -7822, protection_element_id:-7822], primaryKey: false);
      insert('organizations', [ id: 107808, nci_institute_code: "WI112", name: "Duluth Clinic Ashland", city: "Ashland", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI112",GROUP_DESC:"WI112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8823 ,protection_group_id: -7823, protection_element_id:-7823], primaryKey: false);
      insert('organizations', [ id: 107809, nci_institute_code: "WI113", name: "Vince Lombardi Cancer Clinic-Sheboygan", city: "Sheboygan", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI113",GROUP_DESC:"WI113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8824 ,protection_group_id: -7824, protection_element_id:-7824], primaryKey: false);
      insert('organizations', [ id: 107810, nci_institute_code: "WI114", name: "Aurora Health Care", city: "Elkhorn", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI114",GROUP_DESC:"WI114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8825 ,protection_group_id: -7825, protection_element_id:-7825], primaryKey: false);
      insert('organizations', [ id: 107811, nci_institute_code: "WI115", name: "Aurora BayCare Medical Center", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI115",GROUP_DESC:"WI115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8826 ,protection_group_id: -7826, protection_element_id:-7826], primaryKey: false);
      insert('organizations', [ id: 107812, nci_institute_code: "WI116", name: "Green Bay Oncology at Saint Vincent Hospital", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI116",GROUP_DESC:"WI116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8827 ,protection_group_id: -7827, protection_element_id:-7827], primaryKey: false);
      insert('organizations', [ id: 107813, nci_institute_code: "WI117", name: "Green Bay Oncology - Oconto Falls", city: "Oconto Falls", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI117",GROUP_DESC:"WI117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8828 ,protection_group_id: -7828, protection_element_id:-7828], primaryKey: false);
      insert('organizations', [ id: 107814, nci_institute_code: "WI118", name: "Green Bay Oncology - Sturgeon Bay", city: "Sturgeon Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI118",GROUP_DESC:"WI118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8829 ,protection_group_id: -7829, protection_element_id:-7829], primaryKey: false);
      insert('organizations', [ id: 107815, nci_institute_code: "WI119", name: "Green Bay Oncology at Saint Mary's Hospital", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI119",GROUP_DESC:"WI119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8830 ,protection_group_id: -7830, protection_element_id:-7830], primaryKey: false);
      insert('organizations', [ id: 107816, nci_institute_code: "WI121", name: "Oncology Alliance - Kenosha South", city: "Kenosha", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI121",GROUP_DESC:"WI121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8831 ,protection_group_id: -7831, protection_element_id:-7831], primaryKey: false);
      insert('organizations', [ id: 107817, nci_institute_code: "WI122", name: "Milwaukee General and Vascular Surgery", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI122",GROUP_DESC:"WI122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8832 ,protection_group_id: -7832, protection_element_id:-7832], primaryKey: false);
      insert('organizations', [ id: 107818, nci_institute_code: "WI123", name: "ThedaCare Physicians", city: "Shawano", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI123",GROUP_DESC:"WI123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8833 ,protection_group_id: -7833, protection_element_id:-7833], primaryKey: false);
      insert('organizations', [ id: 107819, nci_institute_code: "WI124", name: "Fox Valley Surgical Associates Ltd", city: "Appleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI124",GROUP_DESC:"WI124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8834 ,protection_group_id: -7834, protection_element_id:-7834], primaryKey: false);
      insert('organizations', [ id: 107820, nci_institute_code: "WI125", name: "Appleton Medical Center", city: "Appleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI125",GROUP_DESC:"WI125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8835 ,protection_group_id: -7835, protection_element_id:-7835], primaryKey: false);
      insert('organizations', [ id: 107821, nci_institute_code: "WI127", name: "Southeast Surgical, S.C.", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI127",GROUP_DESC:"WI127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8836 ,protection_group_id: -7836, protection_element_id:-7836], primaryKey: false);
      insert('organizations', [ id: 107822, nci_institute_code: "WI128", name: "Marshfield Clinic at James Beck Cancer Center", city: "Rhinelander", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI128",GROUP_DESC:"WI128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8837 ,protection_group_id: -7837, protection_element_id:-7837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 107823, nci_institute_code: "WI129", name: "Marshfield Clinic - Wisconsin Rapids Center", city: "Wisconsin Rapids", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI129",GROUP_DESC:"WI129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8838 ,protection_group_id: -7838, protection_element_id:-7838], primaryKey: false);
      insert('organizations', [ id: 107824, nci_institute_code: "WI130", name: "Advanced Healthcare, S.C.-East Mequon Clinic", city: "Mequon", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI130",GROUP_DESC:"WI130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8839 ,protection_group_id: -7839, protection_element_id:-7839], primaryKey: false);
      insert('organizations', [ id: 107825, nci_institute_code: "WI131", name: "Surgical Associates of Neenah SC", city: "Neenah", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI131",GROUP_DESC:"WI131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8840 ,protection_group_id: -7840, protection_element_id:-7840], primaryKey: false);
      insert('organizations', [ id: 107826, nci_institute_code: "WI132", name: "Marshfield Clinic - Ladysmith Center", city: "Ladysmith", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI132",GROUP_DESC:"WI132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8841 ,protection_group_id: -7841, protection_element_id:-7841], primaryKey: false);
      insert('organizations', [ id: 107827, nci_institute_code: "WI133", name: "Marshfield Clinic - Merrill Center", city: "Merrill", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI133",GROUP_DESC:"WI133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8842 ,protection_group_id: -7842, protection_element_id:-7842], primaryKey: false);
      insert('organizations', [ id: 107828, nci_institute_code: "WI134", name: "Marshfield Clinic Cancer Care at Regional Cancer Center", city: "Eau Claire", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI134",GROUP_DESC:"WI134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8843 ,protection_group_id: -7843, protection_element_id:-7843], primaryKey: false);
      insert('organizations', [ id: 107829, nci_institute_code: "WI135", name: "Oncology Alliance - Milwaukee West", city: "Wauwautosa", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI135",GROUP_DESC:"WI135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8844 ,protection_group_id: -7844, protection_element_id:-7844], primaryKey: false);
      insert('organizations', [ id: 107830, nci_institute_code: "WI136", name: "Vince Lombardi Cancer Clinic", city: "Two Rivers", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI136",GROUP_DESC:"WI136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8845 ,protection_group_id: -7845, protection_element_id:-7845], primaryKey: false);
      insert('organizations', [ id: 107831, nci_institute_code: "WI137", name: "Reiman Center for Cancer Care", city: "Franklin", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI137",GROUP_DESC:"WI137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8846 ,protection_group_id: -7846, protection_element_id:-7846], primaryKey: false);
      insert('organizations', [ id: 107832, nci_institute_code: "WI138", name: "Milwaukee Oncology Consultants Inc", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI138",GROUP_DESC:"WI138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8847 ,protection_group_id: -7847, protection_element_id:-7847], primaryKey: false);
      insert('organizations', [ id: 107833, nci_institute_code: "WI139", name: "Aurora Medical Group Northshore", city: "Fox Point", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI139",GROUP_DESC:"WI139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8848 ,protection_group_id: -7848, protection_element_id:-7848], primaryKey: false);
      insert('organizations', [ id: 107834, nci_institute_code: "WI140", name: "Columbia Saint Mary's Hospital - Ozaukee", city: "Mequon", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI140",GROUP_DESC:"WI140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8849 ,protection_group_id: -7849, protection_element_id:-7849], primaryKey: false);
      insert('organizations', [ id: 107835, nci_institute_code: "WI141", name: "Diagnostic & Treatment Center", city: "Weston", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI141",GROUP_DESC:"WI141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8850 ,protection_group_id: -7850, protection_element_id:-7850], primaryKey: false);
      insert('organizations', [ id: 107836, nci_institute_code: "WI142", name: "University of Wisconsin Cancer Center Riverview", city: "Wisconsin Rapids", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI142",GROUP_DESC:"WI142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8851 ,protection_group_id: -7851, protection_element_id:-7851], primaryKey: false);
      insert('organizations', [ id: 107837, nci_institute_code: "WI143", name: "D N Greenwald Center", city: "Mukwonago", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI143",GROUP_DESC:"WI143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8852 ,protection_group_id: -7852, protection_element_id:-7852], primaryKey: false);
      insert('organizations', [ id: 107838, nci_institute_code: "WI144", name: "Marshfield Clinic - Weston Center", city: "Weston", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI144",GROUP_DESC:"WI144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8853 ,protection_group_id: -7853, protection_element_id:-7853], primaryKey: false);
      insert('organizations', [ id: 107839, nci_institute_code: "WI145", name: "Elmbrook Memorial Hospital", city: "Brookfield", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI145",GROUP_DESC:"WI145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8854 ,protection_group_id: -7854, protection_element_id:-7854], primaryKey: false);
      insert('organizations', [ id: 107840, nci_institute_code: "WI146", name: "Langlade Memorial Hospital", city: "Antigo", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI146",GROUP_DESC:"WI146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8855 ,protection_group_id: -7855, protection_element_id:-7855], primaryKey: false);
      insert('organizations', [ id: 107841, nci_institute_code: "WI147", name: "Brookfield Surgical Associates", city: "Brookfield", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI147",GROUP_DESC:"WI147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8856 ,protection_group_id: -7856, protection_element_id:-7856], primaryKey: false);
      insert('organizations', [ id: 107842, nci_institute_code: "WI148", name: "Wheaton Franciscan Healthcare", city: "Glendale", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI148",GROUP_DESC:"WI148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8857 ,protection_group_id: -7857, protection_element_id:-7857], primaryKey: false);
      insert('organizations', [ id: 107843, nci_institute_code: "WI149", name: "United Hospital System - Saint Catherine's Medical Center Campus", city: "Pleasant Praire", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI149",GROUP_DESC:"WI149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8858 ,protection_group_id: -7858, protection_element_id:-7858], primaryKey: false);
      insert('organizations', [ id: 107844, nci_institute_code: "WI150", name: "Integrated Breast Specialists SC", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI150",GROUP_DESC:"WI150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8859 ,protection_group_id: -7859, protection_element_id:-7859], primaryKey: false);
      insert('organizations', [ id: 107845, nci_institute_code: "WI151", name: "UW Cancer Center Johnson Creek", city: "Johnson Creek", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI151",GROUP_DESC:"WI151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8860 ,protection_group_id: -7860, protection_element_id:-7860], primaryKey: false);
      insert('organizations', [ id: 107846, nci_institute_code: "WI152", name: "Prospect Medical Commons", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI152",GROUP_DESC:"WI152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8861 ,protection_group_id: -7861, protection_element_id:-7861], primaryKey: false);
      insert('organizations', [ id: 107847, nci_institute_code: "WI153", name: "Door County Cancer Center", city: "Sturgeon Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI153",GROUP_DESC:"WI153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8862 ,protection_group_id: -7862, protection_element_id:-7862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 107848, nci_institute_code: "WI154", name: "Turville Bay MRI and Radiation Oncology Center", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI154",GROUP_DESC:"WI154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8863 ,protection_group_id: -7863, protection_element_id:-7863], primaryKey: false);
      insert('organizations', [ id: 107849, nci_institute_code: "WI155", name: "Thoracic Surgery Associates Limited", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI155",GROUP_DESC:"WI155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8864 ,protection_group_id: -7864, protection_element_id:-7864], primaryKey: false);
      insert('organizations', [ id: 107850, nci_institute_code: "WI156", name: "Vince Lombardi Cancer Clinic - Oshkosh", city: "Oshkosh", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI156",GROUP_DESC:"WI156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8865 ,protection_group_id: -7865, protection_element_id:-7865], primaryKey: false);
      insert('organizations', [ id: 107851, nci_institute_code: "WI158", name: "Vince Lombardi Cancer Clinic - Slinger", city: "Slinger", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI158",GROUP_DESC:"WI158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8866 ,protection_group_id: -7866, protection_element_id:-7866], primaryKey: false);
      insert('organizations', [ id: 107852, nci_institute_code: "WI159", name: "Greater Milwaukee Otolaryngology LLC", city: "Greenfield", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI159",GROUP_DESC:"WI159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8867 ,protection_group_id: -7867, protection_element_id:-7867], primaryKey: false);
      insert('organizations', [ id: 107853, nci_institute_code: "WI160", name: "The Alyce and Elmore Kraemer Cancer Care Center", city: "West Bend", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI160",GROUP_DESC:"WI160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8868 ,protection_group_id: -7868, protection_element_id:-7868], primaryKey: false);
      insert('organizations', [ id: 107854, nci_institute_code: "WI161", name: "Columbia Saint Mary's Water Tower Medical Commons", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI161",GROUP_DESC:"WI161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8869 ,protection_group_id: -7869, protection_element_id:-7869], primaryKey: false);
      insert('organizations', [ id: 107855, nci_institute_code: "WI162", name: "Aurora Gynecologic Oncology", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI162",GROUP_DESC:"WI162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8870 ,protection_group_id: -7870, protection_element_id:-7870], primaryKey: false);
      insert('organizations', [ id: 107856, nci_institute_code: "WI163", name: "Saint Clare's Hospital", city: "Weston", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI163",GROUP_DESC:"WI163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8871 ,protection_group_id: -7871, protection_element_id:-7871], primaryKey: false);
      insert('organizations', [ id: 107857, nci_institute_code: "WV001", name: "Stevens Hospital", city: "Welch", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV001",GROUP_DESC:"WV001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8872 ,protection_group_id: -7872, protection_element_id:-7872], primaryKey: false);
      insert('organizations', [ id: 107858, nci_institute_code: "WV002", name: "Montgomery General Hospital", city: "Montgomery", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV002",GROUP_DESC:"WV002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8873 ,protection_group_id: -7873, protection_element_id:-7873], primaryKey: false);
      insert('organizations', [ id: 107859, nci_institute_code: "WV003", name: "Kanawha Valley Memorial Hospital", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV003",GROUP_DESC:"WV003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8874 ,protection_group_id: -7874, protection_element_id:-7874], primaryKey: false);
      insert('organizations', [ id: 107860, nci_institute_code: "WV004", name: "West Virginia University Charleston", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV004",GROUP_DESC:"WV004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8875 ,protection_group_id: -7875, protection_element_id:-7875], primaryKey: false);
      insert('organizations', [ id: 107861, nci_institute_code: "WV005", name: "Charleston Area Medical Center", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV005",GROUP_DESC:"WV005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8876 ,protection_group_id: -7876, protection_element_id:-7876], primaryKey: false);
      insert('organizations', [ id: 107862, nci_institute_code: "WV006", name: "City Hospital", city: "Martinsburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV006",GROUP_DESC:"WV006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8877 ,protection_group_id: -7877, protection_element_id:-7877], primaryKey: false);
      insert('organizations', [ id: 107863, nci_institute_code: "WV007", name: "Veterans Administration Hospital", city: "Martinsburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV007",GROUP_DESC:"WV007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8878 ,protection_group_id: -7878, protection_element_id:-7878], primaryKey: false);
      insert('organizations', [ id: 107864, nci_institute_code: "WV008", name: "Cabell-Huntington Hospital", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV008",GROUP_DESC:"WV008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8879 ,protection_group_id: -7879, protection_element_id:-7879], primaryKey: false);
      insert('organizations', [ id: 107865, nci_institute_code: "WV009", name: "Marshall University Medical Center", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV009",GROUP_DESC:"WV009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8880 ,protection_group_id: -7880, protection_element_id:-7880], primaryKey: false);
      insert('organizations', [ id: 107866, nci_institute_code: "WV010", name: "Saint Mary's Medical Center", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV010",GROUP_DESC:"WV010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8881 ,protection_group_id: -7881, protection_element_id:-7881], primaryKey: false);
      insert('organizations', [ id: 107867, nci_institute_code: "WV012", name: "Veterans Administration Medical Center", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV012",GROUP_DESC:"WV012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8882 ,protection_group_id: -7882, protection_element_id:-7882], primaryKey: false);
      insert('organizations', [ id: 107868, nci_institute_code: "WV013", name: "Wheeling Hospital", city: "Wheeling", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV013",GROUP_DESC:"WV013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8883 ,protection_group_id: -7883, protection_element_id:-7883], primaryKey: false);
      insert('organizations', [ id: 107869, nci_institute_code: "WV014", name: "Ohio Valley Medical Center", city: "Wheeling", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV014",GROUP_DESC:"WV014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8884 ,protection_group_id: -7884, protection_element_id:-7884], primaryKey: false);
      insert('organizations', [ id: 107870, nci_institute_code: "WV015", name: "Saint Joseph's Hospital", city: "Parkersburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV015",GROUP_DESC:"WV015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8885 ,protection_group_id: -7885, protection_element_id:-7885], primaryKey: false);
      insert('organizations', [ id: 107871, nci_institute_code: "WV016", name: "Davis Memorial Hospital", city: "Elkins", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV016",GROUP_DESC:"WV016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8886 ,protection_group_id: -7886, protection_element_id:-7886], primaryKey: false);
      insert('organizations', [ id: 107872, nci_institute_code: "WV017", name: "United Hospital Center", city: "Clarksburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV017",GROUP_DESC:"WV017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8887 ,protection_group_id: -7887, protection_element_id:-7887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 107873, nci_institute_code: "WV019", name: "Raleigh General Hospital", city: "Beckley", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV019",GROUP_DESC:"WV019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8888 ,protection_group_id: -7888, protection_element_id:-7888], primaryKey: false);
      insert('organizations', [ id: 107874, nci_institute_code: "WV020", name: "Monongalia Hospital", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV020",GROUP_DESC:"WV020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8889 ,protection_group_id: -7889, protection_element_id:-7889], primaryKey: false);
      insert('organizations', [ id: 107875, nci_institute_code: "WV021", name: "West Virginia University Hospital/Robert C Byrd Health and Science Center", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV021",GROUP_DESC:"WV021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8890 ,protection_group_id: -7890, protection_element_id:-7890], primaryKey: false);
      insert('organizations', [ id: 107876, nci_institute_code: "WV022", name: "Princeton Community Hospital", city: "Princeton", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV022",GROUP_DESC:"WV022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8891 ,protection_group_id: -7891, protection_element_id:-7891], primaryKey: false);
      insert('organizations', [ id: 107877, nci_institute_code: "WV023", name: "Camden-Clark Memorial Hospital", city: "Parkersburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV023",GROUP_DESC:"WV023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8892 ,protection_group_id: -7892, protection_element_id:-7892], primaryKey: false);
      insert('organizations', [ id: 107878, nci_institute_code: "WV024", name: "Thomas Memorial Hospital", city: "South Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV024",GROUP_DESC:"WV024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8893 ,protection_group_id: -7893, protection_element_id:-7893], primaryKey: false);
      insert('organizations', [ id: 107879, nci_institute_code: "WV025", name: "West Virginia University", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV025",GROUP_DESC:"WV025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8894 ,protection_group_id: -7894, protection_element_id:-7894], primaryKey: false);
      insert('organizations', [ id: 107880, nci_institute_code: "WV026", name: "Ruby Memorial Hospital", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV026",GROUP_DESC:"WV026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8895 ,protection_group_id: -7895, protection_element_id:-7895], primaryKey: false);
      insert('organizations', [ id: 107881, nci_institute_code: "WV027", name: "Louis A Johnson Veterans Affairs Medical Center", city: "Clarksburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV027",GROUP_DESC:"WV027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8896 ,protection_group_id: -7896, protection_element_id:-7896], primaryKey: false);
      insert('organizations', [ id: 107882, nci_institute_code: "WV028", name: "Logan General Hospital", city: "Logan", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV028",GROUP_DESC:"WV028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8897 ,protection_group_id: -7897, protection_element_id:-7897], primaryKey: false);
      insert('organizations', [ id: 107883, nci_institute_code: "WV029", name: "Bluefield Regional Medical Center", city: "Bluefield", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV029",GROUP_DESC:"WV029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8898 ,protection_group_id: -7898, protection_element_id:-7898], primaryKey: false);
      insert('organizations', [ id: 107884, nci_institute_code: "WV030", name: "Robert C. Byrd Health Sciences Center", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV030",GROUP_DESC:"WV030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8899 ,protection_group_id: -7899, protection_element_id:-7899], primaryKey: false);
      insert('organizations', [ id: 107885, nci_institute_code: "WV031", name: "Charleston Radiation Therapy Consultants LLC", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV031",GROUP_DESC:"WV031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8900 ,protection_group_id: -7900, protection_element_id:-7900], primaryKey: false);
      insert('organizations', [ id: 107886, nci_institute_code: "WV032", name: "Morgantown Internal Medicine Group Inc", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV032",GROUP_DESC:"WV032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8901 ,protection_group_id: -7901, protection_element_id:-7901], primaryKey: false);
      insert('organizations', [ id: 107887, nci_institute_code: "WV034", name: "Womens and Childrens Hospital", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV034",GROUP_DESC:"WV034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8902 ,protection_group_id: -7902, protection_element_id:-7902], primaryKey: false);
      insert('organizations', [ id: 107888, nci_institute_code: "WV035", name: "Plateau Medical Center", city: "Oak Hill", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV035",GROUP_DESC:"WV035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8903 ,protection_group_id: -7903, protection_element_id:-7903], primaryKey: false);
      insert('organizations', [ id: 107889, nci_institute_code: "WV036", name: "Beckley Appalachian Regional Hospital", city: "Beckley", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV036",GROUP_DESC:"WV036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8904 ,protection_group_id: -7904, protection_element_id:-7904], primaryKey: false);
      insert('organizations', [ id: 107890, nci_institute_code: "WV037", name: "Wheeling Valley", city: "Wheeling", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV037",GROUP_DESC:"WV037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8905 ,protection_group_id: -7905, protection_element_id:-7905], primaryKey: false);
      insert('organizations', [ id: 107891, nci_institute_code: "WV038", name: "Princeton Hematology/Oncology", city: "Princeton", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV038",GROUP_DESC:"WV038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8906 ,protection_group_id: -7906, protection_element_id:-7906], primaryKey: false);
      insert('organizations', [ id: 107892, nci_institute_code: "WV039", name: "The Center for Cancer Care", city: "Princeton", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV039",GROUP_DESC:"WV039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8907 ,protection_group_id: -7907, protection_element_id:-7907], primaryKey: false);
      insert('organizations', [ id: 107893, nci_institute_code: "WV040", name: "Weirton Medical Center", city: "Weirton", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV040",GROUP_DESC:"WV040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8908 ,protection_group_id: -7908, protection_element_id:-7908], primaryKey: false);
      insert('organizations', [ id: 107894, nci_institute_code: "WV041", name: "Putnam General Hospital", city: "Hurricane", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV041",GROUP_DESC:"WV041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8909 ,protection_group_id: -7909, protection_element_id:-7909], primaryKey: false);
      insert('organizations', [ id: 107895, nci_institute_code: "WV042", name: "Blanchette Rockefeller Neurosciences Institute", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV042",GROUP_DESC:"WV042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8910 ,protection_group_id: -7910, protection_element_id:-7910], primaryKey: false);
      insert('organizations', [ id: 107896, nci_institute_code: "WV043", name: "Charleston Area Medical Center Health Education & Research Institute", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV043",GROUP_DESC:"WV043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8911 ,protection_group_id: -7911, protection_element_id:-7911], primaryKey: false);
      insert('organizations', [ id: 107897, nci_institute_code: "WV044", name: "Huntington Internal Medicine Group Inc", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV044",GROUP_DESC:"WV044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8912 ,protection_group_id: -7912, protection_element_id:-7912], primaryKey: false);
    }

    void m36() {
        // all36 (19 terms)
      insert('organizations', [ id: 107898, nci_institute_code: "WV045", name: "David Lee Outpatient Cancer Center", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV045",GROUP_DESC:"WV045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8913 ,protection_group_id: -7913, protection_element_id:-7913], primaryKey: false);
      insert('organizations', [ id: 107899, nci_institute_code: "WV046", name: "Edwards Comprehensive Cancer Center", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV046",GROUP_DESC:"WV046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8914 ,protection_group_id: -7914, protection_element_id:-7914], primaryKey: false);
      insert('organizations', [ id: 107900, nci_institute_code: "WV047", name: "Roger K Pons MD Inc", city: "Nutter Fort", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV047",GROUP_DESC:"WV047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8915 ,protection_group_id: -7915, protection_element_id:-7915], primaryKey: false);
      insert('organizations', [ id: 107901, nci_institute_code: "WV048", name: "Gateway Hematology Oncology", city: "Martinsburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV048",GROUP_DESC:"WV048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8916 ,protection_group_id: -7916, protection_element_id:-7916], primaryKey: false);
      insert('organizations', [ id: 107902, nci_institute_code: "WV049", name: "Primary Oncology Network PLLC", city: "Clarksburg", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV049",GROUP_DESC:"WV049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8917 ,protection_group_id: -7917, protection_element_id:-7917], primaryKey: false);
      insert('organizations', [ id: 107903, nci_institute_code: "WY001", name: "Veterans Administration Hospital", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY001",GROUP_DESC:"WY001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8918 ,protection_group_id: -7918, protection_element_id:-7918], primaryKey: false);
      insert('organizations', [ id: 107904, nci_institute_code: "WY003", name: "Memorial Hospital of Laramie County", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY003",GROUP_DESC:"WY003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8919 ,protection_group_id: -7919, protection_element_id:-7919], primaryKey: false);
      insert('organizations', [ id: 107905, nci_institute_code: "WY004", name: "Welch Cancer Center", city: "Sheridan", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY004",GROUP_DESC:"WY004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8920 ,protection_group_id: -7920, protection_element_id:-7920], primaryKey: false);
      insert('organizations', [ id: 107906, nci_institute_code: "WY005", name: "United Medical Center", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY005",GROUP_DESC:"WY005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8921 ,protection_group_id: -7921, protection_element_id:-7921], primaryKey: false);
      insert('organizations', [ id: 107907, nci_institute_code: "WY006", name: "Intrl Med/Neuro., P.C.", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY006",GROUP_DESC:"WY006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8922 ,protection_group_id: -7922, protection_element_id:-7922], primaryKey: false);
      insert('organizations', [ id: 107908, nci_institute_code: "WY007", name: "West Park Hospital", city: "Cody", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY007",GROUP_DESC:"WY007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8923 ,protection_group_id: -7923, protection_element_id:-7923], primaryKey: false);
      insert('organizations', [ id: 107909, nci_institute_code: "WY008", name: "Internal Medical Group, P.C.", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY008",GROUP_DESC:"WY008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8924 ,protection_group_id: -7924, protection_element_id:-7924], primaryKey: false);
      insert('organizations', [ id: 107910, nci_institute_code: "WY009", name: "Wyoming Medical Center", city: "Casper", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY009",GROUP_DESC:"WY009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8925 ,protection_group_id: -7925, protection_element_id:-7925], primaryKey: false);
      insert('organizations', [ id: 107911, nci_institute_code: "WY010", name: "Ivinson Memorial Hospital", city: "Laramie", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY010",GROUP_DESC:"WY010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8926 ,protection_group_id: -7926, protection_element_id:-7926], primaryKey: false);
      insert('organizations', [ id: 107912, nci_institute_code: "WY011", name: "Cheyenne Hematology/Oncology Services", city: "Cheyenne", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY011",GROUP_DESC:"WY011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8927 ,protection_group_id: -7927, protection_element_id:-7927], primaryKey: false);
      insert('organizations', [ id: 107913, nci_institute_code: "WY012", name: "Big Horn Basin Cancer Center", city: "Cody", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY012",GROUP_DESC:"WY012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8928 ,protection_group_id: -7928, protection_element_id:-7928], primaryKey: false);
      insert('organizations', [ id: 107914, nci_institute_code: "WY013", name: "Hematology -Oncology Centers of the Northern Rockies - Sheridan", city: "Sheridan", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY013",GROUP_DESC:"WY013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8929 ,protection_group_id: -7929, protection_element_id:-7929], primaryKey: false);
      insert('organizations', [ id: 107915, nci_institute_code: "WY014", name: "Hematology-Oncology Centers of the Northern Rockies - Cody", city: "Cody", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY014",GROUP_DESC:"WY014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8930 ,protection_group_id: -7930, protection_element_id:-7930], primaryKey: false);
      insert('organizations', [ id: 107916, nci_institute_code: "WY015", name: "Family Medical Center", city: "Buffalo", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY015",GROUP_DESC:"WY015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8931 ,protection_group_id: -7931, protection_element_id:-7931], primaryKey: false);
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8013 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
