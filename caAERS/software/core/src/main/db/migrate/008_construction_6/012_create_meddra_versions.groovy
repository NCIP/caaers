class CreatePriorTherapyAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
 
        createTable("meddra_versions") { t->
            t.setIncludePrimaryKey(false)
            t.addColumn("id", "integer", nullable: false, primaryKey: true)
            t.addColumn("name", "string", nullable: false)
        }

        insert("meddra_versions", [ id: 9, name: "MedDRA v9" ], primaryKey: false)
        
        // Add meddra_version_id code to  terminologies to capture version
        addColumn("terminologies","meddra_version_id","integer", nullable: true)
        // Migrate data
        execute(" update terminologies set meddra_version_id = 9 where term_code = 2 ")
        
    }

    void down() {
        dropTable('meddra_versions')
        dropColumn('terminologies','meddra_version_id')
    }
}