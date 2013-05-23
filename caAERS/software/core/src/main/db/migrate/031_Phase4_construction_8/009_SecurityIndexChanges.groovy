class UpdateSecurityIndexes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropTable("report_index")
        dropTable("researchstaff_index")
        dropTable("investigator_index")
        dropTable("study_index")
        dropTable("participant_index")
        dropTable("organization_index")
        dropTable("adverseevent_index")
        dropTable("expedited_ae_index")
        dropTable("reportingperiod_index")


        createTable("investigator_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("investigator_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }


        createTable("report_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }


        createTable("researchstaff_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("researchstaff_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }


   createTable("study_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("study_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }

   createTable("organization_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("organization_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }


   createTable("adverseevent_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("adverseevent_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }



   createTable("expedited_ae_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("expedited_ae_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }


    createTable("reportingperiod_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("reportingperiod_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }
        createTable("participant_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("participant_id", "integer", nullable: false)
            t.addColumn("grid_id", "string")
            t.addColumn("role" , "integer" , nullable: false , defaultValue: 0)
        }

        execute('CREATE INDEX "org-index-login-id-idx1" ON organization_index(login_id)')
        execute('CREATE INDEX "ae-index-login-id-idx1" ON adverseevent_index(login_id)')
        execute('CREATE INDEX "eae-index-login-id-idx1" ON expedited_ae_index(login_id)')
        execute('CREATE INDEX "inv-index-login-id-idx1" ON investigator_index(login_id)')
        execute('CREATE INDEX "par-index-login-id-idx1" ON participant_index(login_id)')
        execute('CREATE INDEX "rs-index-login-id-idx1" ON researchstaff_index(login_id)')
        execute('CREATE INDEX "rp-index-login-id-idx1" ON reportingperiod_index(login_id)')
        execute('CREATE INDEX "r-index-login-id-idx1" ON report_index(login_id)')
        execute('CREATE INDEX "study-index-login-id-idx1" ON study_index(login_id)')

    }

    void down() {
        //not possible.
    }
}