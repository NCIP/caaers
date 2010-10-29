class CreateIndexes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('oracle')) {

            try { execute('CREATE INDEX "org-index-login-id-idx1" ON organization_index(login_id)') }catch(Exception e){ }
            try { execute('CREATE INDEX "ae-index-login-id-idx1" ON adverseevent_index(login_id)')  }catch(Exception e){ }
            try { execute('CREATE INDEX "eae-index-login-id-idx1" ON expedited_ae_index(login_id)')  }catch(Exception e){ }
            try { execute('CREATE INDEX "inv-index-login-id-idx1" ON investigator_index(login_id)') }catch(Exception e){ }
            try { execute('CREATE INDEX "par-index-login-id-idx1" ON participant_index(login_id)')  }catch(Exception e){ }
            try { execute('CREATE INDEX "rs-index-login-id-idx1" ON researchstaff_index(login_id)')   }catch(Exception e){ }
            try { execute('CREATE INDEX "rp-index-login-id-idx1" ON reportingperiod_index(login_id)')  }catch(Exception e){ }
            try { execute('CREATE INDEX "study-index-login-id-idx1" ON study_index(login_id)')    }catch(Exception e){ }
          
        } else if (databaseMatches('postgresql')){

           try { execute('DROP INDEX "org-index-org-id-idx1"')  }catch(Exception e){ }
           try {  execute('DROP INDEX "ae-index-ae-id-idx1"')   }catch(Exception e){ }
           try {  execute('DROP INDEX "eae-index-eae-id-idx1"') }catch(Exception e){ }
           try {  execute('DROP INDEX "inv-index-inv-id-idx1"') }catch(Exception e){ }
           try {  execute('DROP INDEX "par-index-par-id-idx1"') }catch(Exception e){ }
           try {  execute('DROP INDEX "rs-index-rs-id-idx1"')  }catch(Exception e){ }
           try {  execute('DROP INDEX "rp-index-rp-id-idx1"')    }catch(Exception e){ }
           try {  execute('DROP INDEX "study-index-study-id-idx1"') }catch(Exception e){ }

           try { execute('CREATE INDEX "org-index-org-id-idx1" ON organization_index(organization_id)') }catch(Exception e){ }
           try { execute('CREATE INDEX "ae-index-ae-id-idx1" ON adverseevent_index(adverseevent_id)') }catch(Exception e){ }
           try { execute('CREATE INDEX "eae-index-eae-id-idx1" ON expedited_ae_index(expedited_ae_id)')  }catch(Exception e){ }
           try { execute('CREATE INDEX "inv-index-inv-id-idx1" ON investigator_index(investigator_id)') }catch(Exception e){ }
           try { execute('CREATE INDEX "par-index-par-id-idx1" ON participant_index(participant_id)')  }catch(Exception e){ }
           try { execute('CREATE INDEX "rs-index-rs-id-idx1" ON researchstaff_index(researchstaff_id)') }catch(Exception e){ }
           try { execute('CREATE INDEX "rp-index-rp-id-idx1" ON reportingperiod_index(reportingperiod_id)')  }catch(Exception e){ }
           try { execute('CREATE INDEX "study-index-study-id-idx1" ON study_index(study_id)')   }catch(Exception e){ }
        } 
    }
    
    void down() {
		if (databaseMatches('oracle')) {
            execute('DROP INDEX "org-index-login-id-idx1"')
            execute('DROP INDEX "ae-index-login-id-idx1"')
            execute('DROP INDEX "eae-index-login-id-idx1"')
            execute('DROP INDEX "inv-index-login-id-idx1"')
            execute('DROP INDEX "par-index-login-id-idx1"')
            execute('DROP INDEX "rs-index-login-id-idx1"')
            execute('DROP INDEX "rp-index-login-id-idx1"')
            execute('DROP INDEX "study-index-login-id-idx1"')
        } else if (databaseMatches('postgresql')){
            execute('DROP INDEX "org-index-org-id-idx1"')
            execute('DROP INDEX "ae-index-ae-id-idx1"')
            execute('DROP INDEX "eae-index-eae-id-idx1"')
            execute('DROP INDEX "inv-index-inv-id-idx1"')
            execute('DROP INDEX "par-index-par-id-idx1"')
            execute('DROP INDEX "rs-index-rs-id-idx1"')
            execute('DROP INDEX "rp-index-rp-id-idx1"')
            execute('DROP INDEX "study-index-study-id-idx1"')
        } 
    }
}
