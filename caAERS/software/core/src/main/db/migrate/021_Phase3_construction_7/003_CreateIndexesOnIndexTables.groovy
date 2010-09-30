class CreateIndexes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {
            execute('CREATE INDEX "org-index-login-id-idx1" ON organization_index(login_id)')
            execute('CREATE INDEX "ae-index-login-id-idx1" ON adverseevent_index(login_id)')
            execute('CREATE INDEX "eae-index-login-id-idx1" ON expedited_ae_index(login_id)')
            execute('CREATE INDEX "inv-index-login-id-idx1" ON investigator_index(login_id)')
            execute('CREATE INDEX "par-index-login-id-idx1" ON participant_index(login_id)')
            execute('CREATE INDEX "rs-index-login-id-idx1" ON researchstaff_index(login_id)')
            execute('CREATE INDEX "rp-index-login-id-idx1" ON reportingperiod_index(login_id)')
            execute('CREATE INDEX "study-index-login-id-idx1" ON study_index(login_id)')
        } else if (databaseMatches('postgresql')){
            execute('CREATE INDEX "org-index-org-id-idx1" ON organization_index(organization_id)')
            execute('CREATE INDEX "ae-index-ae-id-idx1" ON adverseevent_index(adverseevent_id)')
            execute('CREATE INDEX "eae-index-eae-id-idx1" ON expedited_ae_index(expedited_ae_id)')
            execute('CREATE INDEX "inv-index-inv-id-idx1" ON investigator_index(investigator_id)')
            execute('CREATE INDEX "par-index-par-id-idx1" ON participant_index(participant_id)')
            execute('CREATE INDEX "rs-index-rs-id-idx1" ON researchstaff_index(researchstaff_id)')
            execute('CREATE INDEX "rp-index-rp-id-idx1" ON reportingperiod_index(reportingperiod_id)')
            execute('CREATE INDEX "study-index-study-id-idx1" ON study_index(study_id)')
        } 
    }
    
    void down() {

    }
}



