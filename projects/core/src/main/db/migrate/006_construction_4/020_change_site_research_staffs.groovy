class ModifSiteResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
   	  	addColumn('research_staffs','site_id',"integer", nullable:false);
     	execute("ALTER TABLE research_staffs ADD CONSTRAINT fk_res_stf_ste FOREIGN KEY (site_id) REFERENCES sites")
    }
    void down() {
    	removeColumn('research_staffs','site_id');  
    }
}