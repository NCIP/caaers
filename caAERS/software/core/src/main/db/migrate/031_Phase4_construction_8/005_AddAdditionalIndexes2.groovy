class CreateIndexesTwo extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

        execute('drop INDEX participants_dob_idx1')
        execute('drop INDEX participants_dob_idx2')
        execute('drop INDEX participants_flname_idx')
        execute('drop INDEX participants_flname_idx2')
        execute('drop INDEX participants_eg_idx1')
        execute('drop INDEX participants_eg_idx2')

        execute('create index "idx_aes_rep_prd_id" on adverse_events(reporting_period_id)')
        execute('create index "idx_aerpd_assgmnt_id" on  ae_reporting_periods(assignment_id)')
        execute('create index "idx_assgmnt_par_id" on participant_assignments(participant_id)')
        execute('create index "idx_assgmnt_ss_id" on participant_assignments(study_site_id)')
        execute('create index "idx_assgmnt_ssi_id" on participant_assignments(study_subject_identifier)')
        execute('create index idx_ae_rp_prd_id on adverse_events(reporting_period_id)')
        execute('create index "idx_ae_attribs_cause" on ae_attributions(cause_id)')
        execute('create index "idx_crs_agnts_st_ag_id" on course_agents(study_agent_id)')
        execute('create index "idx_sa_ag_id" on study_agents(agent_id)')


        execute('create index "idx_par_fn" on participants(first_name)')
        execute('create index "idx_par_ln" on participants(last_name)')
        execute('create index "idx_par_gndr" on participants(gender)')
        execute('create index "idx_par_ethcty" on participants(ethnicity)')

        execute('create index "idx_so_study_id" on study_organizations(study_id)')
        execute('create index "idx_so_site_id" on study_organizations(site_id)')

        execute('create index "idx_srs_rs_id" on site_research_staffs(researchstaff_id)')
        execute('create index "idx_srs_site_id" on site_research_staffs(site_id)')
    }
    void down(){
        execute('drop index idx_aes_rep_prd_id')
        execute('drop index idx_aerpd_assgmnt_id')
        execute('drop index idx_assgmnt_par_id')
        execute('drop index idx_assgmnt_ss_id')
        execute('drop index idx_assgmnt_ssi_id')
        execute('drop index idx_ae_rp_prd_id')
        execute('drop index idx_so_study_id')
        execute('drop index idx_so_site_id')
        execute('drop index idx_srs_rs_id')
        execute('drop index idx_srs_site_id')
        execute('drop index idx_ae_attribs_cause')
        execute('drop index idx_crs_agnts_st_ag_id')
        execute('drop index idx_sa_ag_id')

        execute('drop index idx_par_ethcty')
        execute('drop index idx_par_gndr')
        execute('drop index idx_par_ln')
        execute('drop index idx_par_fn')
    }
}