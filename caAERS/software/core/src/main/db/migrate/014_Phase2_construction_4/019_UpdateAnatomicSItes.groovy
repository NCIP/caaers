class DeleteBodySite extends edu.northwestern.bioinformatics.bering.Migration {
	void up() { 
		execute("delete from metastatic_disease_sites where disease_history_id in (select id from disease_histories where coded_primary_disease_site_id =1)");
		execute("delete from disease_histories where coded_primary_disease_site_id =1");
		execute("delete from spa_metastatic_disease_sites where spa_disease_history_id in (select id from  spa_disease_histories where coded_primary_disease_site_id =1)");
		execute("delete from spa_metastatic_disease_sites where coded_site_id = 1");
		execute("delete from spa_disease_histories where coded_primary_disease_site_id =1");
		execute("delete from anatomic_sites where id=1");
	}
	
	void down(){
	}
}