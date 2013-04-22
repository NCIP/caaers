class CreateIndexes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
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
    void down(){

        execute('drop index  "org-index-login-id-idx1" ')
        execute('drop index  "ae-index-login-id-idx1" ')
        execute('drop index  "eae-index-login-id-idx1" ')
        execute('drop index  "inv-index-login-id-idx1" ')
        execute('drop index  "par-index-login-id-idx1" ')
        execute('drop index  "rs-index-login-id-idx1" ')
        execute('drop index  "rp-index-login-id-idx1" ')
        execute('drop index  "r-index-login-id-idx1" ')
        execute('drop index  "study-index-login-id-idx1" ')
    }
}