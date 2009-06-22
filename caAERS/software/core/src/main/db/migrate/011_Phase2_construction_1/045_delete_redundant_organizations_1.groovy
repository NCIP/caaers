class DeleteOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		
		execute("update identifiers set organization_id = 104824 where organization_id = 3");
		execute("update ind_holders set org_id = 104824 where org_id = 3");
		execute("update research_staffs set site_id = 104824 where site_id = 3");
		execute("update site_investigators set site_id = 104824 where site_id = 3");
		execute("update study_organizations set site_id = 104824 where site_id = 3");
		execute("delete from organizations where id=3");
		
		execute("update identifiers set organization_id = 104831 where organization_id = 4");
		execute("update ind_holders set org_id = 104831 where org_id = 4");
		execute("update research_staffs set site_id = 104831 where site_id = 4");
		execute("update site_investigators set site_id = 104831 where site_id = 4");
		execute("update study_organizations set site_id = 104831 where site_id = 4");
		execute("delete from organizations where id =4");
		
		execute("delete from csm_pg_pe where pg_pe_id IN (7,8)");
		execute("delete from csm_protection_group where protection_group_id IN (-8,-9)");
		execute("delete from csm_protection_element where protection_element_id IN (-8,-9)");
		execute("delete from csm_group where group_id IN (-8,-9)");
	}
	
	void down(){
		
	}
}