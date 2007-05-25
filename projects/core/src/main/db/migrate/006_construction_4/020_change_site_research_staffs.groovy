class ModifSiteResearchStaffs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
   	  	addColumn('research_staffs','site_id',"integer");
   	  	execute("UPDATE research_staffs SET site_id=1 WHERE site_id IS NULL")  // site 1 is "default"
   	  	setNullable('research_staffs', 'site_id', false)
     	execute("ALTER TABLE research_staffs ADD CONSTRAINT fk_res_stf_ste FOREIGN KEY (site_id) REFERENCES sites")
    }
    void down() {
    	removeColumn('research_staffs','site_id');  
    }
}