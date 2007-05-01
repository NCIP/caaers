class CreateMeddra extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("meddra_llt") { t ->
            t.addColumn("meddra_code", "string", nullable: true)
            t.addColumn("meddra_term", "string", nullable: true)
            t.addColumn("costart_symbol", "string", nullable: true)
            t.addColumn("harts_code", "string", nullable: true)
            t.addColumn("who_art_code", "string", nullable: true)
            t.addColumn("icd9_code", "string", nullable: true)
            t.addColumn("icd9_cm_code", "string", nullable: true)
            t.addColumn("icd10_code", "string", nullable: true)
            t.addColumn("jart_code", "string", nullable: true)
            t.addColumn("currency", "string", nullable: true)
            t.addColumn("meddra_pt_id", "integer", nullable: true)
            t.addVersionColumn()
        }
        insert("meddra_llt", [id: 1, meddra_code: "1", meddra_term: "Not Specified"], primaryKey: false)

        createTable("meddra_pt") { t ->
            t.addColumn("meddra_code", "string", nullable: true)
            t.addColumn("meddra_term", "string", nullable: true)
            t.addColumn("costart_symbol", "string", nullable: true)
            t.addColumn("harts_code", "string", nullable: true)
            t.addColumn("who_art_code", "string", nullable: true)
            t.addColumn("icd9_code", "string", nullable: true)
            t.addColumn("icd9_cm_code", "string", nullable: true)
            t.addColumn("icd10_code", "string", nullable: true)
            t.addColumn("jart_code", "string", nullable: true)
            t.addColumn("meddra_soc_id", "integer", nullable: true)
            t.addVersionColumn()
        }
        insert("meddra_pt", [id: 1, meddra_code: "1", meddra_term: "Not Specified"], primaryKey: false)
        
       createTable("meddra_hlt") { t ->
            t.addColumn("meddra_code", "string", nullable: true)
            t.addColumn("meddra_term", "string", nullable: true)
            t.addColumn("costart_symbol", "string", nullable: true)
            t.addColumn("harts_code", "string", nullable: true)
            t.addColumn("who_art_code", "string", nullable: true)
            t.addColumn("icd9_code", "string", nullable: true)
            t.addColumn("icd9_cm_code", "string", nullable: true)
            t.addColumn("icd10_code", "string", nullable: true)
            t.addColumn("jart_code", "string", nullable: true)
            t.addVersionColumn()
        }
        insert("meddra_hlt", [id: 1, meddra_code: "1", meddra_term: "Not Specified"], primaryKey: false)
        
        
       createTable("meddra_hlgt") { t ->
            t.addColumn("meddra_code", "string", nullable: true)
            t.addColumn("meddra_term", "string", nullable: true)
            t.addColumn("costart_symbol", "string", nullable: true)
            t.addColumn("harts_code", "string", nullable: true)
            t.addColumn("who_art_code", "string", nullable: true)
            t.addColumn("icd9_code", "string", nullable: true)
            t.addColumn("icd9_cm_code", "string", nullable: true)
            t.addColumn("icd10_code", "string", nullable: true)
            t.addColumn("jart_code", "string", nullable: true)
            t.addVersionColumn()
        }
        insert("meddra_hlgt", [id: 1, meddra_code: "1", meddra_term: "Not Specified"], primaryKey: false)
        
        
       createTable("meddra_soc") { t ->
            t.addColumn("meddra_code", "string", nullable: true)
            t.addColumn("meddra_term", "string", nullable: true)
            t.addColumn("costart_symbol", "string", nullable: true)
            t.addColumn("harts_code", "string", nullable: true)
            t.addColumn("who_art_code", "string", nullable: true)
            t.addColumn("icd9_code", "string", nullable: true)
            t.addColumn("icd9_cm_code", "string", nullable: true)
            t.addColumn("icd10_code", "string", nullable: true)
            t.addColumn("jart_code", "string", nullable: true)
            t.addVersionColumn()
        }
        insert("meddra_soc", [id: 1, meddra_code: "1", meddra_term: "Not Specified"], primaryKey: false)
        
        createTable("meddra_soc_hlgt") { t ->
            t.addColumn("meddra_soc_id", "integer", nullable: true)
            t.addColumn("meddra_hlgt_id", "integer", nullable: true)
            t.addVersionColumn()
        }
        
        createTable("meddra_hlgt_hlt") { t ->
            t.addColumn("meddra_hlgt_id", "integer", nullable: true)
            t.addColumn("meddra_hlt_id", "integer", nullable: true)
            t.addVersionColumn()
        }
        
        createTable("meddra_hlt_pt") { t ->
            t.addColumn("meddra_hlt_id", "integer", nullable: true)
            t.addColumn("meddra_pt_id", "integer", nullable: true)
            t.addVersionColumn()
        }
        
        
        
    }

    void down() {
        dropTable("meddra_llt")
        dropTable("meddra_pt")
        dropTable("meddra_hlt_pt")
        dropTable("meddra_hlt")
        dropTable("meddra_hlgt_hlt")
        dropTable("meddra_hlgt")
        dropTable("meddra_soc_hlgt")
        dropTable("meddra_soc")
    }
}