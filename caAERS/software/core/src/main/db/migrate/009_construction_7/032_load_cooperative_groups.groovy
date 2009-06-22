class LoadingCooperativeGroups extends edu.northwestern.bioinformatics.bering.Migration {
    
    void up() {
    
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
        }
     
        void m0()
        {	
            insert('organizations', [ id:10006,version:0, grid_id:"88888888",name:"American College of Surgeons Oncology Trials Group",nci_institute_code:"ACOSOG", description_text:"230 West Monroe,Chicago,IL 60606"], primaryKey: false)
      	 }
      	 void m1()
        {
            insert('organizations', [ id:10007,version:0,grid_id:"88888882",name: "Cancer and Leukemia Group B",nci_institute_code:"CALGB", description_text:"280 East Monroe,Chicago,IL 60607"], primaryKey: false)
      	 }
      	 void m2()
        {
            insert('organizations', [ id:10008,version:0,grid_id:"88888448",name: "Eastern Cooperative Oncology Group",nci_institute_code:"ECOG", description_text:"900 Commonwealth Avenue Boston MA 02215"], primaryKey: false)
      	 }
      	 void m3()
        {
            insert('organizations', [ id:10009,version:0,grid_id:"28888888",name: "Gynecologic Oncology Group",nci_institute_code:"GOG" , description_text:"JOHN F. KENNEDY BLVD PHILADELPHIA PA 19103"], primaryKey: false)
      	 }
      	 void m4()
        {
            insert('organizations', [ id:10010,version:0,grid_id:"88328888",name: "North Central Cancer Treatment Group",nci_institute_code:"NCCTG", description_text:"200 First Street SW Rochester MN 55905"], primaryKey: false)
      	 }
      	 void m5()
        {
            insert('organizations', [ id:10011,version:0,grid_id:"884458888",name: "National Cancer Institute of Canada Clinical Trials Group",nci_institute_code:"NCIC", description_text:"10 Alcorn Avenue Toronto Ontario Canada"], primaryKey: false)
      	 }
      	 void m6()
        {
            insert('organizations', [ id:10012,version:0, grid_id:"118433888",name: "National Surgical Adjuvant Breast and Bowel Project",nci_institute_code:"NSABP", description_text:"Four Allegheny Center Pittsburgh PA 15212-5234"], primaryKey: false)
      	 }
      	 void m7()
        {
            insert('organizations', [ id:10013,version:0,grid_id:"4333221",name: "Radiation Therapy Oncology Group",nci_institute_code:"RTOG", description_text:"1818 Market Street Philadelphia PA USA 19103-3604"], primaryKey: false)
      	 }
      	 void m8()
        {
            insert('organizations', [ id:10014,version:0, grid_id:"8888433888" ,name: "Southwest Oncology Group",nci_institute_code:"SWOG",description_text:"24 Frank Lloyd Wright Drive Ann Arbo MI 48106-0483"], primaryKey: false)
      	 }
      	 void m9()
        {
            insert('organizations', [ id:10015,version:0, grid_id:"838433888",name: "Children's Oncology Group",nci_institute_code:"COG", description_text:"440 East Huntington Drive Arcadia CA 91066-6012"], primaryKey: false)
      	 }

    void down() {
     	execute("DELETE from ORGANIZATIONS where id = 10006")
     	execute("DELETE from ORGANIZATIONS where id = 10007")
     	execute("DELETE from ORGANIZATIONS where id = 10008")
     	execute("DELETE from ORGANIZATIONS where id = 10009")
     	execute("DELETE from ORGANIZATIONS where id = 10010")
     	execute("DELETE from ORGANIZATIONS where id = 10011")
     	execute("DELETE from ORGANIZATIONS where id = 10012")
     	execute("DELETE from ORGANIZATIONS where id = 10013")
     	execute("DELETE from ORGANIZATIONS where id = 10014")
     	execute("DELETE from ORGANIZATIONS where id = 10015")
     	
      	
   	}
	
}