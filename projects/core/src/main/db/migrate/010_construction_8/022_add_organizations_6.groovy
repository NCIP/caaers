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
      insert('organizations', [ id: 104998, nci_institute_code: "NCCR", name: "National Coalition for Cancer Research", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCCR",GROUP_DESC:"NCCR group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCCR",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCCR",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCCR", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6013 ,protection_group_id: -5013, protection_element_id:-5013], primaryKey: false);
      insert('organizations', [ id: 104999, nci_institute_code: "NCI", name: "National Cancer Institute", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCI1",GROUP_DESC:"NCI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCI1",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCI1",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCI1", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6014 ,protection_group_id: -5014, protection_element_id:-5014], primaryKey: false);
      insert('organizations', [ id: 105000, nci_institute_code: "NCIBAL", name: "National Cancer Institute Baltimore Cancer Research Center", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBAL",GROUP_DESC:"NCIBAL group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBAL",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIBAL",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBAL", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6015 ,protection_group_id: -5015, protection_element_id:-5015], primaryKey: false);
      insert('organizations', [ id: 105001, nci_institute_code: "NCIBDM", name: "National Cancer Institute Biostatistics and Data Management", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBDM",GROUP_DESC:"NCIBDM group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBDM",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIBDM",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBDM", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6016 ,protection_group_id: -5016, protection_element_id:-5016], primaryKey: false);
      insert('organizations', [ id: 105002, nci_institute_code: "NCIBEI", name: "National Cancer Institute Biomedical Engineering and Instrum", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBEI",GROUP_DESC:"NCIBEI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBEI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIBEI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBEI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6017 ,protection_group_id: -5017, protection_element_id:-5017], primaryKey: false);
      insert('organizations', [ id: 105003, nci_institute_code: "NCIBTB", name: "National Cancer Institute Biological Therapeutics Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBTB",GROUP_DESC:"NCIBTB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBTB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIBTB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIBTB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6018 ,protection_group_id: -5018, protection_element_id:-5018], primaryKey: false);
      insert('organizations', [ id: 105004, nci_institute_code: "NCICBD", name: "National Cancer Institute Cancer Biology and Diagnosis", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICBD",GROUP_DESC:"NCICBD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCICBD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCICBD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICBD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6019 ,protection_group_id: -5019, protection_element_id:-5019], primaryKey: false);
      insert('organizations', [ id: 105005, nci_institute_code: "NCICOP", name: "National Cancer Institute Clinical Oncology Program", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICOP",GROUP_DESC:"NCICOP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCICOP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCICOP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICOP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6020 ,protection_group_id: -5020, protection_element_id:-5020], primaryKey: false);
      insert('organizations', [ id: 105006, nci_institute_code: "NCICPS", name: "National Cancer Institute Cancer Prevention Study", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICPS",GROUP_DESC:"NCICPS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCICPS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCICPS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCICPS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6021 ,protection_group_id: -5021, protection_element_id:-5021], primaryKey: false);
      insert('organizations', [ id: 105007, nci_institute_code: "NCIDER", name: "National Cancer Institute Dermatology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDER",GROUP_DESC:"NCIDER group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDER",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIDER",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDER", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6022 ,protection_group_id: -5022, protection_element_id:-5022], primaryKey: false);
      insert('organizations', [ id: 105008, nci_institute_code: "NCIDRD", name: "National Cancer Institute Diagnostic Radiology Department", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDRD",GROUP_DESC:"NCIDRD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDRD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIDRD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDRD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6023 ,protection_group_id: -5023, protection_element_id:-5023], primaryKey: false);
      insert('organizations', [ id: 105009, nci_institute_code: "NCIDTP", name: "National Cancer Institute Developmental Therapeutics Program", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDTP",GROUP_DESC:"NCIDTP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDTP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIDTP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIDTP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6024 ,protection_group_id: -5024, protection_element_id:-5024], primaryKey: false);
      insert('organizations', [ id: 105010, nci_institute_code: "NCIEEB", name: "National Cancer Institute Environmental Epidemiology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEEB",GROUP_DESC:"NCIEEB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEEB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIEEB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEEB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6025 ,protection_group_id: -5025, protection_element_id:-5025], primaryKey: false);
      insert('organizations', [ id: 105011, nci_institute_code: "NCIEPI", name: "National Cancer Institute Epidemiology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEPI",GROUP_DESC:"NCIEPI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEPI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIEPI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIEPI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6026 ,protection_group_id: -5026, protection_element_id:-5026], primaryKey: false);
      insert('organizations', [ id: 105012, nci_institute_code: "NCIETI", name: "National Cancer Institute Experimental Transplantation & Immunology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIETI",GROUP_DESC:"NCIETI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIETI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIETI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIETI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6027 ,protection_group_id: -5027, protection_element_id:-5027], primaryKey: false);
      insert('organizations', [ id: 105013, nci_institute_code: "NCIH", name: "National Cancer Institute of Hungary", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIH",GROUP_DESC:"NCIH group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIH",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIH",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIH", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6028 ,protection_group_id: -5028, protection_element_id:-5028], primaryKey: false);
      insert('organizations', [ id: 105014, nci_institute_code: "NCIIB", name: "National Cancer Institute Immunology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIB",GROUP_DESC:"NCIIB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIIB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6029 ,protection_group_id: -5029, protection_element_id:-5029], primaryKey: false);
      insert('organizations', [ id: 105015, nci_institute_code: "NCIIRP", name: "National Cancer Institute Intramural Research Program", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIRP",GROUP_DESC:"NCIIRP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIRP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIIRP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIIRP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6030 ,protection_group_id: -5030, protection_element_id:-5030], primaryKey: false);
      insert('organizations', [ id: 105016, nci_institute_code: "NCILAB", name: "NCI Lab of Immune Cell Biology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILAB",GROUP_DESC:"NCILAB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCILAB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCILAB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILAB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6031 ,protection_group_id: -5031, protection_element_id:-5031], primaryKey: false);
      insert('organizations', [ id: 105017, nci_institute_code: "NCILMB", name: "National Cancer Institute Lab of Molecular Biology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILMB",GROUP_DESC:"NCILMB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCILMB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCILMB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILMB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6032 ,protection_group_id: -5032, protection_element_id:-5032], primaryKey: false);
      insert('organizations', [ id: 105018, nci_institute_code: "NCILOI", name: "National Cancer Institute Lab of Immunobiology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOI",GROUP_DESC:"NCILOI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCILOI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6033 ,protection_group_id: -5033, protection_element_id:-5033], primaryKey: false);
      insert('organizations', [ id: 105019, nci_institute_code: "NCILOP", name: "National Cancer Institute Lab of Pathology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOP",GROUP_DESC:"NCILOP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCILOP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILOP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6034 ,protection_group_id: -5034, protection_element_id:-5034], primaryKey: false);
      insert('organizations', [ id: 105020, nci_institute_code: "NCILTB", name: "NCI Lab of Tumor Immunology and Biology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILTB",GROUP_DESC:"NCILTB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCILTB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCILTB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCILTB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6035 ,protection_group_id: -5035, protection_element_id:-5035], primaryKey: false);
      insert('organizations', [ id: 105021, nci_institute_code: "NCIMB", name: "National Cancer Institute Medicine Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMB",GROUP_DESC:"NCIMB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIMB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6036 ,protection_group_id: -5036, protection_element_id:-5036], primaryKey: false);
      insert('organizations', [ id: 105022, nci_institute_code: "NCIMET", name: "National Cancer Institute Metabolism Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMET",GROUP_DESC:"NCIMET group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMET",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIMET",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMET", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6037 ,protection_group_id: -5037, protection_element_id:-5037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 105023, nci_institute_code: "NCIMOC", name: "National Cancer Institute Center for Cancer Research Medical Oncology Clinical Research Unit (MOCRU)", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMOC",GROUP_DESC:"NCIMOC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMOC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIMOC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMOC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6038 ,protection_group_id: -5038, protection_element_id:-5038], primaryKey: false);
      insert('organizations', [ id: 105024, nci_institute_code: "NCINAV", name: "National Cancer Institute Navy Medical Oncology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINAV",GROUP_DESC:"NCINAV group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCINAV",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCINAV",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINAV", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6039 ,protection_group_id: -5039, protection_element_id:-5039], primaryKey: false);
      insert('organizations', [ id: 105025, nci_institute_code: "NCINMD", name: "National Cancer Institute Nuclear Medicine Department", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINMD",GROUP_DESC:"NCINMD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCINMD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCINMD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINMD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6040 ,protection_group_id: -5040, protection_element_id:-5040], primaryKey: false);
      insert('organizations', [ id: 105026, nci_institute_code: "NCINOB", name: "National Cancer Institute Neuro-Oncology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINOB",GROUP_DESC:"NCINOB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCINOB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCINOB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCINOB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6041 ,protection_group_id: -5041, protection_element_id:-5041], primaryKey: false);
      insert('organizations', [ id: 105027, nci_institute_code: "NCIPHA", name: "National Cancer Institute Pharmacology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPHA",GROUP_DESC:"NCIPHA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPHA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIPHA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPHA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6042 ,protection_group_id: -5042, protection_element_id:-5042], primaryKey: false);
      insert('organizations', [ id: 105028, nci_institute_code: "NCIPOB", name: "National Cancer Institute Pediatric Oncology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPOB",GROUP_DESC:"NCIPOB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPOB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIPOB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIPOB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6043 ,protection_group_id: -5043, protection_element_id:-5043], primaryKey: false);
      insert('organizations', [ id: 105029, nci_institute_code: "NCIROB", name: "National Cancer Institute Radiation Oncology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIROB",GROUP_DESC:"NCIROB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIROB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIROB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIROB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6044 ,protection_group_id: -5044, protection_element_id:-5044], primaryKey: false);
      insert('organizations', [ id: 105030, nci_institute_code: "NCISB", name: "National Cancer Institute Surgery Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCISB",GROUP_DESC:"NCISB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCISB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCISB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCISB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6045 ,protection_group_id: -5045, protection_element_id:-5045], primaryKey: false);
      insert('organizations', [ id: 105031, nci_institute_code: "NCIUOB", name: "National Cancer Institute Urologic Oncology Branch", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIUOB",GROUP_DESC:"NCIUOB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIUOB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIUOB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIUOB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6046 ,protection_group_id: -5046, protection_element_id:-5046], primaryKey: false);
      insert('organizations', [ id: 105032, nci_institute_code: "NCIVA", name: "National Cancer Institute Veterans Administration", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIVA",GROUP_DESC:"NCIVA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIVA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIVA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIVA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6047 ,protection_group_id: -5047, protection_element_id:-5047], primaryKey: false);
      insert('organizations', [ id: 105033, nci_institute_code: "NCNSC", name: "National Central Nervous System Consortium", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCNSC",GROUP_DESC:"NCNSC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCNSC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCNSC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCNSC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6048 ,protection_group_id: -5048, protection_element_id:-5048], primaryKey: false);
      insert('organizations', [ id: 105034, nci_institute_code: "NCOG", name: "Northern California Oncology Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCOG",GROUP_DESC:"NCOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6049 ,protection_group_id: -5049, protection_element_id:-5049], primaryKey: false);
      insert('organizations', [ id: 105035, nci_institute_code: "NCTRF", name: "Newfoundland Cancer Treatment and Research Foundation", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCTRF",GROUP_DESC:"NCTRF group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCTRF",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCTRF",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCTRF", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6050 ,protection_group_id: -5050, protection_element_id:-5050], primaryKey: false);
      insert('organizations', [ id: 105036, nci_institute_code: "ND001", name: "Veterans Administration Medical Center", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND001",GROUP_DESC:"ND001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6051 ,protection_group_id: -5051, protection_element_id:-5051], primaryKey: false);
      insert('organizations', [ id: 105037, nci_institute_code: "ND003", name: "Meritcare Hospital", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND003",GROUP_DESC:"ND003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6052 ,protection_group_id: -5052, protection_element_id:-5052], primaryKey: false);
      insert('organizations', [ id: 105038, nci_institute_code: "ND004", name: "United Hospital", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND004",GROUP_DESC:"ND004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6053 ,protection_group_id: -5053, protection_element_id:-5053], primaryKey: false);
      insert('organizations', [ id: 105039, nci_institute_code: "ND005", name: "Roger Maris Cancer Center", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND005",GROUP_DESC:"ND005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6054 ,protection_group_id: -5054, protection_element_id:-5054], primaryKey: false);
      insert('organizations', [ id: 105040, nci_institute_code: "ND006", name: "Quain and Ramstad Clinic", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND006",GROUP_DESC:"ND006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6055 ,protection_group_id: -5055, protection_element_id:-5055], primaryKey: false);
      insert('organizations', [ id: 105041, nci_institute_code: "ND007", name: "Medcenter One Health Systems", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND007",GROUP_DESC:"ND007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6056 ,protection_group_id: -5056, protection_element_id:-5056], primaryKey: false);
      insert('organizations', [ id: 105042, nci_institute_code: "ND008", name: "Unimed Medical Center", city: "Minot", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND008",GROUP_DESC:"ND008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6057 ,protection_group_id: -5057, protection_element_id:-5057], primaryKey: false);
      insert('organizations', [ id: 105043, nci_institute_code: "ND009", name: "Dakota Clinic - South University", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND009",GROUP_DESC:"ND009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6058 ,protection_group_id: -5058, protection_element_id:-5058], primaryKey: false);
      insert('organizations', [ id: 105044, nci_institute_code: "ND010", name: "Fargo Clinic", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND010",GROUP_DESC:"ND010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6059 ,protection_group_id: -5059, protection_element_id:-5059], primaryKey: false);
      insert('organizations', [ id: 105045, nci_institute_code: "ND011", name: "Mid Dakota Clinic", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND011",GROUP_DESC:"ND011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6060 ,protection_group_id: -5060, protection_element_id:-5060], primaryKey: false);
      insert('organizations', [ id: 105046, nci_institute_code: "ND012", name: "Saint Alexius Medical Center", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND012",GROUP_DESC:"ND012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6061 ,protection_group_id: -5061, protection_element_id:-5061], primaryKey: false);
      insert('organizations', [ id: 105047, nci_institute_code: "ND013", name: "Altru Clinic", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND013",GROUP_DESC:"ND013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6062 ,protection_group_id: -5062, protection_element_id:-5062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 105048, nci_institute_code: "ND018", name: "Mercy Hospital/Valley County Clinic", city: "Valley City", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND018",GROUP_DESC:"ND018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6063 ,protection_group_id: -5063, protection_element_id:-5063], primaryKey: false);
      insert('organizations', [ id: 105049, nci_institute_code: "ND021", name: "Medical Arts Clinic, P.C.", city: "Minot", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND021",GROUP_DESC:"ND021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6064 ,protection_group_id: -5064, protection_element_id:-5064], primaryKey: false);
      insert('organizations', [ id: 105050, nci_institute_code: "ND022", name: "Mercy Medical Center", city: "Williston", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND022",GROUP_DESC:"ND022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6065 ,protection_group_id: -5065, protection_element_id:-5065], primaryKey: false);
      insert('organizations', [ id: 105051, nci_institute_code: "ND023", name: "Merit Care Center Wahpeton", city: "Wahpeton", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND023",GROUP_DESC:"ND023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6066 ,protection_group_id: -5066, protection_element_id:-5066], primaryKey: false);
      insert('organizations', [ id: 105052, nci_institute_code: "ND025", name: "Altru Hospital", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND025",GROUP_DESC:"ND025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6067 ,protection_group_id: -5067, protection_element_id:-5067], primaryKey: false);
      insert('organizations', [ id: 105053, nci_institute_code: "ND026", name: "Trinity Cancer Care Center", city: "Minot", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND026",GROUP_DESC:"ND026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6068 ,protection_group_id: -5068, protection_element_id:-5068], primaryKey: false);
      insert('organizations', [ id: 105054, nci_institute_code: "ND028", name: "Altru Cancer Center", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND028",GROUP_DESC:"ND028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6069 ,protection_group_id: -5069, protection_element_id:-5069], primaryKey: false);
      insert('organizations', [ id: 105055, nci_institute_code: "ND029", name: "Bismarck Cancer Center", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND029",GROUP_DESC:"ND029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6070 ,protection_group_id: -5070, protection_element_id:-5070], primaryKey: false);
      insert('organizations', [ id: 105056, nci_institute_code: "ND030", name: "MeritCare Medical Group- Broadway", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND030",GROUP_DESC:"ND030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6071 ,protection_group_id: -5071, protection_element_id:-5071], primaryKey: false);
      insert('organizations', [ id: 105057, nci_institute_code: "ND031", name: "MeritCare Medical Group", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND031",GROUP_DESC:"ND031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6072 ,protection_group_id: -5072, protection_element_id:-5072], primaryKey: false);
      insert('organizations', [ id: 105058, nci_institute_code: "ND032", name: "University of North Dakota", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND032",GROUP_DESC:"ND032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6073 ,protection_group_id: -5073, protection_element_id:-5073], primaryKey: false);
      insert('organizations', [ id: 105059, nci_institute_code: "ND033", name: "FEKA Hematology and Oncology", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND033",GROUP_DESC:"ND033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6074 ,protection_group_id: -5074, protection_element_id:-5074], primaryKey: false);
      insert('organizations', [ id: 105060, nci_institute_code: "ND034", name: "North Dakota State University", city: "Fargo", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND034",GROUP_DESC:"ND034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6075 ,protection_group_id: -5075, protection_element_id:-5075], primaryKey: false);
      insert('organizations', [ id: 105061, nci_institute_code: "ND035", name: "Cancer Center of North Dakota", city: "Grand Forks", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND035",GROUP_DESC:"ND035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6076 ,protection_group_id: -5076, protection_element_id:-5076], primaryKey: false);
      insert('organizations', [ id: 105062, nci_institute_code: "ND036", name: "Odyssey Research Services - Bismarck", city: "Bismarck", state: "ND", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND036",GROUP_DESC:"ND036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ND036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ND036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ND036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6077 ,protection_group_id: -5077, protection_element_id:-5077], primaryKey: false);
      insert('organizations', [ id: 105063, nci_institute_code: "NE001", name: "Bishop Clarkson Memorial Hospital", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE001",GROUP_DESC:"NE001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6078 ,protection_group_id: -5078, protection_element_id:-5078], primaryKey: false);
      insert('organizations', [ id: 105064, nci_institute_code: "NE002", name: "Omaha Veterans Administration Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE002",GROUP_DESC:"NE002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6079 ,protection_group_id: -5079, protection_element_id:-5079], primaryKey: false);
      insert('organizations', [ id: 105065, nci_institute_code: "NE003", name: "University of Nebraska Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE003",GROUP_DESC:"NE003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6080 ,protection_group_id: -5080, protection_element_id:-5080], primaryKey: false);
      insert('organizations', [ id: 105066, nci_institute_code: "NE004", name: "Clarkson Hospital", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE004",GROUP_DESC:"NE004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6081 ,protection_group_id: -5081, protection_element_id:-5081], primaryKey: false);
      insert('organizations', [ id: 105067, nci_institute_code: "NE005", name: "Air Force Regional Hosp Sac", city: "Bellview", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE005",GROUP_DESC:"NE005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6082 ,protection_group_id: -5082, protection_element_id:-5082], primaryKey: false);
      insert('organizations', [ id: 105068, nci_institute_code: "NE006", name: "Childrens Memorial Hospital of Omaha", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE006",GROUP_DESC:"NE006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6083 ,protection_group_id: -5083, protection_element_id:-5083], primaryKey: false);
      insert('organizations', [ id: 105069, nci_institute_code: "NE007", name: "Methodist Cancer Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE007",GROUP_DESC:"NE007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6084 ,protection_group_id: -5084, protection_element_id:-5084], primaryKey: false);
      insert('organizations', [ id: 105070, nci_institute_code: "NE008", name: "Saint Joseph Hospital", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE008",GROUP_DESC:"NE008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6085 ,protection_group_id: -5085, protection_element_id:-5085], primaryKey: false);
      insert('organizations', [ id: 105071, nci_institute_code: "NE009", name: "Creighton University Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE009",GROUP_DESC:"NE009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6086 ,protection_group_id: -5086, protection_element_id:-5086], primaryKey: false);
      insert('organizations', [ id: 105072, nci_institute_code: "NE010", name: "Veterans Administration Medical Center", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE010",GROUP_DESC:"NE010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6087 ,protection_group_id: -5087, protection_element_id:-5087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 105073, nci_institute_code: "NE011", name: "Good Samaritan Health Systems", city: "Kearney", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE011",GROUP_DESC:"NE011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6088 ,protection_group_id: -5088, protection_element_id:-5088], primaryKey: false);
      insert('organizations', [ id: 105074, nci_institute_code: "NE012", name: "West Nebraska General Hospital", city: "Scottsbluff", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE012",GROUP_DESC:"NE012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6089 ,protection_group_id: -5089, protection_element_id:-5089], primaryKey: false);
      insert('organizations', [ id: 105075, nci_institute_code: "NE013", name: "Bryan LGH Medical Center West", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE013",GROUP_DESC:"NE013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6090 ,protection_group_id: -5090, protection_element_id:-5090], primaryKey: false);
      insert('organizations', [ id: 105076, nci_institute_code: "NE014", name: "Bryan LGH Medical Center East", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE014",GROUP_DESC:"NE014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6091 ,protection_group_id: -5091, protection_element_id:-5091], primaryKey: false);
      insert('organizations', [ id: 105077, nci_institute_code: "NE017", name: "Alegent Health Bergan Mercy Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE017",GROUP_DESC:"NE017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6092 ,protection_group_id: -5092, protection_element_id:-5092], primaryKey: false);
      insert('organizations', [ id: 105078, nci_institute_code: "NE018", name: "Mid America Radiation/Oncology Associates., P.C.", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE018",GROUP_DESC:"NE018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6093 ,protection_group_id: -5093, protection_element_id:-5093], primaryKey: false);
      insert('organizations', [ id: 105079, nci_institute_code: "NE019", name: "Platte Valley Medical Group", city: "Kearney", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE019",GROUP_DESC:"NE019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6094 ,protection_group_id: -5094, protection_element_id:-5094], primaryKey: false);
      insert('organizations', [ id: 105080, nci_institute_code: "NE020", name: "Saint Elizabeth Community Health Center", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE020",GROUP_DESC:"NE020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6095 ,protection_group_id: -5095, protection_element_id:-5095], primaryKey: false);
      insert('organizations', [ id: 105081, nci_institute_code: "NE022", name: "Saint Francis Medical Center", city: "Grand Island", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE022",GROUP_DESC:"NE022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6096 ,protection_group_id: -5096, protection_element_id:-5096], primaryKey: false);
      insert('organizations', [ id: 105082, nci_institute_code: "NE023", name: "Fremont Area Medical Center", city: "Fremont", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE023",GROUP_DESC:"NE023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6097 ,protection_group_id: -5097, protection_element_id:-5097], primaryKey: false);
      insert('organizations', [ id: 105083, nci_institute_code: "NE024", name: "Midlands Community Hospital", city: "Papillion", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE024",GROUP_DESC:"NE024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6098 ,protection_group_id: -5098, protection_element_id:-5098], primaryKey: false);
      insert('organizations', [ id: 105084, nci_institute_code: "NE025", name: "Alegent Health Immanuel Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE025",GROUP_DESC:"NE025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6099 ,protection_group_id: -5099, protection_element_id:-5099], primaryKey: false);
      insert('organizations', [ id: 105085, nci_institute_code: "NE026", name: "Missouri Valley Cancer Consortium CCOP", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE026",GROUP_DESC:"NE026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6100 ,protection_group_id: -5100, protection_element_id:-5100], primaryKey: false);
      insert('organizations', [ id: 105086, nci_institute_code: "NE027", name: "Storz Cancer Institute", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE027",GROUP_DESC:"NE027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6101 ,protection_group_id: -5101, protection_element_id:-5101], primaryKey: false);
      insert('organizations', [ id: 105087, nci_institute_code: "NE028", name: "Williamsburg Radiation Oncology Center", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE028",GROUP_DESC:"NE028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6102 ,protection_group_id: -5102, protection_element_id:-5102], primaryKey: false);
      insert('organizations', [ id: 105088, nci_institute_code: "NE029", name: "Mary Lanning Memorial Hospital", city: "Hastings", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE029",GROUP_DESC:"NE029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6103 ,protection_group_id: -5103, protection_element_id:-5103], primaryKey: false);
      insert('organizations', [ id: 105089, nci_institute_code: "NE030", name: "Praire View Clinic", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE030",GROUP_DESC:"NE030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6104 ,protection_group_id: -5104, protection_element_id:-5104], primaryKey: false);
      insert('organizations', [ id: 105090, nci_institute_code: "NE031", name: "Great Plains Regional Medical Center", city: "North Platte", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE031",GROUP_DESC:"NE031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6105 ,protection_group_id: -5105, protection_element_id:-5105], primaryKey: false);
      insert('organizations', [ id: 105091, nci_institute_code: "NE032", name: "Oakland Memorial Hospital Oncology Clinic", city: "Oakland", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE032",GROUP_DESC:"NE032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6106 ,protection_group_id: -5106, protection_element_id:-5106], primaryKey: false);
      insert('organizations', [ id: 105092, nci_institute_code: "NE034", name: "Alegent Health Community Memorial Hospital", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE034",GROUP_DESC:"NE034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6107 ,protection_group_id: -5107, protection_element_id:-5107], primaryKey: false);
      insert('organizations', [ id: 105093, nci_institute_code: "NE035", name: "The Nebraska Medical Center", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE035",GROUP_DESC:"NE035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6108 ,protection_group_id: -5108, protection_element_id:-5108], primaryKey: false);
      insert('organizations', [ id: 105094, nci_institute_code: "NE036", name: "Southeast Nebraska Hematology and Oncology Consultants, P.C", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE036",GROUP_DESC:"NE036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6109 ,protection_group_id: -5109, protection_element_id:-5109], primaryKey: false);
      insert('organizations', [ id: 105095, nci_institute_code: "NE037", name: "Lincoln Medical Education Foundation Cancer Resource Center", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE037",GROUP_DESC:"NE037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6110 ,protection_group_id: -5110, protection_element_id:-5110], primaryKey: false);
      insert('organizations', [ id: 105096, nci_institute_code: "NE039", name: "Regional West Medical Center", city: "Scottsbluff", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE039",GROUP_DESC:"NE039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6111 ,protection_group_id: -5111, protection_element_id:-5111], primaryKey: false);
      insert('organizations', [ id: 105097, nci_institute_code: "NE040", name: "Alegent Health Midlands Community Hospital", city: "Papillion", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE040",GROUP_DESC:"NE040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6112 ,protection_group_id: -5112, protection_element_id:-5112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 105098, nci_institute_code: "NE041", name: "Midwest Urology Associates", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE041",GROUP_DESC:"NE041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6113 ,protection_group_id: -5113, protection_element_id:-5113], primaryKey: false);
      insert('organizations', [ id: 105099, nci_institute_code: "NE042", name: "Urology, PC", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE042",GROUP_DESC:"NE042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6114 ,protection_group_id: -5114, protection_element_id:-5114], primaryKey: false);
      insert('organizations', [ id: 105100, nci_institute_code: "NE043", name: "Missouri Valley Cancer Consortium", city: "Fremont", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE043",GROUP_DESC:"NE043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6115 ,protection_group_id: -5115, protection_element_id:-5115], primaryKey: false);
      insert('organizations', [ id: 105101, nci_institute_code: "NE044", name: "Nebraska Hematology and Oncology", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE044",GROUP_DESC:"NE044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6116 ,protection_group_id: -5116, protection_element_id:-5116], primaryKey: false);
      insert('organizations', [ id: 105102, nci_institute_code: "NE045", name: "Colon and Rectal Surgery, Inc.", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE045",GROUP_DESC:"NE045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6117 ,protection_group_id: -5117, protection_element_id:-5117], primaryKey: false);
      insert('organizations', [ id: 105103, nci_institute_code: "NE046", name: "Consultants in Radiation Oncology, P.C.", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE046",GROUP_DESC:"NE046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6118 ,protection_group_id: -5118, protection_element_id:-5118], primaryKey: false);
      insert('organizations', [ id: 105104, nci_institute_code: "NE047", name: "Oncology/Hematology West", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE047",GROUP_DESC:"NE047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6119 ,protection_group_id: -5119, protection_element_id:-5119], primaryKey: false);
      insert('organizations', [ id: 105105, nci_institute_code: "NE048", name: "Heartland Hematology & Oncology", city: "Kearney", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE048",GROUP_DESC:"NE048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6120 ,protection_group_id: -5120, protection_element_id:-5120], primaryKey: false);
      insert('organizations', [ id: 105106, nci_institute_code: "NE050", name: "Lincoln Radiology Group", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE050",GROUP_DESC:"NE050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6121 ,protection_group_id: -5121, protection_element_id:-5121], primaryKey: false);
      insert('organizations', [ id: 105107, nci_institute_code: "NE051", name: "Fremont Medical Associates", city: "Fremont", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE051",GROUP_DESC:"NE051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6122 ,protection_group_id: -5122, protection_element_id:-5122], primaryKey: false);
      insert('organizations', [ id: 105108, nci_institute_code: "NE052", name: "Internal Medicine Associates PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE052",GROUP_DESC:"NE052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6123 ,protection_group_id: -5123, protection_element_id:-5123], primaryKey: false);
      insert('organizations', [ id: 105109, nci_institute_code: "NE053", name: "Hemotology and Oncology Consultants PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE053",GROUP_DESC:"NE053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6124 ,protection_group_id: -5124, protection_element_id:-5124], primaryKey: false);
      insert('organizations', [ id: 105110, nci_institute_code: "NE054", name: "Bryan LGH Radiation Oncology", city: "Lincoln", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE054",GROUP_DESC:"NE054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6125 ,protection_group_id: -5125, protection_element_id:-5125], primaryKey: false);
      insert('organizations', [ id: 105111, nci_institute_code: "NE055", name: "Alegent Health Lakeside Hospital", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE055",GROUP_DESC:"NE055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6126 ,protection_group_id: -5126, protection_element_id:-5126], primaryKey: false);
      insert('organizations', [ id: 105112, nci_institute_code: "NE056", name: "Oncology Hematology West PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE056",GROUP_DESC:"NE056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6127 ,protection_group_id: -5127, protection_element_id:-5127], primaryKey: false);
      insert('organizations', [ id: 105113, nci_institute_code: "NE057", name: "Surgery Group of Grand Island", city: "Grand Island", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE057",GROUP_DESC:"NE057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6128 ,protection_group_id: -5128, protection_element_id:-5128], primaryKey: false);
      insert('organizations', [ id: 105114, nci_institute_code: "NE058", name: "Medical Oncology and Hematology P C", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE058",GROUP_DESC:"NE058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6129 ,protection_group_id: -5129, protection_element_id:-5129], primaryKey: false);
      insert('organizations', [ id: 105115, nci_institute_code: "NE059", name: "Midwest Hematology Oncology Specialists PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE059",GROUP_DESC:"NE059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6130 ,protection_group_id: -5130, protection_element_id:-5130], primaryKey: false);
      insert('organizations', [ id: 105116, nci_institute_code: "NE060", name: "Oncology Associates PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE060",GROUP_DESC:"NE060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6131 ,protection_group_id: -5131, protection_element_id:-5131], primaryKey: false);
      insert('organizations', [ id: 105117, nci_institute_code: "NE061", name: "Midland Regional Cancer Center", city: "Fremont", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE061",GROUP_DESC:"NE061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6132 ,protection_group_id: -5132, protection_element_id:-5132], primaryKey: false);
      insert('organizations', [ id: 105118, nci_institute_code: "NE062", name: "Oncology Hematology West PC", city: "Omaha", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE062",GROUP_DESC:"NE062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6133 ,protection_group_id: -5133, protection_element_id:-5133], primaryKey: false);
      insert('organizations', [ id: 105119, nci_institute_code: "NE063", name: "Nebraska Cancer Care LLC", city: "Hastings", state: "NE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE063",GROUP_DESC:"NE063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NE063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NE063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NE063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6134 ,protection_group_id: -5134, protection_element_id:-5134], primaryKey: false);
      insert('organizations', [ id: 105120, nci_institute_code: "NEDER", name: "Dutch Childhood Leukemia Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NEDER",GROUP_DESC:"NEDER group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NEDER",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NEDER",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NEDER", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6135 ,protection_group_id: -5135, protection_element_id:-5135], primaryKey: false);
      insert('organizations', [ id: 105121, nci_institute_code: "NH002", name: "Southern New Hampshire Medical Center", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH002",GROUP_DESC:"NH002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6136 ,protection_group_id: -5136, protection_element_id:-5136], primaryKey: false);
      insert('organizations', [ id: 105122, nci_institute_code: "NH003", name: "Catholic Medical Center", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH003",GROUP_DESC:"NH003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6137 ,protection_group_id: -5137, protection_element_id:-5137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 105123, nci_institute_code: "NH004", name: "Elliot Hospital", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH004",GROUP_DESC:"NH004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6138 ,protection_group_id: -5138, protection_element_id:-5138], primaryKey: false);
      insert('organizations', [ id: 105124, nci_institute_code: "NH005", name: "Concord Hospital", city: "Concord", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH005",GROUP_DESC:"NH005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6139 ,protection_group_id: -5139, protection_element_id:-5139], primaryKey: false);
      insert('organizations', [ id: 105125, nci_institute_code: "NH007", name: "Mary Hitchcock Memorial Hospital", city: "Hanover", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH007",GROUP_DESC:"NH007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6140 ,protection_group_id: -5140, protection_element_id:-5140], primaryKey: false);
      insert('organizations', [ id: 105126, nci_institute_code: "NH008", name: "Wentworth-Douglass Hospital", city: "Dover", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH008",GROUP_DESC:"NH008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6141 ,protection_group_id: -5141, protection_element_id:-5141], primaryKey: false);
      insert('organizations', [ id: 105127, nci_institute_code: "NH009", name: "Frisbie Hospital", city: "Rochester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH009",GROUP_DESC:"NH009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6142 ,protection_group_id: -5142, protection_element_id:-5142], primaryKey: false);
      insert('organizations', [ id: 105128, nci_institute_code: "NH010", name: "Department of Veterans Affairs", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH010",GROUP_DESC:"NH010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6143 ,protection_group_id: -5143, protection_element_id:-5143], primaryKey: false);
      insert('organizations', [ id: 105129, nci_institute_code: "NH011", name: "Cheshire Medical Center/Dartmouth-Hitchcock Keene", city: "Keene", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH011",GROUP_DESC:"NH011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6144 ,protection_group_id: -5144, protection_element_id:-5144], primaryKey: false);
      insert('organizations', [ id: 105130, nci_institute_code: "NH012", name: "Dartmouth Hitchcock Medical Center", city: "Lebanon", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH012",GROUP_DESC:"NH012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6145 ,protection_group_id: -5145, protection_element_id:-5145], primaryKey: false);
      insert('organizations', [ id: 105131, nci_institute_code: "NH013", name: "Weeks Memorial Hospital", city: "Lancaster", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH013",GROUP_DESC:"NH013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6146 ,protection_group_id: -5146, protection_element_id:-5146], primaryKey: false);
      insert('organizations', [ id: 105132, nci_institute_code: "NH014", name: "Lahey Hitchcock Clinic-Keene", city: "Keene", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH014",GROUP_DESC:"NH014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6147 ,protection_group_id: -5147, protection_element_id:-5147], primaryKey: false);
      insert('organizations', [ id: 105133, nci_institute_code: "NH015", name: "New Hampshire Oncology Hematology Associates", city: "Hooksett", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH015",GROUP_DESC:"NH015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6148 ,protection_group_id: -5148, protection_element_id:-5148], primaryKey: false);
      insert('organizations', [ id: 105134, nci_institute_code: "NH016", name: "Alice Peck Day Memorial Hospital", city: "Lebanon", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH016",GROUP_DESC:"NH016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6149 ,protection_group_id: -5149, protection_element_id:-5149], primaryKey: false);
      insert('organizations', [ id: 105135, nci_institute_code: "NH017", name: "Androscoggin Valley Hospital", city: "Berlin", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH017",GROUP_DESC:"NH017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6150 ,protection_group_id: -5150, protection_element_id:-5150], primaryKey: false);
      insert('organizations', [ id: 105136, nci_institute_code: "NH018", name: "Upper Connecticut Valley Hospital", city: "Colebrook", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH018",GROUP_DESC:"NH018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6151 ,protection_group_id: -5151, protection_element_id:-5151], primaryKey: false);
      insert('organizations', [ id: 105137, nci_institute_code: "NH019", name: "Cottage Hospital", city: "Woodsville", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH019",GROUP_DESC:"NH019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6152 ,protection_group_id: -5152, protection_element_id:-5152], primaryKey: false);
      insert('organizations', [ id: 105138, nci_institute_code: "NH020", name: "Monadnock Community Hospital", city: "Peterborough", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH020",GROUP_DESC:"NH020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6153 ,protection_group_id: -5153, protection_element_id:-5153], primaryKey: false);
      insert('organizations', [ id: 105139, nci_institute_code: "NH021", name: "Valley Regional", city: "Claremont", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH021",GROUP_DESC:"NH021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6154 ,protection_group_id: -5154, protection_element_id:-5154], primaryKey: false);
      insert('organizations', [ id: 105140, nci_institute_code: "NH022", name: "Huggins Hospital", city: "Wolfeboro", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH022",GROUP_DESC:"NH022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6155 ,protection_group_id: -5155, protection_element_id:-5155], primaryKey: false);
      insert('organizations', [ id: 105141, nci_institute_code: "NH024", name: "Optima Health", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH024",GROUP_DESC:"NH024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6156 ,protection_group_id: -5156, protection_element_id:-5156], primaryKey: false);
      insert('organizations', [ id: 105142, nci_institute_code: "NH025", name: "The Memorial Hospital", city: "North Conway", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH025",GROUP_DESC:"NH025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6157 ,protection_group_id: -5157, protection_element_id:-5157], primaryKey: false);
      insert('organizations', [ id: 105143, nci_institute_code: "NH027", name: "Lahey-Hitchcock Clinic", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH027",GROUP_DESC:"NH027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6158 ,protection_group_id: -5158, protection_element_id:-5158], primaryKey: false);
      insert('organizations', [ id: 105144, nci_institute_code: "NH028", name: "Exeter Hospital", city: "Exeter", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH028",GROUP_DESC:"NH028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6159 ,protection_group_id: -5159, protection_element_id:-5159], primaryKey: false);
      insert('organizations', [ id: 105145, nci_institute_code: "NH029", name: "Lakes Region General Hospital", city: "Laconia", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH029",GROUP_DESC:"NH029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6160 ,protection_group_id: -5160, protection_element_id:-5160], primaryKey: false);
      insert('organizations', [ id: 105146, nci_institute_code: "NH031", name: "Columbia Parkland Medical Center", city: "Derry", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH031",GROUP_DESC:"NH031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6161 ,protection_group_id: -5161, protection_element_id:-5161], primaryKey: false);
      insert('organizations', [ id: 105147, nci_institute_code: "NH032", name: "Hitchcock Clinic", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH032",GROUP_DESC:"NH032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6162 ,protection_group_id: -5162, protection_element_id:-5162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 105148, nci_institute_code: "NH033", name: "Littleton Regional Hospital", city: "Littleton", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH033",GROUP_DESC:"NH033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6163 ,protection_group_id: -5163, protection_element_id:-5163], primaryKey: false);
      insert('organizations', [ id: 105149, nci_institute_code: "NH034", name: "Saint Joseph Hospital", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH034",GROUP_DESC:"NH034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6164 ,protection_group_id: -5164, protection_element_id:-5164], primaryKey: false);
      insert('organizations', [ id: 105150, nci_institute_code: "NH035", name: "Portsmouth Regional Hospital", city: "Portsmouth", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH035",GROUP_DESC:"NH035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6165 ,protection_group_id: -5165, protection_element_id:-5165], primaryKey: false);
      insert('organizations', [ id: 105151, nci_institute_code: "NH036", name: "Oncology Center", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH036",GROUP_DESC:"NH036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6166 ,protection_group_id: -5166, protection_element_id:-5166], primaryKey: false);
      insert('organizations', [ id: 105152, nci_institute_code: "NH037", name: "Foundation Medical Partners", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH037",GROUP_DESC:"NH037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6167 ,protection_group_id: -5167, protection_element_id:-5167], primaryKey: false);
      insert('organizations', [ id: 105153, nci_institute_code: "NH038", name: "New Hampshire Oncology-Hematology, PA", city: "Concord", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH038",GROUP_DESC:"NH038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6168 ,protection_group_id: -5168, protection_element_id:-5168], primaryKey: false);
      insert('organizations', [ id: 105154, nci_institute_code: "NH039", name: "Norris Cotton Cancer Center", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH039",GROUP_DESC:"NH039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6169 ,protection_group_id: -5169, protection_element_id:-5169], primaryKey: false);
      insert('organizations', [ id: 105155, nci_institute_code: "NH040", name: "Dartmouth Hitchcock Medical Center - Manchester", city: "Manchester", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH040",GROUP_DESC:"NH040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6170 ,protection_group_id: -5170, protection_element_id:-5170], primaryKey: false);
      insert('organizations', [ id: 105156, nci_institute_code: "NH041", name: "Nashua Regional Cancer Center", city: "Nashua", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH041",GROUP_DESC:"NH041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6171 ,protection_group_id: -5171, protection_element_id:-5171], primaryKey: false);
      insert('organizations', [ id: 105157, nci_institute_code: "NH042", name: "Hematology Oncology Clinic", city: "Portsmouth", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH042",GROUP_DESC:"NH042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6172 ,protection_group_id: -5172, protection_element_id:-5172], primaryKey: false);
      insert('organizations', [ id: 105158, nci_institute_code: "NHLBI", name: "National Heart Lung and Blood Institute", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NHLBI",GROUP_DESC:"NHLBI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NHLBI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NHLBI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NHLBI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6173 ,protection_group_id: -5173, protection_element_id:-5173], primaryKey: false);
      insert('organizations', [ id: 105159, nci_institute_code: "NIADDK", name: "National Institutes of Health National Institute of Diabetes", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIADDK",GROUP_DESC:"NIADDK group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIADDK",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIADDK",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIADDK", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6174 ,protection_group_id: -5174, protection_element_id:-5174], primaryKey: false);
      insert('organizations', [ id: 105160, nci_institute_code: "NIAID", name: "National Institute of  Allergy and Infectious Disease", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIAID",GROUP_DESC:"NIAID group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIAID",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIAID",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIAID", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6175 ,protection_group_id: -5175, protection_element_id:-5175], primaryKey: false);
      insert('organizations', [ id: 105161, nci_institute_code: "NIAMS", name: "National Institutes of Health National Institute of Arthritis", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIAMS",GROUP_DESC:"NIAMS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIAMS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIAMS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIAMS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6176 ,protection_group_id: -5176, protection_element_id:-5176], primaryKey: false);
      insert('organizations', [ id: 105162, nci_institute_code: "NIDCD", name: "National Institute on Deafness & Other Communication Disorders", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIDCD",GROUP_DESC:"NIDCD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIDCD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIDCD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIDCD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6177 ,protection_group_id: -5177, protection_element_id:-5177], primaryKey: false);
      insert('organizations', [ id: 105163, nci_institute_code: "NIHCC", name: "National Institutes of Health Clinical Center", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHCC",GROUP_DESC:"NIHCC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIHCC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIHCC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHCC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6178 ,protection_group_id: -5178, protection_element_id:-5178], primaryKey: false);
      insert('organizations', [ id: 105164, nci_institute_code: "NIHEI", name: "National Institute of Health National Eye Institute", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHEI",GROUP_DESC:"NIHEI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIHEI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIHEI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHEI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6179 ,protection_group_id: -5179, protection_element_id:-5179], primaryKey: false);
      insert('organizations', [ id: 105165, nci_institute_code: "NIHNCR", name: "National Institute of Health - National Center for Research Resources", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHNCR",GROUP_DESC:"NIHNCR group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NIHNCR",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NIHNCR",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NIHNCR", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6180 ,protection_group_id: -5180, protection_element_id:-5180], primaryKey: false);
      insert('organizations', [ id: 105166, nci_institute_code: "NINCDS", name: "National Institutes of Health National Institute of Neurology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NINCDS",GROUP_DESC:"NINCDS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NINCDS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NINCDS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NINCDS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6181 ,protection_group_id: -5181, protection_element_id:-5181], primaryKey: false);
      insert('organizations', [ id: 105167, nci_institute_code: "NINDS", name: "National Institute of Neurological Disorders and Stroke", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NINDS",GROUP_DESC:"NINDS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NINDS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NINDS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NINDS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6182 ,protection_group_id: -5182, protection_element_id:-5182], primaryKey: false);
      insert('organizations', [ id: 105168, nci_institute_code: "NJ001", name: "Veterans Adminstration New Jersey Health Care System", city: "East Orange", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ001",GROUP_DESC:"NJ001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6183 ,protection_group_id: -5183, protection_element_id:-5183], primaryKey: false);
      insert('organizations', [ id: 105169, nci_institute_code: "NJ002", name: "Saint Barnabas Medical Center", city: "Livingston", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ002",GROUP_DESC:"NJ002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6184 ,protection_group_id: -5184, protection_element_id:-5184], primaryKey: false);
      insert('organizations', [ id: 105170, nci_institute_code: "NJ003", name: "Montclair Breast Center", city: "Montclair", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ003",GROUP_DESC:"NJ003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6185 ,protection_group_id: -5185, protection_element_id:-5185], primaryKey: false);
      insert('organizations', [ id: 105171, nci_institute_code: "NJ004", name: "Mountainside Hospital", city: "Montclair", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ004",GROUP_DESC:"NJ004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6186 ,protection_group_id: -5186, protection_element_id:-5186], primaryKey: false);
      insert('organizations', [ id: 105172, nci_institute_code: "NJ005", name: "Memorial Hospital", city: "Orange", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ005",GROUP_DESC:"NJ005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6187 ,protection_group_id: -5187, protection_element_id:-5187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 105173, nci_institute_code: "NJ006", name: "Metcalf Inst Hospital Center", city: "Orange", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ006",GROUP_DESC:"NJ006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6188 ,protection_group_id: -5188, protection_element_id:-5188], primaryKey: false);
      insert('organizations', [ id: 105174, nci_institute_code: "NJ008", name: "Beth Israel Hospital", city: "Passaic", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ008",GROUP_DESC:"NJ008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6189 ,protection_group_id: -5189, protection_element_id:-5189], primaryKey: false);
      insert('organizations', [ id: 105175, nci_institute_code: "NJ009", name: "Muehlenberg Hospital", city: "Planfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ009",GROUP_DESC:"NJ009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6190 ,protection_group_id: -5190, protection_element_id:-5190], primaryKey: false);
      insert('organizations', [ id: 105176, nci_institute_code: "NJ010", name: "Saint Michael's Medical Center", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ010",GROUP_DESC:"NJ010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6191 ,protection_group_id: -5191, protection_element_id:-5191], primaryKey: false);
      insert('organizations', [ id: 105177, nci_institute_code: "NJ011", name: "UMDNJ - New Jersey Medical School", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ011",GROUP_DESC:"NJ011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6192 ,protection_group_id: -5192, protection_element_id:-5192], primaryKey: false);
      insert('organizations', [ id: 105178, nci_institute_code: "NJ012", name: "Martland Hospital", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ012",GROUP_DESC:"NJ012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6193 ,protection_group_id: -5193, protection_element_id:-5193], primaryKey: false);
      insert('organizations', [ id: 105179, nci_institute_code: "NJ013", name: "Children's Hospital of New Jersey", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ013",GROUP_DESC:"NJ013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6194 ,protection_group_id: -5194, protection_element_id:-5194], primaryKey: false);
      insert('organizations', [ id: 105180, nci_institute_code: "NJ014", name: "Essex Oncology of North Jersey PA", city: "Belleville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ014",GROUP_DESC:"NJ014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6195 ,protection_group_id: -5195, protection_element_id:-5195], primaryKey: false);
      insert('organizations', [ id: 105181, nci_institute_code: "NJ015", name: "Newark Beth Israel Medical Center", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ015",GROUP_DESC:"NJ015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6196 ,protection_group_id: -5196, protection_element_id:-5196], primaryKey: false);
      insert('organizations', [ id: 105182, nci_institute_code: "NJ016", name: "Trinitas Hospital-Jersey Street Campus", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ016",GROUP_DESC:"NJ016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6197 ,protection_group_id: -5197, protection_element_id:-5197], primaryKey: false);
      insert('organizations', [ id: 105183, nci_institute_code: "NJ017", name: "Columbia-Presbyterian Medical Center", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ017",GROUP_DESC:"NJ017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6198 ,protection_group_id: -5198, protection_element_id:-5198], primaryKey: false);
      insert('organizations', [ id: 105184, nci_institute_code: "NJ018", name: "Christ Hospital", city: "Jersey City", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ018",GROUP_DESC:"NJ018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6199 ,protection_group_id: -5199, protection_element_id:-5199], primaryKey: false);
      insert('organizations', [ id: 105185, nci_institute_code: "NJ019", name: "Valley Hospital", city: "Ridgewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ019",GROUP_DESC:"NJ019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6200 ,protection_group_id: -5200, protection_element_id:-5200], primaryKey: false);
      insert('organizations', [ id: 105186, nci_institute_code: "NJ020", name: "Saint Joseph's Regional Medical Center", city: "Paterson", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ020",GROUP_DESC:"NJ020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6201 ,protection_group_id: -5201, protection_element_id:-5201], primaryKey: false);
      insert('organizations', [ id: 105187, nci_institute_code: "NJ021", name: "New Jersey College", city: "Paterson", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ021",GROUP_DESC:"NJ021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6202 ,protection_group_id: -5202, protection_element_id:-5202], primaryKey: false);
      insert('organizations', [ id: 105188, nci_institute_code: "NJ022", name: "Hackensack University Medical Center", city: "Hackensack", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ022",GROUP_DESC:"NJ022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6203 ,protection_group_id: -5203, protection_element_id:-5203], primaryKey: false);
      insert('organizations', [ id: 105189, nci_institute_code: "NJ023", name: "Englewood Hospital and Medical Center", city: "Englewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ023",GROUP_DESC:"NJ023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6204 ,protection_group_id: -5204, protection_element_id:-5204], primaryKey: false);
      insert('organizations', [ id: 105190, nci_institute_code: "NJ024", name: "Holy Name Hospital", city: "Teaneck", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ024",GROUP_DESC:"NJ024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6205 ,protection_group_id: -5205, protection_element_id:-5205], primaryKey: false);
      insert('organizations', [ id: 105191, nci_institute_code: "NJ025", name: "Patterson Army Hospital", city: "Fort Monmouth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ025",GROUP_DESC:"NJ025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6206 ,protection_group_id: -5206, protection_element_id:-5206], primaryKey: false);
      insert('organizations', [ id: 105192, nci_institute_code: "NJ026", name: "Overlook Hospital", city: "Summit", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ026",GROUP_DESC:"NJ026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6207 ,protection_group_id: -5207, protection_element_id:-5207], primaryKey: false);
      insert('organizations', [ id: 105193, nci_institute_code: "NJ027", name: "Sandoz", city: "Hanover", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ027",GROUP_DESC:"NJ027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6208 ,protection_group_id: -5208, protection_element_id:-5208], primaryKey: false);
      insert('organizations', [ id: 105194, nci_institute_code: "NJ028", name: "Morristown Memorial Hospital", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ028",GROUP_DESC:"NJ028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6209 ,protection_group_id: -5209, protection_element_id:-5209], primaryKey: false);
      insert('organizations', [ id: 105195, nci_institute_code: "NJ029", name: "Our Lady of Lourdes Hospital", city: "Haddenfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ029",GROUP_DESC:"NJ029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6210 ,protection_group_id: -5210, protection_element_id:-5210], primaryKey: false);
      insert('organizations', [ id: 105196, nci_institute_code: "NJ030", name: "Lourdes Medical Center of Burlington County", city: "Willingboro", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ030",GROUP_DESC:"NJ030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6211 ,protection_group_id: -5211, protection_element_id:-5211], primaryKey: false);
      insert('organizations', [ id: 105197, nci_institute_code: "NJ031", name: "South Jersey Healthcare", city: "Vineland", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ031",GROUP_DESC:"NJ031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6212 ,protection_group_id: -5212, protection_element_id:-5212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 105198, nci_institute_code: "NJ032", name: "Fox Chase Cancer Center at Virtua Memorial Hospital of Burlington County", city: "Mount Holly", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ032",GROUP_DESC:"NJ032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6213 ,protection_group_id: -5213, protection_element_id:-5213], primaryKey: false);
      insert('organizations', [ id: 105199, nci_institute_code: "NJ033", name: "Kennedy Memorial Hospital", city: "Stratford", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ033",GROUP_DESC:"NJ033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6214 ,protection_group_id: -5214, protection_element_id:-5214], primaryKey: false);
      insert('organizations', [ id: 105200, nci_institute_code: "NJ034", name: "University of Medicine - Dentistry", city: "Stratford", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ034",GROUP_DESC:"NJ034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6215 ,protection_group_id: -5215, protection_element_id:-5215], primaryKey: false);
      insert('organizations', [ id: 105201, nci_institute_code: "NJ035", name: "Underwood-Memorial Hospital", city: "Woodbury", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ035",GROUP_DESC:"NJ035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6216 ,protection_group_id: -5216, protection_element_id:-5216], primaryKey: false);
      insert('organizations', [ id: 105202, nci_institute_code: "NJ036", name: "Cooper Hospital University Medical Center", city: "Camden", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ036",GROUP_DESC:"NJ036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6217 ,protection_group_id: -5217, protection_element_id:-5217], primaryKey: false);
      insert('organizations', [ id: 105203, nci_institute_code: "NJ037", name: "AtlantiCare Regional Medical Center -Main Campus", city: "Pomona", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ037",GROUP_DESC:"NJ037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6218 ,protection_group_id: -5218, protection_element_id:-5218], primaryKey: false);
      insert('organizations', [ id: 105204, nci_institute_code: "NJ038", name: "Shore Memorial Hospital", city: "Somers Point", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ038",GROUP_DESC:"NJ038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6219 ,protection_group_id: -5219, protection_element_id:-5219], primaryKey: false);
      insert('organizations', [ id: 105205, nci_institute_code: "NJ039", name: "The Medical Center At Princeton", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ039",GROUP_DESC:"NJ039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6220 ,protection_group_id: -5220, protection_element_id:-5220], primaryKey: false);
      insert('organizations', [ id: 105206, nci_institute_code: "NJ040", name: "Capital Health System", city: "Trenton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ040",GROUP_DESC:"NJ040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6221 ,protection_group_id: -5221, protection_element_id:-5221], primaryKey: false);
      insert('organizations', [ id: 105207, nci_institute_code: "NJ041", name: "Saint Francis Medical Center", city: "Trenton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ041",GROUP_DESC:"NJ041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6222 ,protection_group_id: -5222, protection_element_id:-5222], primaryKey: false);
      insert('organizations', [ id: 105208, nci_institute_code: "NJ042", name: "Raritan Valley Hospital", city: "Green Brook", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ042",GROUP_DESC:"NJ042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6223 ,protection_group_id: -5223, protection_element_id:-5223], primaryKey: false);
      insert('organizations', [ id: 105209, nci_institute_code: "NJ043", name: "John F. Kennedy Medical Center", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ043",GROUP_DESC:"NJ043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6224 ,protection_group_id: -5224, protection_element_id:-5224], primaryKey: false);
      insert('organizations', [ id: 105210, nci_institute_code: "NJ044", name: "Walter Reed General Hospital", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ044",GROUP_DESC:"NJ044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6225 ,protection_group_id: -5225, protection_element_id:-5225], primaryKey: false);
      insert('organizations', [ id: 105211, nci_institute_code: "NJ045", name: "Hunterdon Medical Center", city: "Flemington", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ045",GROUP_DESC:"NJ045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6226 ,protection_group_id: -5226, protection_element_id:-5226], primaryKey: false);
      insert('organizations', [ id: 105212, nci_institute_code: "NJ047", name: "Rutgers University Medical School", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ047",GROUP_DESC:"NJ047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6227 ,protection_group_id: -5227, protection_element_id:-5227], primaryKey: false);
      insert('organizations', [ id: 105213, nci_institute_code: "NJ048", name: "Saint Peter's University Hospital", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ048",GROUP_DESC:"NJ048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6228 ,protection_group_id: -5228, protection_element_id:-5228], primaryKey: false);
      insert('organizations', [ id: 105214, nci_institute_code: "NJ050", name: "Trinitas Hospital - Williamson Street Campus", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ050",GROUP_DESC:"NJ050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6229 ,protection_group_id: -5229, protection_element_id:-5229], primaryKey: false);
      insert('organizations', [ id: 105215, nci_institute_code: "NJ051", name: "Saint Francis Hospital", city: "Jersey City", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ051",GROUP_DESC:"NJ051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6230 ,protection_group_id: -5230, protection_element_id:-5230], primaryKey: false);
      insert('organizations', [ id: 105216, nci_institute_code: "NJ052", name: "Bayonne Hospital", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ052",GROUP_DESC:"NJ052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6231 ,protection_group_id: -5231, protection_element_id:-5231], primaryKey: false);
      insert('organizations', [ id: 105217, nci_institute_code: "NJ053", name: "UMDNJ - Robert Wood Johnson University Hospital", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ053",GROUP_DESC:"NJ053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6232 ,protection_group_id: -5232, protection_element_id:-5232], primaryKey: false);
      insert('organizations', [ id: 105218, nci_institute_code: "NJ054", name: "Chilton Memorial Hospital", city: "Pompton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ054",GROUP_DESC:"NJ054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6233 ,protection_group_id: -5233, protection_element_id:-5233], primaryKey: false);
      insert('organizations', [ id: 105219, nci_institute_code: "NJ055", name: "Dover General Hospital and Medical Center", city: "Dover", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ055",GROUP_DESC:"NJ055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6234 ,protection_group_id: -5234, protection_element_id:-5234], primaryKey: false);
      insert('organizations', [ id: 105220, nci_institute_code: "NJ056", name: "Jersey City Medical Center", city: "Jersey City", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ056",GROUP_DESC:"NJ056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6235 ,protection_group_id: -5235, protection_element_id:-5235], primaryKey: false);
      insert('organizations', [ id: 105221, nci_institute_code: "NJ057", name: "Wayne General Hospital", city: "Wayne", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ057",GROUP_DESC:"NJ057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6236 ,protection_group_id: -5236, protection_element_id:-5236], primaryKey: false);
      insert('organizations', [ id: 105222, nci_institute_code: "NJ058", name: "Northern New Jersey CCOP", city: "Hackensack", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ058",GROUP_DESC:"NJ058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6237 ,protection_group_id: -5237, protection_element_id:-5237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 105223, nci_institute_code: "NJ059", name: "Union Hospital", city: "Union", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ059",GROUP_DESC:"NJ059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6238 ,protection_group_id: -5238, protection_element_id:-5238], primaryKey: false);
      insert('organizations', [ id: 105224, nci_institute_code: "NJ060", name: "Barnert Hospital", city: "Patterson", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ060",GROUP_DESC:"NJ060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6239 ,protection_group_id: -5239, protection_element_id:-5239], primaryKey: false);
      insert('organizations', [ id: 105225, nci_institute_code: "NJ061", name: "University Hospital", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ061",GROUP_DESC:"NJ061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6240 ,protection_group_id: -5240, protection_element_id:-5240], primaryKey: false);
      insert('organizations', [ id: 105226, nci_institute_code: "NJ062", name: "Warren Hospital", city: "Phillipsburg", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ062",GROUP_DESC:"NJ062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6241 ,protection_group_id: -5241, protection_element_id:-5241], primaryKey: false);
      insert('organizations', [ id: 105227, nci_institute_code: "NJ063", name: "Robert W. Johnson Medical School", city: "Piscataway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ063",GROUP_DESC:"NJ063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6242 ,protection_group_id: -5242, protection_element_id:-5242], primaryKey: false);
      insert('organizations', [ id: 105228, nci_institute_code: "NJ064", name: "Jersey Shore Medical Center", city: "Neptune", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ064",GROUP_DESC:"NJ064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6243 ,protection_group_id: -5243, protection_element_id:-5243], primaryKey: false);
      insert('organizations', [ id: 105229, nci_institute_code: "NJ065", name: "Jersey Shore Medical Center", city: "Manasquan", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ065",GROUP_DESC:"NJ065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6244 ,protection_group_id: -5244, protection_element_id:-5244], primaryKey: false);
      insert('organizations', [ id: 105230, nci_institute_code: "NJ066", name: "Cancer Institute of New Jersey", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ066",GROUP_DESC:"NJ066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6245 ,protection_group_id: -5245, protection_element_id:-5245], primaryKey: false);
      insert('organizations', [ id: 105231, nci_institute_code: "NJ067", name: "Booker Cancer Center at Riverview Medical Center", city: "Red Bank", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ067",GROUP_DESC:"NJ067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6246 ,protection_group_id: -5246, protection_element_id:-5246], primaryKey: false);
      insert('organizations', [ id: 105232, nci_institute_code: "NJ068", name: "Monmouth Medical Center", city: "Long Branch", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ068",GROUP_DESC:"NJ068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6247 ,protection_group_id: -5247, protection_element_id:-5247], primaryKey: false);
      insert('organizations', [ id: 105233, nci_institute_code: "NJ069", name: "Summit Medical Group", city: "Berkeley Heights", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ069",GROUP_DESC:"NJ069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6248 ,protection_group_id: -5248, protection_element_id:-5248], primaryKey: false);
      insert('organizations', [ id: 105234, nci_institute_code: "NJ070", name: "Community Medical Center", city: "Toms River", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ070",GROUP_DESC:"NJ070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6249 ,protection_group_id: -5249, protection_element_id:-5249], primaryKey: false);
      insert('organizations', [ id: 105235, nci_institute_code: "NJ071", name: "Newcomb Medical Center", city: "Vineland", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ071",GROUP_DESC:"NJ071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6250 ,protection_group_id: -5250, protection_element_id:-5250], primaryKey: false);
      insert('organizations', [ id: 105236, nci_institute_code: "NJ072", name: "Hematology Oncology Association P.A.", city: "Plainfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ072",GROUP_DESC:"NJ072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6251 ,protection_group_id: -5251, protection_element_id:-5251], primaryKey: false);
      insert('organizations', [ id: 105237, nci_institute_code: "NJ074", name: "Hackensack University Medical CCOP", city: "Hackensack", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ074",GROUP_DESC:"NJ074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6252 ,protection_group_id: -5252, protection_element_id:-5252], primaryKey: false);
      insert('organizations', [ id: 105238, nci_institute_code: "NJ075", name: "Clara Maass Medical Center", city: "Belleville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ075",GROUP_DESC:"NJ075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6253 ,protection_group_id: -5253, protection_element_id:-5253], primaryKey: false);
      insert('organizations', [ id: 105239, nci_institute_code: "NJ076", name: "Tri-County CCOP", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ076",GROUP_DESC:"NJ076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6254 ,protection_group_id: -5254, protection_element_id:-5254], primaryKey: false);
      insert('organizations', [ id: 105240, nci_institute_code: "NJ077", name: "New Jersey - Cooper Hospital CCOP", city: "Camden", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ077",GROUP_DESC:"NJ077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6255 ,protection_group_id: -5255, protection_element_id:-5255], primaryKey: false);
      insert('organizations', [ id: 105241, nci_institute_code: "NJ079", name: "Umdnj - The Cancer Institute of New Jersey", city: "Camden", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ079",GROUP_DESC:"NJ079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6256 ,protection_group_id: -5256, protection_element_id:-5256], primaryKey: false);
      insert('organizations', [ id: 105242, nci_institute_code: "NJ080", name: "Memorial Hospital of Salem County", city: "Salem", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ080",GROUP_DESC:"NJ080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6257 ,protection_group_id: -5257, protection_element_id:-5257], primaryKey: false);
      insert('organizations', [ id: 105243, nci_institute_code: "NJ081", name: "Medical Diagnostic Associates", city: "Springfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ081",GROUP_DESC:"NJ081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6258 ,protection_group_id: -5258, protection_element_id:-5258], primaryKey: false);
      insert('organizations', [ id: 105244, nci_institute_code: "NJ082", name: "Elizabeth Mdcl Assoc", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ082",GROUP_DESC:"NJ082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6259 ,protection_group_id: -5259, protection_element_id:-5259], primaryKey: false);
      insert('organizations', [ id: 105245, nci_institute_code: "NJ083", name: "Oncology  Hematology  Assoc.", city: "Woodbury", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ083",GROUP_DESC:"NJ083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6260 ,protection_group_id: -5260, protection_element_id:-5260], primaryKey: false);
      insert('organizations', [ id: 105246, nci_institute_code: "NJ084", name: "Princeton Surgical Associates PA", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ084",GROUP_DESC:"NJ084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6261 ,protection_group_id: -5261, protection_element_id:-5261], primaryKey: false);
      insert('organizations', [ id: 105247, nci_institute_code: "NJ085", name: "Marmora Medical Commons", city: "Marmora", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ085",GROUP_DESC:"NJ085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6262 ,protection_group_id: -5262, protection_element_id:-5262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 105248, nci_institute_code: "NJ086", name: "Hematology Oncology Associates Professional Center", city: "Somerset", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ086",GROUP_DESC:"NJ086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6263 ,protection_group_id: -5263, protection_element_id:-5263], primaryKey: false);
      insert('organizations', [ id: 105249, nci_institute_code: "NJ087", name: "Millburn Surgical Associates", city: "Millburn", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ087",GROUP_DESC:"NJ087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6264 ,protection_group_id: -5264, protection_element_id:-5264], primaryKey: false);
      insert('organizations', [ id: 105250, nci_institute_code: "NJ088", name: "Hip of New Jersey", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ088",GROUP_DESC:"NJ088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6265 ,protection_group_id: -5265, protection_element_id:-5265], primaryKey: false);
      insert('organizations', [ id: 105251, nci_institute_code: "NJ089", name: "Westfield Medical Group", city: "Westfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ089",GROUP_DESC:"NJ089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6266 ,protection_group_id: -5266, protection_element_id:-5266], primaryKey: false);
      insert('organizations', [ id: 105252, nci_institute_code: "NJ090", name: "Hightstown Medical Association", city: "E. Windsor", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ090",GROUP_DESC:"NJ090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6267 ,protection_group_id: -5267, protection_element_id:-5267], primaryKey: false);
      insert('organizations', [ id: 105253, nci_institute_code: "NJ091", name: "Cancer Center of Saint Barnabas at Union", city: "Union", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ091",GROUP_DESC:"NJ091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6268 ,protection_group_id: -5268, protection_element_id:-5268], primaryKey: false);
      insert('organizations', [ id: 105254, nci_institute_code: "NJ092", name: "Northwest Covenant Medical Ctr.", city: "Dover", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ092",GROUP_DESC:"NJ092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6269 ,protection_group_id: -5269, protection_element_id:-5269], primaryKey: false);
      insert('organizations', [ id: 105255, nci_institute_code: "NJ094", name: "The Minniti Center for Medical Oncology and Hematology", city: "Mickleton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ094",GROUP_DESC:"NJ094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6270 ,protection_group_id: -5270, protection_element_id:-5270], primaryKey: false);
      insert('organizations', [ id: 105256, nci_institute_code: "NJ095", name: "Atlantic HematologyOncology Group", city: "Absecon", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ095",GROUP_DESC:"NJ095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6271 ,protection_group_id: -5271, protection_element_id:-5271], primaryKey: false);
      insert('organizations', [ id: 105257, nci_institute_code: "NJ096", name: "Pinnacle Medical Group", city: "Somerset", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ096",GROUP_DESC:"NJ096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6272 ,protection_group_id: -5272, protection_element_id:-5272], primaryKey: false);
      insert('organizations', [ id: 105258, nci_institute_code: "NJ097", name: "Cancer Institute of New Jersey At Hamilton", city: "Hamilton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ097",GROUP_DESC:"NJ097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6273 ,protection_group_id: -5273, protection_element_id:-5273], primaryKey: false);
      insert('organizations', [ id: 105259, nci_institute_code: "NJ098", name: "Woodbridge Internal Medical Assoc., P.A.", city: "Woodbridge", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ098",GROUP_DESC:"NJ098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6274 ,protection_group_id: -5274, protection_element_id:-5274], primaryKey: false);
      insert('organizations', [ id: 105260, nci_institute_code: "NJ099", name: "Southern Ocean County Medical Center", city: "Manahawkin", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ099",GROUP_DESC:"NJ099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6275 ,protection_group_id: -5275, protection_element_id:-5275], primaryKey: false);
      insert('organizations', [ id: 105261, nci_institute_code: "NJ100", name: "West Hudson Hospital", city: "Kearny", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ100",GROUP_DESC:"NJ100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6276 ,protection_group_id: -5276, protection_element_id:-5276], primaryKey: false);
      insert('organizations', [ id: 105262, nci_institute_code: "NJ101", name: "Central Jersey Oncology Group", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ101",GROUP_DESC:"NJ101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6277 ,protection_group_id: -5277, protection_element_id:-5277], primaryKey: false);
      insert('organizations', [ id: 105263, nci_institute_code: "NJ102", name: "Riverside Medical Center", city: "Little Silver", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ102",GROUP_DESC:"NJ102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6278 ,protection_group_id: -5278, protection_element_id:-5278], primaryKey: false);
      insert('organizations', [ id: 105264, nci_institute_code: "NJ104", name: "The Center for Cancer and Hematologic Disease", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ104",GROUP_DESC:"NJ104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6279 ,protection_group_id: -5279, protection_element_id:-5279], primaryKey: false);
      insert('organizations', [ id: 105265, nci_institute_code: "NJ106", name: "Prospect Medical Offices", city: "Midland Park", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ106",GROUP_DESC:"NJ106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6280 ,protection_group_id: -5280, protection_element_id:-5280], primaryKey: false);
      insert('organizations', [ id: 105266, nci_institute_code: "NJ107", name: "Howell Office Plaza", city: "Howell", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ107",GROUP_DESC:"NJ107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6281 ,protection_group_id: -5281, protection_element_id:-5281], primaryKey: false);
      insert('organizations', [ id: 105267, nci_institute_code: "NJ108", name: "Kimball Medical Center", city: "Lakewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ108",GROUP_DESC:"NJ108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6282 ,protection_group_id: -5282, protection_element_id:-5282], primaryKey: false);
      insert('organizations', [ id: 105268, nci_institute_code: "NJ109", name: "Southern Oncology Hematology Associates PA", city: "Vineland", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ109",GROUP_DESC:"NJ109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6283 ,protection_group_id: -5283, protection_element_id:-5283], primaryKey: false);
      insert('organizations', [ id: 105269, nci_institute_code: "NJ110", name: "Somerset Medical Center", city: "Somerville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ110",GROUP_DESC:"NJ110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6284 ,protection_group_id: -5284, protection_element_id:-5284], primaryKey: false);
      insert('organizations', [ id: 105270, nci_institute_code: "NJ111", name: "Cooper Cancer Institute", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ111",GROUP_DESC:"NJ111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6285 ,protection_group_id: -5285, protection_element_id:-5285], primaryKey: false);
      insert('organizations', [ id: 105271, nci_institute_code: "NJ112", name: "Garden State Cancer Center", city: "Belleville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ112",GROUP_DESC:"NJ112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6286 ,protection_group_id: -5286, protection_element_id:-5286], primaryKey: false);
      insert('organizations', [ id: 105272, nci_institute_code: "NJ115", name: "The Cancer Institute of New Jersey at Cooper University Hospital", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ115",GROUP_DESC:"NJ115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6287 ,protection_group_id: -5287, protection_element_id:-5287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 105273, nci_institute_code: "NJ116", name: "Pascack Valley Hospital", city: "Westwood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ116",GROUP_DESC:"NJ116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6288 ,protection_group_id: -5288, protection_element_id:-5288], primaryKey: false);
      insert('organizations', [ id: 105274, nci_institute_code: "NJ117", name: "Raritan Bay Medical Center", city: "Perth Amboy", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ117",GROUP_DESC:"NJ117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6289 ,protection_group_id: -5289, protection_element_id:-5289], primaryKey: false);
      insert('organizations', [ id: 105275, nci_institute_code: "NJ118", name: "Center for Molecular Medicine and Immunology", city: "Newark", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ118",GROUP_DESC:"NJ118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6290 ,protection_group_id: -5290, protection_element_id:-5290], primaryKey: false);
      insert('organizations', [ id: 105276, nci_institute_code: "NJ119", name: "Walson Army Hospital", city: "Hackensack", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ119",GROUP_DESC:"NJ119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6291 ,protection_group_id: -5291, protection_element_id:-5291], primaryKey: false);
      insert('organizations', [ id: 105277, nci_institute_code: "NJ120", name: "Warren Radiation Therapy Center", city: "Warren", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ120",GROUP_DESC:"NJ120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6292 ,protection_group_id: -5292, protection_element_id:-5292], primaryKey: false);
      insert('organizations', [ id: 105278, nci_institute_code: "NJ122", name: "Saint Clare's Health Services", city: "Denville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ122",GROUP_DESC:"NJ122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6293 ,protection_group_id: -5293, protection_element_id:-5293], primaryKey: false);
      insert('organizations', [ id: 105279, nci_institute_code: "NJ123", name: "Hematology, Medical Oncology", city: "Bayonne", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ123",GROUP_DESC:"NJ123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6294 ,protection_group_id: -5294, protection_element_id:-5294], primaryKey: false);
      insert('organizations', [ id: 105280, nci_institute_code: "NJ124", name: "Northern New Jersey Cancer Associates", city: "Franklin Lakes", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ124",GROUP_DESC:"NJ124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6295 ,protection_group_id: -5295, protection_element_id:-5295], primaryKey: false);
      insert('organizations', [ id: 105281, nci_institute_code: "NJ125", name: "Radiation Facilities Group", city: "Willingboro", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ125",GROUP_DESC:"NJ125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6296 ,protection_group_id: -5296, protection_element_id:-5296], primaryKey: false);
      insert('organizations', [ id: 105282, nci_institute_code: "NJ126", name: "University of Medicine & Denistry of New Jersey", city: "Piscataway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ126",GROUP_DESC:"NJ126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6297 ,protection_group_id: -5297, protection_element_id:-5297], primaryKey: false);
      insert('organizations', [ id: 105283, nci_institute_code: "NJ127", name: "Smithkline Beecham Consumer Healthcare", city: "Parsippany", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ127",GROUP_DESC:"NJ127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6298 ,protection_group_id: -5298, protection_element_id:-5298], primaryKey: false);
      insert('organizations', [ id: 105284, nci_institute_code: "NJ128", name: "Trinitas Hospital Jersey St. Campus", city: "Elizabeth", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ128",GROUP_DESC:"NJ128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6299 ,protection_group_id: -5299, protection_element_id:-5299], primaryKey: false);
      insert('organizations', [ id: 105285, nci_institute_code: "NJ129", name: "Burdette Tomlin Memorial Hospital", city: "Cape May Court House", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ129",GROUP_DESC:"NJ129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6300 ,protection_group_id: -5300, protection_element_id:-5300], primaryKey: false);
      insert('organizations', [ id: 105286, nci_institute_code: "NJ130", name: "Burlington County Hematology Oncology Associates PA", city: "Westampton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ130",GROUP_DESC:"NJ130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6301 ,protection_group_id: -5301, protection_element_id:-5301], primaryKey: false);
      insert('organizations', [ id: 105287, nci_institute_code: "NJ131", name: "Comprehensive Cancer and Hematology Specialists", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ131",GROUP_DESC:"NJ131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6302 ,protection_group_id: -5302, protection_element_id:-5302], primaryKey: false);
      insert('organizations', [ id: 105288, nci_institute_code: "NJ132", name: "Princeton Radiology Associates,P.A", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ132",GROUP_DESC:"NJ132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6303 ,protection_group_id: -5303, protection_element_id:-5303], primaryKey: false);
      insert('organizations', [ id: 105289, nci_institute_code: "NJ134", name: "Robert Wood Johnson Univ Hospital", city: "New Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ134",GROUP_DESC:"NJ134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6304 ,protection_group_id: -5304, protection_element_id:-5304], primaryKey: false);
      insert('organizations', [ id: 105290, nci_institute_code: "NJ135", name: "Ocean Medical Center", city: "Brick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ135",GROUP_DESC:"NJ135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6305 ,protection_group_id: -5305, protection_element_id:-5305], primaryKey: false);
      insert('organizations', [ id: 105291, nci_institute_code: "NJ136", name: "Hematology and Oncology Associates, LLC", city: "Union", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ136",GROUP_DESC:"NJ136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6306 ,protection_group_id: -5306, protection_element_id:-5306], primaryKey: false);
      insert('organizations', [ id: 105292, nci_institute_code: "NJ137", name: "Urology Group P. A.", city: "Midland Park", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ137",GROUP_DESC:"NJ137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6307 ,protection_group_id: -5307, protection_element_id:-5307], primaryKey: false);
      insert('organizations', [ id: 105293, nci_institute_code: "NJ138", name: "Medical Oncology and Hematology", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ138",GROUP_DESC:"NJ138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6308 ,protection_group_id: -5308, protection_element_id:-5308], primaryKey: false);
      insert('organizations', [ id: 105294, nci_institute_code: "NJ139", name: "Riverview Medical Center", city: "Red Bank", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ139",GROUP_DESC:"NJ139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6309 ,protection_group_id: -5309, protection_element_id:-5309], primaryKey: false);
      insert('organizations', [ id: 105295, nci_institute_code: "NJ140", name: "Virtua West Jersey Hospital Marlton", city: "Marlton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ140",GROUP_DESC:"NJ140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6310 ,protection_group_id: -5310, protection_element_id:-5310], primaryKey: false);
      insert('organizations', [ id: 105296, nci_institute_code: "NJ141", name: "CentraState Medical Center", city: "Freehold", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ141",GROUP_DESC:"NJ141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6311 ,protection_group_id: -5311, protection_element_id:-5311], primaryKey: false);
      insert('organizations', [ id: 105297, nci_institute_code: "NJ142", name: "Virtua West Jersey Hospital Voorhees", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ142",GROUP_DESC:"NJ142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6312 ,protection_group_id: -5312, protection_element_id:-5312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 105298, nci_institute_code: "NJ143", name: "Centennial Medical Center", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ143",GROUP_DESC:"NJ143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6313 ,protection_group_id: -5313, protection_element_id:-5313], primaryKey: false);
      insert('organizations', [ id: 105299, nci_institute_code: "NJ144", name: "West Jersey Cancer Center", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ144",GROUP_DESC:"NJ144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6314 ,protection_group_id: -5314, protection_element_id:-5314], primaryKey: false);
      insert('organizations', [ id: 105300, nci_institute_code: "NJ145", name: "Summit Breast Care", city: "Summit", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ145",GROUP_DESC:"NJ145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6315 ,protection_group_id: -5315, protection_element_id:-5315], primaryKey: false);
      insert('organizations', [ id: 105301, nci_institute_code: "NJ146", name: "The Valley Hospital", city: "Paramus", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ146",GROUP_DESC:"NJ146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6316 ,protection_group_id: -5316, protection_element_id:-5316], primaryKey: false);
      insert('organizations', [ id: 105302, nci_institute_code: "NJ147", name: "Princeton Medical Group, P.A.", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ147",GROUP_DESC:"NJ147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6317 ,protection_group_id: -5317, protection_element_id:-5317], primaryKey: false);
      insert('organizations', [ id: 105303, nci_institute_code: "NJ148", name: "Adult and Pediatric Urology Group PA", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ148",GROUP_DESC:"NJ148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6318 ,protection_group_id: -5318, protection_element_id:-5318], primaryKey: false);
      insert('organizations', [ id: 105304, nci_institute_code: "NJ149", name: "Princeton Radiation Oncology Center", city: "Jamesburg", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ149",GROUP_DESC:"NJ149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6319 ,protection_group_id: -5319, protection_element_id:-5319], primaryKey: false);
      insert('organizations', [ id: 105305, nci_institute_code: "NJ150", name: "Comprehensive Surgical Associates", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ150",GROUP_DESC:"NJ150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6320 ,protection_group_id: -5320, protection_element_id:-5320], primaryKey: false);
      insert('organizations', [ id: 105306, nci_institute_code: "NJ151", name: "Adult Medical Oncology", city: "Little Silver", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ151",GROUP_DESC:"NJ151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6321 ,protection_group_id: -5321, protection_element_id:-5321], primaryKey: false);
      insert('organizations', [ id: 105307, nci_institute_code: "NJ152", name: "North Jersey Thoracic Surgical Associates, P.C.", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ152",GROUP_DESC:"NJ152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6322 ,protection_group_id: -5322, protection_element_id:-5322], primaryKey: false);
      insert('organizations', [ id: 105308, nci_institute_code: "NJ153", name: "Central Jersey Otolaryngology", city: "Shrewsbury", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ153",GROUP_DESC:"NJ153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6323 ,protection_group_id: -5323, protection_element_id:-5323], primaryKey: false);
      insert('organizations', [ id: 105309, nci_institute_code: "NJ154", name: "North Jersey Surgical Services & Consultants, Inc", city: "Bernardsville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ154",GROUP_DESC:"NJ154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6324 ,protection_group_id: -5324, protection_element_id:-5324], primaryKey: false);
      insert('organizations', [ id: 105310, nci_institute_code: "NJ155", name: "GFM Surgical Associates", city: "Somers Point", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ155",GROUP_DESC:"NJ155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6325 ,protection_group_id: -5325, protection_element_id:-5325], primaryKey: false);
      insert('organizations', [ id: 105311, nci_institute_code: "NJ156", name: "University of Pennsylvania Health System", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ156",GROUP_DESC:"NJ156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6326 ,protection_group_id: -5326, protection_element_id:-5326], primaryKey: false);
      insert('organizations', [ id: 105312, nci_institute_code: "NJ157", name: "Children's Hospital of Philadelphia", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ157",GROUP_DESC:"NJ157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6327 ,protection_group_id: -5327, protection_element_id:-5327], primaryKey: false);
      insert('organizations', [ id: 105313, nci_institute_code: "NJ158", name: "Bellevue Hematology and Oncology", city: "Trenton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ158",GROUP_DESC:"NJ158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6328 ,protection_group_id: -5328, protection_element_id:-5328], primaryKey: false);
      insert('organizations', [ id: 105314, nci_institute_code: "NJ159", name: "Somers Point Associates, P.A.", city: "Somers Point", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ159",GROUP_DESC:"NJ159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6329 ,protection_group_id: -5329, protection_element_id:-5329], primaryKey: false);
      insert('organizations', [ id: 105315, nci_institute_code: "NJ160", name: "Central Jersey Oncology Center- Brier Hill", city: "East Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ160",GROUP_DESC:"NJ160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6330 ,protection_group_id: -5330, protection_element_id:-5330], primaryKey: false);
      insert('organizations', [ id: 105316, nci_institute_code: "NJ161", name: "Monmouth Hematology-Oncology Associates PC", city: "West Long Branch", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ161",GROUP_DESC:"NJ161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6331 ,protection_group_id: -5331, protection_element_id:-5331], primaryKey: false);
      insert('organizations', [ id: 105317, nci_institute_code: "NJ162", name: "Virtua Health Cancer Program-Voorhees", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ162",GROUP_DESC:"NJ162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6332 ,protection_group_id: -5332, protection_element_id:-5332], primaryKey: false);
      insert('organizations', [ id: 105318, nci_institute_code: "NJ163", name: "Theradex", city: "Princeton Junction", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ163",GROUP_DESC:"NJ163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6333 ,protection_group_id: -5333, protection_element_id:-5333], primaryKey: false);
      insert('organizations', [ id: 105319, nci_institute_code: "NJ164", name: "Virtua Surgical Group PA", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ164",GROUP_DESC:"NJ164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6334 ,protection_group_id: -5334, protection_element_id:-5334], primaryKey: false);
      insert('organizations', [ id: 105320, nci_institute_code: "NJ165", name: "Allied Surgical Group, P.A.", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ165",GROUP_DESC:"NJ165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6335 ,protection_group_id: -5335, protection_element_id:-5335], primaryKey: false);
      insert('organizations', [ id: 105321, nci_institute_code: "NJ166", name: "Northwest Urology Associates", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ166",GROUP_DESC:"NJ166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6336 ,protection_group_id: -5336, protection_element_id:-5336], primaryKey: false);
      insert('organizations', [ id: 105322, nci_institute_code: "NJ167", name: "Oncology Hematology Specialists", city: "Denville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ167",GROUP_DESC:"NJ167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6337 ,protection_group_id: -5337, protection_element_id:-5337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 105323, nci_institute_code: "NJ168", name: "Breast & General Surgeons of North Jersey", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ168",GROUP_DESC:"NJ168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6338 ,protection_group_id: -5338, protection_element_id:-5338], primaryKey: false);
      insert('organizations', [ id: 105324, nci_institute_code: "NJ169", name: "North Jersey Hematology / Oncology Group", city: "Clifton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ169",GROUP_DESC:"NJ169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6339 ,protection_group_id: -5339, protection_element_id:-5339], primaryKey: false);
      insert('organizations', [ id: 105325, nci_institute_code: "NJ170", name: "Family Practice Center", city: "Woodbury", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ170",GROUP_DESC:"NJ170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6340 ,protection_group_id: -5340, protection_element_id:-5340], primaryKey: false);
      insert('organizations', [ id: 105326, nci_institute_code: "NJ171", name: "Oncology Hematology and Internal Medicine", city: "Westwood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ171",GROUP_DESC:"NJ171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6341 ,protection_group_id: -5341, protection_element_id:-5341], primaryKey: false);
      insert('organizations', [ id: 105327, nci_institute_code: "NJ172", name: "The Center for Cancer and Hematologic Disease", city: "Marlton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ172",GROUP_DESC:"NJ172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6342 ,protection_group_id: -5342, protection_element_id:-5342], primaryKey: false);
      insert('organizations', [ id: 105328, nci_institute_code: "NJ173", name: "Comprehensive Neurosurgical", city: "Paramus", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ173",GROUP_DESC:"NJ173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6343 ,protection_group_id: -5343, protection_element_id:-5343], primaryKey: false);
      insert('organizations', [ id: 105329, nci_institute_code: "NJ174", name: "Somerset Hematology Oncology Associates", city: "Somerville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ174",GROUP_DESC:"NJ174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6344 ,protection_group_id: -5344, protection_element_id:-5344], primaryKey: false);
      insert('organizations', [ id: 105330, nci_institute_code: "NJ175", name: "Colon and Rectal Surgery Associates", city: "Cherry Hill", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ175",GROUP_DESC:"NJ175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6345 ,protection_group_id: -5345, protection_element_id:-5345], primaryKey: false);
      insert('organizations', [ id: 105331, nci_institute_code: "NJ176", name: "Urology Health Care Associates", city: "Westampton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ176",GROUP_DESC:"NJ176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6346 ,protection_group_id: -5346, protection_element_id:-5346], primaryKey: false);
      insert('organizations', [ id: 105332, nci_institute_code: "NJ177", name: "Urology Physicians, P.A.", city: "Mount Holly", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ177",GROUP_DESC:"NJ177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6347 ,protection_group_id: -5347, protection_element_id:-5347], primaryKey: false);
      insert('organizations', [ id: 105333, nci_institute_code: "NJ178", name: "Memorial Sloan-Kettering Cancer Center at Saint Claires", city: "Denville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ178",GROUP_DESC:"NJ178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6348 ,protection_group_id: -5348, protection_element_id:-5348], primaryKey: false);
      insert('organizations', [ id: 105334, nci_institute_code: "NJ179", name: "JASRA Inc", city: "Chester", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ179",GROUP_DESC:"NJ179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6349 ,protection_group_id: -5349, protection_element_id:-5349], primaryKey: false);
      insert('organizations', [ id: 105335, nci_institute_code: "NJ180", name: "Princeton University", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ180",GROUP_DESC:"NJ180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6350 ,protection_group_id: -5350, protection_element_id:-5350], primaryKey: false);
      insert('organizations', [ id: 105336, nci_institute_code: "NJ182", name: "Starr Hematology and Medical Oncology PA", city: "Phillipsburg", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ182",GROUP_DESC:"NJ182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6351 ,protection_group_id: -5351, protection_element_id:-5351], primaryKey: false);
      insert('organizations', [ id: 105337, nci_institute_code: "NJ183", name: "Eastlantic Diagnostic Institute", city: "South Vineland", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ183",GROUP_DESC:"NJ183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6352 ,protection_group_id: -5352, protection_element_id:-5352], primaryKey: false);
      insert('organizations', [ id: 105338, nci_institute_code: "NJ184", name: "Hematology Oncology of Central New Jersey", city: "Little Silver", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ184",GROUP_DESC:"NJ184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6353 ,protection_group_id: -5353, protection_element_id:-5353], primaryKey: false);
      insert('organizations', [ id: 105339, nci_institute_code: "NJ185", name: "Essex Oncology Group", city: "Montclair", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ185",GROUP_DESC:"NJ185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6354 ,protection_group_id: -5354, protection_element_id:-5354], primaryKey: false);
      insert('organizations', [ id: 105340, nci_institute_code: "NJ186", name: "AtlantiCare Regional Medical Center - City Campus", city: "Atlantic City", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ186",GROUP_DESC:"NJ186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6355 ,protection_group_id: -5355, protection_element_id:-5355], primaryKey: false);
      insert('organizations', [ id: 105341, nci_institute_code: "NJ187", name: "Atlantic Hematology Oncology Associates", city: "Manasquan", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ187",GROUP_DESC:"NJ187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6356 ,protection_group_id: -5356, protection_element_id:-5356], primaryKey: false);
      insert('organizations', [ id: 105342, nci_institute_code: "NJ188", name: "Adult and Pediatric Urology", city: "Millburn", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ188",GROUP_DESC:"NJ188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6357 ,protection_group_id: -5357, protection_element_id:-5357], primaryKey: false);
      insert('organizations', [ id: 105343, nci_institute_code: "NJ189", name: "Kennedy Health Systems", city: "Sewell", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ189",GROUP_DESC:"NJ189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6358 ,protection_group_id: -5358, protection_element_id:-5358], primaryKey: false);
      insert('organizations', [ id: 105344, nci_institute_code: "NJ190", name: "Cardiology and Oncology Associates", city: "Mahwah", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ190",GROUP_DESC:"NJ190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6359 ,protection_group_id: -5359, protection_element_id:-5359], primaryKey: false);
      insert('organizations', [ id: 105345, nci_institute_code: "NJ191", name: "University Radiology Group", city: "East Brunswick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ191",GROUP_DESC:"NJ191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6360 ,protection_group_id: -5360, protection_element_id:-5360], primaryKey: false);
      insert('organizations', [ id: 105346, nci_institute_code: "NJ192", name: "The Nazha Cancer Treatment Center", city: "Northfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ192",GROUP_DESC:"NJ192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6361 ,protection_group_id: -5361, protection_element_id:-5361], primaryKey: false);
      insert('organizations', [ id: 105347, nci_institute_code: "NJ193", name: "Mill Hill Associates", city: "Trenton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ193",GROUP_DESC:"NJ193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6362 ,protection_group_id: -5362, protection_element_id:-5362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 105348, nci_institute_code: "NJ195", name: "Colon and Rectal Surgery Center", city: "Mount Holly", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ195",GROUP_DESC:"NJ195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6363 ,protection_group_id: -5363, protection_element_id:-5363], primaryKey: false);
      insert('organizations', [ id: 105349, nci_institute_code: "NJ196", name: "Monmouth Thoracic Surgical Group", city: "Freehold", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ196",GROUP_DESC:"NJ196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6364 ,protection_group_id: -5364, protection_element_id:-5364], primaryKey: false);
      insert('organizations', [ id: 105350, nci_institute_code: "NJ197", name: "Morristown Urology Associates LLC", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ197",GROUP_DESC:"NJ197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6365 ,protection_group_id: -5365, protection_element_id:-5365], primaryKey: false);
      insert('organizations', [ id: 105351, nci_institute_code: "NJ198", name: "Virtua West Jersey Hospital Berlin", city: "Berlin", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ198",GROUP_DESC:"NJ198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6366 ,protection_group_id: -5366, protection_element_id:-5366], primaryKey: false);
      insert('organizations', [ id: 105352, nci_institute_code: "NJ199", name: "Foc Chase Virtua Health Cancer Program - Gibbsboro", city: "Gibbsboro", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ199",GROUP_DESC:"NJ199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6367 ,protection_group_id: -5367, protection_element_id:-5367], primaryKey: false);
      insert('organizations', [ id: 105353, nci_institute_code: "NJ200", name: "Memorial Sloan Kettering Cancer Center at Basking Ridge", city: "Basking Ridge", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ200",GROUP_DESC:"NJ200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6368 ,protection_group_id: -5368, protection_element_id:-5368], primaryKey: false);
      insert('organizations', [ id: 105354, nci_institute_code: "NJ201", name: "Sussex Radiation Oncology Associates", city: "Sparta", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ201",GROUP_DESC:"NJ201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6369 ,protection_group_id: -5369, protection_element_id:-5369], primaryKey: false);
      insert('organizations', [ id: 105355, nci_institute_code: "NJ202", name: "Sparta Cancer Treatment Center", city: "Sparta", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ202",GROUP_DESC:"NJ202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6370 ,protection_group_id: -5370, protection_element_id:-5370], primaryKey: false);
      insert('organizations', [ id: 105356, nci_institute_code: "NJ203", name: "Lakewood Surgical Group PA", city: "Lakewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ203",GROUP_DESC:"NJ203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6371 ,protection_group_id: -5371, protection_element_id:-5371], primaryKey: false);
      insert('organizations', [ id: 105357, nci_institute_code: "NJ204", name: "Summit Urologic Associates", city: "Summit", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ204",GROUP_DESC:"NJ204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6372 ,protection_group_id: -5372, protection_element_id:-5372], primaryKey: false);
      insert('organizations', [ id: 105358, nci_institute_code: "NJ205", name: "Atlantic Coast Urologic Implant Center LLC", city: "Millburn", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ205",GROUP_DESC:"NJ205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6373 ,protection_group_id: -5373, protection_element_id:-5373], primaryKey: false);
      insert('organizations', [ id: 105359, nci_institute_code: "NJ206", name: "Urology Associates", city: "Red Bank", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ206",GROUP_DESC:"NJ206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6374 ,protection_group_id: -5374, protection_element_id:-5374], primaryKey: false);
      insert('organizations', [ id: 105360, nci_institute_code: "NJ207", name: "New Jersey Hemotology Oncology Associates", city: "Brick", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ207",GROUP_DESC:"NJ207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6375 ,protection_group_id: -5375, protection_element_id:-5375], primaryKey: false);
      insert('organizations', [ id: 105361, nci_institute_code: "NJ208", name: "Bayshore Community Hospital", city: "Holmdel", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ208",GROUP_DESC:"NJ208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6376 ,protection_group_id: -5376, protection_element_id:-5376], primaryKey: false);
      insert('organizations', [ id: 105362, nci_institute_code: "NJ209", name: "Princeton Nassau Pediatrics", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ209",GROUP_DESC:"NJ209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6377 ,protection_group_id: -5377, protection_element_id:-5377], primaryKey: false);
      insert('organizations', [ id: 105363, nci_institute_code: "NJ210", name: "Athena Breast Care Center LLC", city: "Moorestown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ210",GROUP_DESC:"NJ210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6378 ,protection_group_id: -5378, protection_element_id:-5378], primaryKey: false);
      insert('organizations', [ id: 105364, nci_institute_code: "NJ211", name: "Mercer Bucks Hematology Oncology", city: "Lawrenceville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ211",GROUP_DESC:"NJ211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6379 ,protection_group_id: -5379, protection_element_id:-5379], primaryKey: false);
      insert('organizations', [ id: 105365, nci_institute_code: "NJ212", name: "Kathleen Murphy MD PA", city: "Westfield", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ212",GROUP_DESC:"NJ212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6380 ,protection_group_id: -5380, protection_element_id:-5380], primaryKey: false);
      insert('organizations', [ id: 105366, nci_institute_code: "NJ213", name: "Harold M Bruck MD PA", city: "Glen Rock", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ213",GROUP_DESC:"NJ213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6381 ,protection_group_id: -5381, protection_element_id:-5381], primaryKey: false);
      insert('organizations', [ id: 105367, nci_institute_code: "NJ214", name: "Forte Schleider and Attas PA", city: "Englewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ214",GROUP_DESC:"NJ214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6382 ,protection_group_id: -5382, protection_element_id:-5382], primaryKey: false);
      insert('organizations', [ id: 105368, nci_institute_code: "NJ215", name: "Tepper, Erwin MD (office)", city: "Manasquan", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ215",GROUP_DESC:"NJ215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6383 ,protection_group_id: -5383, protection_element_id:-5383], primaryKey: false);
      insert('organizations', [ id: 105369, nci_institute_code: "NJ216", name: "Shore Hematology Oncology", city: "Lakewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ216",GROUP_DESC:"NJ216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6384 ,protection_group_id: -5384, protection_element_id:-5384], primaryKey: false);
      insert('organizations', [ id: 105370, nci_institute_code: "NJ217", name: "Rose Medical Clinic", city: "Manalapan", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ217",GROUP_DESC:"NJ217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6385 ,protection_group_id: -5385, protection_element_id:-5385], primaryKey: false);
      insert('organizations', [ id: 105371, nci_institute_code: "NJ219", name: "Middlesex Oncology PA", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ219",GROUP_DESC:"NJ219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6386 ,protection_group_id: -5386, protection_element_id:-5386], primaryKey: false);
      insert('organizations', [ id: 105372, nci_institute_code: "NJ220", name: "Medical Diagnostic Associates PA", city: "Summit", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ220",GROUP_DESC:"NJ220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6387 ,protection_group_id: -5387, protection_element_id:-5387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 105373, nci_institute_code: "NJ221", name: "Hematology-Oncology Associates of Northern New Jersey- Morristown Office", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ221",GROUP_DESC:"NJ221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6388 ,protection_group_id: -5388, protection_element_id:-5388], primaryKey: false);
      insert('organizations', [ id: 105374, nci_institute_code: "NJ222", name: "Hematology and Oncology Associates", city: "Mount Holly", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ222",GROUP_DESC:"NJ222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6389 ,protection_group_id: -5389, protection_element_id:-5389], primaryKey: false);
      insert('organizations', [ id: 105375, nci_institute_code: "NJ223", name: "Cornel Mircea MD PA", city: "Glenridge", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ223",GROUP_DESC:"NJ223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6390 ,protection_group_id: -5390, protection_element_id:-5390], primaryKey: false);
      insert('organizations', [ id: 105376, nci_institute_code: "NJ224", name: "Hematology-Oncology at Somerville LLC", city: "Somerville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ224",GROUP_DESC:"NJ224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6391 ,protection_group_id: -5391, protection_element_id:-5391], primaryKey: false);
      insert('organizations', [ id: 105377, nci_institute_code: "NJ225", name: "Associated Radiologists PA", city: "Somerville", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ225",GROUP_DESC:"NJ225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6392 ,protection_group_id: -5392, protection_element_id:-5392], primaryKey: false);
      insert('organizations', [ id: 105378, nci_institute_code: "NM001", name: "Lovelace Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM001",GROUP_DESC:"NM001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6393 ,protection_group_id: -5393, protection_element_id:-5393], primaryKey: false);
      insert('organizations', [ id: 105379, nci_institute_code: "NM002", name: "Bernalillo County Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM002",GROUP_DESC:"NM002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6394 ,protection_group_id: -5394, protection_element_id:-5394], primaryKey: false);
      insert('organizations', [ id: 105380, nci_institute_code: "NM003", name: "Albuquerque Veterans Administration Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM003",GROUP_DESC:"NM003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6395 ,protection_group_id: -5395, protection_element_id:-5395], primaryKey: false);
      insert('organizations', [ id: 105381, nci_institute_code: "NM004", name: "University of New Mexico", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM004",GROUP_DESC:"NM004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6396 ,protection_group_id: -5396, protection_element_id:-5396], primaryKey: false);
      insert('organizations', [ id: 105382, nci_institute_code: "NM005", name: "Presbyterian Kaseman Hospital", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM005",GROUP_DESC:"NM005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6397 ,protection_group_id: -5397, protection_element_id:-5397], primaryKey: false);
      insert('organizations', [ id: 105383, nci_institute_code: "NM006", name: "Cancer Treatment Center", city: "Farmington", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM006",GROUP_DESC:"NM006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6398 ,protection_group_id: -5398, protection_element_id:-5398], primaryKey: false);
      insert('organizations', [ id: 105384, nci_institute_code: "NM007", name: "Saint Vincent Hospital", city: "Santa Fe", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM007",GROUP_DESC:"NM007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6399 ,protection_group_id: -5399, protection_element_id:-5399], primaryKey: false);
      insert('organizations', [ id: 105385, nci_institute_code: "NM008", name: "Guadalupe Medical Center", city: "Carlsbad", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM008",GROUP_DESC:"NM008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6400 ,protection_group_id: -5400, protection_element_id:-5400], primaryKey: false);
      insert('organizations', [ id: 105386, nci_institute_code: "NM009", name: "San Juan Regional Medical Center", city: "San Juan", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM009",GROUP_DESC:"NM009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6401 ,protection_group_id: -5401, protection_element_id:-5401], primaryKey: false);
      insert('organizations', [ id: 105387, nci_institute_code: "NM010", name: "New Mexico Oncology Hematology Consultants", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM010",GROUP_DESC:"NM010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6402 ,protection_group_id: -5402, protection_element_id:-5402], primaryKey: false);
      insert('organizations', [ id: 105388, nci_institute_code: "NM011", name: "Lovelace Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM011",GROUP_DESC:"NM011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6403 ,protection_group_id: -5403, protection_element_id:-5403], primaryKey: false);
      insert('organizations', [ id: 105389, nci_institute_code: "NM012", name: "Miner's Colfax Medical Center", city: "Raton", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM012",GROUP_DESC:"NM012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6404 ,protection_group_id: -5404, protection_element_id:-5404], primaryKey: false);
      insert('organizations', [ id: 105390, nci_institute_code: "NM013", name: "Memorial Medical Center", city: "Las Cruces", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM013",GROUP_DESC:"NM013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6405 ,protection_group_id: -5405, protection_element_id:-5405], primaryKey: false);
      insert('organizations', [ id: 105391, nci_institute_code: "NM014", name: "Eastern New Mexcio Medical Center", city: "Roswell", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM014",GROUP_DESC:"NM014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6406 ,protection_group_id: -5406, protection_element_id:-5406], primaryKey: false);
      insert('organizations', [ id: 105392, nci_institute_code: "NM015", name: "Albuquerque Surgical Group", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM015",GROUP_DESC:"NM015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6407 ,protection_group_id: -5407, protection_element_id:-5407], primaryKey: false);
      insert('organizations', [ id: 105393, nci_institute_code: "NM016", name: "Southern New Mexico Cancer Center", city: "Las Cruces", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM016",GROUP_DESC:"NM016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6408 ,protection_group_id: -5408, protection_element_id:-5408], primaryKey: false);
      insert('organizations', [ id: 105394, nci_institute_code: "NM017", name: "Hematology Oncology Associates", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM017",GROUP_DESC:"NM017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6409 ,protection_group_id: -5409, protection_element_id:-5409], primaryKey: false);
      insert('organizations', [ id: 105395, nci_institute_code: "NM018", name: "Southeastern New Mexico Hematology and Oncology", city: "Roswell", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM018",GROUP_DESC:"NM018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6410 ,protection_group_id: -5410, protection_element_id:-5410], primaryKey: false);
      insert('organizations', [ id: 105396, nci_institute_code: "NM019", name: "Otero Oncology", city: "Alamogordo", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM019",GROUP_DESC:"NM019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6411 ,protection_group_id: -5411, protection_element_id:-5411], primaryKey: false);
      insert('organizations', [ id: 105397, nci_institute_code: "NM020", name: "Southwest Gynecologic Oncology Associates, Inc", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM020",GROUP_DESC:"NM020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6412 ,protection_group_id: -5412, protection_element_id:-5412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 105398, nci_institute_code: "NM022", name: "New Mexico Cancer Care Associates", city: "Santa Fe", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM022",GROUP_DESC:"NM022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6413 ,protection_group_id: -5413, protection_element_id:-5413], primaryKey: false);
      insert('organizations', [ id: 105399, nci_institute_code: "NM023", name: "Urology Group of New Mexico, PC", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM023",GROUP_DESC:"NM023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6414 ,protection_group_id: -5414, protection_element_id:-5414], primaryKey: false);
      insert('organizations', [ id: 105400, nci_institute_code: "NM024", name: "Albuquerque Regional Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM024",GROUP_DESC:"NM024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6415 ,protection_group_id: -5415, protection_element_id:-5415], primaryKey: false);
      insert('organizations', [ id: 105401, nci_institute_code: "NM025", name: "Lovelace Sandia Health System", city: "Rio Rancho", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM025",GROUP_DESC:"NM025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6416 ,protection_group_id: -5416, protection_element_id:-5416], primaryKey: false);
      insert('organizations', [ id: 105402, nci_institute_code: "NM026", name: "New Mexico Cancer Care Alliance", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM026",GROUP_DESC:"NM026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6417 ,protection_group_id: -5417, protection_element_id:-5417], primaryKey: false);
      insert('organizations', [ id: 105403, nci_institute_code: "NM027", name: "New Hope Cancer Center", city: "Las Cruces", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM027",GROUP_DESC:"NM027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6418 ,protection_group_id: -5418, protection_element_id:-5418], primaryKey: false);
      insert('organizations', [ id: 105404, nci_institute_code: "NM028", name: "San Juan Oncology Associates", city: "Farmington", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM028",GROUP_DESC:"NM028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6419 ,protection_group_id: -5419, protection_element_id:-5419], primaryKey: false);
      insert('organizations', [ id: 105405, nci_institute_code: "NM029", name: "Albuquerque Urology Associates PA", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM029",GROUP_DESC:"NM029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6420 ,protection_group_id: -5420, protection_element_id:-5420], primaryKey: false);
      insert('organizations', [ id: 105406, nci_institute_code: "NM031", name: "Cancer Institute of New Mexico", city: "Santa Fe", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM031",GROUP_DESC:"NM031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6421 ,protection_group_id: -5421, protection_element_id:-5421], primaryKey: false);
      insert('organizations', [ id: 105407, nci_institute_code: "NM032", name: "Cancer Research and Treatment Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM032",GROUP_DESC:"NM032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6422 ,protection_group_id: -5422, protection_element_id:-5422], primaryKey: false);
      insert('organizations', [ id: 105408, nci_institute_code: "NM033", name: "Radiation Oncology Associates", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM033",GROUP_DESC:"NM033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6423 ,protection_group_id: -5423, protection_element_id:-5423], primaryKey: false);
      insert('organizations', [ id: 105409, nci_institute_code: "NM034", name: "Los Alamos Medical Center", city: "Los Alamos", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM034",GROUP_DESC:"NM034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6424 ,protection_group_id: -5424, protection_element_id:-5424], primaryKey: false);
      insert('organizations', [ id: 105410, nci_institute_code: "NM035", name: "William C Abbott MD PC", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM035",GROUP_DESC:"NM035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6425 ,protection_group_id: -5425, protection_element_id:-5425], primaryKey: false);
      insert('organizations', [ id: 105411, nci_institute_code: "NM036", name: "New Mexico State University", city: "Las Cruces", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM036",GROUP_DESC:"NM036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6426 ,protection_group_id: -5426, protection_element_id:-5426], primaryKey: false);
      insert('organizations', [ id: 105412, nci_institute_code: "NM037", name: "Susan Seedman MD FACS PC", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM037",GROUP_DESC:"NM037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6427 ,protection_group_id: -5427, protection_element_id:-5427], primaryKey: false);
      insert('organizations', [ id: 105413, nci_institute_code: "NM038", name: "Smith, Linda MD (office)", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM038",GROUP_DESC:"NM038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6428 ,protection_group_id: -5428, protection_element_id:-5428], primaryKey: false);
      insert('organizations', [ id: 105414, nci_institute_code: "NPCTG", name: "National Prostatic Cancer Treatment Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NPCTG",GROUP_DESC:"NPCTG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NPCTG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NPCTG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NPCTG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6429 ,protection_group_id: -5429, protection_element_id:-5429], primaryKey: false);
      insert('organizations', [ id: 105415, nci_institute_code: "NV001", name: "Southwest Medical Association", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV001",GROUP_DESC:"NV001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6430 ,protection_group_id: -5430, protection_element_id:-5430], primaryKey: false);
      insert('organizations', [ id: 105416, nci_institute_code: "NV002", name: "Southern Nevada Memorial Hospital", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV002",GROUP_DESC:"NV002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6431 ,protection_group_id: -5431, protection_element_id:-5431], primaryKey: false);
      insert('organizations', [ id: 105417, nci_institute_code: "NV003", name: "University of Nevada Medical School", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV003",GROUP_DESC:"NV003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6432 ,protection_group_id: -5432, protection_element_id:-5432], primaryKey: false);
      insert('organizations', [ id: 105418, nci_institute_code: "NV004", name: "Sunrise Hospital and Medical Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV004",GROUP_DESC:"NV004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6433 ,protection_group_id: -5433, protection_element_id:-5433], primaryKey: false);
      insert('organizations', [ id: 105419, nci_institute_code: "NV005", name: "Renown Regional Medical Center", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV005",GROUP_DESC:"NV005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6434 ,protection_group_id: -5434, protection_element_id:-5434], primaryKey: false);
      insert('organizations', [ id: 105420, nci_institute_code: "NV006", name: "Medic House", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV006",GROUP_DESC:"NV006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6435 ,protection_group_id: -5435, protection_element_id:-5435], primaryKey: false);
      insert('organizations', [ id: 105421, nci_institute_code: "NV007", name: "Northern Nevada Cancer Council", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV007",GROUP_DESC:"NV007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6436 ,protection_group_id: -5436, protection_element_id:-5436], primaryKey: false);
      insert('organizations', [ id: 105422, nci_institute_code: "NV008", name: "University of Nevada at Reno Veterans Affairs Medical Center", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV008",GROUP_DESC:"NV008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6437 ,protection_group_id: -5437, protection_element_id:-5437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 105423, nci_institute_code: "NV009", name: "University of Nevada", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV009",GROUP_DESC:"NV009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6438 ,protection_group_id: -5438, protection_element_id:-5438], primaryKey: false);
      insert('organizations', [ id: 105424, nci_institute_code: "NV011", name: "Saint Mary's Regional Medical Center", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV011",GROUP_DESC:"NV011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6439 ,protection_group_id: -5439, protection_element_id:-5439], primaryKey: false);
      insert('organizations', [ id: 105425, nci_institute_code: "NV012", name: "Nevada Cancer Research Foundation CCOP", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV012",GROUP_DESC:"NV012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6440 ,protection_group_id: -5440, protection_element_id:-5440], primaryKey: false);
      insert('organizations', [ id: 105426, nci_institute_code: "NV013", name: "Saint Rose Dominican Hospital", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV013",GROUP_DESC:"NV013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6441 ,protection_group_id: -5441, protection_element_id:-5441], primaryKey: false);
      insert('organizations', [ id: 105427, nci_institute_code: "NV014", name: "Nevada Breast Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV014",GROUP_DESC:"NV014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6442 ,protection_group_id: -5442, protection_element_id:-5442], primaryKey: false);
      insert('organizations', [ id: 105428, nci_institute_code: "NV016", name: "Valley Hospital Medical Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV016",GROUP_DESC:"NV016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6443 ,protection_group_id: -5443, protection_element_id:-5443], primaryKey: false);
      insert('organizations', [ id: 105429, nci_institute_code: "NV017", name: "University Medical Center of Southern Nevada", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV017",GROUP_DESC:"NV017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6444 ,protection_group_id: -5444, protection_element_id:-5444], primaryKey: false);
      insert('organizations', [ id: 105430, nci_institute_code: "NV018", name: "Cancer Hematology Center of Nevada", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV018",GROUP_DESC:"NV018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6445 ,protection_group_id: -5445, protection_element_id:-5445], primaryKey: false);
      insert('organizations', [ id: 105431, nci_institute_code: "NV019", name: "Nevada Cancer Center - Central", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV019",GROUP_DESC:"NV019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6446 ,protection_group_id: -5446, protection_element_id:-5446], primaryKey: false);
      insert('organizations', [ id: 105432, nci_institute_code: "NV020", name: "Alpine Hematology-Oncology", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV020",GROUP_DESC:"NV020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6447 ,protection_group_id: -5447, protection_element_id:-5447], primaryKey: false);
      insert('organizations', [ id: 105433, nci_institute_code: "NV021", name: "Hematology/Oncology", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV021",GROUP_DESC:"NV021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6448 ,protection_group_id: -5448, protection_element_id:-5448], primaryKey: false);
      insert('organizations', [ id: 105434, nci_institute_code: "NV022", name: "Nellis Federal Hospital", city: "Nellis Afb", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV022",GROUP_DESC:"NV022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6449 ,protection_group_id: -5449, protection_element_id:-5449], primaryKey: false);
      insert('organizations', [ id: 105435, nci_institute_code: "NV023", name: "Nevada Medical Group", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV023",GROUP_DESC:"NV023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6450 ,protection_group_id: -5450, protection_element_id:-5450], primaryKey: false);
      insert('organizations', [ id: 105436, nci_institute_code: "NV024", name: "Cancer and Blood Specialists of Nevada", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV024",GROUP_DESC:"NV024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6451 ,protection_group_id: -5451, protection_element_id:-5451], primaryKey: false);
      insert('organizations', [ id: 105437, nci_institute_code: "NV025", name: "Veterans Administration Sourthern Nevada Healthcare System", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV025",GROUP_DESC:"NV025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6452 ,protection_group_id: -5452, protection_element_id:-5452], primaryKey: false);
      insert('organizations', [ id: 105438, nci_institute_code: "NV026", name: "Nevada Radiation Therapy Regional Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV026",GROUP_DESC:"NV026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6453 ,protection_group_id: -5453, protection_element_id:-5453], primaryKey: false);
      insert('organizations', [ id: 105439, nci_institute_code: "NV027", name: "Columbia Sunrise Mountainview Hospital", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV027",GROUP_DESC:"NV027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6454 ,protection_group_id: -5454, protection_element_id:-5454], primaryKey: false);
      insert('organizations', [ id: 105440, nci_institute_code: "NV028", name: "Comprehensive Cancer Centers of Nevada - Central Valley", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV028",GROUP_DESC:"NV028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6455 ,protection_group_id: -5455, protection_element_id:-5455], primaryKey: false);
      insert('organizations', [ id: 105441, nci_institute_code: "NV029", name: "Women's Cancer Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV029",GROUP_DESC:"NV029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6456 ,protection_group_id: -5456, protection_element_id:-5456], primaryKey: false);
      insert('organizations', [ id: 105442, nci_institute_code: "NV031", name: "Michael O'Callaghan Federal Hospital", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV031",GROUP_DESC:"NV031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6457 ,protection_group_id: -5457, protection_element_id:-5457], primaryKey: false);
      insert('organizations', [ id: 105443, nci_institute_code: "NV032", name: "Cancer and Blood Specialists", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV032",GROUP_DESC:"NV032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6458 ,protection_group_id: -5458, protection_element_id:-5458], primaryKey: false);
      insert('organizations', [ id: 105444, nci_institute_code: "NV033", name: "Las Vegas Cancer Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV033",GROUP_DESC:"NV033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6459 ,protection_group_id: -5459, protection_element_id:-5459], primaryKey: false);
      insert('organizations', [ id: 105445, nci_institute_code: "NV034", name: "Childrens Ctr for Cancer/Blood Dis.", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV034",GROUP_DESC:"NV034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6460 ,protection_group_id: -5460, protection_element_id:-5460], primaryKey: false);
      insert('organizations', [ id: 105446, nci_institute_code: "NV035", name: "MedSchool Associates North", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV035",GROUP_DESC:"NV035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6461 ,protection_group_id: -5461, protection_element_id:-5461], primaryKey: false);
      insert('organizations', [ id: 105447, nci_institute_code: "NV036", name: "Lake Mead Hospital", city: "North Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV036",GROUP_DESC:"NV036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6462 ,protection_group_id: -5462, protection_element_id:-5462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 105448, nci_institute_code: "NV037", name: "Nevada Radiation Oncology Centers", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV037",GROUP_DESC:"NV037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6463 ,protection_group_id: -5463, protection_element_id:-5463], primaryKey: false);
      insert('organizations', [ id: 105449, nci_institute_code: "NV038", name: "Radiation Oncology Centers of Las Vegas", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV038",GROUP_DESC:"NV038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6464 ,protection_group_id: -5464, protection_element_id:-5464], primaryKey: false);
      insert('organizations', [ id: 105450, nci_institute_code: "NV039", name: "Nevada Cancer Institute", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV039",GROUP_DESC:"NV039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6465 ,protection_group_id: -5465, protection_element_id:-5465], primaryKey: false);
      insert('organizations', [ id: 105451, nci_institute_code: "NV040", name: "Women's Cancer Center at Washoe", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV040",GROUP_DESC:"NV040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6466 ,protection_group_id: -5466, protection_element_id:-5466], primaryKey: false);
      insert('organizations', [ id: 105452, nci_institute_code: "NV041", name: "Veteran Affairs Southern Nevada Healthcare System - Warehouse Transport Center", city: "North Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV041",GROUP_DESC:"NV041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6467 ,protection_group_id: -5467, protection_element_id:-5467], primaryKey: false);
      insert('organizations', [ id: 105453, nci_institute_code: "NV042", name: "Cancer Consultants", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV042",GROUP_DESC:"NV042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6468 ,protection_group_id: -5468, protection_element_id:-5468], primaryKey: false);
      insert('organizations', [ id: 105454, nci_institute_code: "NV043", name: "Comprehensive Cancer Centers of Nevada", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV043",GROUP_DESC:"NV043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6469 ,protection_group_id: -5469, protection_element_id:-5469], primaryKey: false);
      insert('organizations', [ id: 105455, nci_institute_code: "NV044", name: "21st Century Oncology", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV044",GROUP_DESC:"NV044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6470 ,protection_group_id: -5470, protection_element_id:-5470], primaryKey: false);
      insert('organizations', [ id: 105456, nci_institute_code: "NV045", name: "Women's Cancer Center at University Medical Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV045",GROUP_DESC:"NV045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6471 ,protection_group_id: -5471, protection_element_id:-5471], primaryKey: false);
      insert('organizations', [ id: 105457, nci_institute_code: "NV046", name: "Reno Oncology Consultants", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV046",GROUP_DESC:"NV046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6472 ,protection_group_id: -5472, protection_element_id:-5472], primaryKey: false);
      insert('organizations', [ id: 105458, nci_institute_code: "NV047", name: "Capitol Oncology", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV047",GROUP_DESC:"NV047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6473 ,protection_group_id: -5473, protection_element_id:-5473], primaryKey: false);
      insert('organizations', [ id: 105459, nci_institute_code: "NV048", name: "Carson-Tahoe Specialty Medical Center", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV048",GROUP_DESC:"NV048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6474 ,protection_group_id: -5474, protection_element_id:-5474], primaryKey: false);
      insert('organizations', [ id: 105460, nci_institute_code: "NV049", name: "Women's Cancer Center - Las Vegas", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV049",GROUP_DESC:"NV049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6475 ,protection_group_id: -5475, protection_element_id:-5475], primaryKey: false);
      insert('organizations', [ id: 105461, nci_institute_code: "NV050", name: "Sierra Nevada Oncology Care", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV050",GROUP_DESC:"NV050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6476 ,protection_group_id: -5476, protection_element_id:-5476], primaryKey: false);
      insert('organizations', [ id: 105462, nci_institute_code: "NV051", name: "Radiation Oncology Associates", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV051",GROUP_DESC:"NV051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6477 ,protection_group_id: -5477, protection_element_id:-5477], primaryKey: false);
      insert('organizations', [ id: 105463, nci_institute_code: "NV052", name: "Cancer Therapy and Integrative Medicine", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV052",GROUP_DESC:"NV052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6478 ,protection_group_id: -5478, protection_element_id:-5478], primaryKey: false);
      insert('organizations', [ id: 105464, nci_institute_code: "NV053", name: "Radiation Oncology Centers of Las Vegas - Tonopah", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV053",GROUP_DESC:"NV053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6479 ,protection_group_id: -5479, protection_element_id:-5479], primaryKey: false);
      insert('organizations', [ id: 105465, nci_institute_code: "NV054", name: "Comprehensive Center Centers of Nevada - Northwest", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV054",GROUP_DESC:"NV054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6480 ,protection_group_id: -5480, protection_element_id:-5480], primaryKey: false);
      insert('organizations', [ id: 105466, nci_institute_code: "NV055", name: "Center of Hope at Renown Medical Center", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV055",GROUP_DESC:"NV055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6481 ,protection_group_id: -5481, protection_element_id:-5481], primaryKey: false);
      insert('organizations', [ id: 105467, nci_institute_code: "NV056", name: "Carson Tahoe Regional Medical Center", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV056",GROUP_DESC:"NV056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6482 ,protection_group_id: -5482, protection_element_id:-5482], primaryKey: false);
      insert('organizations', [ id: 105468, nci_institute_code: "NV058", name: "Comprehensive Cancer Centers of Nevada - Henderson", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV058",GROUP_DESC:"NV058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6483 ,protection_group_id: -5483, protection_element_id:-5483], primaryKey: false);
      insert('organizations', [ id: 105469, nci_institute_code: "NV059", name: "Arnold Wax MD Ltd", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV059",GROUP_DESC:"NV059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6484 ,protection_group_id: -5484, protection_element_id:-5484], primaryKey: false);
      insert('organizations', [ id: 105470, nci_institute_code: "NV060", name: "Red Rock Medical Group", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV060",GROUP_DESC:"NV060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6485 ,protection_group_id: -5485, protection_element_id:-5485], primaryKey: false);
      insert('organizations', [ id: 105471, nci_institute_code: "NV061", name: "Nevada Cancer Center - Northwest", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV061",GROUP_DESC:"NV061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6486 ,protection_group_id: -5486, protection_element_id:-5486], primaryKey: false);
      insert('organizations', [ id: 105472, nci_institute_code: "NY001", name: "U. S. Naval Medical Research", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY001",GROUP_DESC:"NY001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6487 ,protection_group_id: -5487, protection_element_id:-5487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 105473, nci_institute_code: "NY002", name: "French and Polyclinic Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY002",GROUP_DESC:"NY002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6488 ,protection_group_id: -5488, protection_element_id:-5488], primaryKey: false);
      insert('organizations', [ id: 105474, nci_institute_code: "NY003", name: "Beth Israel Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY003",GROUP_DESC:"NY003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6489 ,protection_group_id: -5489, protection_element_id:-5489], primaryKey: false);
      insert('organizations', [ id: 105475, nci_institute_code: "NY004", name: "New Gouverneur Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY004",GROUP_DESC:"NY004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6490 ,protection_group_id: -5490, protection_element_id:-5490], primaryKey: false);
      insert('organizations', [ id: 105476, nci_institute_code: "NY005", name: "Columbus Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY005",GROUP_DESC:"NY005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6491 ,protection_group_id: -5491, protection_element_id:-5491], primaryKey: false);
      insert('organizations', [ id: 105477, nci_institute_code: "NY006", name: "Cabrini Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY006",GROUP_DESC:"NY006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6492 ,protection_group_id: -5492, protection_element_id:-5492], primaryKey: false);
      insert('organizations', [ id: 105478, nci_institute_code: "NY007", name: "New York Department Veterans Affairs Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY007",GROUP_DESC:"NY007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6493 ,protection_group_id: -5493, protection_element_id:-5493], primaryKey: false);
      insert('organizations', [ id: 105479, nci_institute_code: "NY008", name: "Neurological Institute, New York, NY", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY008",GROUP_DESC:"NY008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6494 ,protection_group_id: -5494, protection_element_id:-5494], primaryKey: false);
      insert('organizations', [ id: 105480, nci_institute_code: "NY009", name: "Saint Vincent's Hospital and Medical Center of New York", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY009",GROUP_DESC:"NY009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6495 ,protection_group_id: -5495, protection_element_id:-5495], primaryKey: false);
      insert('organizations', [ id: 105481, nci_institute_code: "NY010", name: "Bellevue Hospital Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY010",GROUP_DESC:"NY010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6496 ,protection_group_id: -5496, protection_element_id:-5496], primaryKey: false);
      insert('organizations', [ id: 105482, nci_institute_code: "NY011", name: "New York University Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY011",GROUP_DESC:"NY011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6497 ,protection_group_id: -5497, protection_element_id:-5497], primaryKey: false);
      insert('organizations', [ id: 105483, nci_institute_code: "NY012", name: "Saint Luke's Roosevelt Hospital Center - Saint Luke's Division", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY012",GROUP_DESC:"NY012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6498 ,protection_group_id: -5498, protection_element_id:-5498], primaryKey: false);
      insert('organizations', [ id: 105484, nci_institute_code: "NY014", name: "Saint Clares Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY014",GROUP_DESC:"NY014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6499 ,protection_group_id: -5499, protection_element_id:-5499], primaryKey: false);
      insert('organizations', [ id: 105485, nci_institute_code: "NY015", name: "Lenox Hill Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY015",GROUP_DESC:"NY015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6500 ,protection_group_id: -5500, protection_element_id:-5500], primaryKey: false);
      insert('organizations', [ id: 105486, nci_institute_code: "NY016", name: "Memorial Sloan Kettering Cancer Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY016",GROUP_DESC:"NY016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6501 ,protection_group_id: -5501, protection_element_id:-5501], primaryKey: false);
      insert('organizations', [ id: 105487, nci_institute_code: "NY017", name: "Pop Council Rockefeller University", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY017",GROUP_DESC:"NY017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6502 ,protection_group_id: -5502, protection_element_id:-5502], primaryKey: false);
      insert('organizations', [ id: 105488, nci_institute_code: "NY018", name: "Weill Medical College of Cornell University", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY018",GROUP_DESC:"NY018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6503 ,protection_group_id: -5503, protection_element_id:-5503], primaryKey: false);
      insert('organizations', [ id: 105489, nci_institute_code: "NY019", name: "Rockefeller University", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY019",GROUP_DESC:"NY019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6504 ,protection_group_id: -5504, protection_element_id:-5504], primaryKey: false);
      insert('organizations', [ id: 105490, nci_institute_code: "NY020", name: "North Shore Hem/Oncology Associates Incorporated", city: "Port Jefferson", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY020",GROUP_DESC:"NY020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6505 ,protection_group_id: -5505, protection_element_id:-5505], primaryKey: false);
      insert('organizations', [ id: 105491, nci_institute_code: "NY021", name: "Mount Sinai Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY021",GROUP_DESC:"NY021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6506 ,protection_group_id: -5506, protection_element_id:-5506], primaryKey: false);
      insert('organizations', [ id: 105492, nci_institute_code: "NY022", name: "Flower & Fifth Avenue Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY022",GROUP_DESC:"NY022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6507 ,protection_group_id: -5507, protection_element_id:-5507], primaryKey: false);
      insert('organizations', [ id: 105493, nci_institute_code: "NY023", name: "New York Medical College-Metro Hospital Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY023",GROUP_DESC:"NY023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6508 ,protection_group_id: -5508, protection_element_id:-5508], primaryKey: false);
      insert('organizations', [ id: 105494, nci_institute_code: "NY024", name: "Columbia University Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY024",GROUP_DESC:"NY024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6509 ,protection_group_id: -5509, protection_element_id:-5509], primaryKey: false);
      insert('organizations', [ id: 105495, nci_institute_code: "NY025", name: "Frances Delafield Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY025",GROUP_DESC:"NY025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6510 ,protection_group_id: -5510, protection_element_id:-5510], primaryKey: false);
      insert('organizations', [ id: 105496, nci_institute_code: "NY026", name: "Children's Hospital of New York Presbyterian", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY026",GROUP_DESC:"NY026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6511 ,protection_group_id: -5511, protection_element_id:-5511], primaryKey: false);
      insert('organizations', [ id: 105497, nci_institute_code: "NY028", name: "Oswego Hospital", city: "Oswego", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY028",GROUP_DESC:"NY028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6512 ,protection_group_id: -5512, protection_element_id:-5512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 105498, nci_institute_code: "NY029", name: "North General Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY029",GROUP_DESC:"NY029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6513 ,protection_group_id: -5513, protection_element_id:-5513], primaryKey: false);
      insert('organizations', [ id: 105499, nci_institute_code: "NY030", name: "Hospital for Joint Diseases and Medical Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY030",GROUP_DESC:"NY030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6514 ,protection_group_id: -5514, protection_element_id:-5514], primaryKey: false);
      insert('organizations', [ id: 105500, nci_institute_code: "NY031", name: "Harlem Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY031",GROUP_DESC:"NY031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6515 ,protection_group_id: -5515, protection_element_id:-5515], primaryKey: false);
      insert('organizations', [ id: 105501, nci_institute_code: "NY032", name: "Jewish Memorial Hospital, New York", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY032",GROUP_DESC:"NY032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6516 ,protection_group_id: -5516, protection_element_id:-5516], primaryKey: false);
      insert('organizations', [ id: 105502, nci_institute_code: "NY033", name: "Air India, 32nd Floor", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY033",GROUP_DESC:"NY033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6517 ,protection_group_id: -5517, protection_element_id:-5517], primaryKey: false);
      insert('organizations', [ id: 105503, nci_institute_code: "NY034", name: "U.S. Public Health Service Hospital", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY034",GROUP_DESC:"NY034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6518 ,protection_group_id: -5518, protection_element_id:-5518], primaryKey: false);
      insert('organizations', [ id: 105504, nci_institute_code: "NY035", name: "Staten Island University Hospital", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY035",GROUP_DESC:"NY035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6519 ,protection_group_id: -5519, protection_element_id:-5519], primaryKey: false);
      insert('organizations', [ id: 105505, nci_institute_code: "NY036", name: "Richmond University Medical Center", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY036",GROUP_DESC:"NY036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6520 ,protection_group_id: -5520, protection_element_id:-5520], primaryKey: false);
      insert('organizations', [ id: 105506, nci_institute_code: "NY037", name: "Lincoln Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY037",GROUP_DESC:"NY037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6521 ,protection_group_id: -5521, protection_element_id:-5521], primaryKey: false);
      insert('organizations', [ id: 105507, nci_institute_code: "NY038", name: "North Central Bronx Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY038",GROUP_DESC:"NY038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6522 ,protection_group_id: -5522, protection_element_id:-5522], primaryKey: false);
      insert('organizations', [ id: 105508, nci_institute_code: "NY039", name: "Bronx-Lebanon Hospital Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY039",GROUP_DESC:"NY039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6523 ,protection_group_id: -5523, protection_element_id:-5523], primaryKey: false);
      insert('organizations', [ id: 105509, nci_institute_code: "NY040", name: "Calvary Hospital, Inc.", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY040",GROUP_DESC:"NY040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6524 ,protection_group_id: -5524, protection_element_id:-5524], primaryKey: false);
      insert('organizations', [ id: 105510, nci_institute_code: "NY041", name: "Bronx Municipal Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY041",GROUP_DESC:"NY041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6525 ,protection_group_id: -5525, protection_element_id:-5525], primaryKey: false);
      insert('organizations', [ id: 105511, nci_institute_code: "NY042", name: "Jacobi Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY042",GROUP_DESC:"NY042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6526 ,protection_group_id: -5526, protection_element_id:-5526], primaryKey: false);
      insert('organizations', [ id: 105512, nci_institute_code: "NY043", name: "Albert Einstein College of Medicine", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY043",GROUP_DESC:"NY043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6527 ,protection_group_id: -5527, protection_element_id:-5527], primaryKey: false);
      insert('organizations', [ id: 105513, nci_institute_code: "NY044", name: "Misercordia Hospital Medical Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY044",GROUP_DESC:"NY044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6528 ,protection_group_id: -5528, protection_element_id:-5528], primaryKey: false);
      insert('organizations', [ id: 105514, nci_institute_code: "NY045", name: "Montefiore Medical Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY045",GROUP_DESC:"NY045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6529 ,protection_group_id: -5529, protection_element_id:-5529], primaryKey: false);
      insert('organizations', [ id: 105515, nci_institute_code: "NY046", name: "Park Ridge Hospital", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY046",GROUP_DESC:"NY046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6530 ,protection_group_id: -5530, protection_element_id:-5530], primaryKey: false);
      insert('organizations', [ id: 105516, nci_institute_code: "NY047", name: "North Westchester Hospital", city: "Mount Kisco", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY047",GROUP_DESC:"NY047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6531 ,protection_group_id: -5531, protection_element_id:-5531], primaryKey: false);
      insert('organizations', [ id: 105517, nci_institute_code: "NY048", name: "Mount Kisco Medical Group", city: "Mount Kisco", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY048",GROUP_DESC:"NY048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6532 ,protection_group_id: -5532, protection_element_id:-5532], primaryKey: false);
      insert('organizations', [ id: 105518, nci_institute_code: "NY050", name: "United Hospital", city: "Port Chester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY050",GROUP_DESC:"NY050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6533 ,protection_group_id: -5533, protection_element_id:-5533], primaryKey: false);
      insert('organizations', [ id: 105519, nci_institute_code: "NY052", name: "Grasslands Hospital", city: "Valhalla", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY052",GROUP_DESC:"NY052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6534 ,protection_group_id: -5534, protection_element_id:-5534], primaryKey: false);
      insert('organizations', [ id: 105520, nci_institute_code: "NY053", name: "New York Medical College", city: "Valhalla", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY053",GROUP_DESC:"NY053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6535 ,protection_group_id: -5535, protection_element_id:-5535], primaryKey: false);
      insert('organizations', [ id: 105521, nci_institute_code: "NY055", name: "Auburn Memorial Hospital", city: "Auburn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY055",GROUP_DESC:"NY055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6536 ,protection_group_id: -5536, protection_element_id:-5536], primaryKey: false);
      insert('organizations', [ id: 105522, nci_institute_code: "NY056", name: "White Plains Hospital Center", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY056",GROUP_DESC:"NY056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6537 ,protection_group_id: -5537, protection_element_id:-5537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 105523, nci_institute_code: "NY057", name: "Saint John's Hospital", city: "Yonkers", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY057",GROUP_DESC:"NY057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6538 ,protection_group_id: -5538, protection_element_id:-5538], primaryKey: false);
      insert('organizations', [ id: 105524, nci_institute_code: "NY058", name: "Lawrence Hospital", city: "Bronxville", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY058",GROUP_DESC:"NY058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6539 ,protection_group_id: -5539, protection_element_id:-5539], primaryKey: false);
      insert('organizations', [ id: 105525, nci_institute_code: "NY059", name: "New Rochelle Hospital", city: "New  Rochelle", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY059",GROUP_DESC:"NY059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6540 ,protection_group_id: -5540, protection_element_id:-5540], primaryKey: false);
      insert('organizations', [ id: 105526, nci_institute_code: "NY061", name: "Orange Regional Medical Center", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY061",GROUP_DESC:"NY061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6541 ,protection_group_id: -5541, protection_element_id:-5541], primaryKey: false);
      insert('organizations', [ id: 105527, nci_institute_code: "NY063", name: "U.S. Army Hospital", city: "West Point", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY063",GROUP_DESC:"NY063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6542 ,protection_group_id: -5542, protection_element_id:-5542], primaryKey: false);
      insert('organizations', [ id: 105528, nci_institute_code: "NY064", name: "North Shore University Hospital", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY064",GROUP_DESC:"NY064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6543 ,protection_group_id: -5543, protection_element_id:-5543], primaryKey: false);
      insert('organizations', [ id: 105529, nci_institute_code: "NY065", name: "Long Island Jewish Medical Center", city: "New Hyde Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY065",GROUP_DESC:"NY065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6544 ,protection_group_id: -5544, protection_element_id:-5544], primaryKey: false);
      insert('organizations', [ id: 105530, nci_institute_code: "NY066", name: "Brooklyn Hospital Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY066",GROUP_DESC:"NY066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6545 ,protection_group_id: -5545, protection_element_id:-5545], primaryKey: false);
      insert('organizations', [ id: 105531, nci_institute_code: "NY067", name: "Long Island College Hospital", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY067",GROUP_DESC:"NY067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6546 ,protection_group_id: -5546, protection_element_id:-5546], primaryKey: false);
      insert('organizations', [ id: 105532, nci_institute_code: "NY069", name: "Kingsbrook Jewish Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY069",GROUP_DESC:"NY069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6547 ,protection_group_id: -5547, protection_element_id:-5547], primaryKey: false);
      insert('organizations', [ id: 105533, nci_institute_code: "NY071", name: "Olean General Hospital", city: "Olean", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY071",GROUP_DESC:"NY071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6548 ,protection_group_id: -5548, protection_element_id:-5548], primaryKey: false);
      insert('organizations', [ id: 105534, nci_institute_code: "NY072", name: "Woodhull Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY072",GROUP_DESC:"NY072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6549 ,protection_group_id: -5549, protection_element_id:-5549], primaryKey: false);
      insert('organizations', [ id: 105535, nci_institute_code: "NY073", name: "Veteran Affairs New York Harbor Healthcare System-Brooklyn Campus", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY073",GROUP_DESC:"NY073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6550 ,protection_group_id: -5550, protection_element_id:-5550], primaryKey: false);
      insert('organizations', [ id: 105536, nci_institute_code: "NY074", name: "Brookdale Hospital Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY074",GROUP_DESC:"NY074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6551 ,protection_group_id: -5551, protection_element_id:-5551], primaryKey: false);
      insert('organizations', [ id: 105537, nci_institute_code: "NY075", name: "The New York Methodist Hospital", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY075",GROUP_DESC:"NY075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6552 ,protection_group_id: -5552, protection_element_id:-5552], primaryKey: false);
      insert('organizations', [ id: 105538, nci_institute_code: "NY076", name: "Maimonides Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY076",GROUP_DESC:"NY076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6553 ,protection_group_id: -5553, protection_element_id:-5553], primaryKey: false);
      insert('organizations', [ id: 105539, nci_institute_code: "NY077", name: "Lutheran Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY077",GROUP_DESC:"NY077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6554 ,protection_group_id: -5554, protection_element_id:-5554], primaryKey: false);
      insert('organizations', [ id: 105540, nci_institute_code: "NY078", name: "Coney Island Hospital", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY078",GROUP_DESC:"NY078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6555 ,protection_group_id: -5555, protection_element_id:-5555], primaryKey: false);
      insert('organizations', [ id: 105541, nci_institute_code: "NY079", name: "Wyckoff Heights Hospital", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY079",GROUP_DESC:"NY079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6556 ,protection_group_id: -5556, protection_element_id:-5556], primaryKey: false);
      insert('organizations', [ id: 105542, nci_institute_code: "NY080", name: "Interfaith Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY080",GROUP_DESC:"NY080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6557 ,protection_group_id: -5557, protection_element_id:-5557], primaryKey: false);
      insert('organizations', [ id: 105543, nci_institute_code: "NY081", name: "Jewish Hospital Medical Center Brooklyn", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY081",GROUP_DESC:"NY081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6558 ,protection_group_id: -5558, protection_element_id:-5558], primaryKey: false);
      insert('organizations', [ id: 105544, nci_institute_code: "NY082", name: "Booth Memorial Medical Center", city: "Flushing", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY082",GROUP_DESC:"NY082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6559 ,protection_group_id: -5559, protection_element_id:-5559], primaryKey: false);
      insert('organizations', [ id: 105545, nci_institute_code: "NY083", name: "Flushing Hospitaland and Medical Center", city: "Flushing", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY083",GROUP_DESC:"NY083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6560 ,protection_group_id: -5560, protection_element_id:-5560], primaryKey: false);
      insert('organizations', [ id: 105546, nci_institute_code: "NY084", name: "Saint Joseph's Hospital Health Center", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY084",GROUP_DESC:"NY084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6561 ,protection_group_id: -5561, protection_element_id:-5561], primaryKey: false);
      insert('organizations', [ id: 105547, nci_institute_code: "NY085", name: "Elmhurst Hospital Center", city: "Elmhurst", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY085",GROUP_DESC:"NY085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6562 ,protection_group_id: -5562, protection_element_id:-5562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 105548, nci_institute_code: "NY087", name: "Veterans Administration Hospital, Forest Hills", city: "Forest Hills", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY087",GROUP_DESC:"NY087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6563 ,protection_group_id: -5563, protection_element_id:-5563], primaryKey: false);
      insert('organizations', [ id: 105549, nci_institute_code: "NY088", name: "Saint Anthony's Hospital Cancer Labs", city: "Woodhaven", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY088",GROUP_DESC:"NY088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6564 ,protection_group_id: -5564, protection_element_id:-5564], primaryKey: false);
      insert('organizations', [ id: 105550, nci_institute_code: "NY089", name: "Catholic Medical Center", city: "Woodhaven", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY089",GROUP_DESC:"NY089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6565 ,protection_group_id: -5565, protection_element_id:-5565], primaryKey: false);
      insert('organizations', [ id: 105551, nci_institute_code: "NY090", name: "Catholic Medical Center", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY090",GROUP_DESC:"NY090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6566 ,protection_group_id: -5566, protection_element_id:-5566], primaryKey: false);
      insert('organizations', [ id: 105552, nci_institute_code: "NY091", name: "Queens Hospital Center", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY091",GROUP_DESC:"NY091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6567 ,protection_group_id: -5567, protection_element_id:-5567], primaryKey: false);
      insert('organizations', [ id: 105553, nci_institute_code: "NY092", name: "Jamaica Hospital", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY092",GROUP_DESC:"NY092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6568 ,protection_group_id: -5568, protection_element_id:-5568], primaryKey: false);
      insert('organizations', [ id: 105554, nci_institute_code: "NY093", name: "Winthrop University Hospital", city: "Mineola", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY093",GROUP_DESC:"NY093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6569 ,protection_group_id: -5569, protection_element_id:-5569], primaryKey: false);
      insert('organizations', [ id: 105555, nci_institute_code: "NY094", name: "The New York Hospital Medical Center of Queens", city: "Flushing", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY094",GROUP_DESC:"NY094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6570 ,protection_group_id: -5570, protection_element_id:-5570], primaryKey: false);
      insert('organizations', [ id: 105556, nci_institute_code: "NY095", name: "Lydia E. Hall Hospital", city: "Freeport", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY095",GROUP_DESC:"NY095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6571 ,protection_group_id: -5571, protection_element_id:-5571], primaryKey: false);
      insert('organizations', [ id: 105557, nci_institute_code: "NY096", name: "Community Hospital at Glen Cove", city: "Glen Cove", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY096",GROUP_DESC:"NY096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6572 ,protection_group_id: -5572, protection_element_id:-5572], primaryKey: false);
      insert('organizations', [ id: 105558, nci_institute_code: "NY097", name: "Nassau County Medical Center", city: "East Meadow", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY097",GROUP_DESC:"NY097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6573 ,protection_group_id: -5573, protection_element_id:-5573], primaryKey: false);
      insert('organizations', [ id: 105559, nci_institute_code: "NY098", name: "Mercy Hospital, Rockville Center", city: "Rockville Centre", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY098",GROUP_DESC:"NY098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6574 ,protection_group_id: -5574, protection_element_id:-5574], primaryKey: false);
      insert('organizations', [ id: 105560, nci_institute_code: "NY099", name: "South Nassau Communities", city: "Oceanside", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY099",GROUP_DESC:"NY099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6575 ,protection_group_id: -5575, protection_element_id:-5575], primaryKey: false);
      insert('organizations', [ id: 105561, nci_institute_code: "NY102", name: "Penninsula Hospital Center", city: "Far Rockaway", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY102",GROUP_DESC:"NY102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6576 ,protection_group_id: -5576, protection_element_id:-5576], primaryKey: false);
      insert('organizations', [ id: 105562, nci_institute_code: "NY105", name: "Huntington Hospital", city: "Huntington", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY105",GROUP_DESC:"NY105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6577 ,protection_group_id: -5577, protection_element_id:-5577], primaryKey: false);
      insert('organizations', [ id: 105563, nci_institute_code: "NY106", name: "State University of New York", city: "Jericho", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY106",GROUP_DESC:"NY106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6578 ,protection_group_id: -5578, protection_element_id:-5578], primaryKey: false);
      insert('organizations', [ id: 105564, nci_institute_code: "NY107", name: "Northport Veterans Affairs Medical Center", city: "Northport", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY107",GROUP_DESC:"NY107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6579 ,protection_group_id: -5579, protection_element_id:-5579], primaryKey: false);
      insert('organizations', [ id: 105565, nci_institute_code: "NY108", name: "Brookhaven Memorial Hospital Medical Center", city: "Patchogue", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY108",GROUP_DESC:"NY108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6580 ,protection_group_id: -5580, protection_element_id:-5580], primaryKey: false);
      insert('organizations', [ id: 105566, nci_institute_code: "NY110", name: "Saint. Charles Hospital", city: "Port Jefferson", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY110",GROUP_DESC:"NY110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6581 ,protection_group_id: -5581, protection_element_id:-5581], primaryKey: false);
      insert('organizations', [ id: 105567, nci_institute_code: "NY111", name: "Staten Island University", city: "Smithtown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY111",GROUP_DESC:"NY111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6582 ,protection_group_id: -5582, protection_element_id:-5582], primaryKey: false);
      insert('organizations', [ id: 105568, nci_institute_code: "NY112", name: "North Shore Hematology Oncology Association., P.C.", city: "East Setauket", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY112",GROUP_DESC:"NY112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6583 ,protection_group_id: -5583, protection_element_id:-5583], primaryKey: false);
      insert('organizations', [ id: 105569, nci_institute_code: "NY114", name: "New York Oncology Hematology PC - Troy", city: "Troy", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY114",GROUP_DESC:"NY114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6584 ,protection_group_id: -5584, protection_element_id:-5584], primaryKey: false);
      insert('organizations', [ id: 105570, nci_institute_code: "NY115", name: "Saint Marys Hospital, Troy", city: "Troy", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY115",GROUP_DESC:"NY115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6585 ,protection_group_id: -5585, protection_element_id:-5585], primaryKey: false);
      insert('organizations', [ id: 105571, nci_institute_code: "NY116", name: "Memorial Hospital, Albany", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY116",GROUP_DESC:"NY116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6586 ,protection_group_id: -5586, protection_element_id:-5586], primaryKey: false);
      insert('organizations', [ id: 105572, nci_institute_code: "NY117", name: "New York Oncology Hematology PC - Albany", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY117",GROUP_DESC:"NY117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6587 ,protection_group_id: -5587, protection_element_id:-5587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 105573, nci_institute_code: "NY118", name: "Saint Peters Hospital, Albany", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY118",GROUP_DESC:"NY118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6588 ,protection_group_id: -5588, protection_element_id:-5588], primaryKey: false);
      insert('organizations', [ id: 105574, nci_institute_code: "NY119", name: "Albany Medical Center", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY119",GROUP_DESC:"NY119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6589 ,protection_group_id: -5589, protection_element_id:-5589], primaryKey: false);
      insert('organizations', [ id: 105575, nci_institute_code: "NY120", name: "Samuel S Stratton Veterans Administration Medical Center", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY120",GROUP_DESC:"NY120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6590 ,protection_group_id: -5590, protection_element_id:-5590], primaryKey: false);
      insert('organizations', [ id: 105576, nci_institute_code: "NY121", name: "Albany Area Radiation Therapy Program", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY121",GROUP_DESC:"NY121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6591 ,protection_group_id: -5591, protection_element_id:-5591], primaryKey: false);
      insert('organizations', [ id: 105577, nci_institute_code: "NY122", name: "New York State University", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY122",GROUP_DESC:"NY122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6592 ,protection_group_id: -5592, protection_element_id:-5592], primaryKey: false);
      insert('organizations', [ id: 105578, nci_institute_code: "NY124", name: "Saint Clare's Hospital", city: "Schenectady", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY124",GROUP_DESC:"NY124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6593 ,protection_group_id: -5593, protection_element_id:-5593], primaryKey: false);
      insert('organizations', [ id: 105579, nci_institute_code: "NY125", name: "Ellis Hospital", city: "Schenectady", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY125",GROUP_DESC:"NY125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6594 ,protection_group_id: -5594, protection_element_id:-5594], primaryKey: false);
      insert('organizations', [ id: 105580, nci_institute_code: "NY127", name: "Catskill Community Cancer Center", city: "Catskill", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY127",GROUP_DESC:"NY127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6595 ,protection_group_id: -5595, protection_element_id:-5595], primaryKey: false);
      insert('organizations', [ id: 105581, nci_institute_code: "NY128", name: "New York Oncology Hematology PC-Carvell Cancer Treatment", city: "Hudson", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY128",GROUP_DESC:"NY128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6596 ,protection_group_id: -5596, protection_element_id:-5596], primaryKey: false);
      insert('organizations', [ id: 105582, nci_institute_code: "NY129", name: "Saint Luke's Hospital, Newburgh", city: "Newburgh", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY129",GROUP_DESC:"NY129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6597 ,protection_group_id: -5597, protection_element_id:-5597], primaryKey: false);
      insert('organizations', [ id: 105583, nci_institute_code: "NY130", name: "Saint Francis", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY130",GROUP_DESC:"NY130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6598 ,protection_group_id: -5598, protection_element_id:-5598], primaryKey: false);
      insert('organizations', [ id: 105584, nci_institute_code: "NY131", name: "Vassar Brothers Medical Center", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY131",GROUP_DESC:"NY131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6599 ,protection_group_id: -5599, protection_element_id:-5599], primaryKey: false);
      insert('organizations', [ id: 105585, nci_institute_code: "NY133", name: "Saint Francis Hospital", city: "Port Jervis", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY133",GROUP_DESC:"NY133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6600 ,protection_group_id: -5600, protection_element_id:-5600], primaryKey: false);
      insert('organizations', [ id: 105586, nci_institute_code: "NY134", name: "Glens Falls Hospital", city: "Glens Falls", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY134",GROUP_DESC:"NY134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6601 ,protection_group_id: -5601, protection_element_id:-5601], primaryKey: false);
      insert('organizations', [ id: 105587, nci_institute_code: "NY136", name: "Barbara Kopp Research Center", city: "Auburn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY136",GROUP_DESC:"NY136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6602 ,protection_group_id: -5602, protection_element_id:-5602], primaryKey: false);
      insert('organizations', [ id: 105588, nci_institute_code: "NY139", name: "Syracuse Veterans Administration Medical Center", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY139",GROUP_DESC:"NY139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6603 ,protection_group_id: -5603, protection_element_id:-5603], primaryKey: false);
      insert('organizations', [ id: 105589, nci_institute_code: "NY140", name: "Crouse Hospital", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY140",GROUP_DESC:"NY140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6604 ,protection_group_id: -5604, protection_element_id:-5604], primaryKey: false);
      insert('organizations', [ id: 105590, nci_institute_code: "NY141", name: "State University of New York Upstate Medical University", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY141",GROUP_DESC:"NY141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6605 ,protection_group_id: -5605, protection_element_id:-5605], primaryKey: false);
      insert('organizations', [ id: 105591, nci_institute_code: "NY143", name: "Mary Imogene Bassett Hospital", city: "Cooperstown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY143",GROUP_DESC:"NY143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6606 ,protection_group_id: -5606, protection_element_id:-5606], primaryKey: false);
      insert('organizations', [ id: 105592, nci_institute_code: "NY144", name: "House of The Good Samaritan", city: "Watertown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY144",GROUP_DESC:"NY144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6607 ,protection_group_id: -5607, protection_element_id:-5607], primaryKey: false);
      insert('organizations', [ id: 105593, nci_institute_code: "NY145", name: "Charles S. Wilson Memorial Hospital", city: "Johnson City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY145",GROUP_DESC:"NY145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6608 ,protection_group_id: -5608, protection_element_id:-5608], primaryKey: false);
      insert('organizations', [ id: 105594, nci_institute_code: "NY146", name: "Fox Memorial Hospital", city: "Oneonta", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY146",GROUP_DESC:"NY146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6609 ,protection_group_id: -5609, protection_element_id:-5609], primaryKey: false);
      insert('organizations', [ id: 105595, nci_institute_code: "NY147", name: "State University of New York", city: "Binghamton", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY147",GROUP_DESC:"NY147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6610 ,protection_group_id: -5610, protection_element_id:-5610], primaryKey: false);
      insert('organizations', [ id: 105596, nci_institute_code: "NY148", name: "United Health Services", city: "Johnson City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY148",GROUP_DESC:"NY148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6611 ,protection_group_id: -5611, protection_element_id:-5611], primaryKey: false);
      insert('organizations', [ id: 105597, nci_institute_code: "NY149", name: "Lourdes Hospital", city: "Binghamton", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY149",GROUP_DESC:"NY149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6612 ,protection_group_id: -5612, protection_element_id:-5612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 105598, nci_institute_code: "NY150", name: "Veterans Administration Hospital, Batavia, New York", city: "Batavia", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY150",GROUP_DESC:"NY150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6613 ,protection_group_id: -5613, protection_element_id:-5613], primaryKey: false);
      insert('organizations', [ id: 105599, nci_institute_code: "NY151", name: "Buffalo General Hospital", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY151",GROUP_DESC:"NY151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6614 ,protection_group_id: -5614, protection_element_id:-5614], primaryKey: false);
      insert('organizations', [ id: 105600, nci_institute_code: "NY153", name: "State University of New York At Buffalo", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY153",GROUP_DESC:"NY153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6615 ,protection_group_id: -5615, protection_element_id:-5615], primaryKey: false);
      insert('organizations', [ id: 105601, nci_institute_code: "NY154", name: "Erie County Medical Center", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY154",GROUP_DESC:"NY154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6616 ,protection_group_id: -5616, protection_element_id:-5616], primaryKey: false);
      insert('organizations', [ id: 105602, nci_institute_code: "NY155", name: "VA Western New York Healthcare System", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY155",GROUP_DESC:"NY155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6617 ,protection_group_id: -5617, protection_element_id:-5617], primaryKey: false);
      insert('organizations', [ id: 105603, nci_institute_code: "NY156", name: "E. J. Meyer Hospital", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY156",GROUP_DESC:"NY156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6618 ,protection_group_id: -5618, protection_element_id:-5618], primaryKey: false);
      insert('organizations', [ id: 105604, nci_institute_code: "NY157", name: "Childrens Hospital of Buffalo", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY157",GROUP_DESC:"NY157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6619 ,protection_group_id: -5619, protection_element_id:-5619], primaryKey: false);
      insert('organizations', [ id: 105605, nci_institute_code: "NY158", name: "Roswell Park Cancer Institute", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY158",GROUP_DESC:"NY158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6620 ,protection_group_id: -5620, protection_element_id:-5620], primaryKey: false);
      insert('organizations', [ id: 105606, nci_institute_code: "NY159", name: "Niagra Falls Memorial Hospital", city: "Niagara Falls", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY159",GROUP_DESC:"NY159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6621 ,protection_group_id: -5621, protection_element_id:-5621], primaryKey: false);
      insert('organizations', [ id: 105607, nci_institute_code: "NY161", name: "Genesee Surgical Associates, PC", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY161",GROUP_DESC:"NY161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6622 ,protection_group_id: -5622, protection_element_id:-5622], primaryKey: false);
      insert('organizations', [ id: 105608, nci_institute_code: "NY162", name: "New York Oncology Hematology PC - Latham", city: "Latham", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY162",GROUP_DESC:"NY162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6623 ,protection_group_id: -5623, protection_element_id:-5623], primaryKey: false);
      insert('organizations', [ id: 105609, nci_institute_code: "NY163", name: "Saint Mary's Hospital", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY163",GROUP_DESC:"NY163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6624 ,protection_group_id: -5624, protection_element_id:-5624], primaryKey: false);
      insert('organizations', [ id: 105610, nci_institute_code: "NY164", name: "Highland Hospital", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY164",GROUP_DESC:"NY164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6625 ,protection_group_id: -5625, protection_element_id:-5625], primaryKey: false);
      insert('organizations', [ id: 105611, nci_institute_code: "NY165", name: "Rochester General Hospital", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY165",GROUP_DESC:"NY165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6626 ,protection_group_id: -5626, protection_element_id:-5626], primaryKey: false);
      insert('organizations', [ id: 105612, nci_institute_code: "NY166", name: "Strong Memorial Hospital", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY166",GROUP_DESC:"NY166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6627 ,protection_group_id: -5627, protection_element_id:-5627], primaryKey: false);
      insert('organizations', [ id: 105613, nci_institute_code: "NY167", name: "University of Rochester", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY167",GROUP_DESC:"NY167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6628 ,protection_group_id: -5628, protection_element_id:-5628], primaryKey: false);
      insert('organizations', [ id: 105614, nci_institute_code: "NY169", name: "Womans Christian Association Hospital", city: "Jamestown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY169",GROUP_DESC:"NY169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6629 ,protection_group_id: -5629, protection_element_id:-5629], primaryKey: false);
      insert('organizations', [ id: 105615, nci_institute_code: "NY170", name: "Falck Cancer Center", city: "Elmira", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY170",GROUP_DESC:"NY170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6630 ,protection_group_id: -5630, protection_element_id:-5630], primaryKey: false);
      insert('organizations', [ id: 105616, nci_institute_code: "NY171", name: "Saint Joseph's Hospital", city: "Elmira", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY171",GROUP_DESC:"NY171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6631 ,protection_group_id: -5631, protection_element_id:-5631], primaryKey: false);
      insert('organizations', [ id: 105617, nci_institute_code: "NY172", name: "Champlain Valley Physicians Hospital Medical Center", city: "Plattsburgh", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY172",GROUP_DESC:"NY172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6632 ,protection_group_id: -5632, protection_element_id:-5632], primaryKey: false);
      insert('organizations', [ id: 105618, nci_institute_code: "NY173", name: "Millard Fillmore Hospital", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY173",GROUP_DESC:"NY173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6633 ,protection_group_id: -5633, protection_element_id:-5633], primaryKey: false);
      insert('organizations', [ id: 105619, nci_institute_code: "NY174", name: "Bayley Seton Hospital", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY174",GROUP_DESC:"NY174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6634 ,protection_group_id: -5634, protection_element_id:-5634], primaryKey: false);
      insert('organizations', [ id: 105620, nci_institute_code: "NY175", name: "Hematology Oncology Associates of Central New York", city: "East Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY175",GROUP_DESC:"NY175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6635 ,protection_group_id: -5635, protection_element_id:-5635], primaryKey: false);
      insert('organizations', [ id: 105621, nci_institute_code: "NY176", name: "Westchester County Medical Center", city: "Valhalla", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY176",GROUP_DESC:"NY176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6636 ,protection_group_id: -5636, protection_element_id:-5636], primaryKey: false);
      insert('organizations', [ id: 105622, nci_institute_code: "NY177", name: "Bronx Veterans Administration Medical Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY177",GROUP_DESC:"NY177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6637 ,protection_group_id: -5637, protection_element_id:-5637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 105623, nci_institute_code: "NY178", name: "State University of New York Downstate Medical Center", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY178",GROUP_DESC:"NY178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6638 ,protection_group_id: -5638, protection_element_id:-5638], primaryKey: false);
      insert('organizations', [ id: 105624, nci_institute_code: "NY179", name: "Our Lady of Mercy Medical Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY179",GROUP_DESC:"NY179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6639 ,protection_group_id: -5639, protection_element_id:-5639], primaryKey: false);
      insert('organizations', [ id: 105625, nci_institute_code: "NY180", name: "Mercy Hospital", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY180",GROUP_DESC:"NY180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6640 ,protection_group_id: -5640, protection_element_id:-5640], primaryKey: false);
      insert('organizations', [ id: 105626, nci_institute_code: "NY181", name: "Hospital for Special Surgery", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY181",GROUP_DESC:"NY181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6641 ,protection_group_id: -5641, protection_element_id:-5641], primaryKey: false);
      insert('organizations', [ id: 105627, nci_institute_code: "NY182", name: "Finger Lakes Community Cancer Center", city: "Clifton Springs", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY182",GROUP_DESC:"NY182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6642 ,protection_group_id: -5642, protection_element_id:-5642], primaryKey: false);
      insert('organizations', [ id: 105628, nci_institute_code: "NY183", name: "Clifton Springs Hospital and Clinic", city: "Clifton Springs", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY183",GROUP_DESC:"NY183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6643 ,protection_group_id: -5643, protection_element_id:-5643], primaryKey: false);
      insert('organizations', [ id: 105629, nci_institute_code: "NY184", name: "Stony Brook University Hospital", city: "Stony Brook", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY184",GROUP_DESC:"NY184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6644 ,protection_group_id: -5644, protection_element_id:-5644], primaryKey: false);
      insert('organizations', [ id: 105630, nci_institute_code: "NY185", name: "Putnam Hospital Center", city: "Carmel", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY185",GROUP_DESC:"NY185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6645 ,protection_group_id: -5645, protection_element_id:-5645], primaryKey: false);
      insert('organizations', [ id: 105631, nci_institute_code: "NY186", name: "Brooklyn New York CCOP", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY186",GROUP_DESC:"NY186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6646 ,protection_group_id: -5646, protection_element_id:-5646], primaryKey: false);
      insert('organizations', [ id: 105632, nci_institute_code: "NY187", name: "Oxford Medical Group", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY187",GROUP_DESC:"NY187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6647 ,protection_group_id: -5647, protection_element_id:-5647], primaryKey: false);
      insert('organizations', [ id: 105633, nci_institute_code: "NY188", name: "North Shore University Hospital CCOP", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY188",GROUP_DESC:"NY188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6648 ,protection_group_id: -5648, protection_element_id:-5648], primaryKey: false);
      insert('organizations', [ id: 105634, nci_institute_code: "NY190", name: "Community General Hospital", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY190",GROUP_DESC:"NY190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6649 ,protection_group_id: -5649, protection_element_id:-5649], primaryKey: false);
      insert('organizations', [ id: 105635, nci_institute_code: "NY191", name: "Madison County Medical Care, P.C.", city: "Wampsville", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY191",GROUP_DESC:"NY191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6650 ,protection_group_id: -5650, protection_element_id:-5650], primaryKey: false);
      insert('organizations', [ id: 105636, nci_institute_code: "NY192", name: "Island Oncology Hematology Association", city: "Bethpage", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY192",GROUP_DESC:"NY192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6651 ,protection_group_id: -5651, protection_element_id:-5651], primaryKey: false);
      insert('organizations', [ id: 105637, nci_institute_code: "NY193", name: "Medical Oncology Associates Long Island", city: "Syosset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY193",GROUP_DESC:"NY193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6652 ,protection_group_id: -5652, protection_element_id:-5652], primaryKey: false);
      insert('organizations', [ id: 105638, nci_institute_code: "NY194", name: "Palisades Oncology Associates PC", city: "Pomona", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY194",GROUP_DESC:"NY194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6653 ,protection_group_id: -5653, protection_element_id:-5653], primaryKey: false);
      insert('organizations', [ id: 105639, nci_institute_code: "NY195", name: "Hematology Oncology Care PC", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY195",GROUP_DESC:"NY195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6654 ,protection_group_id: -5654, protection_element_id:-5654], primaryKey: false);
      insert('organizations', [ id: 105640, nci_institute_code: "NY196", name: "Hematology Oncology Associates of Long Island PC", city: "New Hyde Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY196",GROUP_DESC:"NY196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6655 ,protection_group_id: -5655, protection_element_id:-5655], primaryKey: false);
      insert('organizations', [ id: 105641, nci_institute_code: "NY197", name: "New York Oncology Hematology PC - Amsterdam", city: "Amsterdam", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY197",GROUP_DESC:"NY197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6656 ,protection_group_id: -5656, protection_element_id:-5656], primaryKey: false);
      insert('organizations', [ id: 105642, nci_institute_code: "NY198", name: "Goshen Medical Associates, P.C.", city: "Goshen", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY198",GROUP_DESC:"NY198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6657 ,protection_group_id: -5657, protection_element_id:-5657], primaryKey: false);
      insert('organizations', [ id: 105643, nci_institute_code: "NY199", name: "Bronx Cross County Medical Group", city: "Yonkers", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY199",GROUP_DESC:"NY199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6658 ,protection_group_id: -5658, protection_element_id:-5658], primaryKey: false);
      insert('organizations', [ id: 105644, nci_institute_code: "NY200", name: "Plaza Onc Assoc., P.C.", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY200",GROUP_DESC:"NY200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6659 ,protection_group_id: -5659, protection_element_id:-5659], primaryKey: false);
      insert('organizations', [ id: 105645, nci_institute_code: "NY201", name: "Orange County Urological Association", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY201",GROUP_DESC:"NY201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6660 ,protection_group_id: -5660, protection_element_id:-5660], primaryKey: false);
      insert('organizations', [ id: 105646, nci_institute_code: "NY202", name: "North Shore Hematology Oncology PLLC", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY202",GROUP_DESC:"NY202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6661 ,protection_group_id: -5661, protection_element_id:-5661], primaryKey: false);
      insert('organizations', [ id: 105647, nci_institute_code: "NY203", name: "Middletown Urologic Associates PC", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY203",GROUP_DESC:"NY203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6662 ,protection_group_id: -5662, protection_element_id:-5662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 105648, nci_institute_code: "NY205", name: "Guthrie Clinic", city: "Corning", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY205",GROUP_DESC:"NY205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6663 ,protection_group_id: -5663, protection_element_id:-5663], primaryKey: false);
      insert('organizations', [ id: 105649, nci_institute_code: "NY206", name: "East Nassau Medical Group", city: "North Babylon", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY206",GROUP_DESC:"NY206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6664 ,protection_group_id: -5664, protection_element_id:-5664], primaryKey: false);
      insert('organizations', [ id: 105650, nci_institute_code: "NY207", name: "Katonah Medical Group, P.C.", city: "Katonah", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY207",GROUP_DESC:"NY207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6665 ,protection_group_id: -5665, protection_element_id:-5665], primaryKey: false);
      insert('organizations', [ id: 105651, nci_institute_code: "NY208", name: "Mid-Houston Medical Group, P.C.", city: "Fishkill", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY208",GROUP_DESC:"NY208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6666 ,protection_group_id: -5666, protection_element_id:-5666], primaryKey: false);
      insert('organizations', [ id: 105652, nci_institute_code: "NY209", name: "Bayshore Hematology/Oncology", city: "Bayshore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY209",GROUP_DESC:"NY209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6667 ,protection_group_id: -5667, protection_element_id:-5667], primaryKey: false);
      insert('organizations', [ id: 105653, nci_institute_code: "NY211", name: "Long Island Comprehensive Hematology Oncology Association PC", city: "Bayshore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY211",GROUP_DESC:"NY211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6668 ,protection_group_id: -5668, protection_element_id:-5668], primaryKey: false);
      insert('organizations', [ id: 105654, nci_institute_code: "NY213", name: "Kings County Hospital", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY213",GROUP_DESC:"NY213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6669 ,protection_group_id: -5669, protection_element_id:-5669], primaryKey: false);
      insert('organizations', [ id: 105655, nci_institute_code: "NY214", name: "Faxton-Saint Luke's Healthcare", city: "Utica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY214",GROUP_DESC:"NY214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6670 ,protection_group_id: -5670, protection_element_id:-5670], primaryKey: false);
      insert('organizations', [ id: 105656, nci_institute_code: "NY215", name: "Einstein-Wieler Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY215",GROUP_DESC:"NY215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6671 ,protection_group_id: -5671, protection_element_id:-5671], primaryKey: false);
      insert('organizations', [ id: 105657, nci_institute_code: "NY216", name: "Metropolitan Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY216",GROUP_DESC:"NY216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6672 ,protection_group_id: -5672, protection_element_id:-5672], primaryKey: false);
      insert('organizations', [ id: 105658, nci_institute_code: "NY218", name: "Strang Cancer Prevention Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY218",GROUP_DESC:"NY218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6673 ,protection_group_id: -5673, protection_element_id:-5673], primaryKey: false);
      insert('organizations', [ id: 105659, nci_institute_code: "NY219", name: "Jefferson Valley Medical Building, Suite, D", city: "Yorktown Heights", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY219",GROUP_DESC:"NY219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6674 ,protection_group_id: -5674, protection_element_id:-5674], primaryKey: false);
      insert('organizations', [ id: 105660, nci_institute_code: "NY222", name: "United Medical Association., P.C.", city: "Johnson City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY222",GROUP_DESC:"NY222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6675 ,protection_group_id: -5675, protection_element_id:-5675], primaryKey: false);
      insert('organizations', [ id: 105661, nci_institute_code: "NY223", name: "New York Oncology Hematology PC - Rexford", city: "Rexford", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY223",GROUP_DESC:"NY223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6676 ,protection_group_id: -5676, protection_element_id:-5676], primaryKey: false);
      insert('organizations', [ id: 105662, nci_institute_code: "NY226", name: "Schneider Children's Hospital", city: "New Hyde Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY226",GROUP_DESC:"NY226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6677 ,protection_group_id: -5677, protection_element_id:-5677], primaryKey: false);
      insert('organizations', [ id: 105663, nci_institute_code: "NY227", name: "Central Suffolk Hospital", city: "Roanoke", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY227",GROUP_DESC:"NY227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6678 ,protection_group_id: -5678, protection_element_id:-5678], primaryKey: false);
      insert('organizations', [ id: 105664, nci_institute_code: "NY229", name: "Hematology Oncology Association", city: "New City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY229",GROUP_DESC:"NY229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6679 ,protection_group_id: -5679, protection_element_id:-5679], primaryKey: false);
      insert('organizations', [ id: 105665, nci_institute_code: "NY230", name: "Great Neck Medical Associates", city: "Great Neck", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY230",GROUP_DESC:"NY230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6680 ,protection_group_id: -5680, protection_element_id:-5680], primaryKey: false);
      insert('organizations', [ id: 105666, nci_institute_code: "NY231", name: "Aaron Diamond AIDS Research Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY231",GROUP_DESC:"NY231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6681 ,protection_group_id: -5681, protection_element_id:-5681], primaryKey: false);
      insert('organizations', [ id: 105667, nci_institute_code: "NY232", name: "Memorial Hospital for Cancer and Allied Diseases, New York, New York", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY232",GROUP_DESC:"NY232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6682 ,protection_group_id: -5682, protection_element_id:-5682], primaryKey: false);
      insert('organizations', [ id: 105668, nci_institute_code: "NY233", name: "North Shore Medical Accelerator, P.C.", city: "Smithtown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY233",GROUP_DESC:"NY233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6683 ,protection_group_id: -5683, protection_element_id:-5683], primaryKey: false);
      insert('organizations', [ id: 105669, nci_institute_code: "NY234", name: "Interlakes Foundation Inc", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY234",GROUP_DESC:"NY234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6684 ,protection_group_id: -5684, protection_element_id:-5684], primaryKey: false);
      insert('organizations', [ id: 105670, nci_institute_code: "NY235", name: "Hudson Valley Hematology Oncology Association", city: "Fishkill", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY235",GROUP_DESC:"NY235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6685 ,protection_group_id: -5685, protection_element_id:-5685], primaryKey: false);
      insert('organizations', [ id: 105671, nci_institute_code: "NY236", name: "Century Medical Associates", city: "Williamsville", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY236",GROUP_DESC:"NY236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6686 ,protection_group_id: -5686, protection_element_id:-5686], primaryKey: false);
      insert('organizations', [ id: 105672, nci_institute_code: "NY237", name: "Brooks Memorial Hospital", city: "Dunkirk", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY237",GROUP_DESC:"NY237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6687 ,protection_group_id: -5687, protection_element_id:-5687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 105673, nci_institute_code: "NY238", name: "Veterans Affairs Western New York Health Care System", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY238",GROUP_DESC:"NY238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6688 ,protection_group_id: -5688, protection_element_id:-5688], primaryKey: false);
      insert('organizations', [ id: 105674, nci_institute_code: "NY240", name: "Adirondack Cancer Center", city: "Glens Falls", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY240",GROUP_DESC:"NY240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6689 ,protection_group_id: -5689, protection_element_id:-5689], primaryKey: false);
      insert('organizations', [ id: 105675, nci_institute_code: "NY241", name: "Westchester County Therapy Regional Center", city: "Yonkers", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY241",GROUP_DESC:"NY241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6690 ,protection_group_id: -5690, protection_element_id:-5690], primaryKey: false);
      insert('organizations', [ id: 105676, nci_institute_code: "NY242", name: "Comprehensive Gynecology PC", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY242",GROUP_DESC:"NY242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6691 ,protection_group_id: -5691, protection_element_id:-5691], primaryKey: false);
      insert('organizations', [ id: 105677, nci_institute_code: "NY243", name: "Sound Shore Medical Center", city: "New Rochelle", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY243",GROUP_DESC:"NY243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6692 ,protection_group_id: -5692, protection_element_id:-5692], primaryKey: false);
      insert('organizations', [ id: 105678, nci_institute_code: "NY244", name: "Sisters of Charity Hospital", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY244",GROUP_DESC:"NY244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6693 ,protection_group_id: -5693, protection_element_id:-5693], primaryKey: false);
      insert('organizations', [ id: 105679, nci_institute_code: "NY245", name: "Rome Hospital", city: "Rome", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY245",GROUP_DESC:"NY245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6694 ,protection_group_id: -5694, protection_element_id:-5694], primaryKey: false);
      insert('organizations', [ id: 105680, nci_institute_code: "NY246", name: "Stem Cell Sciences, Incorporated.,", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY246",GROUP_DESC:"NY246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6695 ,protection_group_id: -5695, protection_element_id:-5695], primaryKey: false);
      insert('organizations', [ id: 105681, nci_institute_code: "NY248", name: "Saint Joseph Hospital", city: "Cheektowaga", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY248",GROUP_DESC:"NY248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6696 ,protection_group_id: -5696, protection_element_id:-5696], primaryKey: false);
      insert('organizations', [ id: 105682, nci_institute_code: "NY250", name: "Benedictine Hospital", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY250",GROUP_DESC:"NY250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6697 ,protection_group_id: -5697, protection_element_id:-5697], primaryKey: false);
      insert('organizations', [ id: 105683, nci_institute_code: "NY251", name: "Riverside Women's Health", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY251",GROUP_DESC:"NY251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6698 ,protection_group_id: -5698, protection_element_id:-5698], primaryKey: false);
      insert('organizations', [ id: 105684, nci_institute_code: "NY252", name: "Richard E. Winter Cancer Treament Center", city: "Ogdensburg", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY252",GROUP_DESC:"NY252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6699 ,protection_group_id: -5699, protection_element_id:-5699], primaryKey: false);
      insert('organizations', [ id: 105685, nci_institute_code: "NY253", name: "Interlakes Oncology Hematology PC", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY253",GROUP_DESC:"NY253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6700 ,protection_group_id: -5700, protection_element_id:-5700], primaryKey: false);
      insert('organizations', [ id: 105686, nci_institute_code: "NY254", name: "Catskill Regional Medical Center", city: "Harris", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY254",GROUP_DESC:"NY254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6701 ,protection_group_id: -5701, protection_element_id:-5701], primaryKey: false);
      insert('organizations', [ id: 105687, nci_institute_code: "NY256", name: "Good Samaritan Hospital Medical Center", city: "West Islip", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY256",GROUP_DESC:"NY256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6702 ,protection_group_id: -5702, protection_element_id:-5702], primaryKey: false);
      insert('organizations', [ id: 105688, nci_institute_code: "NY258", name: "Arena Oncology Associates PC", city: "Lake Success", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY258",GROUP_DESC:"NY258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6703 ,protection_group_id: -5703, protection_element_id:-5703], primaryKey: false);
      insert('organizations', [ id: 105689, nci_institute_code: "NY259", name: "Slocum-Dickson Medical Group", city: "New Hartford", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY259",GROUP_DESC:"NY259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6704 ,protection_group_id: -5704, protection_element_id:-5704], primaryKey: false);
      insert('organizations', [ id: 105690, nci_institute_code: "NY260", name: "Frank A. Tomao and John Marino, P.C.", city: "Port Washington", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY260",GROUP_DESC:"NY260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6705 ,protection_group_id: -5705, protection_element_id:-5705], primaryKey: false);
      insert('organizations', [ id: 105691, nci_institute_code: "NY261", name: "Pro Health Care Associates, Llp", city: "Lake Success", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY261",GROUP_DESC:"NY261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6706 ,protection_group_id: -5706, protection_element_id:-5706], primaryKey: false);
      insert('organizations', [ id: 105692, nci_institute_code: "NY262", name: "New York Presbyterian-The University Hospital of Columbia and Cornell", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY262",GROUP_DESC:"NY262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6707 ,protection_group_id: -5707, protection_element_id:-5707], primaryKey: false);
      insert('organizations', [ id: 105693, nci_institute_code: "NY263", name: "Cancer Treatment Services, Corning", city: "Hornell", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY263",GROUP_DESC:"NY263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6708 ,protection_group_id: -5708, protection_element_id:-5708], primaryKey: false);
      insert('organizations', [ id: 105694, nci_institute_code: "NY264", name: "New York Oncology Hematology PC", city: "Clifton Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY264",GROUP_DESC:"NY264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6709 ,protection_group_id: -5709, protection_element_id:-5709], primaryKey: false);
      insert('organizations', [ id: 105695, nci_institute_code: "NY265", name: "Cancer Treatment Centers", city: "Geneseo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY265",GROUP_DESC:"NY265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6710 ,protection_group_id: -5710, protection_element_id:-5710], primaryKey: false);
      insert('organizations', [ id: 105696, nci_institute_code: "NY266", name: "Hepburn Medical Center", city: "Ogdensburg", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY266",GROUP_DESC:"NY266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6711 ,protection_group_id: -5711, protection_element_id:-5711], primaryKey: false);
      insert('organizations', [ id: 105697, nci_institute_code: "NY274", name: "Geneseo Medical Center", city: "Geneseo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY274",GROUP_DESC:"NY274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6712 ,protection_group_id: -5712, protection_element_id:-5712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 105698, nci_institute_code: "NY275", name: "Crystal Run Healthcare LLP", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY275",GROUP_DESC:"NY275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6713 ,protection_group_id: -5713, protection_element_id:-5713], primaryKey: false);
      insert('organizations', [ id: 105699, nci_institute_code: "NY276", name: "Saint Agnes Hospital", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY276",GROUP_DESC:"NY276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6714 ,protection_group_id: -5714, protection_element_id:-5714], primaryKey: false);
      insert('organizations', [ id: 105700, nci_institute_code: "NY277", name: "Buffalo Medical Group", city: "Williamsville", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY277",GROUP_DESC:"NY277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6715 ,protection_group_id: -5715, protection_element_id:-5715], primaryKey: false);
      insert('organizations', [ id: 105701, nci_institute_code: "NY278", name: "Syracuse Hematology Oncology", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY278",GROUP_DESC:"NY278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6716 ,protection_group_id: -5716, protection_element_id:-5716], primaryKey: false);
      insert('organizations', [ id: 105702, nci_institute_code: "NY279", name: "Western New York Urology Associates", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY279",GROUP_DESC:"NY279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6717 ,protection_group_id: -5717, protection_element_id:-5717], primaryKey: false);
      insert('organizations', [ id: 105703, nci_institute_code: "NY280", name: "Elizabeth Wende Breast Clinic", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY280",GROUP_DESC:"NY280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6718 ,protection_group_id: -5718, protection_element_id:-5718], primaryKey: false);
      insert('organizations', [ id: 105704, nci_institute_code: "NY281", name: "Riverhill Radiation Oncology", city: "Yonkers", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY281",GROUP_DESC:"NY281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6719 ,protection_group_id: -5719, protection_element_id:-5719], primaryKey: false);
      insert('organizations', [ id: 105705, nci_institute_code: "NY283", name: "University Hospital of Brooklyn", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY283",GROUP_DESC:"NY283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6720 ,protection_group_id: -5720, protection_element_id:-5720], primaryKey: false);
      insert('organizations', [ id: 105706, nci_institute_code: "NY284", name: "Queens Long Island Medical Grp,P.C.", city: "Woodbury", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY284",GROUP_DESC:"NY284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6721 ,protection_group_id: -5721, protection_element_id:-5721], primaryKey: false);
      insert('organizations', [ id: 105707, nci_institute_code: "NY285", name: "South Shore Hematology-Oncology Associates, P.C.", city: "Rockville Center", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY285",GROUP_DESC:"NY285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6722 ,protection_group_id: -5722, protection_element_id:-5722], primaryKey: false);
      insert('organizations', [ id: 105708, nci_institute_code: "NY286", name: "UHCC/Breast Care Center", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY286",GROUP_DESC:"NY286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6723 ,protection_group_id: -5723, protection_element_id:-5723], primaryKey: false);
      insert('organizations', [ id: 105709, nci_institute_code: "NY287", name: "Lincoln Medical and Mental Health Center", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY287",GROUP_DESC:"NY287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6724 ,protection_group_id: -5724, protection_element_id:-5724], primaryKey: false);
      insert('organizations', [ id: 105710, nci_institute_code: "NY288", name: "Faxton Regional Cancer Center", city: "Utica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY288",GROUP_DESC:"NY288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6725 ,protection_group_id: -5725, protection_element_id:-5725], primaryKey: false);
      insert('organizations', [ id: 105711, nci_institute_code: "NY289", name: "Kenmore Mercy Hospital", city: "Kenmore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY289",GROUP_DESC:"NY289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6726 ,protection_group_id: -5726, protection_element_id:-5726], primaryKey: false);
      insert('organizations', [ id: 105712, nci_institute_code: "NY290", name: "Sands Cancer Center", city: "Canandiaqua", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY290",GROUP_DESC:"NY290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6727 ,protection_group_id: -5727, protection_element_id:-5727], primaryKey: false);
      insert('organizations', [ id: 105713, nci_institute_code: "NY291", name: "Unity Health System", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY291",GROUP_DESC:"NY291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6728 ,protection_group_id: -5728, protection_element_id:-5728], primaryKey: false);
      insert('organizations', [ id: 105714, nci_institute_code: "NY292", name: "Kingston Hospital", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY292",GROUP_DESC:"NY292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6729 ,protection_group_id: -5729, protection_element_id:-5729], primaryKey: false);
      insert('organizations', [ id: 105715, nci_institute_code: "NY293", name: "Westchester Medical Group", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY293",GROUP_DESC:"NY293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6730 ,protection_group_id: -5730, protection_element_id:-5730], primaryKey: false);
      insert('organizations', [ id: 105716, nci_institute_code: "NY294", name: "Long Island Radiation Therapy", city: "Garden City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY294",GROUP_DESC:"NY294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6731 ,protection_group_id: -5731, protection_element_id:-5731], primaryKey: false);
      insert('organizations', [ id: 105717, nci_institute_code: "NY295", name: "Nyack Hospital", city: "Nyack", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY295",GROUP_DESC:"NY295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6732 ,protection_group_id: -5732, protection_element_id:-5732], primaryKey: false);
      insert('organizations', [ id: 105718, nci_institute_code: "NY296", name: "Eastern Long Island Hematology Oncology, P.C.", city: "Riverhead", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY296",GROUP_DESC:"NY296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6733 ,protection_group_id: -5733, protection_element_id:-5733], primaryKey: false);
      insert('organizations', [ id: 105719, nci_institute_code: "NY297", name: "Mid-Hudson Hematology and Oncology", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY297",GROUP_DESC:"NY297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6734 ,protection_group_id: -5734, protection_element_id:-5734], primaryKey: false);
      insert('organizations', [ id: 105720, nci_institute_code: "NY298", name: "Staten Island Medical Group", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY298",GROUP_DESC:"NY298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6735 ,protection_group_id: -5735, protection_element_id:-5735], primaryKey: false);
      insert('organizations', [ id: 105721, nci_institute_code: "NY300", name: "Broome Oncology, L.L.C.", city: "Johnson City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY300",GROUP_DESC:"NY300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6736 ,protection_group_id: -5736, protection_element_id:-5736], primaryKey: false);
      insert('organizations', [ id: 105722, nci_institute_code: "NY301", name: "Veterans Administration Medical Center, Bath", city: "Bath", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY301",GROUP_DESC:"NY301 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY301",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY301",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY301", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6737 ,protection_group_id: -5737, protection_element_id:-5737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 105723, nci_institute_code: "NY302", name: "Mary Immaculate Hospital", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY302",GROUP_DESC:"NY302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6738 ,protection_group_id: -5738, protection_element_id:-5738], primaryKey: false);
      insert('organizations', [ id: 105724, nci_institute_code: "NY303", name: "Saint John's Queens Hospital", city: "Elmhurst", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY303",GROUP_DESC:"NY303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6739 ,protection_group_id: -5739, protection_element_id:-5739], primaryKey: false);
      insert('organizations', [ id: 105725, nci_institute_code: "NY304", name: "Surgical Oncology Associates, LLP", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY304",GROUP_DESC:"NY304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6740 ,protection_group_id: -5740, protection_element_id:-5740], primaryKey: false);
      insert('organizations', [ id: 105726, nci_institute_code: "NY305", name: "North Shore Radiation Oncology", city: "East Setauket", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY305",GROUP_DESC:"NY305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6741 ,protection_group_id: -5741, protection_element_id:-5741], primaryKey: false);
      insert('organizations', [ id: 105727, nci_institute_code: "NY306", name: "International Myeloma Foundation", city: "Bayside", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY306",GROUP_DESC:"NY306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6742 ,protection_group_id: -5742, protection_element_id:-5742], primaryKey: false);
      insert('organizations', [ id: 105728, nci_institute_code: "NY307", name: "Weill Cornell University", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY307",GROUP_DESC:"NY307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6743 ,protection_group_id: -5743, protection_element_id:-5743], primaryKey: false);
      insert('organizations', [ id: 105729, nci_institute_code: "NY308", name: "Saint Luke's Roosevelt Hospital Center - Roosevelt Division", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY308",GROUP_DESC:"NY308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6744 ,protection_group_id: -5744, protection_element_id:-5744], primaryKey: false);
      insert('organizations', [ id: 105730, nci_institute_code: "NY309", name: "Presbyterian /Weill Medical College", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY309",GROUP_DESC:"NY309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6745 ,protection_group_id: -5745, protection_element_id:-5745], primaryKey: false);
      insert('organizations', [ id: 105731, nci_institute_code: "NY310", name: "Saint Vincent Catholic Medical Centers", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY310",GROUP_DESC:"NY310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6746 ,protection_group_id: -5746, protection_element_id:-5746], primaryKey: false);
      insert('organizations', [ id: 105732, nci_institute_code: "NY311", name: "Huntington Medical Group", city: "Huntington", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY311",GROUP_DESC:"NY311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6747 ,protection_group_id: -5747, protection_element_id:-5747], primaryKey: false);
      insert('organizations', [ id: 105733, nci_institute_code: "NY312", name: "Nathan Kline Research Institute", city: "Orangeburg", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY312",GROUP_DESC:"NY312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6748 ,protection_group_id: -5748, protection_element_id:-5748], primaryKey: false);
      insert('organizations', [ id: 105734, nci_institute_code: "NY313", name: "Montefiore Medical Center-Women's Health", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY313",GROUP_DESC:"NY313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6749 ,protection_group_id: -5749, protection_element_id:-5749], primaryKey: false);
      insert('organizations', [ id: 105735, nci_institute_code: "NY314", name: "Institute for Cancer Prevention", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY314",GROUP_DESC:"NY314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6750 ,protection_group_id: -5750, protection_element_id:-5750], primaryKey: false);
      insert('organizations', [ id: 105736, nci_institute_code: "NY315", name: "Hudson Valley Urology, PC", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY315",GROUP_DESC:"NY315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6751 ,protection_group_id: -5751, protection_element_id:-5751], primaryKey: false);
      insert('organizations', [ id: 105737, nci_institute_code: "NY316", name: "Pluta Cancer Center", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY316",GROUP_DESC:"NY316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6752 ,protection_group_id: -5752, protection_element_id:-5752], primaryKey: false);
      insert('organizations', [ id: 105738, nci_institute_code: "NY317", name: "New York State Psychiatric Institute", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY317",GROUP_DESC:"NY317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6753 ,protection_group_id: -5753, protection_element_id:-5753], primaryKey: false);
      insert('organizations', [ id: 105739, nci_institute_code: "NY318", name: "North Shore University Hospital at Glen Cove", city: "Glen Cove", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY318",GROUP_DESC:"NY318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6754 ,protection_group_id: -5754, protection_element_id:-5754], primaryKey: false);
      insert('organizations', [ id: 105740, nci_institute_code: "NY319", name: "CuraScript Pharmacy, Inc.", city: "Brewster", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY319",GROUP_DESC:"NY319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6755 ,protection_group_id: -5755, protection_element_id:-5755], primaryKey: false);
      insert('organizations', [ id: 105741, nci_institute_code: "NY320", name: "Nassau Hematology Oncology, P.C.", city: "Lake Success", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY320",GROUP_DESC:"NY320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6756 ,protection_group_id: -5756, protection_element_id:-5756], primaryKey: false);
      insert('organizations', [ id: 105742, nci_institute_code: "NY321", name: "Long Island Gynecologic Oncologists, PC", city: "Smithtown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY321",GROUP_DESC:"NY321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6757 ,protection_group_id: -5757, protection_element_id:-5757], primaryKey: false);
      insert('organizations', [ id: 105743, nci_institute_code: "NY322", name: "Grand Street Medical Association", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY322",GROUP_DESC:"NY322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6758 ,protection_group_id: -5758, protection_element_id:-5758], primaryKey: false);
      insert('organizations', [ id: 105744, nci_institute_code: "NY323", name: "Western New York Urology Associates, LLC - Orchard Park", city: "Orchard Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY323",GROUP_DESC:"NY323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6759 ,protection_group_id: -5759, protection_element_id:-5759], primaryKey: false);
      insert('organizations', [ id: 105745, nci_institute_code: "NY324", name: "Laser Surgery Care", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY324",GROUP_DESC:"NY324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6760 ,protection_group_id: -5760, protection_element_id:-5760], primaryKey: false);
      insert('organizations', [ id: 105746, nci_institute_code: "NY325", name: "Hudson Valley Surgical Associates, PC", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY325",GROUP_DESC:"NY325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6761 ,protection_group_id: -5761, protection_element_id:-5761], primaryKey: false);
      insert('organizations', [ id: 105747, nci_institute_code: "NY326", name: "Queens Medical Associates, P.C.", city: "Fresh Meadows", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY326",GROUP_DESC:"NY326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6762 ,protection_group_id: -5762, protection_element_id:-5762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 105748, nci_institute_code: "NY327", name: "Children's Hospital at Montefiore", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY327",GROUP_DESC:"NY327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6763 ,protection_group_id: -5763, protection_element_id:-5763], primaryKey: false);
      insert('organizations', [ id: 105749, nci_institute_code: "NY328", name: "New York University Clinical Cancer Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY328",GROUP_DESC:"NY328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6764 ,protection_group_id: -5764, protection_element_id:-5764], primaryKey: false);
      insert('organizations', [ id: 105750, nci_institute_code: "NY329", name: "Saint Elizabeth Medical Group", city: "Utica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY329",GROUP_DESC:"NY329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6765 ,protection_group_id: -5765, protection_element_id:-5765], primaryKey: false);
      insert('organizations', [ id: 105751, nci_institute_code: "NY330", name: "Horton Medical Pavilion", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY330",GROUP_DESC:"NY330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6766 ,protection_group_id: -5766, protection_element_id:-5766], primaryKey: false);
      insert('organizations', [ id: 105752, nci_institute_code: "NY331", name: "Orange Surgical Group", city: "Middletown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY331",GROUP_DESC:"NY331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6767 ,protection_group_id: -5767, protection_element_id:-5767], primaryKey: false);
      insert('organizations', [ id: 105753, nci_institute_code: "NY332", name: "The Urological Institute of Northeastern New York", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY332",GROUP_DESC:"NY332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6768 ,protection_group_id: -5768, protection_element_id:-5768], primaryKey: false);
      insert('organizations', [ id: 105754, nci_institute_code: "NY333", name: "Saint Vincent Catholic Medical Centers-Manhattan", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY333",GROUP_DESC:"NY333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6769 ,protection_group_id: -5769, protection_element_id:-5769], primaryKey: false);
      insert('organizations', [ id: 105755, nci_institute_code: "NY334", name: "Finger Lakes Hematology and Oncology", city: "Clifton Springs", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY334",GROUP_DESC:"NY334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6770 ,protection_group_id: -5770, protection_element_id:-5770], primaryKey: false);
      insert('organizations', [ id: 105756, nci_institute_code: "NY335", name: "Hematology-Oncology Associates of Rockland", city: "New City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY335",GROUP_DESC:"NY335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6771 ,protection_group_id: -5771, protection_element_id:-5771], primaryKey: false);
      insert('organizations', [ id: 105757, nci_institute_code: "NY336", name: "New York Eye and Ear Infirmary", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY336",GROUP_DESC:"NY336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6772 ,protection_group_id: -5772, protection_element_id:-5772], primaryKey: false);
      insert('organizations', [ id: 105758, nci_institute_code: "NY337", name: "Maimonides Women's Breast Center", city: "Brooklyn", state: "New York", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY337",GROUP_DESC:"NY337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6773 ,protection_group_id: -5773, protection_element_id:-5773], primaryKey: false);
      insert('organizations', [ id: 105759, nci_institute_code: "NY338", name: "Ramapo Valley Surgical Associates", city: "Suffern", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY338",GROUP_DESC:"NY338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6774 ,protection_group_id: -5774, protection_element_id:-5774], primaryKey: false);
      insert('organizations', [ id: 105760, nci_institute_code: "NY339", name: "Hudson Valley Hematology Oncology Associates", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY339",GROUP_DESC:"NY339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6775 ,protection_group_id: -5775, protection_element_id:-5775], primaryKey: false);
      insert('organizations', [ id: 105761, nci_institute_code: "NY340", name: "Good Samaritan Hospital", city: "Suffern", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY340",GROUP_DESC:"NY340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6776 ,protection_group_id: -5776, protection_element_id:-5776], primaryKey: false);
      insert('organizations', [ id: 105762, nci_institute_code: "NY341", name: "New York Downtown Hospital", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY341",GROUP_DESC:"NY341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6777 ,protection_group_id: -5777, protection_element_id:-5777], primaryKey: false);
      insert('organizations', [ id: 105763, nci_institute_code: "NY342", name: "New York Oncology Hematology PC -Albany Medical Cancer Treatment Center", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY342",GROUP_DESC:"NY342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6778 ,protection_group_id: -5778, protection_element_id:-5778], primaryKey: false);
      insert('organizations', [ id: 105764, nci_institute_code: "NY343", name: "Frontier Science and Technology Research Foundation", city: "Amherst", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY343",GROUP_DESC:"NY343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6779 ,protection_group_id: -5779, protection_element_id:-5779], primaryKey: false);
      insert('organizations', [ id: 105765, nci_institute_code: "NY344", name: "Eastchester Center for Cancer Care", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY344",GROUP_DESC:"NY344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6780 ,protection_group_id: -5780, protection_element_id:-5780], primaryKey: false);
      insert('organizations', [ id: 105766, nci_institute_code: "NY345", name: "Broome Oncology L.L.C.", city: "Binghampton", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY345",GROUP_DESC:"NY345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6781 ,protection_group_id: -5781, protection_element_id:-5781], primaryKey: false);
      insert('organizations', [ id: 105767, nci_institute_code: "NY346", name: "Scarsdale Medical Group", city: "Scarsdale", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY346",GROUP_DESC:"NY346 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY346",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY346",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY346", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6782 ,protection_group_id: -5782, protection_element_id:-5782], primaryKey: false);
      insert('organizations', [ id: 105768, nci_institute_code: "NY347", name: "Long Island Radiation Therapy - Lake Success", city: "Lake Success", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY347",GROUP_DESC:"NY347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6783 ,protection_group_id: -5783, protection_element_id:-5783], primaryKey: false);
      insert('organizations', [ id: 105769, nci_institute_code: "NY349", name: "New York Oncology Care", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY349",GROUP_DESC:"NY349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6784 ,protection_group_id: -5784, protection_element_id:-5784], primaryKey: false);
      insert('organizations', [ id: 105770, nci_institute_code: "NY350", name: "Hematology Oncology Association of Western Suffolk, PC", city: "Bayshore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY350",GROUP_DESC:"NY350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6785 ,protection_group_id: -5785, protection_element_id:-5785], primaryKey: false);
      insert('organizations', [ id: 105771, nci_institute_code: "NY351", name: "Memorial Sloan-Kettering Cancer Center at Suffolk", city: "Commack", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY351",GROUP_DESC:"NY351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6786 ,protection_group_id: -5786, protection_element_id:-5786], primaryKey: false);
      insert('organizations', [ id: 105772, nci_institute_code: "NY352", name: "Memorial Sloan-Kettering Cancer Center at Phelps", city: "Sleepy Hollow", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY352",GROUP_DESC:"NY352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6787 ,protection_group_id: -5787, protection_element_id:-5787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 105773, nci_institute_code: "NY353", name: "Memorial Sloan-Kettering Cancer Center at Mercy", city: "Rockville Centre", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY353",GROUP_DESC:"NY353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6788 ,protection_group_id: -5788, protection_element_id:-5788], primaryKey: false);
      insert('organizations', [ id: 105774, nci_institute_code: "NY354", name: "Finger Lakes Radiation Oncology", city: "Clifton Springs", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY354",GROUP_DESC:"NY354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6789 ,protection_group_id: -5789, protection_element_id:-5789], primaryKey: false);
      insert('organizations', [ id: 105775, nci_institute_code: "NY355", name: "Guthrie Cancer Center", city: "Corning", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY355",GROUP_DESC:"NY355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6790 ,protection_group_id: -5790, protection_element_id:-5790], primaryKey: false);
      insert('organizations', [ id: 105776, nci_institute_code: "NY356", name: "21st Century Oncology", city: "Yonkers", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY356",GROUP_DESC:"NY356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6791 ,protection_group_id: -5791, protection_element_id:-5791], primaryKey: false);
      insert('organizations', [ id: 105777, nci_institute_code: "NY357", name: "Research Advisors", city: "Manlius", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY357",GROUP_DESC:"NY357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6792 ,protection_group_id: -5792, protection_element_id:-5792], primaryKey: false);
      insert('organizations', [ id: 105778, nci_institute_code: "NY358", name: "Saint Vincent Cancer Center", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY358",GROUP_DESC:"NY358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6793 ,protection_group_id: -5793, protection_element_id:-5793], primaryKey: false);
      insert('organizations', [ id: 105779, nci_institute_code: "NY359", name: "Wadsworth Center New York State Department of Health", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY359",GROUP_DESC:"NY359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6794 ,protection_group_id: -5794, protection_element_id:-5794], primaryKey: false);
      insert('organizations', [ id: 105780, nci_institute_code: "NY360", name: "Dickstein Cancer Treatment Center", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY360",GROUP_DESC:"NY360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6795 ,protection_group_id: -5795, protection_element_id:-5795], primaryKey: false);
      insert('organizations', [ id: 105781, nci_institute_code: "NY361", name: "Ralph Lauren Center for Cancer Care and Prevention", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY361",GROUP_DESC:"NY361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6796 ,protection_group_id: -5796, protection_element_id:-5796], primaryKey: false);
      insert('organizations', [ id: 105782, nci_institute_code: "NY362", name: "The Feinstein Institute for Medical Research", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY362",GROUP_DESC:"NY362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6797 ,protection_group_id: -5797, protection_element_id:-5797], primaryKey: false);
      insert('organizations', [ id: 105783, nci_institute_code: "NY363", name: "Schwartz Gynecologic Oncology PLLC", city: "Babylon", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY363",GROUP_DESC:"NY363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6798 ,protection_group_id: -5798, protection_element_id:-5798], primaryKey: false);
      insert('organizations', [ id: 105784, nci_institute_code: "NY364", name: "New York Oncology Hematology PC - Ellis Hospital", city: "Schenectady", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY364",GROUP_DESC:"NY364 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY364",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY364",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY364", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6799 ,protection_group_id: -5799, protection_element_id:-5799], primaryKey: false);
      insert('organizations', [ id: 105785, nci_institute_code: "NY365", name: "Westchester Hematology Oncology Associates", city: "Mount Kisco", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY365",GROUP_DESC:"NY365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6800 ,protection_group_id: -5800, protection_element_id:-5800], primaryKey: false);
      insert('organizations', [ id: 105786, nci_institute_code: "NY366", name: "Mailman School of Public Health", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY366",GROUP_DESC:"NY366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6801 ,protection_group_id: -5801, protection_element_id:-5801], primaryKey: false);
      insert('organizations', [ id: 105787, nci_institute_code: "NY367", name: "Arnot Ogden Medical Center", city: "Elmira", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY367",GROUP_DESC:"NY367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6802 ,protection_group_id: -5802, protection_element_id:-5802], primaryKey: false);
      insert('organizations', [ id: 105788, nci_institute_code: "NY368", name: "Long Island Thoracic Surgery", city: "Lynbrook", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY368",GROUP_DESC:"NY368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6803 ,protection_group_id: -5803, protection_element_id:-5803], primaryKey: false);
      insert('organizations', [ id: 105789, nci_institute_code: "NY369", name: "Corning Hospital", city: "Corning", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY369",GROUP_DESC:"NY369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6804 ,protection_group_id: -5804, protection_element_id:-5804], primaryKey: false);
      insert('organizations', [ id: 105790, nci_institute_code: "NY370", name: "South Nassau Cancer Center", city: "Valley Stream", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY370",GROUP_DESC:"NY370 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY370",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY370",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY370", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6805 ,protection_group_id: -5805, protection_element_id:-5805], primaryKey: false);
      insert('organizations', [ id: 105791, nci_institute_code: "NY371", name: "Cornell University", city: "Ithaca", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY371",GROUP_DESC:"NY371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6806 ,protection_group_id: -5806, protection_element_id:-5806], primaryKey: false);
      insert('organizations', [ id: 105792, nci_institute_code: "NY372", name: "Radiation Oncology Associates of Long Island", city: "Plainview", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY372",GROUP_DESC:"NY372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6807 ,protection_group_id: -5807, protection_element_id:-5807], primaryKey: false);
      insert('organizations', [ id: 105793, nci_institute_code: "NY373", name: "Long Island Radiation Therapy - Manhasset", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY373",GROUP_DESC:"NY373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6808 ,protection_group_id: -5808, protection_element_id:-5808], primaryKey: false);
      insert('organizations', [ id: 105794, nci_institute_code: "NY374", name: "Great Neck Hematology/Oncology PC", city: "Great Neck", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY374",GROUP_DESC:"NY374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6809 ,protection_group_id: -5809, protection_element_id:-5809], primaryKey: false);
      insert('organizations', [ id: 105795, nci_institute_code: "NY375", name: "Long Island Gynecologic Oncologist PC", city: "Mineola", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY375",GROUP_DESC:"NY375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6810 ,protection_group_id: -5810, protection_element_id:-5810], primaryKey: false);
      insert('organizations', [ id: 105796, nci_institute_code: "NY376", name: "Medical and Clinical Research Associates", city: "Bayshore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY376",GROUP_DESC:"NY376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6811 ,protection_group_id: -5811, protection_element_id:-5811], primaryKey: false);
      insert('organizations', [ id: 105797, nci_institute_code: "NY377", name: "Jamestown Area Medical Associates LLP", city: "Jamestown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY377",GROUP_DESC:"NY377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6812 ,protection_group_id: -5812, protection_element_id:-5812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 105798, nci_institute_code: "NY378", name: "Associates in Gynecological Care PC", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY378",GROUP_DESC:"NY378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6813 ,protection_group_id: -5813, protection_element_id:-5813], primaryKey: false);
      insert('organizations', [ id: 105799, nci_institute_code: "NY379", name: "Breast Cancer Watch", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY379",GROUP_DESC:"NY379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6814 ,protection_group_id: -5814, protection_element_id:-5814], primaryKey: false);
      insert('organizations', [ id: 105800, nci_institute_code: "NY380", name: "Staten Island Medical Group - Clove Lake Center", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY380",GROUP_DESC:"NY380 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY380",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY380",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY380", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6815 ,protection_group_id: -5815, protection_element_id:-5815], primaryKey: false);
      insert('organizations', [ id: 105801, nci_institute_code: "NY381", name: "Richmond Cancer and Blood Disorders PC", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY381",GROUP_DESC:"NY381 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY381",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY381",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY381", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6816 ,protection_group_id: -5816, protection_element_id:-5816], primaryKey: false);
      insert('organizations', [ id: 105802, nci_institute_code: "NY382", name: "Advanced Oncology Associates", city: "New Rochelle", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY382",GROUP_DESC:"NY382 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY382",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY382",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY382", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6817 ,protection_group_id: -5817, protection_element_id:-5817], primaryKey: false);
      insert('organizations', [ id: 105803, nci_institute_code: "NY383", name: "Hudson Valley Oncology Associates", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY383",GROUP_DESC:"NY383 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY383",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY383",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY383", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6818 ,protection_group_id: -5818, protection_element_id:-5818], primaryKey: false);
      insert('organizations', [ id: 105804, nci_institute_code: "NY384", name: "Saint Johns University", city: "Jamaica", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY384",GROUP_DESC:"NY384 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY384",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY384",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY384", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6819 ,protection_group_id: -5819, protection_element_id:-5819], primaryKey: false);
      insert('organizations', [ id: 105805, nci_institute_code: "NY385", name: "Cayuga Medical Center", city: "Ithaca", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY385",GROUP_DESC:"NY385 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY385",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY385",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY385", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6820 ,protection_group_id: -5820, protection_element_id:-5820], primaryKey: false);
      insert('organizations', [ id: 105806, nci_institute_code: "NY386", name: "Cancer Center of Poughkeepsie", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY386",GROUP_DESC:"NY386 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY386",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY386",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY386", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6821 ,protection_group_id: -5821, protection_element_id:-5821], primaryKey: false);
      insert('organizations', [ id: 105807, nci_institute_code: "NY387", name: "Monter Cancer Center", city: "Lake Success", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY387",GROUP_DESC:"NY387 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY387",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY387",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY387", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6822 ,protection_group_id: -5822, protection_element_id:-5822], primaryKey: false);
      insert('organizations', [ id: 105808, nci_institute_code: "NY388", name: "GYN Oncology of CNY PC", city: "East Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY388",GROUP_DESC:"NY388 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY388",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY388",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY388", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6823 ,protection_group_id: -5823, protection_element_id:-5823], primaryKey: false);
      insert('organizations', [ id: 105809, nci_institute_code: "NY389", name: "Fernando J Camacho MD and Mark A Ramirez MD PC", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY389",GROUP_DESC:"NY389 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY389",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY389",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY389", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6824 ,protection_group_id: -5824, protection_element_id:-5824], primaryKey: false);
      insert('organizations', [ id: 105810, nci_institute_code: "NY390", name: "The Women's Oncology & Wellness Practice", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY390",GROUP_DESC:"NY390 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY390",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY390",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY390", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6825 ,protection_group_id: -5825, protection_element_id:-5825], primaryKey: false);
      insert('organizations', [ id: 105811, nci_institute_code: "NY391", name: "Raman Sood Physician PC", city: "Dunkirk", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY391",GROUP_DESC:"NY391 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY391",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY391",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY391", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6826 ,protection_group_id: -5826, protection_element_id:-5826], primaryKey: false);
      insert('organizations', [ id: 105812, nci_institute_code: "NY392", name: "Breast Surgery Associates PLLC", city: "Huntington", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY392",GROUP_DESC:"NY392 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY392",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY392",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY392", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6827 ,protection_group_id: -5827, protection_element_id:-5827], primaryKey: false);
      insert('organizations', [ id: 105813, nci_institute_code: "NY393", name: "Fifth Avenue Medical Healthcare", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY393",GROUP_DESC:"NY393 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY393",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY393",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY393", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6828 ,protection_group_id: -5828, protection_element_id:-5828], primaryKey: false);
      insert('organizations', [ id: 105814, nci_institute_code: "NY394", name: "Southside Hospital", city: "Bay Shore", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY394",GROUP_DESC:"NY394 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY394",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY394",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY394", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6829 ,protection_group_id: -5829, protection_element_id:-5829], primaryKey: false);
      insert('organizations', [ id: 105815, nci_institute_code: "NY395", name: "Saint Barnabas Hospital", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY395",GROUP_DESC:"NY395 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY395",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY395",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY395", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6830 ,protection_group_id: -5830, protection_element_id:-5830], primaryKey: false);
      insert('organizations', [ id: 105816, nci_institute_code: "NY396", name: "Southern Tier Cancer Care", city: "Olean", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY396",GROUP_DESC:"NY396 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY396",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY396",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY396", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6831 ,protection_group_id: -5831, protection_element_id:-5831], primaryKey: false);
      insert('organizations', [ id: 105817, nci_institute_code: "NY397", name: "Diplomates of American Board of Internal Medicine in Oncology and Hematology", city: "Stony Brook", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY397",GROUP_DESC:"NY397 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY397",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY397",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY397", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6832 ,protection_group_id: -5832, protection_element_id:-5832], primaryKey: false);
      insert('organizations', [ id: 105818, nci_institute_code: "NY398", name: "Ordway Research Institute", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY398",GROUP_DESC:"NY398 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY398",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY398",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY398", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6833 ,protection_group_id: -5833, protection_element_id:-5833], primaryKey: false);
      insert('organizations', [ id: 105819, nci_institute_code: "NY399", name: "The Center for Radiation Therapy", city: "Bronx", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY399",GROUP_DESC:"NY399 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY399",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY399",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY399", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6834 ,protection_group_id: -5834, protection_element_id:-5834], primaryKey: false);
      insert('organizations', [ id: 105820, nci_institute_code: "NY400", name: "University Medical Practice Association", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY400",GROUP_DESC:"NY400 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY400",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY400",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY400", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6835 ,protection_group_id: -5835, protection_element_id:-5835], primaryKey: false);
      insert('organizations', [ id: 105821, nci_institute_code: "NY401", name: "Oncology and Hematology of White Plains", city: "White Plains", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY401",GROUP_DESC:"NY401 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY401",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY401",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY401", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6836 ,protection_group_id: -5836, protection_element_id:-5836], primaryKey: false);
      insert('organizations', [ id: 105822, nci_institute_code: "NY402", name: "Long Island University - Brooklyn Campus", city: "Brooklyn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY402",GROUP_DESC:"NY402 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY402",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY402",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY402", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6837 ,protection_group_id: -5837, protection_element_id:-5837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 105823, nci_institute_code: "NY403", name: "Hudson Valley Urology PC", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY403",GROUP_DESC:"NY403 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY403",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY403",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY403", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6838 ,protection_group_id: -5838, protection_element_id:-5838], primaryKey: false);
      insert('organizations', [ id: 105824, nci_institute_code: "NY404", name: "Gynecologic Oncology Associates PC", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY404",GROUP_DESC:"NY404 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY404",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY404",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY404", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6839 ,protection_group_id: -5839, protection_element_id:-5839], primaryKey: false);
      insert('organizations', [ id: 105825, nci_institute_code: "NY405", name: "Lipsey Lewis R MD (Office)", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY405",GROUP_DESC:"NY405 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY405",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY405",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY405", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6840 ,protection_group_id: -5840, protection_element_id:-5840], primaryKey: false);
      insert('organizations', [ id: 105826, nci_institute_code: "NY406", name: "Michael Buchholtz MD PC", city: "Huntington", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY406",GROUP_DESC:"NY406 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY406",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY406",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY406", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6841 ,protection_group_id: -5841, protection_element_id:-5841], primaryKey: false);
      insert('organizations', [ id: 105827, nci_institute_code: "NY407", name: "Women's Cancer Care Associates", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY407",GROUP_DESC:"NY407 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY407",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY407",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY407", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6842 ,protection_group_id: -5842, protection_element_id:-5842], primaryKey: false);
      insert('organizations', [ id: 105828, nci_institute_code: "NY408", name: "Catskill Surgical PC", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY408",GROUP_DESC:"NY408 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY408",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY408",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY408", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6843 ,protection_group_id: -5843, protection_element_id:-5843], primaryKey: false);
      insert('organizations', [ id: 105829, nci_institute_code: "NY409", name: "Hudson Valley Oncology PC", city: "Kingston", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY409",GROUP_DESC:"NY409 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY409",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY409",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY409", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6844 ,protection_group_id: -5844, protection_element_id:-5844], primaryKey: false);
      insert('organizations', [ id: 105830, nci_institute_code: "NY410", name: "New York Oncology LLC", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY410",GROUP_DESC:"NY410 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY410",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY410",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY410", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6845 ,protection_group_id: -5845, protection_element_id:-5845], primaryKey: false);
      insert('organizations', [ id: 105831, nci_institute_code: "NY411", name: "Mitchell R Berger MD PLLC", city: "Lake Sucess", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY411",GROUP_DESC:"NY411 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY411",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY411",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY411", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6846 ,protection_group_id: -5846, protection_element_id:-5846], primaryKey: false);
      insert('organizations', [ id: 105832, nci_institute_code: "NY412", name: "AccuMed Research Associates", city: "Garden City", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY412",GROUP_DESC:"NY412 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY412",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY412",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY412", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6847 ,protection_group_id: -5847, protection_element_id:-5847], primaryKey: false);
      insert('organizations', [ id: 105833, nci_institute_code: "NY413", name: "Regional Radiology", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY413",GROUP_DESC:"NY413 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY413",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY413",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY413", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6848 ,protection_group_id: -5848, protection_element_id:-5848], primaryKey: false);
      insert('organizations', [ id: 105834, nci_institute_code: "NY414", name: "Advanced Oncology Associates - Armonk", city: "Armonk", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY414",GROUP_DESC:"NY414 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY414",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY414",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY414", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6849 ,protection_group_id: -5849, protection_element_id:-5849], primaryKey: false);
      insert('organizations', [ id: 105835, nci_institute_code: "NY415", name: "Winthrop Surgical Associates PC", city: "Mineola", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY415",GROUP_DESC:"NY415 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY415",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY415",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY415", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6850 ,protection_group_id: -5850, protection_element_id:-5850], primaryKey: false);
      insert('organizations', [ id: 105836, nci_institute_code: "NY416", name: "Westchester Oncology and Hematology Group PC", city: "Hawthorne", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY416",GROUP_DESC:"NY416 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY416",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY416",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY416", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6851 ,protection_group_id: -5851, protection_element_id:-5851], primaryKey: false);
      insert('organizations', [ id: 105837, nci_institute_code: "NY417", name: "Neurological Surgery PC", city: "Commack", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY417",GROUP_DESC:"NY417 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY417",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY417",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY417", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6852 ,protection_group_id: -5852, protection_element_id:-5852], primaryKey: false);
      insert('organizations', [ id: 105838, nci_institute_code: "OBCTD", name: "Ob, Division Clinical  Trial Design/Analysis, Off Therapy Re", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OBCTD",GROUP_DESC:"OBCTD group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OBCTD",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OBCTD",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OBCTD", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6853 ,protection_group_id: -5853, protection_element_id:-5853], primaryKey: false);
      insert('organizations', [ id: 105839, nci_institute_code: "OH001", name: "Qhg of Ohio, Incorporated, Dba Park Medical Center", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH001",GROUP_DESC:"OH001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6854 ,protection_group_id: -5854, protection_element_id:-5854], primaryKey: false);
      insert('organizations', [ id: 105840, nci_institute_code: "OH002", name: "Philips Roxane Labs Inc.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH002",GROUP_DESC:"OH002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6855 ,protection_group_id: -5855, protection_element_id:-5855], primaryKey: false);
      insert('organizations', [ id: 105841, nci_institute_code: "OH003", name: "Mercy Memorial Hospital", city: "Urbana", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH003",GROUP_DESC:"OH003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6856 ,protection_group_id: -5856, protection_element_id:-5856], primaryKey: false);
      insert('organizations', [ id: 105842, nci_institute_code: "OH004", name: "Battelle Memorial Institute", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH004",GROUP_DESC:"OH004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6857 ,protection_group_id: -5857, protection_element_id:-5857], primaryKey: false);
      insert('organizations', [ id: 105843, nci_institute_code: "OH005", name: "Doctors Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH005",GROUP_DESC:"OH005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6858 ,protection_group_id: -5858, protection_element_id:-5858], primaryKey: false);
      insert('organizations', [ id: 105844, nci_institute_code: "OH006", name: "Nationwide Children's Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH006",GROUP_DESC:"OH006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6859 ,protection_group_id: -5859, protection_element_id:-5859], primaryKey: false);
      insert('organizations', [ id: 105845, nci_institute_code: "OH007", name: "Ohio State University Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH007",GROUP_DESC:"OH007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6860 ,protection_group_id: -5860, protection_element_id:-5860], primaryKey: false);
      insert('organizations', [ id: 105846, nci_institute_code: "OH008", name: "Riverside Methodist Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH008",GROUP_DESC:"OH008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6861 ,protection_group_id: -5861, protection_element_id:-5861], primaryKey: false);
      insert('organizations', [ id: 105847, nci_institute_code: "OH011", name: "Mount Carmel East Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH011",GROUP_DESC:"OH011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6862 ,protection_group_id: -5862, protection_element_id:-5862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 105848, nci_institute_code: "OH012", name: "Flower Memorial Hospital", city: "Sylvania", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH012",GROUP_DESC:"OH012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6863 ,protection_group_id: -5863, protection_element_id:-5863], primaryKey: false);
      insert('organizations', [ id: 105849, nci_institute_code: "OH013", name: "Columbus CCOP", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH013",GROUP_DESC:"OH013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6864 ,protection_group_id: -5864, protection_element_id:-5864], primaryKey: false);
      insert('organizations', [ id: 105850, nci_institute_code: "OH014", name: "Saint Vincent Mercy Medical Center", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH014",GROUP_DESC:"OH014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6865 ,protection_group_id: -5865, protection_element_id:-5865], primaryKey: false);
      insert('organizations', [ id: 105851, nci_institute_code: "OH015", name: "University of Toledo", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH015",GROUP_DESC:"OH015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6866 ,protection_group_id: -5866, protection_element_id:-5866], primaryKey: false);
      insert('organizations', [ id: 105852, nci_institute_code: "OH016", name: "Toledo Clinic", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH016",GROUP_DESC:"OH016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6867 ,protection_group_id: -5867, protection_element_id:-5867], primaryKey: false);
      insert('organizations', [ id: 105853, nci_institute_code: "OH017", name: "Bethesda Hospital", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH017",GROUP_DESC:"OH017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6868 ,protection_group_id: -5868, protection_element_id:-5868], primaryKey: false);
      insert('organizations', [ id: 105854, nci_institute_code: "OH018", name: "Hematology Oncology Medical Associates", city: "Steubenville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH018",GROUP_DESC:"OH018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6869 ,protection_group_id: -5869, protection_element_id:-5869], primaryKey: false);
      insert('organizations', [ id: 105855, nci_institute_code: "OH021", name: "Saint Joseph Hospital", city: "Lorain", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH021",GROUP_DESC:"OH021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6870 ,protection_group_id: -5870, protection_element_id:-5870], primaryKey: false);
      insert('organizations', [ id: 105856, nci_institute_code: "OH022", name: "Mednet-Euclid Clinic Foundation", city: "Mentor", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH022",GROUP_DESC:"OH022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6871 ,protection_group_id: -5871, protection_element_id:-5871], primaryKey: false);
      insert('organizations', [ id: 105857, nci_institute_code: "OH023", name: "Oberlin Clinic", city: "Oberlin", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH023",GROUP_DESC:"OH023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6872 ,protection_group_id: -5872, protection_element_id:-5872], primaryKey: false);
      insert('organizations', [ id: 105858, nci_institute_code: "OH024", name: "Saint John's Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH024",GROUP_DESC:"OH024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6873 ,protection_group_id: -5873, protection_element_id:-5873], primaryKey: false);
      insert('organizations', [ id: 105859, nci_institute_code: "OH025", name: "Saint Luke's Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH025",GROUP_DESC:"OH025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6874 ,protection_group_id: -5874, protection_element_id:-5874], primaryKey: false);
      insert('organizations', [ id: 105860, nci_institute_code: "OH026", name: "Marymount Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH026",GROUP_DESC:"OH026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6875 ,protection_group_id: -5875, protection_element_id:-5875], primaryKey: false);
      insert('organizations', [ id: 105861, nci_institute_code: "OH027", name: "Cleveland Clinic Foundation", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH027",GROUP_DESC:"OH027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6876 ,protection_group_id: -5876, protection_element_id:-5876], primaryKey: false);
      insert('organizations', [ id: 105862, nci_institute_code: "OH028", name: "Cleveland Veterans Administration", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH028",GROUP_DESC:"OH028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6877 ,protection_group_id: -5877, protection_element_id:-5877], primaryKey: false);
      insert('organizations', [ id: 105863, nci_institute_code: "OH029", name: "Case Western Reserve University", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH029",GROUP_DESC:"OH029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6878 ,protection_group_id: -5878, protection_element_id:-5878], primaryKey: false);
      insert('organizations', [ id: 105864, nci_institute_code: "OH031", name: "Northside Hospital", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH031",GROUP_DESC:"OH031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6879 ,protection_group_id: -5879, protection_element_id:-5879], primaryKey: false);
      insert('organizations', [ id: 105865, nci_institute_code: "OH032", name: "Mount Sinai Medical Center", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH032",GROUP_DESC:"OH032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6880 ,protection_group_id: -5880, protection_element_id:-5880], primaryKey: false);
      insert('organizations', [ id: 105866, nci_institute_code: "OH033", name: "Rainbow Babies and Childrens Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH033",GROUP_DESC:"OH033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6881 ,protection_group_id: -5881, protection_element_id:-5881], primaryKey: false);
      insert('organizations', [ id: 105867, nci_institute_code: "OH034", name: "Cuyahoga County Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH034",GROUP_DESC:"OH034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6882 ,protection_group_id: -5882, protection_element_id:-5882], primaryKey: false);
      insert('organizations', [ id: 105868, nci_institute_code: "OH035", name: "Deaconess Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH035",GROUP_DESC:"OH035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6883 ,protection_group_id: -5883, protection_element_id:-5883], primaryKey: false);
      insert('organizations', [ id: 105869, nci_institute_code: "OH036", name: "MetroHealth Medical Center", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH036",GROUP_DESC:"OH036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6884 ,protection_group_id: -5884, protection_element_id:-5884], primaryKey: false);
      insert('organizations', [ id: 105870, nci_institute_code: "OH037", name: "Mather Pavilion", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH037",GROUP_DESC:"OH037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6885 ,protection_group_id: -5885, protection_element_id:-5885], primaryKey: false);
      insert('organizations', [ id: 105871, nci_institute_code: "OH038", name: "Fairview Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH038",GROUP_DESC:"OH038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6886 ,protection_group_id: -5886, protection_element_id:-5886], primaryKey: false);
      insert('organizations', [ id: 105872, nci_institute_code: "OH039", name: "Meridia Huron Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH039",GROUP_DESC:"OH039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6887 ,protection_group_id: -5887, protection_element_id:-5887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 105873, nci_institute_code: "OH040", name: "Saint Vincent Charity", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH040",GROUP_DESC:"OH040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6888 ,protection_group_id: -5888, protection_element_id:-5888], primaryKey: false);
      insert('organizations', [ id: 105874, nci_institute_code: "OH041", name: "Euclid Clinic Foundation", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH041",GROUP_DESC:"OH041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6889 ,protection_group_id: -5889, protection_element_id:-5889], primaryKey: false);
      insert('organizations', [ id: 105875, nci_institute_code: "OH042", name: "University Suburban Medical Center", city: "South Euclid", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH042",GROUP_DESC:"OH042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6890 ,protection_group_id: -5890, protection_element_id:-5890], primaryKey: false);
      insert('organizations', [ id: 105876, nci_institute_code: "OH044", name: "Marymount Hospital", city: "Garfiled Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH044",GROUP_DESC:"OH044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6891 ,protection_group_id: -5891, protection_element_id:-5891], primaryKey: false);
      insert('organizations', [ id: 105877, nci_institute_code: "OH045", name: "Southwest General Health Center Ireland Cancer Center", city: "Middleburg Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH045",GROUP_DESC:"OH045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6892 ,protection_group_id: -5892, protection_element_id:-5892], primaryKey: false);
      insert('organizations', [ id: 105878, nci_institute_code: "OH046", name: "Kaiser Foundation Hospital", city: "Perma", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH046",GROUP_DESC:"OH046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6893 ,protection_group_id: -5893, protection_element_id:-5893], primaryKey: false);
      insert('organizations', [ id: 105879, nci_institute_code: "OH047", name: "Providence Hospital", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH047",GROUP_DESC:"OH047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6894 ,protection_group_id: -5894, protection_element_id:-5894], primaryKey: false);
      insert('organizations', [ id: 105880, nci_institute_code: "OH048", name: "Richmond Heights General Hospital", city: "Richmond Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH048",GROUP_DESC:"OH048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6895 ,protection_group_id: -5895, protection_element_id:-5895], primaryKey: false);
      insert('organizations', [ id: 105881, nci_institute_code: "OH049", name: "Barberton City Hospital", city: "Barberton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH049",GROUP_DESC:"OH049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6896 ,protection_group_id: -5896, protection_element_id:-5896], primaryKey: false);
      insert('organizations', [ id: 105882, nci_institute_code: "OH050", name: "Tod Children's Hospital - Forum Health", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH050",GROUP_DESC:"OH050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6897 ,protection_group_id: -5897, protection_element_id:-5897], primaryKey: false);
      insert('organizations', [ id: 105883, nci_institute_code: "OH051", name: "Green Cross Hospital", city: "Cuyahoga Falls", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH051",GROUP_DESC:"OH051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6898 ,protection_group_id: -5898, protection_element_id:-5898], primaryKey: false);
      insert('organizations', [ id: 105884, nci_institute_code: "OH052", name: "Medina Community Hospital", city: "Medina", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH052",GROUP_DESC:"OH052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6899 ,protection_group_id: -5899, protection_element_id:-5899], primaryKey: false);
      insert('organizations', [ id: 105885, nci_institute_code: "OH053", name: "Akron General Medical Center", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH053",GROUP_DESC:"OH053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6900 ,protection_group_id: -5900, protection_element_id:-5900], primaryKey: false);
      insert('organizations', [ id: 105886, nci_institute_code: "OH054", name: "Children's Hospital Medical Center of Akron", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH054",GROUP_DESC:"OH054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6901 ,protection_group_id: -5901, protection_element_id:-5901], primaryKey: false);
      insert('organizations', [ id: 105887, nci_institute_code: "OH055", name: "Akron City Hospital", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH055",GROUP_DESC:"OH055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6902 ,protection_group_id: -5902, protection_element_id:-5902], primaryKey: false);
      insert('organizations', [ id: 105888, nci_institute_code: "OH056", name: "Saint Thomas Hospital Medical Center", city: "Akron", state: "ON", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH056",GROUP_DESC:"OH056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6903 ,protection_group_id: -5903, protection_element_id:-5903], primaryKey: false);
      insert('organizations', [ id: 105889, nci_institute_code: "OH057", name: "Trumbull Memorial Hospital", city: "Warren", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH057",GROUP_DESC:"OH057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6904 ,protection_group_id: -5904, protection_element_id:-5904], primaryKey: false);
      insert('organizations', [ id: 105890, nci_institute_code: "OH058", name: "Youngstown Hospital Assoc", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH058",GROUP_DESC:"OH058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6905 ,protection_group_id: -5905, protection_element_id:-5905], primaryKey: false);
      insert('organizations', [ id: 105891, nci_institute_code: "OH059", name: "Tod Childrens Hospital", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH059",GROUP_DESC:"OH059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6906 ,protection_group_id: -5906, protection_element_id:-5906], primaryKey: false);
      insert('organizations', [ id: 105892, nci_institute_code: "OH060", name: "Saint Elizabeth Hospital Medical Center", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH060",GROUP_DESC:"OH060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6907 ,protection_group_id: -5907, protection_element_id:-5907], primaryKey: false);
      insert('organizations', [ id: 105893, nci_institute_code: "OH061", name: "Lorain Community Hospital", city: "Lorain", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH061",GROUP_DESC:"OH061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6908 ,protection_group_id: -5908, protection_element_id:-5908], primaryKey: false);
      insert('organizations', [ id: 105894, nci_institute_code: "OH063", name: "Union Hospital", city: "Dover", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH063",GROUP_DESC:"OH063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6909 ,protection_group_id: -5909, protection_element_id:-5909], primaryKey: false);
      insert('organizations', [ id: 105895, nci_institute_code: "OH064", name: "Mercy Medical Center", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH064",GROUP_DESC:"OH064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6910 ,protection_group_id: -5910, protection_element_id:-5910], primaryKey: false);
      insert('organizations', [ id: 105896, nci_institute_code: "OH065", name: "Good Samaritan Hospital", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH065",GROUP_DESC:"OH065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6911 ,protection_group_id: -5911, protection_element_id:-5911], primaryKey: false);
      insert('organizations', [ id: 105897, nci_institute_code: "OH066", name: "Firelands Regional Medical Center", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH066",GROUP_DESC:"OH066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6912 ,protection_group_id: -5912, protection_element_id:-5912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 105898, nci_institute_code: "OH067", name: "Mansfield General Hospital", city: "Mansfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH067",GROUP_DESC:"OH067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6913 ,protection_group_id: -5913, protection_element_id:-5913], primaryKey: false);
      insert('organizations', [ id: 105899, nci_institute_code: "OH068", name: "Clinton Memorial Hospital", city: "Wilmington", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH068",GROUP_DESC:"OH068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6914 ,protection_group_id: -5914, protection_element_id:-5914], primaryKey: false);
      insert('organizations', [ id: 105900, nci_institute_code: "OH069", name: "Bethesda North Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH069",GROUP_DESC:"OH069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6915 ,protection_group_id: -5915, protection_element_id:-5915], primaryKey: false);
      insert('organizations', [ id: 105901, nci_institute_code: "OH070", name: "University of Cincinnati", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH070",GROUP_DESC:"OH070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6916 ,protection_group_id: -5916, protection_element_id:-5916], primaryKey: false);
      insert('organizations', [ id: 105902, nci_institute_code: "OH071", name: "The Christ Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH071",GROUP_DESC:"OH071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6917 ,protection_group_id: -5917, protection_element_id:-5917], primaryKey: false);
      insert('organizations', [ id: 105903, nci_institute_code: "OH072", name: "Holmes Hospital University of Cincinnati", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH072",GROUP_DESC:"OH072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6918 ,protection_group_id: -5918, protection_element_id:-5918], primaryKey: false);
      insert('organizations', [ id: 105904, nci_institute_code: "OH073", name: "Good Samaritan Hospital - Cincinnati", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH073",GROUP_DESC:"OH073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6919 ,protection_group_id: -5919, protection_element_id:-5919], primaryKey: false);
      insert('organizations', [ id: 105905, nci_institute_code: "OH074", name: "Veterans Administration Medical Center - Cincinnati", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH074",GROUP_DESC:"OH074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6920 ,protection_group_id: -5920, protection_element_id:-5920], primaryKey: false);
      insert('organizations', [ id: 105906, nci_institute_code: "OH075", name: "The Jewish Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH075",GROUP_DESC:"OH075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6921 ,protection_group_id: -5921, protection_element_id:-5921], primaryKey: false);
      insert('organizations', [ id: 105907, nci_institute_code: "OH076", name: "Cincinnati Children's Hospital Medical Center", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH076",GROUP_DESC:"OH076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6922 ,protection_group_id: -5922, protection_element_id:-5922], primaryKey: false);
      insert('organizations', [ id: 105908, nci_institute_code: "OH078", name: "Dettmer Hospital, Incorporated", city: "Troy", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH078",GROUP_DESC:"OH078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6923 ,protection_group_id: -5923, protection_element_id:-5923], primaryKey: false);
      insert('organizations', [ id: 105909, nci_institute_code: "OH079", name: "Greene Memorial Hospital", city: "Xenia", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH079",GROUP_DESC:"OH079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6924 ,protection_group_id: -5924, protection_element_id:-5924], primaryKey: false);
      insert('organizations', [ id: 105910, nci_institute_code: "OH080", name: "The Children's Medical Center of Dayton", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH080",GROUP_DESC:"OH080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6925 ,protection_group_id: -5925, protection_element_id:-5925], primaryKey: false);
      insert('organizations', [ id: 105911, nci_institute_code: "OH081", name: "Grandview Hospital", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH081",GROUP_DESC:"OH081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6926 ,protection_group_id: -5926, protection_element_id:-5926], primaryKey: false);
      insert('organizations', [ id: 105912, nci_institute_code: "OH082", name: "Good Samaritan Hospital - Dayton", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH082",GROUP_DESC:"OH082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6927 ,protection_group_id: -5927, protection_element_id:-5927], primaryKey: false);
      insert('organizations', [ id: 105913, nci_institute_code: "OH083", name: "Franciscan Medical Center", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH083",GROUP_DESC:"OH083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6928 ,protection_group_id: -5928, protection_element_id:-5928], primaryKey: false);
      insert('organizations', [ id: 105914, nci_institute_code: "OH084", name: "Miami Valley Hospital", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH084",GROUP_DESC:"OH084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6929 ,protection_group_id: -5929, protection_element_id:-5929], primaryKey: false);
      insert('organizations', [ id: 105915, nci_institute_code: "OH085", name: "Veteran Affairs Medical Center", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH085",GROUP_DESC:"OH085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6930 ,protection_group_id: -5930, protection_element_id:-5930], primaryKey: false);
      insert('organizations', [ id: 105916, nci_institute_code: "OH086", name: "Kettering Medical Center", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH086",GROUP_DESC:"OH086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6931 ,protection_group_id: -5931, protection_element_id:-5931], primaryKey: false);
      insert('organizations', [ id: 105917, nci_institute_code: "OH087", name: "United States Air Force Medical Center", city: "Wright Patterson Afb", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH087",GROUP_DESC:"OH087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6932 ,protection_group_id: -5932, protection_element_id:-5932], primaryKey: false);
      insert('organizations', [ id: 105918, nci_institute_code: "OH088", name: "Mercy Medical Center", city: "Springfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH088",GROUP_DESC:"OH088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6933 ,protection_group_id: -5933, protection_element_id:-5933], primaryKey: false);
      insert('organizations', [ id: 105919, nci_institute_code: "OH089", name: "Riverhills Healthcare, Incorporated", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH089",GROUP_DESC:"OH089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6934 ,protection_group_id: -5934, protection_element_id:-5934], primaryKey: false);
      insert('organizations', [ id: 105920, nci_institute_code: "OH090", name: "Community Hospital, Springfield", city: "Springfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH090",GROUP_DESC:"OH090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6935 ,protection_group_id: -5935, protection_element_id:-5935], primaryKey: false);
      insert('organizations', [ id: 105921, nci_institute_code: "OH091", name: "Marietta Memorial Hospital", city: "Marietta", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH091",GROUP_DESC:"OH091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6936 ,protection_group_id: -5936, protection_element_id:-5936], primaryKey: false);
      insert('organizations', [ id: 105922, nci_institute_code: "OH092", name: "Veterans Memorial Hospital", city: "Pomeroy", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH092",GROUP_DESC:"OH092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6937 ,protection_group_id: -5937, protection_element_id:-5937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 105923, nci_institute_code: "OH094", name: "Saint Rita's Medical Center", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH094",GROUP_DESC:"OH094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6938 ,protection_group_id: -5938, protection_element_id:-5938], primaryKey: false);
      insert('organizations', [ id: 105924, nci_institute_code: "OH095", name: "Toledo Community Hospital Oncology Program CCOP", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH095",GROUP_DESC:"OH095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6939 ,protection_group_id: -5939, protection_element_id:-5939], primaryKey: false);
      insert('organizations', [ id: 105925, nci_institute_code: "OH096", name: "Euclid Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH096",GROUP_DESC:"OH096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6940 ,protection_group_id: -5940, protection_element_id:-5940], primaryKey: false);
      insert('organizations', [ id: 105926, nci_institute_code: "OH097", name: "Hillcrest Hospital Cancer Center", city: "Mayfield Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH097",GROUP_DESC:"OH097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6941 ,protection_group_id: -5941, protection_element_id:-5941], primaryKey: false);
      insert('organizations', [ id: 105927, nci_institute_code: "OH098", name: "Mercy Anderson Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH098",GROUP_DESC:"OH098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6942 ,protection_group_id: -5942, protection_element_id:-5942], primaryKey: false);
      insert('organizations', [ id: 105928, nci_institute_code: "OH099", name: "The Toledo Hospital", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH099",GROUP_DESC:"OH099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6943 ,protection_group_id: -5943, protection_element_id:-5943], primaryKey: false);
      insert('organizations', [ id: 105929, nci_institute_code: "OH100", name: "Aultman Health Foundation", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH100",GROUP_DESC:"OH100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6944 ,protection_group_id: -5944, protection_element_id:-5944], primaryKey: false);
      insert('organizations', [ id: 105930, nci_institute_code: "OH101", name: "Wooster Community Hospital", city: "Wooster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH101",GROUP_DESC:"OH101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6945 ,protection_group_id: -5945, protection_element_id:-5945], primaryKey: false);
      insert('organizations', [ id: 105931, nci_institute_code: "OH102", name: "Meridia Huron Hospital.", city: "Mayfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH102",GROUP_DESC:"OH102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6946 ,protection_group_id: -5946, protection_element_id:-5946], primaryKey: false);
      insert('organizations', [ id: 105932, nci_institute_code: "OH104", name: "A.G. James Cancer Hospital", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH104",GROUP_DESC:"OH104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6947 ,protection_group_id: -5947, protection_element_id:-5947], primaryKey: false);
      insert('organizations', [ id: 105933, nci_institute_code: "OH105", name: "Riverside Hospital", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH105",GROUP_DESC:"OH105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6948 ,protection_group_id: -5948, protection_element_id:-5948], primaryKey: false);
      insert('organizations', [ id: 105934, nci_institute_code: "OH106", name: "Good Samaritan Cancer Center of South East Ohio", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH106",GROUP_DESC:"OH106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6949 ,protection_group_id: -5949, protection_element_id:-5949], primaryKey: false);
      insert('organizations', [ id: 105935, nci_institute_code: "OH107", name: "Central Ohio Medical Group", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH107",GROUP_DESC:"OH107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6950 ,protection_group_id: -5950, protection_element_id:-5950], primaryKey: false);
      insert('organizations', [ id: 105936, nci_institute_code: "OH108", name: "Mercy Hospital", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH108",GROUP_DESC:"OH108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6951 ,protection_group_id: -5951, protection_element_id:-5951], primaryKey: false);
      insert('organizations', [ id: 105937, nci_institute_code: "OH109", name: "Meridia Suburban Hospital", city: "Warrensville Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH109",GROUP_DESC:"OH109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6952 ,protection_group_id: -5952, protection_element_id:-5952], primaryKey: false);
      insert('organizations', [ id: 105938, nci_institute_code: "OH110", name: "Medcenter Hospital", city: "Marion", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH110",GROUP_DESC:"OH110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6953 ,protection_group_id: -5953, protection_element_id:-5953], primaryKey: false);
      insert('organizations', [ id: 105939, nci_institute_code: "OH111", name: "Barrett Cancer Center", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH111",GROUP_DESC:"OH111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6954 ,protection_group_id: -5954, protection_element_id:-5954], primaryKey: false);
      insert('organizations', [ id: 105940, nci_institute_code: "OH112", name: "Oncology Associates Inc., Medical Center", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH112",GROUP_DESC:"OH112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6955 ,protection_group_id: -5955, protection_element_id:-5955], primaryKey: false);
      insert('organizations', [ id: 105941, nci_institute_code: "OH113", name: "Akron Oncology Associates., Incorporated.", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH113",GROUP_DESC:"OH113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6956 ,protection_group_id: -5956, protection_element_id:-5956], primaryKey: false);
      insert('organizations', [ id: 105942, nci_institute_code: "OH114", name: "Wright State University", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH114",GROUP_DESC:"OH114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6957 ,protection_group_id: -5957, protection_element_id:-5957], primaryKey: false);
      insert('organizations', [ id: 105943, nci_institute_code: "OH115", name: "Robinson Memorial Hospital", city: "Ravenna", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH115",GROUP_DESC:"OH115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6958 ,protection_group_id: -5958, protection_element_id:-5958], primaryKey: false);
      insert('organizations', [ id: 105944, nci_institute_code: "OH116", name: "Mount Sinai Medical Ctr, Imc", city: "Beachwood", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH116",GROUP_DESC:"OH116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6959 ,protection_group_id: -5959, protection_element_id:-5959], primaryKey: false);
      insert('organizations', [ id: 105945, nci_institute_code: "OH117", name: "Holzer Clinic, Limited", city: "Gallipolis", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH117",GROUP_DESC:"OH117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6960 ,protection_group_id: -5960, protection_element_id:-5960], primaryKey: false);
      insert('organizations', [ id: 105946, nci_institute_code: "OH118", name: "Ne Oh Univ College/Med", city: "Okron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH118",GROUP_DESC:"OH118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6961 ,protection_group_id: -5961, protection_element_id:-5961], primaryKey: false);
      insert('organizations', [ id: 105947, nci_institute_code: "OH119", name: "Wright-Patterson Medical Center", city: "Wright-Patterson Afb", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH119",GROUP_DESC:"OH119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6962 ,protection_group_id: -5962, protection_element_id:-5962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 105948, nci_institute_code: "OH120", name: "Community Hospital and Wellness Centers", city: "Bryan", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH120",GROUP_DESC:"OH120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6963 ,protection_group_id: -5963, protection_element_id:-5963], primaryKey: false);
      insert('organizations', [ id: 105949, nci_institute_code: "OH121", name: "Smith Clinic", city: "Marion", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH121",GROUP_DESC:"OH121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6964 ,protection_group_id: -5964, protection_element_id:-5964], primaryKey: false);
      insert('organizations', [ id: 105950, nci_institute_code: "OH122", name: "Licking Memorial Hospital", city: "Newark", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH122",GROUP_DESC:"OH122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6965 ,protection_group_id: -5965, protection_element_id:-5965], primaryKey: false);
      insert('organizations', [ id: 105951, nci_institute_code: "OH123", name: "Samaritan North Health Center", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH123",GROUP_DESC:"OH123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6966 ,protection_group_id: -5966, protection_element_id:-5966], primaryKey: false);
      insert('organizations', [ id: 105952, nci_institute_code: "OH124", name: "The Mark H Zangmeister Center", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH124",GROUP_DESC:"OH124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6967 ,protection_group_id: -5967, protection_element_id:-5967], primaryKey: false);
      insert('organizations', [ id: 105953, nci_institute_code: "OH125", name: "Dayton Intrl Med Assoc", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH125",GROUP_DESC:"OH125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6968 ,protection_group_id: -5968, protection_element_id:-5968], primaryKey: false);
      insert('organizations', [ id: 105954, nci_institute_code: "OH126", name: "Fairview Health Center North West", city: "Westlake", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH126",GROUP_DESC:"OH126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6969 ,protection_group_id: -5969, protection_element_id:-5969], primaryKey: false);
      insert('organizations', [ id: 105955, nci_institute_code: "OH127", name: "Wooster Clinic, Incorporated.,", city: "Wooster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH127",GROUP_DESC:"OH127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6970 ,protection_group_id: -5970, protection_element_id:-5970], primaryKey: false);
      insert('organizations', [ id: 105956, nci_institute_code: "OH129", name: "Fremont Memorial Hospital", city: "Fremont", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH129",GROUP_DESC:"OH129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6971 ,protection_group_id: -5971, protection_element_id:-5971], primaryKey: false);
      insert('organizations', [ id: 105957, nci_institute_code: "OH131", name: "Dayton CCOP", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH131",GROUP_DESC:"OH131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6972 ,protection_group_id: -5972, protection_element_id:-5972], primaryKey: false);
      insert('organizations', [ id: 105958, nci_institute_code: "OH132", name: "Middletown Regional Hospital", city: "Middletown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH132",GROUP_DESC:"OH132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6973 ,protection_group_id: -5973, protection_element_id:-5973], primaryKey: false);
      insert('organizations', [ id: 105959, nci_institute_code: "OH133", name: "Saint Alexis Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH133",GROUP_DESC:"OH133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6974 ,protection_group_id: -5974, protection_element_id:-5974], primaryKey: false);
      insert('organizations', [ id: 105960, nci_institute_code: "OH134", name: "Saint Charles Hospital", city: "Oregon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH134",GROUP_DESC:"OH134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6975 ,protection_group_id: -5975, protection_element_id:-5975], primaryKey: false);
      insert('organizations', [ id: 105961, nci_institute_code: "OH135", name: "Fulton County Health Center", city: "Wauseon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH135",GROUP_DESC:"OH135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6976 ,protection_group_id: -5976, protection_element_id:-5976], primaryKey: false);
      insert('organizations', [ id: 105962, nci_institute_code: "OH136", name: "Hematology-Oncology Association, Incorporated", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH136",GROUP_DESC:"OH136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6977 ,protection_group_id: -5977, protection_element_id:-5977], primaryKey: false);
      insert('organizations', [ id: 105963, nci_institute_code: "OH137", name: "Lima Memorial Hospital", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH137",GROUP_DESC:"OH137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6978 ,protection_group_id: -5978, protection_element_id:-5978], primaryKey: false);
      insert('organizations', [ id: 105964, nci_institute_code: "OH138", name: "Horizon's Therapy Center", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH138",GROUP_DESC:"OH138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6979 ,protection_group_id: -5979, protection_element_id:-5979], primaryKey: false);
      insert('organizations', [ id: 105965, nci_institute_code: "OH139", name: "Blanchard Valley Medical Association", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH139",GROUP_DESC:"OH139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6980 ,protection_group_id: -5980, protection_element_id:-5980], primaryKey: false);
      insert('organizations', [ id: 105966, nci_institute_code: "OH140", name: "Hematology Oncology Consultants, Incorporated.,", city: "Worthington", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH140",GROUP_DESC:"OH140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6981 ,protection_group_id: -5981, protection_element_id:-5981], primaryKey: false);
      insert('organizations', [ id: 105967, nci_institute_code: "OH141", name: "Oncology Consultants", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH141",GROUP_DESC:"OH141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6982 ,protection_group_id: -5982, protection_element_id:-5982], primaryKey: false);
      insert('organizations', [ id: 105968, nci_institute_code: "OH142", name: "Oncology Hematology Care, Inc.", city: "Fairfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH142",GROUP_DESC:"OH142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6983 ,protection_group_id: -5983, protection_element_id:-5983], primaryKey: false);
      insert('organizations', [ id: 105969, nci_institute_code: "OH143", name: "Doctors Hospital", city: "Massillon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH143",GROUP_DESC:"OH143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6984 ,protection_group_id: -5984, protection_element_id:-5984], primaryKey: false);
      insert('organizations', [ id: 105970, nci_institute_code: "OH144", name: "Metropolitan Urologists, Incorporated.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH144",GROUP_DESC:"OH144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6985 ,protection_group_id: -5985, protection_element_id:-5985], primaryKey: false);
      insert('organizations', [ id: 105971, nci_institute_code: "OH145", name: "Hematology and Oncology Association Incorporated.", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH145",GROUP_DESC:"OH145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6986 ,protection_group_id: -5986, protection_element_id:-5986], primaryKey: false);
      insert('organizations', [ id: 105972, nci_institute_code: "OH146", name: "University of Mednet Medical Supply", city: "Eastlake", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH146",GROUP_DESC:"OH146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6987 ,protection_group_id: -5987, protection_element_id:-5987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 105973, nci_institute_code: "OH147", name: "Meridia South Pointe", city: "Warrensville Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH147",GROUP_DESC:"OH147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6988 ,protection_group_id: -5988, protection_element_id:-5988], primaryKey: false);
      insert('organizations', [ id: 105974, nci_institute_code: "OH148", name: "Upper Valley Medical Center", city: "Piqua", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH148",GROUP_DESC:"OH148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6989 ,protection_group_id: -5989, protection_element_id:-5989], primaryKey: false);
      insert('organizations', [ id: 105975, nci_institute_code: "OH149", name: "Ross Urology Incorporated", city: "Chillicothe", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH149",GROUP_DESC:"OH149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6990 ,protection_group_id: -5990, protection_element_id:-5990], primaryKey: false);
      insert('organizations', [ id: 105976, nci_institute_code: "OH150", name: "Deaconess Professional Center, Suite 411", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH150",GROUP_DESC:"OH150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6991 ,protection_group_id: -5991, protection_element_id:-5991], primaryKey: false);
      insert('organizations', [ id: 105977, nci_institute_code: "OH151", name: "Saint Luke's Hospital", city: "Maumee", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH151",GROUP_DESC:"OH151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6992 ,protection_group_id: -5992, protection_element_id:-5992], primaryKey: false);
      insert('organizations', [ id: 105978, nci_institute_code: "OH152", name: "Blanchard Valley Hospital", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH152",GROUP_DESC:"OH152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6993 ,protection_group_id: -5993, protection_element_id:-5993], primaryKey: false);
      insert('organizations', [ id: 105979, nci_institute_code: "OH153", name: "Providence Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH153",GROUP_DESC:"OH153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6994 ,protection_group_id: -5994, protection_element_id:-5994], primaryKey: false);
      insert('organizations', [ id: 105980, nci_institute_code: "OH154", name: "Community Oncology Group, Incorporated.,", city: "Independence", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH154",GROUP_DESC:"OH154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6995 ,protection_group_id: -5995, protection_element_id:-5995], primaryKey: false);
      insert('organizations', [ id: 105981, nci_institute_code: "OH156", name: "Greater Dayton Cancer Center", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH156",GROUP_DESC:"OH156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6996 ,protection_group_id: -5996, protection_element_id:-5996], primaryKey: false);
      insert('organizations', [ id: 105982, nci_institute_code: "OH157", name: "Fairfield Medical Center", city: "Lancaster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH157",GROUP_DESC:"OH157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6997 ,protection_group_id: -5997, protection_element_id:-5997], primaryKey: false);
      insert('organizations', [ id: 105983, nci_institute_code: "OH158", name: "Sandusky Internists", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH158",GROUP_DESC:"OH158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6998 ,protection_group_id: -5998, protection_element_id:-5998], primaryKey: false);
      insert('organizations', [ id: 105984, nci_institute_code: "OH159", name: "Grady Memorial Hospital", city: "Delaware", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH159",GROUP_DESC:"OH159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6999 ,protection_group_id: -5999, protection_element_id:-5999], primaryKey: false);
      insert('organizations', [ id: 105985, nci_institute_code: "OH177", name: "Medical Oncology-Hematology Associates", city: "Troy", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH177",GROUP_DESC:"OH177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7000 ,protection_group_id: -6000, protection_element_id:-6000], primaryKey: false);
      insert('organizations', [ id: 105986, nci_institute_code: "OH178", name: "Community Oncology Group", city: "Middleburg Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH178",GROUP_DESC:"OH178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7001 ,protection_group_id: -6001, protection_element_id:-6001], primaryKey: false);
      insert('organizations', [ id: 105987, nci_institute_code: "OH182", name: "Adena Regional Medical Center", city: "Chillicothe", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH182",GROUP_DESC:"OH182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7002 ,protection_group_id: -6002, protection_element_id:-6002], primaryKey: false);
      insert('organizations', [ id: 105988, nci_institute_code: "OH183", name: "Lake University Ireland Cancer Center", city: "Mentor", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH183",GROUP_DESC:"OH183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7003 ,protection_group_id: -6003, protection_element_id:-6003], primaryKey: false);
      insert('organizations', [ id: 105989, nci_institute_code: "OH184", name: "Geaugra Hospital", city: "Chardon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH184",GROUP_DESC:"OH184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7004 ,protection_group_id: -6004, protection_element_id:-6004], primaryKey: false);
      insert('organizations', [ id: 105990, nci_institute_code: "OH185", name: "Metropolitan Surgery", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH185",GROUP_DESC:"OH185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7005 ,protection_group_id: -6005, protection_element_id:-6005], primaryKey: false);
      insert('organizations', [ id: 105991, nci_institute_code: "OH186", name: "Wood County Women's Care, Incorporated.,", city: "Bowling Green", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH186",GROUP_DESC:"OH186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7006 ,protection_group_id: -6006, protection_element_id:-6006], primaryKey: false);
      insert('organizations', [ id: 105992, nci_institute_code: "OH187", name: "Fort Hamilton Hughes", city: "Hamilton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH187",GROUP_DESC:"OH187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7007 ,protection_group_id: -6007, protection_element_id:-6007], primaryKey: false);
      insert('organizations', [ id: 105993, nci_institute_code: "OH188", name: "Saint Ann's Hospital", city: "Westerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH188",GROUP_DESC:"OH188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7008 ,protection_group_id: -6008, protection_element_id:-6008], primaryKey: false);
      insert('organizations', [ id: 105994, nci_institute_code: "OH191", name: "The Cleveland Clinic Cancer Center at Fairview Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH191",GROUP_DESC:"OH191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7009 ,protection_group_id: -6009, protection_element_id:-6009], primaryKey: false);
      insert('organizations', [ id: 105995, nci_institute_code: "OH192", name: "University Hospital's East", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH192",GROUP_DESC:"OH192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7010 ,protection_group_id: -6010, protection_element_id:-6010], primaryKey: false);
      insert('organizations', [ id: 105996, nci_institute_code: "OH194", name: "Urology Associates of Dayton", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH194",GROUP_DESC:"OH194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7011 ,protection_group_id: -6011, protection_element_id:-6011], primaryKey: false);
      insert('organizations', [ id: 105997, nci_institute_code: "OH195", name: "Cancer Treatment Center", city: "Wooster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH195",GROUP_DESC:"OH195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7012 ,protection_group_id: -6012, protection_element_id:-6012], primaryKey: false);
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
