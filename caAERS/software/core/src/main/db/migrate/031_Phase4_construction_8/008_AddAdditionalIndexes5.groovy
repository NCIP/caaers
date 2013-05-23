class CreateIndexesSeven extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('create index idx_trmnlgy_study_id on terminologies(study_id)')
        execute('create index idx_ruleset_type on rule_sets(rule_type_name)')
        execute('create index idx_ruleset_status on rule_sets(status)')
        execute('create index idx_othr_intrvntns_rp_id on ae_other_interventions(report_id)')
        execute('create index idx_othr_intrvntns_intrv_id on ae_other_interventions(study_intervention_id)')

        execute('create index idx_dstrmnlgy_study_id on disease_terminologies(study_id)')
    }

    void down(){
        execute('drop index idx_trmnlgy_study_id')
        execute('drop index idx_ruleset_type')
        execute('drop index idx_ruleset_status')
        execute('drop index idx_othr_intrvntns_rp_id')
        execute('drop index idx_othr_intrvntns_intrv_id')

        execute('drop index idx_dstrmnlgy_study_id')
    }
}