class CreateIndexesSeven extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('create index idx_lab_asgmnt_id  on  labs(assignment_id)')
        execute('create index idx_inv_usr_id  on  investigators(user_id)')
        execute('create index idx_rs_usr_id  on  research_staffs(user_id)')
        execute('create index idx_ctctmcs_usr_id  on  contact_mechanisms(person_id)')
        execute('create index idx_idntfrs_org_id  on  identifiers(organization_id)')
        execute('create index idx_stds_load_st  on  studies(load_status)')
        execute('create index idx_pars_load_st  on  participants(load_status)')

    }

    void down(){
        execute('drop index idx_lab_asgmnt_id')
        execute('drop index idx_inv_usr_id')
        execute('drop index idx_rs_usr_id')
        execute('drop index idx_ctctmcs_usr_id')
        execute('drop index idx_ctctmcs_usr_id')
        execute('drop index idx_idntfrs_org_id')
        execute('drop index idx_stds_load_st')
        execute('drop index idx_pars_load_st')
    }
}