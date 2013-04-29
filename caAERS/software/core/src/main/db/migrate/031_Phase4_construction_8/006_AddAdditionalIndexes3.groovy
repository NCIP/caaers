class CreateIndexesTwo extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute('create index idx_outcomes_ae_id  on  outcomes(adverse_event_id)')
        execute('create index idx_ctcgrds_trm_id on ctc_grades(term_id)')
    }

    void down(){
        execute('drop index idx_outcomes_ae_id')
        execute('drop index idx_ctcgrds_trm_id')
    }
}