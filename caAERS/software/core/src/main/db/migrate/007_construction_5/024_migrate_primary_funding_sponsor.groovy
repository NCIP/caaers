class MigratePrimaryFundingSponsor extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	if (databaseMatches('postgres')) {
          execute("insert into study_organizations(site_id, study_id, version, grid_id, \"type\") select o.id, s.id, s.version, s.grid_id,'SFS' from studies s, organizations o where s.primary_sponsor_code = o.name   and s.id not in (select study_id from study_organizations where type='SFS')");
        }
    }

    void down() {
        //no down script possible
    }
}
